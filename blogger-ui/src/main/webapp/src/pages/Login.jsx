import React, { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { formClient } from '../configs/axios-conf';
import { userLogin } from '../apis/AuthEndpoints';

const Login = () => {
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
            const result = await formClient.request(userLogin(userData));
            console.info(result);
            navigate('/');
        } catch (error) {
            console.error(error);
            setError(error.response.data.message);
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