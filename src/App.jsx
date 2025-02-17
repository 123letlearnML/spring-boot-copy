import { useState } from 'react'

import './App.css'
import LoginRegis from './admin/KhachHang/LoginRegis'
import Phim from './admin/Phim/Phim'
import Phong from './admin/Phong/Phong'
import RapChieuPhim from './admin/RapChieuPhim/RapChieuPhim'

function App() {


  return (
    <>
    <LoginRegis />
    <Phim />
    <Phong />
    <RapChieuPhim />
    </>
      
  )
}

export default App
