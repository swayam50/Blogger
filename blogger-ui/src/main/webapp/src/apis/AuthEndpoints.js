export const userRegistration = userData => ({
    method: 'post',
    url: '/auth/register',
    data: new URLSearchParams(userData),
    maxBodyLength: Infinity,
    maxContentLength: Infinity
});

export const userLogin = userData => ({
    method: 'post',
    url: '/auth/login',
    data: new URLSearchParams(userData)
});

export const userLogout = () => ({
    method: 'post',
    url: '/auth/logout'
})