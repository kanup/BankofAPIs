// Signup.js
import React, { useState } from 'react';
import './Signup.css';
import Snackbar from '@mui/material/Snackbar';
import MuiAlert from '@mui/material/Alert';
import { useNavigate } from 'react-router-dom';

const Signup = ({ addUser }) => {
  const [firstName, setFirstName] = useState('');
  const [lastName, setLastName] = useState('');
  const [email, setEmail] = useState('');
  const [PhoneNumber, setPhoneNumber] = useState('');
  const [password, setPassword] = useState('');
  const [dob, setDob] = useState('');
  const [display, setDispaly] = useState(false);
  const [openSnackbar, setOpenSnackbar] = useState(false);

  const handleCloseSnackbar = () => {
    setOpenSnackbar(false);
  };

  const handleFirstNameChange = (e) => {
    setFirstName(e.target.value);
  };

  const handleLastNameChange = (e) => {
    setLastName(e.target.value);
  };

  const handleEmailChange = (e) => {
    setEmail(e.target.value);
  };
  const handlePhoneNumberChange = (e) => {
    setPhoneNumber(e.target.value);
  };

  const handlePasswordChange = (e) => {
    setPassword(e.target.value);
  };

  const handleDobChange = (e) => {
    setDob(e.target.value);
  };

  const navigate = useNavigate();

  const handleSubmit = (e) => {
    e.preventDefault();
    const newUser = {
      firstName: firstName,
      lastName: lastName,
      email: email,
      phoneNumber: PhoneNumber,
      password: password,
      dob: dob
    }
    addUser(newUser).then(
      result => {
        if (!result) {
          setDispaly(true);
          setTimeout(() => {
            setDispaly(false)
          }, 3000);
        }
        else {
          setOpenSnackbar(true)
          setTimeout(() => {
            navigate('/login')
          }, 500)
        }
      }
    );
  };

  return (
    <div className="signup-container">
      <h3 style={{ paddingBottom: '10px' }}>Become a Member of NatWest Group!</h3>
      <form className="signup-form" onSubmit={handleSubmit}>
        <label htmlFor="First Name">First Name:</label>
        <input
          type="text"
          id="First Name"
          className="signup-form input"
          value={firstName}
          onChange={handleFirstNameChange}
          placeholder="Enter your First Name"
          required
        />
        <label htmlFor="Last Name">Last Name:</label>
        <input
          type="text"
          id="LastName"
          value={lastName}
          onChange={handleLastNameChange}
          placeholder="Enter your Last Name"
          required
        />
        <label htmlFor="Email">Email:</label>
        <input
          type="email"
          id="email"
          value={email}
          onChange={handleEmailChange}
          placeholder="Enter your Email"
          required
        />
        <label htmlFor="PhoneNumber">Phone Number:</label>
        <input
          type="tel"
          id="phonenumber"
          value={PhoneNumber}
          pattern="[0-9]{10}"
          onChange={handlePhoneNumberChange}
          placeholder="Enter your Phone Number"
          required
        />
        <label htmlFor="DOB">Date Of Birth:</label>
        <input
          type="date"
          id="dob"
          value={dob}
          onChange={handleDobChange}
          placeholder="Enter your DOB"
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
        {display ? <p style={{ paddingBottom: '10px', paddingLeft: '10px', fontSize: '14px', color: 'red' }}>Phone Number already exists. Please Login!!</p> : ''}
        <Snackbar open={openSnackbar} autoHideDuration={6000} onClose={handleCloseSnackbar}>
          <MuiAlert style={{ paddingLeft: '50px' }} elevation={6} variant="filled" severity="success" onClose={handleCloseSnackbar}>
            SignUp succcesfull. Please Login!!
          </MuiAlert>
        </Snackbar>
        <button type="submit">Signup</button>
      </form>
      <br></br>
      <br></br>
      <br></br>

    </div>



  );
};

export default Signup;