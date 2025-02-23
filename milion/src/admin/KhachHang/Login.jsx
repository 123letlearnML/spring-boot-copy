import React, { useState } from 'react';
import axios from 'axios';

const Login = () => {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [token, setToken] = useState('');

  const handleLogin = async (e) => {
    e.preventDefault();
    try {
      const response = await axios.post('http://localhost:8080/KhachHang/login', {
        email: email,
        password: password
      });
      setToken(response.data.token);
    } catch (error) {
      console.error('Đăng nhập thất bại:', error);
    }
  };

  return (
    <div>
      <h2>Đăng nhập</h2>
      <form onSubmit={handleLogin}>
        <div>
          <label>Email: </label>
          <input 
            type="email" 
            value={email} 
            onChange={(e) => setEmail(e.target.value)} 
            placeholder="Nhập email" 
            required 
          />
        </div>
        <div>
          <label>Mật khẩu: </label>
          <input 
            type="password" 
            value={password} 
            onChange={(e) => setPassword(e.target.value)} 
            placeholder="Nhập mật khẩu" 
            required 
          />
        </div>
        <button type="submit">Đăng nhập</button>
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

export default Login;
