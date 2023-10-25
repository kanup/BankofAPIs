import React, { useState } from 'react';
import './MoneyTransfer.css';
import axios from 'axios';
import PopUp from '../feedbackpopup/PopUp';

const MoneyTransfer = ({ isLoggedIn }) => {
    const [PopUpOpen, setPopUpOpen] = useState(false);
    const [message, setMessage] = useState('');
    

  
    const [formData, setFormData] = useState({
        beneficiary_name: '',
        beneficiary_bank: '',
        beneficiary_account_number: '',
        sender_bank: '',
        sender_account_number: '',
        payment_option: 'Within Home Bank',
        ifscCode: '',
        amount: '',
        purpose: 'Salary',
    });

    const handleSubmit = (e) => {
        e.preventDefault();
    
        if (!formData.amount || isNaN(parseFloat(formData.amount))) {
            shoWFeedback("Please enter a valid amount");
            return;
        }
        if (
            (formData.sender_account_number.length !== 10 && formData.sender_account_number.length !== 11) ||
            (formData.beneficiary_account_number.length !== 10 && formData.beneficiary_account_number.length !== 11)
        ) {
            shoWFeedback("Account numbers must be 10 or 11 digits long");
            return;
        }
    
        const requestData = {
            senderAccountNumber: formData.sender_account_number,
            beneficiaryBank: formData.beneficiary_bank,
            beneficiaryAccountNumber: formData.beneficiary_account_number,
            ifscCode: formData.payment_option === 'Outside Home Bank' ? formData.ifsc_code : '',
            amount: formData.amount,
            purpose: formData.purpose,
            senderBank: formData.sender_bank,
        };
    
        const updatebalancerequestData_sender = {
            "bankName": formData.sender_bank,
            "accountNo": formData.sender_account_number,
            "amount": formData.amount,
            "type": "w"
        };
        
        const updatebalancerequestData_beneficiary = {
            "bankName": formData.beneficiary_bank,
            "accountNo": formData.beneficiary_account_number,
            "amount": parseFloat(formData.amount),
            "type": "d"
        };
    
        axios.put('http://localhost:9200/accounts/updatebalance', updatebalancerequestData_beneficiary)
            .then((response) => {
                if (response.data.message === "not found") {
                    shoWFeedback('Transaction failed');
                    return;
                }
                console.log(response);
            })
            .catch((error) => {
                console.error('Error saving beneficiary transaction:', error);
            });
    
        axios.put('http://localhost:9200/accounts/updatebalance', updatebalancerequestData_sender)
            .then((response) => {
                if (response.data.message === "not found") {
                    shoWFeedback('Transaction failed');
                    return;
                }
                console.log(response);
            })
            .catch((error) => {
                console.error('Error saving sender transaction:', error);
            });
    
        axios.post('http://localhost:9200/transactions/saveTransaction', requestData)
            .then((response) => {
                if (response.data.message === "not found") {
                    shoWFeedback('Transaction failed');
                    return;
                }
                console.log('Transaction saved:', response.data);
                shoWFeedback('Transaction saved successfully');
                setFormData({
                    beneficiary_name: '',
                    beneficiary_bank: '',
                    beneficiary_account_number: '',
                    sender_bank: '',
                    sender_account_number: '',
                    payment_option: 'Within Home Bank',
                    ifscCode: '',
                    amount: '',
                    purpose: 'Salary',
                });
            })
            .catch((error) => {
                console.error('Error saving transaction:', error);
            });
    };

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData({ ...formData, [name]: value });
    };
    const handlePopUpClose = () => {
        setMessage('');
        setPopUpOpen(false);
    };

    const shoWFeedback = (message) => {
        setMessage(message);
        setPopUpOpen(true);
    };

    const renderAdditionalFields = () => {
        if (formData.payment_option === 'Outside Home Bank') {
            return (
                <div>

                    <div className="form-group">
                        <label htmlFor="ifsc_code">IFSC Code:</label>
                        <input
                            type="text"
                            id="ifsc_code"
                            name="ifsc_code"
                            value={formData.ifsc_code}
                            onChange={handleChange}
                            required
                        />
                    </div>
                </div>
            );
        }
        return null; 
    };


    return (
        <div>
            <div className="transfer-container">
                <form onSubmit={handleSubmit} className="transfer-form">
                    <h2>Payment Form</h2>
                    <div className="form-group">
                        <label htmlFor="beneficiary_bank">Beneficiary Bank:</label>
                        <input
                            type="text"
                            id="beneficiary_bank"
                            name="beneficiary_bank"
                            value={formData.beneficiary_bank}
                            onChange={handleChange}
                            required
                        />
                    </div>
                    <div className="form-group">
                        <label htmlFor="beneficiary_account_number">Beneficiary Account Number:</label>
                        <input
                            type="text"
                            id="beneficiary_account_number"
                            name="beneficiary_account_number"
                            value={formData.beneficiary_account_number}
                            onChange={handleChange}
                            required
                        />
                    </div>
                    <div className="form-group">
                        <label htmlFor="sender_bank">Sender Bank:</label>
                        <input
                            type="text"
                            id="sender_bank"
                            name="sender_bank"
                            value={formData.sender_bank}
                            onChange={handleChange}
                            required
                        />
                    </div>
                    <div className="form-group">
                        <label htmlFor="sender_account_number">Sender Account Number:</label>
                        <input
                            type="text"
                            id="sender_account_number"
                            name="sender_account_number"
                            value={formData.sender_account_number}
                            onChange={handleChange}
                            required
                        />
                    </div>
                    <div className="form-group">
                        <label htmlFor="payment_option">Payment Option:</label>
                        <div className="radio-group">
                            <input
                                type="radio"
                                id="within_home_bank"
                                name="payment_option"
                                value="Within Home Bank"
                                checked={formData.payment_option === 'Within Home Bank'}
                                onChange={handleChange}
                            />
                            <label htmlFor="within_home_bank">Within Home Bank</label>
                            <input
                                type="radio"
                                id="outside_home_bank"
                                name="payment_option"
                                value="Outside Home Bank"
                                checked={formData.payment_option === 'Outside Home Bank'}
                                onChange={handleChange}
                            />
                            <label htmlFor="outside_home_bank">Outside Home Bank</label>
                        </div>
                    </div>
                    {renderAdditionalFields()}
                    <div className="form-group">
                        <label htmlFor="amount">Amount:</label>
                        <input
                            type="number"
                            id="amount"
                            name="amount"
                            value={formData.amount}
                            onChange={handleChange}
                            required
                        />
                    </div>
                    <div className="form-group">
                        <label htmlFor="purpose">Purpose:</label>
                        <select
                            id="purpose"
                            name="purpose"
                            value={formData.purpose}
                            onChange={handleChange}
                            style={{ width: '302px' }}
                        >
                            <option value="Salary">Salary</option>
                            <option value="Bill Payment">Bill Payment</option>
                            <option value="Loan Repayment">Loan Repayment</option>
                            <option value="Gift">Gift</option>
                            <option value="Others">Others</option>
                        </select>
                    </div>
                    <button type="submit">Submit</button>
                    {PopUpOpen && (
                         <PopUp message={message} onClose={handlePopUpClose} />
                    )}
                   
                </form>
            </div>
        </div>
    );
};

export default MoneyTransfer;
