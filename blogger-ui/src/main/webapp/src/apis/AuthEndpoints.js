export const userRegistration = userData => ({
    method: 'post',
    url: '/auth/register',
    data: new URLSearchParams(userData),
    maxBodyLength: Infinity
});

export const userLogin = () => ({
    method: 'post',
    url: '/auth/login'
});

export const userLogout = () => ({
    method: 'post',
    url: '/auth/logout'
})