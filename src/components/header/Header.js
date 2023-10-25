import React from 'react';
import './Header.css'
import logo from './banklogo.png'
import { useRef, useState } from "react";
import { FaBars, FaTimes } from "react-icons/fa";
import { Link, useNavigate } from 'react-router-dom';

const Header = ({ isLoggedIn, logout }) => {
  const navRef = useRef();
  const [isOpen, setIsOpen] = useState(false);

  const toggleDropdown = () => {
    setIsOpen(!isOpen);
  };

  const navigate = useNavigate();
  const handleLogout = (e) => {
    e.preventDefault();
    logout()
    navigate('/')
  }

  const showNavbar = () => {
    const isExpanded = navRef.current.classList.contains("responsive_nav");
    if (isExpanded) {
      navRef.current.classList.remove("responsive_nav");
    } else {
      navRef.current.classList.add("responsive_nav");
    }
  };
  return (
    <header>
      <div>
        <img src={logo} />
        <h3>NatWest</h3>
      </div>

      <nav ref={navRef}>
        <Link to="/"><b>Home</b></Link>
        <div className={`dropdown ${isOpen ? 'open' : ''}`} onClick={toggleDropdown}>
          <a className="dropdown-link"><b>Services</b></a>
          <div className="dropdown-menu">

            <Link to="/account-overview">Bank statements</Link>
            <Link to="/transfer">Money transfer</Link>
            <Link to="/creditcardHome">Credit card</Link>
            <div className="divider"></div>
            <Link to="/contact">Help</Link>
          </div>
        </div>
        <a style={{ cursor: 'pointer' }} onClick={() => { navigate('/contact') }}><b>Contact</b></a>
        {!isLoggedIn ? <a href="/login"><b>Login</b></a> : <a style={{ cursor: 'pointer' }} onClick={handleLogout}><b>Logout</b></a>}
        <button
          className="nav-btn nav-close-btn"
          onClick={showNavbar}>
          <FaTimes />
        </button>
      </nav>

      <button
        className="nav-btn"
        onClick={showNavbar}>
        <FaBars />
      </button>

    </header>

  );
};

export default Header;
