import axios from 'axios';

export const formClient = axios.create({
    baseURL: 'http://127.0.0.1:8080/blogger/api',
    withCredentials: true,
    headers: {
        "Host": "http://127.0.0.1:8080",
        "Content-Type": "application/x-www-form-urlencoded",
        "Accept": "application/json",
    }
});

export const resourceClient = axios.create({
    baseURL: 'http://127.0.0.1:8080/blogger/api',
    headers: {
        "Host": "http://127.0.0.1:8080",
        "Content-Type": "application/json",
        "Accept": "application/json",
        "Authorization": `USER_COOKIE`
    }
});