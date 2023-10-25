import React, { Component } from 'react';
import './CreditcardHome.css';

import HotlistDialog from '../Hotlisting/HotlistDialog';
import TransactionDialog from '../Statement/TransactionDialog';

class Home extends Component {


  constructor(props) {
    super(props);
    this.state = {
      creditCardNumbers: [],
      selectedCard: null,
      isDialogOpen: false,
      selectedReason: '',
      isPaymentDialogOpen: false,
      paymentData: null,
      isTransactionDialogOpen: false,
      cardData: [],
    };
  }

  componentDidMount() {

    const phoneNumber = this.props.phoneNumber;
    this.fetchCardData(phoneNumber);
  }

  fetchCardData = (phoneNumber) => {
    fetch('http://localhost:9200/creditCard/getCreditCards/' + phoneNumber)
      .then((response) => response.json())
      .then((cardData) => {
        this.setState({
          cardData,
          creditCardNumbers: cardData.map((card) => card.creditCardNumber),
        });
      })
      .catch((error) => {
        console.error('Error fetching card data:', error);
      });
  };


  handleOpenDialog = () => {
    this.setState({ isDialogOpen: true });
  };

  handleCloseDialog = () => {
    this.setState({ isDialogOpen: false });
  };

  handleConfirm = (reason) => {
    this.setState({ selectedReason: reason });
  };

  handleOpenPaymentDialog = () => {
    this.setState({ isPaymentDialogOpen: true });
  };

  handleClosePaymentDialog = () => {
    this.setState({ isPaymentDialogOpen: false });
  };

  handlePaymentConfirm = (data) => {
    this.setState({ paymentData: data });
  };

  handleCardChange = (event) => {
    event.preventDefault();
    const selectedCard = event.target.value;
    this.setState({ selectedCard });
  };

  handleOpenTransactionDialog = () => {
    this.setState({ isTransactionDialogOpen: true });
  };

  handleCloseTransactionDialog = () => {
    this.setState({ isTransactionDialogOpen: false });
  };


  renderCardInfo = () => {
    const { selectedCard, cardData } = this.state;
    if (!selectedCard) return null;

    const selectedCardInfo = cardData.find((card) => card.creditCardNumber === selectedCard);

    if (!selectedCardInfo) {
      return null;
    }

    const { selectedReason, isDialogOpen } = this.state;

    return (
      <div className="card-info">
        <table>
          <tbody>
            <tr>
              <td>Card Number:</td>
              <td>{selectedCardInfo.creditCardNumber}</td>
            </tr>
            <tr>
              <td>Bank Name:</td>
              <td>{selectedCardInfo.bankName}</td>
            </tr>
            <tr>
              <td>Card Type:</td>
              <td>{selectedCardInfo.creditCardType}</td>
            </tr>
            <tr>
              <td>Name:</td>
              <td>{selectedCardInfo.creditCardHolderName}</td>
            </tr>
            <tr>
              <td>Total Unbilled Debits:</td>
              <td>{selectedCardInfo.totalUnbilledDebits}</td>
            </tr>
            <tr>
              <td>Available Credit:</td>
              <td>{selectedCardInfo.availableCredit}</td>
            </tr>
            <tr>
              <td>Statement Balance:</td>
              <td>{selectedCardInfo.statementBalance}</td>
            </tr>
          </tbody>
        </table>
        <div className="buttons-container">
          <button className="cta-button" onClick={this.handleOpenDialog} style={{ marginLeft: '5px', fontSize: '16px' }}>Hotlist</button>
          <HotlistDialog
            open={isDialogOpen}
            onClose={this.handleCloseDialog}
            onConfirm={this.handleConfirm}
            selectedCard={this.state.selectedCard}
          />
          <button className="cta-button" onClick={this.handleOpenTransactionDialog} style={{ marginLeft: '5px', fontSize: '16px' }}>View Statement</button>
          <TransactionDialog
            open={this.state.isTransactionDialogOpen}
            onClose={this.handleCloseTransactionDialog}
            selectedCard={this.state.selectedCard}
          />

        </div>
      </div>
    )
  }

  render() {
    return (
      <div className="card-container">
        <div className="left"></div>
        <div className="middle">
          <div className="dropdown-container">
            <label>Select Card Number:</label>
            <select onChange={this.handleCardChange}>
              <option key="" value="">Select a card</option>
              {this.state.creditCardNumbers.map((creditCardNumber) => (
                <option key={creditCardNumber} value={creditCardNumber}>
                  {creditCardNumber}
                </option>
              ))}
            </select>
          </div>
          {this.state.selectedCard ? (
            this.renderCardInfo()
          ) : null}
        </div>
        <div className="right"></div>
      </div>
    );
  }

}

export default Home;
