import React, { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { formClient } from '../configs/axios-conf';
import { userRegistration } from '../apis/AuthEndpoints';
import { convertFileToBase64String } from '../utils/IOUtils';
import DefaultProfPic from '../resources/img/default-prof.jpg';

const Register = () => {
    const [formInputs, setFormInputs] = useState({
        username: '',
        email: '',
        password: '',
        profilePic: null
    });

    const [error, setError] = useState(null);

    const navigate = useNavigate();

    const handleTextChange = event => {
        let { name: field, value } = event.target;
        setFormInputs(inputs => ({...inputs, [field]: value}));
    };

    const handleImageChange = event => {
        let image = event.target.files[0];
        setFormInputs(inputs => ({...inputs, profilePic: image}));
    };

    const handleSubmit = async (event) => {
        event.preventDefault();

        let userData = {...formInputs, profilePic: null};
        
        try {
            userData = {...formInputs, profilePic: await convertFileToBase64String(formInputs.profilePic, null)};
        } catch (defaultProfilePic) {
            userData = {...formInputs, profilePic: defaultProfilePic};
        }
    
        try {
            const result = await formClient.request(userRegistration(userData));
            console.info(result.data);
            navigate('/auth/login');
        } catch (error) {
            console.error(error.response.data);
            setError(error.response.data.message);
        }
    }

    return (
        <div className="auth">
            <form method="POST" onSubmit={handleSubmit}>
                <h1>REGISTER</h1>
                <div className="profile-pic-input">
                    <input type="file" accept="image/*" placeholder="profilePic" onChange={handleImageChange} name="profilePic" id="profilePic" />
                    <label htmlFor="profilePic">
                        {formInputs.profilePic ? (
                            <img src={URL.createObjectURL(formInputs.profilePic)} alt={`${formInputs.username}-profpic`} />
                        ) : (
                            <img src={DefaultProfPic} alt="default-profpic" />
                        )}

                    </label>
                </div>
                <input type="text" placeholder="username" name="username" onChange={handleTextChange} required />
                <input type="email" placeholder="email" name="email" onChange={handleTextChange} required />
                <input type="text" placeholder="password" name="password" onChange={handleTextChange} required />
                <button>register</button>
                {error && <p>{error}</p>}
                <span>Already have an account? <Link to="/auth/login">login</Link></span>
            </form>
        </div>
    );
};

export default Register;