import axios from 'axios';

export const formClient = axios.create({
    baseURL: 'http://127.0.0.1:8080/blogger/api',
    headers: {
        "Content-Type": "application/x-www-form-urlencoded",
        "Accept": "application/json"
    }
});

export const resourceClient = axios.create({
    baseURL: 'http://127.0.0.1:8080/blogger/api',
    headers: {
        "Content-Type": "application/json",
        "Accept": "application/json",
        "Authorization": `USER_COOKIE`
    }
});