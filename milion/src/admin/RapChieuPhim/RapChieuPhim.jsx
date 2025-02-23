import React, { useState, useEffect } from "react";
import axios from "axios";

const RapChieuPhim = () => {
  const [raps, setRaps] = useState([]);
  const [newRap, setNewRap] = useState({ soPhongCoSan: 0 });
  const [updateRap, setUpdateRap] = useState({ id: "", soPhongCoSan: 0 });
  const [deleteId, setDeleteId] = useState("");

  // Fetch danh sách rạp
  useEffect(() => {
    axios
      .get("http://localhost:8080/RapChieuPhim/all")
      .then((response) => setRaps(response.data))
      .catch((error) => console.error("Lỗi khi lấy danh sách rạp:", error));
  }, []);

  // Thêm rạp
  const addRap = async () => {
    try {
      const response = await axios.post("http://localhost:8080/RapChieuPhim/post", newRap);
      setRaps([...raps, response.data]);
    } catch (error) {
      console.error("Lỗi khi thêm rạp:", error);
    }
  };

  // Cập nhật rạp
  const updateRapData = async () => {
    try {
      await axios.put(`http://localhost:8080/RapChieuPhim/put/${updateRap.id}`, updateRap);
      setRaps(raps.map((rap) => (rap.id === updateRap.id ? updateRap : rap)));
    } catch (error) {
      console.error("Lỗi khi cập nhật rạp:", error);
    }
  };

  // Xóa rạp
  const deleteRap = async () => {
    try {
      await axios.delete(`http://localhost:8080/RapChieuPhim/delete/${deleteId}`);
      setRaps(raps.filter((rap) => rap.id !== deleteId));
    } catch (error) {
      console.error("Lỗi khi xóa rạp:", error);
    }
  };

  return (
    <div>
      <h2>Danh sách Rạp Chiếu Phim</h2>
      <ul>
        {raps.map((rap) => (
          <li key={rap.RCP_id}>{`Rạp ID: ${rap.RCP_id} - Số phòng có sẵn: ${rap.soPhongCoSan}`}</li>
        ))}
      </ul>

      <h3>Thêm Rạp</h3>
      <input
        type="number"
        placeholder="Số phòng có sẵn"
        onChange={(e) => setNewRap({ soPhongCoSan: e.target.value })}
      />
      <button onClick={addRap}>Thêm</button>

      <h3>Cập nhật Rạp</h3>
      <input
        type="text"
        placeholder="ID"
        onChange={(e) => setUpdateRap({ ...updateRap, id: e.target.value })}
      />
      <input
        type="number"
        placeholder="Số phòng có sẵn"
        onChange={(e) => setUpdateRap({ ...updateRap, soPhongCoSan: e.target.value })}
      />
      <button onClick={updateRapData}>Cập nhật</button>

      <h3>Xóa Rạp</h3>
      <input
        type="text"
        placeholder="ID cần xóa"
        onChange={(e) => setDeleteId(e.target.value)}
      />
      <button onClick={deleteRap}>Xóa</button>
    </div>
  );
};

export default RapChieuPhim;
