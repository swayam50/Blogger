import React from 'react';
import { Link } from 'react-router-dom';

const Register = () => {
    return (
        <div className="entry">
            <form action="POST">
                <h1>Register</h1>
                <input type="text" placeholder="username" required />
                <input type="email" placeholder="email" required />
                <input type="text" placeholder="password" required />
                <button>register</button>
                <p>This is an error!</p>
                <span>Already have an account? <Link to="/entry/login">login</Link></span>
            </form>
        </div>
    );
};

export default Register;