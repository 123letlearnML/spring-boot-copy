import { useState } from 'react'

import './App.css'
import Login from './admin/KhachHang/Login'
import Register from './admin/KhachHang/Register'
import Phim from './admin/Phim/Phim'
import Phong from './admin/Phong/Phong'
import RapChieuPhim from './admin/RapChieuPhim/RapChieuPhim'

function App() {


  return (
    <>
   <Login />
   <Register />
    <Phim />
    <Phong />
    <RapChieuPhim />
    </>
      
  )
}

export default App
