import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import useApi from '../hooks/useApi';
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

    // const [registrationRequest, setRegistrationRequest] = useState({
    //     client: formClient,
    //     endpoint: {},
    //     executeRequest: false
    // });

    // const [result, error, loading] = useApi(registrationRequest);

    const handleTextChange = event => {
        let { name: field, value } = event.target;
        setFormInputs(inputs => ({...inputs, [field]: value}));
    };

    const handleImageChange = event => {
        let image = event.target.files[0];
        setFormInputs(inputs => ({...inputs, profilePic: image}));
    };

    const handleSubmit = event => {
        event.preventDefault();

        let userData = {...formInputs, profilePic: null};
        
        convertFileToBase64String(formInputs.profilePic, null)
            .then(encodedImage => userData.profilePic = encodedImage)
            .catch(defaultImage => userData.profilePic = defaultImage);

        console.log(userData);

        formClient.request(userRegistration(userData))
        .then(result => console.log(result))
        .catch(error => console.error(error));
    }

    return (
        <div className="entry">
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
                <p>This is an error!</p>
                <span>Already have an account? <Link to="/entry/login">login</Link></span>
            </form>
        </div>
    );
};

export default Register;