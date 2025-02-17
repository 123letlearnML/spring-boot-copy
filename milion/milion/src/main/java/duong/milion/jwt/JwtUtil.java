package duong.milion.jwt;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class JwtUtil {


    private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS512);
//    @Value("${jwt.secret}")
    String jwtSecret = Encoders.BASE64.encode(key.getEncoded());


    // Thời gian hiệu lực của token (mili giây), ví dụ 86400000 = 24h
    @Value("${jwt.expirationMs}")
    private int jwtExpirationMs;

    // Tạo token dựa trên email (subject)
    private Map<String, String> tokenStore = new ConcurrentHashMap<>();
    public String generateTokenFromEmail(String email) {
        String token = Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
        tokenStore.put(email, token); // Lưu token vào kho
        return token;
    }



    public Boolean daDangNhapChua(String email) {
        String token = tokenStore.get(email);
        if (token == null) {
            // Chưa có token cho email này
            return false;
        }
        try {
            // Kiểm tra token có hợp lệ và chưa hết hạn không
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true; // Token hợp lệ
        } catch (JwtException | IllegalArgumentException e) {
            // Token không hợp lệ hoặc đã hết hạn
            tokenStore.remove(email); // Loại bỏ token cũ
            return false;
        }
    }



    // Lấy email từ token
    public String getEmailFromJwtToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    // Kiểm tra token hợp lệ
    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (Exception e) {
            // Có thể log lỗi tại đây
        }
        return false;
    }
}
