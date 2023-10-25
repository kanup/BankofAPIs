import React, { useState } from 'react';
import Dialog from '@mui/material/Dialog';
import DialogActions from '@mui/material/DialogActions';
import DialogContent from '@mui/material/DialogContent';
import DialogContentText from '@mui/material/DialogContentText';
import DialogTitle from '@mui/material/DialogTitle';
import Button from '@mui/material/Button';
import Radio from '@mui/material/Radio';
import RadioGroup from '@mui/material/RadioGroup';
import FormControlLabel from '@mui/material/FormControlLabel';
import axios from 'axios';
import './HotlistDialog.css';

const HotlistDialog = ({ open, onClose, selectedCard }) => {
  const [reason, setReason] = useState('');

  const handleRadioChange = (event) => {
    setReason(event.target.value);
  };

  const handleConfirm = () => {

    const selectedReason = reason;

    axios
      .post(`http://localhost:9200/creditCard/hotlist/${selectedCard}/${selectedReason}`)
      .then((response) => {
        console.log('Hotlist successful:', response.data);
      })
      .catch((error) => {
        console.error('Error hotlisting the card:', error);
      });

    onClose();
  };

  return (
    <Dialog open={open} onClose={onClose}>
      <DialogContent>
        <DialogContentText style={{ marginBottom: '16px' }}>
          Please select the reason for hotlisting:
        </DialogContentText>
        <RadioGroup value={reason} onChange={handleRadioChange}>
          <FormControlLabel
            value="Stolen"
            control={<Radio />}
            label="Stolen"
          />
          <FormControlLabel
            value="Lost"
            control={<Radio />}
            label="Lost"
            style={{ marginBottom: '1px' }}
          />
        </RadioGroup>
      </DialogContent>
      <DialogActions>
        <Button className="dialog-button" onClick={onClose} color="primary">
          Cancel
        </Button>
        <Button className="dialog-button" onClick={handleConfirm} color="primary">
          Confirm
        </Button>
      </DialogActions>
    </Dialog>
  );
};

export default HotlistDialog;
