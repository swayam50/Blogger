import axios from 'axios';

export const formClient = axios.create({
    baseURL: 'http://127.0.0.1:8080/blogger/api',
    headers: {
        "Content-Type": "application/x-www-form-urlencoded",
        "Accept": "application/json",
        "Accept-Encoding": "gzip, deflate, compress, br"
    }
});

export const resourceClient = axios.create({
    baseURL: 'http://127.0.0.1:8080/blogger/api',
    headers: {
        "Content-Type": "application/json",
        "Accept": "application/json",
        "Accept-Encoding": "gzip, deflate, compress, br",
        "Authorization": `USER_COOKIE`
    }
});