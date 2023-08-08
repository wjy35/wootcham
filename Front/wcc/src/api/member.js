import api from "./http.js";

async function join(member, isSuccess, fail) {
    await api.post(`/member/join`, JSON.stringify(member)).then(isSuccess).catch(fail);
}

async function login(member, isSuccess, fail) {
    await api.post(`/member/login`, JSON.stringify(member)).then(isSuccess).catch(fail);
}

async function findById(memberid, isSuccess, fail) {
    api.defaults.headers["access-token"] = sessionStorage.getItem("access-token");
    await api.get(`/member/info/${memberid}`).then(isSuccess).catch(fail);
}

async function tokenRegeneration(member, isSuccess, fail) {
    api.defaults.headers["refresh-token"] = sessionStorage.getItem("refresh-token"); //axios header에 refresh-token 셋팅
    await api.post(`/member/refresh`, member).then(isSuccess).catch(fail);
}

async function logout(memberid, isSuccess, fail) {
    await api.get(`/member/logout/${memberid}`).then(isSuccess).catch(fail);
}

export { join, login, findById, tokenRegeneration, logout };