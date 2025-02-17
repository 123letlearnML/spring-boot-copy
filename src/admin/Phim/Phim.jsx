import { useState, useEffect } from "react";
import axios from "axios";

const API_BASE_URL = "http://localhost:8080/Phim";

export default function Phim() {
  const [phimList, setPhimList] = useState([]);
  const [selectedPhim, setSelectedPhim] = useState(null);
  const [file, setFile] = useState(null);

  useEffect(() => {
    fetchPhimList();
  }, []);

  const fetchPhimList = async () => {
    try {
      const response = await axios.get(`${API_BASE_URL}/all`);
      setPhimList(response.data);
    } catch (error) {
      console.error("Lỗi khi lấy danh sách phim", error);
    }
  };

  const fetchPhimById = async (id) => {
    try {
      const response = await axios.get(`${API_BASE_URL}/${id}`);
      setSelectedPhim(response.data);
    } catch (error) {
      console.error("Lỗi khi lấy thông tin phim", error);
    }
  };

  const handleFileChange = (event) => {
    setFile(event.target.files[0]);
  };

  const uploadImage = async (id) => {
    if (!file) return;
    const formData = new FormData();
    formData.append("file", file);
    try {
      await axios.post(`${API_BASE_URL}/image/${id}`, formData, {
        headers: { "Content-Type": "multipart/form-data" },
      });
      alert("Upload ảnh thành công!");
    } catch (error) {
      console.error("Lỗi khi upload ảnh", error);
    }
  };

  return (
    <div>
      <h1>Danh sách phim</h1>
      <ul>
        {phimList.map((phim) => (
          <li key={phim.phimId}>
            {phim.tenPhim} 
            <button onClick={() => fetchPhimById(phim.phimId)}>Xem chi tiết</button>
          </li>
        ))}
      </ul>

      {selectedPhim && (
        <div>
          <h2>Chi tiết phim: {selectedPhim.tenPhim}</h2>
          <p>Giờ chiếu: {selectedPhim.gioChieuPhim}</p>
          <p>Giá tiền: {selectedPhim.giaTienphim} VND</p>
          {selectedPhim.anhPhim && (
            <img src={`${API_BASE_URL}/image/${selectedPhim.phimId}`} alt="Ảnh phim" width={200} />
          )}
          <div>
            <input type="file" onChange={handleFileChange} />
            <button onClick={() => uploadImage(selectedPhim.phimId)}>Upload Ảnh</button>
          </div>
        </div>
      )}
    </div>
  );
}
