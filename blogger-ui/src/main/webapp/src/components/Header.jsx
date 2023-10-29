import React from 'react';
import { Link } from 'react-router-dom';
import Logo from '../resources/img/blogger-logo-color.png';

const Header = () => {
    return (
        <header>
            <div className="header-container">
                <div className="logo">
                    <img src={Logo} alt="blogger" />
                </div>
                <nav className="links">
                    <Link className="link" to="/?category=art">
                        <h6>ART</h6>
                    </Link>
                    <Link className="link" to="/?category=science">
                        <h6>SCIENCE</h6>
                    </Link>
                    <Link className="link" to="/?category=technology">
                        <h6>TECHNOLOGY</h6>
                    </Link>
                    <Link className="link" to="/?category=cinema">
                        <h6>CINEMA</h6>
                    </Link>
                    <Link className="link" to="/?category=design">
                        <h6>DESIGN</h6>
                    </Link>
                    <Link className="link" to="/?category=food">
                        <h6>FOOD</h6>
                    </Link>
                    <span>Swayam</span>
                    <span>Logout</span>
                    <span className="write">
                        <Link className="link" to="/create">Write</Link>
                    </span>
                </nav>
            </div>
        </header>
    );
};

export default Header;