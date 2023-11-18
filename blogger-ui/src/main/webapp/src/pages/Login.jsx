import React, { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { useCookies } from 'react-cookie';
import { formClient } from '../configs/axios-conf';
import { userLogin } from '../apis/AuthEndpoints';

const Login = () => {
    const [cookies, setCookie, removeCookie] = useCookies(['access_token', 'proxy_token']);

    const [formInputs, setFormInputs] = useState({
        username: '',
        password: ''
    });

    const [error, setError] = useState(null);

    const navigate = useNavigate();

    const handleTextChange = event => {
        let { name: field, value } = event.target;
        setFormInputs(inputs => ({...inputs, [field]: value}));
    };

    const handleSubmit = async (event) => {
        event.preventDefault();

        let userData = {...formInputs};
    
        try {
            const response = await formClient.request(userLogin(userData));
            console.info(response);

            setCookie('access_token', response.headers['x-access-token']);
            setCookie('proxy_token', response.headers['x-proxy-token']);

            navigate('/');
        } catch (error) {
            console.error(error);
            setError(error.response.data.message || 'Something wrong happened!');
        }
    }

    return (
        <div className="auth">
            <form action="POST" onSubmit={handleSubmit}>
                <h1>LOGIN</h1>
                <input type="text" placeholder="username" name="username" onChange={handleTextChange} required />
                <input type="password" placeholder="password" name="password" onChange={handleTextChange} required />
                <button>login</button>
                {error && <p>{error}</p>}
                <span>Don't have an account? <Link to="/auth/register">register</Link></span>
            </form>
        </div>
    );
};

export default Login;