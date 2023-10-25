import React, { useState, useEffect } from 'react';
import Dialog from '@mui/material/Dialog';
import DialogContent from '@mui/material/DialogContent';
import DialogTitle from '@mui/material/DialogTitle';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Paper from '@mui/material/Paper';
import html2canvas from 'html2canvas';
import jsPDF from 'jspdf';

const TransactionDialog = ({ open, onClose, selectedCard }) => {
  console.log(selectedCard)
  const [pdfGenerating, setPdfGenerating] = useState(false);
  const [transactions, setTransactions] = useState([]);


  useEffect(() => {
    if (open) {
      fetchTransactions();
    }
  }, [open]);


  const fetchTransactions = () => {
    fetch('http://localhost:9200/creditCardTransactions/getTransactions/' + selectedCard)
      .then((response) => response.json())
      .then((data) => {
        setTransactions(data);
      })
      .catch((error) => {
        console.error(error);
      });
  };


  const generatePDF = () => {
    setPdfGenerating(true);

    const input = document.getElementById('transaction-table');

    html2canvas(input).then((canvas) => {
      const imgData = canvas.toDataURL('image/png');
      const pdf = new jsPDF();
      pdf.addImage(imgData, 'PNG', 10, 10);
      pdf.save('Statement.pdf');
      setPdfGenerating(false);
    });
  };


  return (
    <Dialog open={open} onClose={onClose} maxWidth="md">
      <DialogTitle>
        <b>Transaction Details</b>
        <span
          style={{ position: 'absolute', top: 10, right: 10, cursor: 'pointer', color: 'red' }}
          onClick={onClose}
        >
          x
        </span>
      </DialogTitle>
      <DialogContent>
        <a
          href="#"
          onClick={(e) => {
            e.preventDefault();
            generatePDF();
          }}
          style={{
            color: '#5A287D',
            textDecoration: 'underline',
            cursor: 'pointer',
            position: 'absolute',
            top: 20,
            right: 40,
          }}
        >
          Print Statement
        </a>
        <TableContainer component={Paper}>
          <Table id="transaction-table" aria-label="Transaction Table">
            <TableHead>
              <TableRow>
                <TableCell><b>Date</b></TableCell>
                <TableCell><b>Transaction Description</b></TableCell>
                <TableCell><b>Amount in Rs.</b></TableCell>
              </TableRow>
            </TableHead>
            <TableBody>
              {transactions.map((transaction, index) => (
                <TableRow key={index}>
                  <TableCell>{transaction.date}</TableCell>
                  <TableCell>{transaction.transactionDescription}</TableCell>
                  <TableCell>{transaction.amountInRupees}</TableCell>
                </TableRow>
              ))}
            </TableBody>
          </Table>
        </TableContainer>
      </DialogContent>
    </Dialog>
  );
};

export default TransactionDialog;
