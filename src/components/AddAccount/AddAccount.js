import React, { useEffect, useState } from 'react';
import './AddAccount.css';

const AddAccount = ({ onClose, phoneNumber, refreshaccounts }) => {
  const [selectedBank, setSelectedBank] = useState('');
  const [selectedAccount, setSelectedAccount] = useState('');
  const [display, setDisplay] = useState(false);
  const [banks, setBanks] = useState([]);
  const [accounts, setAccounts] = useState([]);
  const [displayAccounts, setDisplayAccounts] = useState(false);
  const [zeroAccounts, setZeroAccounts] = useState(false);


  useEffect(() => {
    getBanks();

  }, []);

  const getBanks = async () => {
    try {
      const response = await fetch('http://localhost:9200/banks/getBanks');
      const gettingBanks = await response.json();
      setBanks(gettingBanks);
    } catch (error) {
      console.error('Error fetching banks:', error);
    }
  };

  const handleSelectBankAccount = (e) => {
    e.preventDefault();
    setDisplayAccounts(false);
    setSelectedBank(e.target.value);
    setAccounts([]);
  };

  const getBankAccounts = async () => {
    try {
      const response = await fetch(`http://localhost:9200/accounts/getAccounts/${phoneNumber}/${selectedBank}`);
      const gettingAccounts = await response.json();
      setAccounts(gettingAccounts);
      if (!gettingAccounts.length) {
        setZeroAccounts(true);
        setTimeout(() => {
          setZeroAccounts(false);
        }, 3000)
      }

    } catch (error) {
      console.error('Error fetching bank accounts:', error);
    }
  };

  const handleAddAccount = (e) => {
    e.preventDefault();

    if (selectedAccount === '') {
      setDisplay(true);
      setTimeout(() => {
        setDisplay(false);
      }, 3000);
      return;
    }

    addAccountToUserAccounts(phoneNumber, selectedBank, Number(selectedAccount));
  };

  const handleGetAccounts = () => {
    setZeroAccounts(false);
    getBankAccounts();
    setDisplayAccounts(true);
  };

  const headers = new Headers();
  headers.append('Content-Type', 'application/json');

  const addAccountToUserAccounts = async (phoneNumber, selectedBank, selectedAccount) => {
    const addingAccount = {
      phoneNumber: phoneNumber,
      bankName: selectedBank,
      accountNumber: selectedAccount,
    };

    try {
      const response = await fetch('http://localhost:9200/userLinkedAccounts/linkAccount', {
        method: 'POST',
        headers: headers,
        body: JSON.stringify(addingAccount),
      });



      onClose();
    } catch (error) {
      console.error('Error adding account:', error);
    }
    refreshaccounts();
  };

  return (
    <div className="add-account-modal">
      <h2>Add Account</h2>
      <label>
        Select Bank:
        <select value={selectedBank} onChange={handleSelectBankAccount} required>
          <option value="">Select a bank</option>
          {banks.map((bank) => (
            <option key={bank.id} value={bank.name}>
              {bank.bankName}
            </option>
          ))}
        </select>
        {zeroAccounts ? <p style={{ color: 'red' }}>No Accounts Found!!!</p> : ''}
        <button className="verify" onClick={handleGetAccounts}>
          Get Accounts
        </button>
      </label>

      {selectedBank && displayAccounts && accounts.length > 0 && (
        <div>
          <label>
            Select Account:
            <select
              value={selectedAccount}
              onChange={(e) => setSelectedAccount(e.target.value)}
              required
            >
              <option value="">Select an Account</option>
              {accounts.map((account) => (
                <option key={account.id} value={account.accountNumber}>
                  {account.accountNo}
                </option>
              ))}
            </select>
          </label>

          {display && (
            <p style={{ color: 'red', paddingLeft: '10px', paddingBottom: '10px' }}>
              Please select an account to continue!!
            </p>
          )}

          <button className="verify" onClick={handleAddAccount}>
            Add Account
          </button>
        </div>
      )}
      <button className="close" onClick={onClose}>
        Close
      </button>
    </div>
  );
};

export default AddAccount;
