import axios from "axios";

// axios 객체 생성
export default axios.create({
  // baseURL: "https://i9a604.p.ssafy.io",
  baseURL: "http://localhost:8080",
  headers: {
    "Content-type": "application/json",
  },
});