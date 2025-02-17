import React, { useState, useEffect } from "react";
import axios from "axios";

const Phong = () => {
  const [phongs, setPhongs] = useState([]);
  const [newPhong, setNewPhong] = useState({ soChoNgoi: 0 });
  const [updatePhong, setUpdatePhong] = useState({ id: "", soChoNgoi: 0 });
  const [deleteId, setDeleteId] = useState("");

 // Hàm fetch danh sách phòng
 const fetchPhongs = () => {
    axios
      .get("http://localhost:8080/Phong/all")
      .then((response) => setPhongs(response.data))
      .catch((error) => console.error("Lỗi khi lấy danh sách phòng:", error));
  };
  useEffect(() => {
    fetchPhongs();
  }, []);


  // Thêm phòng
  const addPhong = async () => {
    try {
      const response = await axios.post("http://localhost:8080/Phong/post", newPhong);
      setPhongs([...phongs, response.data]);
    } catch (error) {
      console.error("Lỗi khi thêm phòng:", error);
    }
  };

  // Cập nhật phòng
  const updatePhongData = async () => {
    try {
      await axios.put(`http://localhost:8080/Phong/put/${updatePhong.id}`, updatePhong);
      setPhongs(phongs.map((phong) => (phong.id === updatePhong.id ? updatePhong : phong)));
    } catch (error) {
      console.error("Lỗi khi cập nhật phòng:", error);
    }
  };

  // Xóa phòng
  const deletePhong = async () => {
    try {
      await axios.delete(`http://localhost:8080/Phong/delete/${deleteId}`);
      setPhongs(phongs.filter((phong) => phong.id !== deleteId));
    } catch (error) {
      console.error("Lỗi khi xóa phòng:", error);
    }
  };

  return (
    <div>
      <h2>Danh sách phòng</h2>
      <ul>
        {phongs.map((phong) => (
          <li key={phong.PhongId}>{`Phòng ID: ${phong.PhongId} - Số chỗ: ${phong.soChoNgoi}`}</li>
        ))}
      </ul>

      <h3>Thêm Phòng</h3>
      <input
        type="number"
        placeholder="Số chỗ ngồi"
        onChange={(e) => setNewPhong({ soChoNgoi: e.target.value })}
      />
      <button onClick={addPhong}>Thêm</button>

      <h3>Cập nhật Phòng</h3>
      <input
        type="text"
        placeholder="ID"
        onChange={(e) => setUpdatePhong({ ...updatePhong, id: e.target.value })}
      />
      <input
        type="number"
        placeholder="Số chỗ ngồi"
        onChange={(e) => setUpdatePhong({ ...updatePhong, soChoNgoi: e.target.value })}
      />
      <button onClick={updatePhongData}>Cập nhật</button>

      <h3>Xóa Phòng</h3>
      <input
        type="text"
        placeholder="ID cần xóa"
        onChange={(e) => setDeleteId(e.target.value)}
      />
      <button onClick={deletePhong}>Xóa</button>
    </div>
  );
};

export default Phong;
