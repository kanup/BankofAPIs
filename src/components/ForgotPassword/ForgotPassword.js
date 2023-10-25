import React, { useState } from 'react';
import './ForgotPassword.css';
import { useNavigate } from 'react-router-dom';

const ForgotPassword = ({ forgotValueChange }) => {
  const navigate = useNavigate();
  const [display, setDisplay] = useState(false);
  const [displayError, setDisplayError] = useState(false);

  const handleSubmit = async (e) => {
    e.preventDefault();
    console.log(e.target.forgotValue.value);
    const res = forgotValueChange(e.target.forgotValue.value);
    if (!res) {
      setDisplayError(true);
      setTimeout(() => {
        setDisplayError(false);
      }, 3000)
      return;
    }
    
    setDisplay(true);
    navigate('/change');
  };


  const handleVerifyOtp = (e) => {
    e.preventDefault();
   
  };

  return (
    <div className="forgot-container">
      <form className="forgot-form" onSubmit={handleSubmit}>
        <h2 className='heading'>Reset Password</h2>
        <div className="formf-group">
          <input
            type="tel"
            id="forgotValue"
            pattern="[0-9]{10}"
            placeholder="Enter your Phone Number"
            required
          />

          <button type="submit" >Change</button>
        </div>
        <div id='recaptcha-container' />
        {displayError ? <p style={{ color: 'red', paddingLeft: '5px' }}>Phone number does not exist!!</p> : ''}
        {/* {display && (
          <div className='otp'>
            <input type="tel" id="otp" placeholder="Enter OTP" required />
            <button type="button" >Verify OTP</button>
          </div>
        )} */}

      </form>

    </div>
  );
};

export default ForgotPassword;
