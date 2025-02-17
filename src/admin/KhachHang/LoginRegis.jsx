import React, { useState } from "react";

const LoginRegis = () => {
  const [loginEmail, setLoginEmail] = useState("");
  const [loginPassword, setLoginPassword] = useState("");
  const [registerEmail, setRegisterEmail] = useState("");
  const [registerPassword, setRegisterPassword] = useState("");
  const [token, setToken] = useState(null);
  const [error, setError] = useState("");

  const handleLogin = async (e) => {
    e.preventDefault();
    setError("");
    try {
      const response = await fetch("http://localhost:8080/KhachHang/login", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ khachHangGmail: loginEmail, khachHangPass: loginPassword })
      });
      if (!response.ok) throw new Error("Login failed");
      const data = await response.json();
      setToken(data.token);
    } catch (err) {
      console.error(err);
      setError(err.message);
    }
  };

  const handleRegister = async (e) => {
    e.preventDefault();
    setError("");
    try {
      const response = await fetch("http://localhost:8080/KhachHang/register", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ khachHangGmail: registerEmail, khachHangPass: registerPassword })
      });
      if (!response.ok) throw new Error("Register failed");
      const data = await response.json();
      setToken(data.token);
    } catch (err) {
      console.error(err);
      setError(err.message);
    }
  };

  return (
    <div>
      <h2>Authentication</h2>
      {error && <p style={{ color: "red" }}>{error}</p>}
      {token ? <p>Token: {token}</p> : null}
      <div>
        <h3>Login</h3>
        <form onSubmit={handleLogin}>
          <input type="text" placeholder="Email" value={loginEmail} onChange={(e) => setLoginEmail(e.target.value)} required />
          <input type="password" placeholder="Password" value={loginPassword} onChange={(e) => setLoginPassword(e.target.value)} required />
          <button type="submit">Login</button>
        </form>
      </div>
      <div>
        <h3>Register</h3>
        <form onSubmit={handleRegister}>
          <input type="text" placeholder="Email" value={registerEmail} onChange={(e) => setRegisterEmail(e.target.value)} required />
          <input type="password" placeholder="Password" value={registerPassword} onChange={(e) => setRegisterPassword(e.target.value)} required />
          <button type="submit">Register</button>
        </form>
      </div>
    </div>
  );
};

export default LoginRegis;
