import React from 'react';
import { Link } from 'react-router-dom';

const Login = () => {
    return (
        <div className="entry">
            <form action="POST">
                <h1>Login</h1>
                <input type="text" placeholder="username" required />
                <input type="password" placeholder="password" required />
                <button>login</button>
                <p>This is an error!</p>
                <span>Don't have an account? <Link to="/entry/register">register</Link></span>
            </form>
        </div>
    );
};

export default Login;