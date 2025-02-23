import axios from "axios";
import { useEffect, useState } from "react";

const Phong = () => {
  const [PhongId, setPhongId] = useState();
  const [viTriGheNgoi, setViTriGheNgoi] = useState("");
  const [daFullPhong, setDaFullPhong] = useState(false);
  const [daDatCho, setDaDatCho] = useState(true);
  const [soChoNgoi, setSoChoNgoi] = useState();
  // const [soChoNgoiConLai, setSoChoNgoiConLai] = useState();
  const [soChoDaDat, setSoChoDaDat] = useState();

  const getAllPhong = async () => {
    try {
      const response = await axios.get("http://localhost:8080/Phong/all");
      setViTriGheNgoi(response.data.viTriGheNgoi);
      setDaFullPhong(response.data.daFullPhong);
      setDaDatCho(response.data.daDatCho);
      setSoChoNgoi(response.data.soChoNgoi);
      // setSoChoNgoiConLai(response.data.soChoNgoiConLai);
      setSoChoDaDat(response.data.soChoDaDat);
    } catch (error) {
      console.log("Lỗi get all ở phòng: " + error);
    }
  };
  
  useEffect(() => {
    getAllPhong();
  }, []);

  const getById = async (id) => {
    try {
      const response = await axios.get(`http://localhost:8080/Phong/getId/${id}`);
      setPhongId(response.data.PhongId);
      setSoChoDaDat(response.data.soChoDaDat);
    } catch (error) {
      console.log("Lỗi get by ID phòng là: " + error);
    }
  };

  const postPhong = async () => {
    try {
      const response = await axios.post("http://localhost:8080/Phong/post", {
        viTriGheNgoi: viTriGheNgoi.split(","),
        daFullPhong,
        daDatCho,
        soChoNgoi,
   
        soChoDaDat,
      });
      console.log("Thêm phòng thành công:", response.data);
    } catch (error) {
      console.log("Lỗi khi thêm phòng:", error);
    }
  };

  const updatePhong = async (id) => {
    try {
      const response = await axios.put(`http://localhost:8080/Phong/put/${id}`, {
        PhongId,
        viTriGheNgoi: viTriGheNgoi.split(","),
        daFullPhong,
        daDatCho,
        soChoNgoi,

        soChoDaDat,
      });
      console.log("Cập nhật phòng thành công:", response.data);
    } catch (error) {
      console.log("Lỗi khi cập nhật phòng:", error);
    }
  };

  const deletePhong = async (id) => {
    try {
      await axios.delete(`http://localhost:8080/Phong/delete/${id}`);
      console.log("Xóa phòng thành công");
    } catch (error) {
      console.log("Lỗi khi xóa phòng:", error);
    }
  };

  return (
    <div>
      <h1>Quản lý Phòng</h1>
      <input
        type="text"
        placeholder="Vị trí ghế ngồi (ngăn cách bởi dấu phẩy)"
        value={viTriGheNgoi}
        onChange={(e) => setViTriGheNgoi(e.target.value)}
      />
      <input
        type="number"
        placeholder="Số chỗ ngồi"
        value={soChoNgoi}
        onChange={(e) => setSoChoNgoi(Number(e.target.value))}
      />
     
      <input
        type="number"
        placeholder="Số chỗ đã đặt"
        value={soChoDaDat}
        onChange={(e) => setSoChoDaDat(Number(e.target.value))}
      />
      <button onClick={postPhong}>Thêm Phòng</button>
      <button onClick={() => updatePhong(PhongId)}>Cập Nhật Phòng</button>
      <button onClick={() => deletePhong(PhongId)}>Xóa Phòng</button>
    </div>
  );
};

export default Phong;
