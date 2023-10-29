import React from 'react';
import Logo from '../resources/img/blogger-logo-no-background.png';

const Footer = () => {
    return (
        <footer>
            <img src={Logo} alt="blogger" />
            <span>Made with <strong>&hearts;</strong> by <strong>Swayam</strong>.</span>
        </footer>
    );
};

export default Footer;