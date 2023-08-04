import axios from "axios";

// axios 객체 생성
export default axios.create({
  baseURL: "https://i9a604.p.ssafy.io",
  headers: {
    "Content-type": "application/json",
  },
});


