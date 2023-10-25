import React from 'react';
import { Link } from 'react-router-dom';
import './Home.css';
import background from './background.png'
import icon1 from './icon-1.png'
import icon2 from './icon-2.png'
import icon3 from './icon-3.png'
import icon4 from './icon-4.png'

const Home = ({ isLoggedIn }) => {
  return (
    <div className="home-page">


      <section className="hero-section">
        <div className="hero-content">
          <h1>Welcome to Bank of APIs</h1>
          <p>Your Financial Partner</p>
          {isLoggedIn ? <a href="#services" className="cta-button" style={{ textAlign: 'center' }}>
            Get Started
          </a> :
            <Link to="/login" className="cta-button" style={{ textAlign: 'center' }}>
              Get Started
            </Link>}
        </div>
        <div className="hero-image">
          <img className='hero-image' src={background} alt="Banking Illustration" />
        </div>
      </section>

      <section className="feature-section" id='services'>

        <div className="feature-card">
          <div className="feature-icon">
            <img src={icon1} alt="Feature 1" />
          </div>
          <h2>Account Statements</h2>
          <p>View and manage your account statements.</p>
          <Link to={isLoggedIn ? "/account-overview" : "/login"}>
            <button className="cta-button">Explore</button>
          </Link>
        </div>
        <div className="feature-card">
          <div className="feature-icon">
            <img src={icon2} alt="Feature 2" />
          </div>
          <h2>Money Transfer</h2>
          <p>Transfer money between accounts securely.</p>
          <Link to={isLoggedIn ? "/transfer" : "/login"}>
            <button className="cta-button">Explore</button>
          </Link>
        </div>
        <div className="feature-card">
          <div className="feature-icon">
            <img src={icon3} alt="Feature 3" />
          </div>
          <h2>Credit Card</h2>
          <p>Manage your credit card and transactions.</p>
          <Link to={isLoggedIn ? "/creditcardHome" : "/login"}>
            <button className="cta-button">Explore</button>
          </Link>
        </div>
        <div className="feature-card">
          <div className="feature-icon">
            <img src={icon4} alt="Feature 3" />
          </div>
          <h2>Customer Support</h2>
          <p>Need help? Customer support is here.</p>
          <Link to="/contact">
            <button className="cta-button" >Explore</button>
          </Link>
        </div>

      </section>
      <section className='SubFooter_subFooter__1ig8z ob-section subFooter'>
        <div class="ob-section-content">
          <div class="SubFooter_title__23n_1">Still can't find what you're looking for?</div>
          <div>
            <Link to="/contact" className="styled-button">Contact us</Link>


          </div>
        </div>
      </section>


    </div>
  );
};

export default Home;
