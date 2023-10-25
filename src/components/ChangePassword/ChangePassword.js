import React from 'react'
import './ChangePassword.css'
import { useNavigate } from 'react-router-dom'
import { useState } from 'react';

const ChangePassword = ({ handlesetNewPassword }) => {

  const navigate = useNavigate();
  const [display, setDisplay] = useState(false);
  const [enteredPassword, setEnteredPassword] = useState('');

  const handleSubmit = (e) => {
    e.preventDefault();
    if (e.target.password.value != e.target.newPassword.value) {
      setDisplay(true);
      return;
    }
    handlesetNewPassword(e.target.newPassword.value);
    navigate("/login");
  }

  const handlePasswordChange = (event) => {
    setEnteredPassword(event.target.value);
  };

  const handleReEnterPassword = (e) => {
    e.preventDefault();
    e.preventDefault();
    if (e.target.value != '' && e.target.value != enteredPassword) {
      setDisplay(true);
    }
    else {
      setDisplay(false)
    }
  }
  return (
    <div className="change-container">
      <form className="change-form" onSubmit={handleSubmit}>
        <h2 className='password-heading'>Create New Password</h2>
        <input
          type="password"
          id="newPassword"
          placeholder="Enter new password"
          value={enteredPassword}
          onChange={handlePasswordChange}
          required
        />
        <input
          type="password"
          id="password"
          onChange={handleReEnterPassword}
          placeholder="Re enter new password"
          required
        />
        {display ? <p style={{ color: 'red', paddingLeft: '5px' }}>Passwords do not match</p> : ''}
        <button type="submit">Reset Password</button>
      </form>
    </div>
  )
}

export default ChangePassword