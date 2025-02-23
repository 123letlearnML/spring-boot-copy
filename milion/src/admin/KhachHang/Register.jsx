import React, { useState } from 'react';
import axios from 'axios';

const Register = () => {
  const [khachHangGmail, setKhachHangGmail] = useState('');
  const [khachHangPass, setKhachHangPass] = useState('');
  const [tenKhachHang, setTenKhachHang] = useState('');
  const [token, setToken] = useState('');

  const handleRegister = async (e) => {
    e.preventDefault();
    try {
      const response = await axios.post('http://localhost:8080/KhachHang/register', {
        khachHangGmail: khachHangGmail,
        khachHangPass: khachHangPass,
        tenKhachHang: tenKhachHang
      });
      setToken(response.data.token);
    } catch (error) {
      console.error('Đăng ký thất bại:', error);
    }
  };

  return (
    <div>
      <h2>Đăng ký</h2>
      <form onSubmit={handleRegister}>
        <div>
          <label>Email: </label>
          <input 
            type="email" 
            value={khachHangGmail} 
            onChange={(e) => setKhachHangGmail(e.target.value)} 
            placeholder="Nhập email" 
            required 
          />
        </div>
        <div>
          <label>Mật khẩu: </label>
          <input 
            type="password" 
            value={khachHangPass} 
            onChange={(e) => setKhachHangPass(e.target.value)} 
            placeholder="Nhập mật khẩu" 
            required 
          />
        </div>
        <div>
          <label>Tên khách hàng: </label>
          <input 
            type="text" 
            value={tenKhachHang} 
            onChange={(e) => setTenKhachHang(e.target.value)} 
            placeholder="Nhập tên khách hàng" 
            required 
          />
        </div>
        <button type="submit">Đăng ký</button>
      </form>
      {token && (
        <div>
          <p>Token nhận được:</p>
          <pre>{token}</pre>
        </div>
      )}
    </div>
  );
};

export default Register;
