
import React, { useState, useEffect } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import './AccountsOverview.css';
import AddAccount from '../AddAccount/AddAccount';
import AccountStatements from './AccountStatements';
import axios from 'axios';

const AccountsOverview = ({ phoneNumber, isLoggedIn }) => {
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [isClicked, setisClicked] = useState(false);
  const [bankStatements, setBankStatements] = useState([]);
  const [accounts, setAccounts] = useState([]);

  const navigate = useNavigate();

  if (!isLoggedIn) {
    navigate("/")
  }


  const handleOpenModal = () => {
    setIsModalOpen(true);
  };

  const handleCloseModal = () => {
    setIsModalOpen(false);
  };

  const handleAccountAdded = (bank, account) => {

    console.log('Account added:', bank, account);

  };

  useEffect(() => {
    getAccounts();
  }, []);

  const getAccounts = async () => {
    const response = await fetch(`http://localhost:9200/userLinkedAccounts/getUserLinkedAccounts/${phoneNumber}`);
    const gettingAccounts = await response.json();
    setAccounts(gettingAccounts);
  };

  const handleCloseAccountStatements = () => {
    setisClicked(false);
    setBankStatements([]);
  };

  const fetchBankStatements = (bankName, accountNumber) => {
    console.log('clicked');
    setisClicked(true);
    axios
      .get('http://localhost:9200/getTransaction', {
        params: {
          senderBank: bankName,
          senderAccountNumber: accountNumber,
        },
      })
      .then((response) => {
        setBankStatements(response.data.data);
        console.log(bankStatements);
        console.log(response.data.data);
        setisClicked(true);
      })
      .catch((error) => {
        console.error('Error fetching bank statements:', error);
        setisClicked(false);
      });
  };

  useEffect(() => {
    console.log('Bank Statements Updated:', bankStatements);
  }, [bankStatements]);

  return (
    <div>
      <section className="welcome-section" style={{ display: 'flex', justifyContent: 'space-between', margin: "20px" }}>

        <div className="welcome-text">
          <h2 style={{ fontSize: '24px', marginBottom: '10px' }}>Welcome to Bank of APIs</h2>
          <p>Your Trusted Financial Partner</p>
        </div>
        <div className="cta-buttons">
          <button className="cta-button" onClick={handleOpenModal}>
            Add Account
          </button>
        </div>
      </section>
      {isModalOpen && (
        <AddAccount onClose={handleCloseModal} onAccountAdded={handleAccountAdded} phoneNumber={phoneNumber} refreshaccounts={getAccounts} />
      )}

      <div className="accounts-overview">
        <h1>Accounts Overview</h1>
        <div className="account-cards">
          {accounts.map((account) => (
            <div key={account.id} className="account-card">
              <div className="account-card-header">
                <h2>{account.bankName}</h2>
                <p>Account Number: {account.accountNumber}</p>
              </div>

              <div>
                <button className="cta-button" onClick={() => fetchBankStatements(account.bankName, account.accountNumber)}>
                  fetch statements
                </button>
              </div>
            </div>
          ))}
        </div>
      </div>
      <div>
        <AccountStatements bankstatements={bankStatements} onClose={handleCloseAccountStatements} isClicked={isClicked} />
      </div>
    </div>
  );
};

export default AccountsOverview;
