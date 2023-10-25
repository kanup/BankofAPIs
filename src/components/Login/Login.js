import React, { useState } from 'react';
import './Login.css';
import { useNavigate, Link } from 'react-router-dom';
import Snackbar from '@mui/material/Snackbar';
import MuiAlert from '@mui/material/Alert';

const Login = ({ login }) => {
  const [phonenumber, setPhoneNumber] = useState('');
  const [password, setPassword] = useState('');
  const [display, setDisplay] = useState(false);
  const [isLoggedIn, setIsLoggedIn] = useState(false);
  const [openSnackbar, setOpenSnackbar] = useState(false);

  const navigate = useNavigate();

  const handlePhoneNumberChange = (e) => {
    setPhoneNumber(e.target.value);
  };

  const handlePasswordChange = (e) => {
    setPassword(e.target.value);
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    const loginCheck = login(phonenumber, password);
    console.log(loginCheck)
    if (loginCheck === false) {
      setDisplay(true);
      setTimeout(() => {
        setDisplay(false);
      }, 3000);
    } else {
      setIsLoggedIn(true);
      setOpenSnackbar(true);
      setTimeout(() => {
        navigate('/account-overview')
      },500)
    }
  };

  const handleCloseSnackbar = () => {
    setOpenSnackbar(false);
  };

  const handleForgotClick = (e) => {
    e.preventDefault();
    navigate('/forgot');
  }

  return (
    <div className="login-container">
      <h3 style={{ paddingBottom: '10px' }}>Welcome back! Login to proceed.</h3>
      <form className="login-form" onSubmit={handleSubmit}>
        <label htmlFor="phonenumber">Phone Number:</label>
        <input
          type="tel"
          id="phonenumber"
          value={phonenumber}
          onChange={handlePhoneNumberChange}
          pattern="[0-9]{10}"
          placeholder="Enter your Phone Number"
          required
        />
        <label htmlFor="password">Password:</label>
        <input
          type="password"
          id="password"
          value={password}
          onChange={handlePasswordChange}
          placeholder="Enter your password"
          required
        />
        {display ? (
          <p style={{ paddingBottom: '10px', paddingLeft: '5px', color: 'red' }}>
            Please enter valid credentials!!
          </p>
        ) : (
          ''
        )}
        <Snackbar open={openSnackbar} autoHideDuration={6000} onClose={handleCloseSnackbar}>
          <MuiAlert style={{paddingLeft:'50px'}} elevation={6} variant="filled" severity="success" onClose={handleCloseSnackbar}>
            Login successful! You are now logged in.
          </MuiAlert>
        </Snackbar>
        <button type="submit">Login</button>
        <div className="signup-message">
          New user? <Link to="/signup">Sign up here</Link>.
        </div>
      </form>
      <div className="forgot-password">
        <a href="#" onClick={handleForgotClick}>
          Forgot your password?
        </a>
      </div>

    </div>
  );
};

export default Login;