package duong.milion.authencation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@Configuration
@EnableWebSecurity
public class Auth {


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Tắt CSRF nếu không dùng cho API REST
                .csrf(csrf -> csrf.disable())
                // Cấu hình phân quyền truy cập theo URL
                .authorizeHttpRequests(auth -> auth
                        // Các endpoint yêu cầu role USER
                        .requestMatchers("/KhachHangDto", "/PhimDto", "/RapChieuPhimDto").hasRole("USER")
                        // Các endpoint yêu cầu role ADMIN
                        .requestMatchers("/KhachHang", "/Phim", "/RapChieuPhim").hasRole("ADMIN")

                        .requestMatchers("http://localhost:5173/","http://localhost:5174/").permitAll()
                        // Các endpoint khác cho phép truy cập tự do
                        .anyRequest().permitAll()
                )
                // Sử dụng xác thực HTTP Basic (có thể thay bằng formLogin hoặc JWT nếu cần)
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }


    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService(PasswordEncoder passwordEncoder) {
        UserDetails admin = User.withUsername("admin")
                .password(passwordEncoder.encode("admin"))
                .roles("ADMIN")
                .build();
        return new InMemoryUserDetailsManager(admin);
    }
}
