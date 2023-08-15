import axios from "axios";
import store from "@/store";
import router from "@/router";

let isRefreshing = false; // flag to check if we are already refreshing the token

const errorHandler = async (error) => {
  console.log("error: ", error)
  if (error.response.status === 401) {
      if (isRefreshing) {
          // We already tried to refresh the token, redirect to login
          redirectToLogin();
          return;
      }

      isRefreshing = true;

      // ... existing code ...
      // accessToken 과 refreshToken으로 사용자 토큰값 검증 요청 API
      console.log("401...................");
      _axios.defaults.headers["Authorization"] =
        localStorage.getItem("refreshToken");
      const res = await _axios.post("/refresh");
      console.log("res: ", res);

      if (res.status === 200) {
        // ... existing code ...
        console.log("res.status == 200: ", res.data)
        localStorage.setItem("accessToken", res.data.accessToken);
        store.commit("setAccessToken", res.data.accessToken);
        error.config.headers.Authorization = localStorage.getItem("accessToken");
        console.log("error.config: ", error.config.headers);
        isRefreshing = false; // reset the flag

        return _axios(error.config);
      } else {
        redirectToLogin();
      }
  }
  return Promise.reject(error);
}

const redirectToLogin = () => {
    console.log("refreshToken not valid..")
    store.commit("initUserInfo");
    localStorage.removeItem("accessToken");
    localStorage.removeItem("refreshToken");
    delete _axios.defaults.headers.Authorization;
    router.push({name: "login"});
    return;
}


// axios 객체 생성
const _axios = axios.create({
  // baseURL: "https://i9a604.p.ssafy.io",
  baseURL: "https://wootcham.com/api",
  // baseURL: "http://localhost:8080",
  headers: {
    "Content-type": "application/json",
  },
});

_axios.interceptors.request.use(
  (config) => {
      return config;
  },
  (error) => {
      return Promise.reject(error);
  }
);

_axios.interceptors.response.use(
  (response) => {
      return response;
  },
  error => errorHandler({...error})
);

export default _axios;
