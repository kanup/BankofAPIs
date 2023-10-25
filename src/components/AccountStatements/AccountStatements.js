import React from 'react';
import { Link } from 'react-router-dom';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Paper from '@mui/material/Paper';
import jsPDF from 'jspdf';
import 'jspdf-autotable';
const AccountStatements = ({ bankstatements, onClose, isClicked }) => {
  const generatePDF = () => {
    const pdf = new jsPDF();
  
    if (pdf) {
      pdf.text('Bank Statements', 10, 10); 
      const tableData = [];
      bankstatements.forEach((statement) => {
        tableData.push([
          statement.date,
          statement.purpose,
          statement.withdrawal,
          statement.deposit,
        ]);
      });
  
      pdf.autoTable({
        startY: 20, 
        head: [['Date', 'Transaction Description', 'Withdrawal', 'Deposit']],
        body: tableData,
      });
  
      pdf.save('Statement.pdf');
    }
  };
  
  
  


  return (
    <div style={{maxWidth: "80%", margin:"20px auto"}}>
      {bankstatements == null ?
        <h2>No Transactions yet! But you can make one <Link to={"/transfer"}></Link></h2> :
        <div>
          {isClicked && (
            <div>
              
              <div style={{ display: "flex", justifyContent:"space-between", padding:"10px"}}>
                <div >
                  <h2>Bank Statements</h2>
                </div>
                <div>
                <button className='cta-button' onClick={(e) => { e.preventDefault(); generatePDF(); }} >Print Statement</button>
                <button onClick={onClose} className='cta-button'>Close</button>
                </div>
              
                
              </div>
              <TableContainer component={Paper}>
                <Table id="statements-table" aria-label="Statements Table">
                  <TableHead>
                    <TableRow>
                      <TableCell><b>Date</b></TableCell>
                      <TableCell><b>Transaction Description</b></TableCell>
                      <TableCell><b>Withdrawal</b></TableCell>
                      <TableCell><b>Deposit</b></TableCell>
                    </TableRow>
                  </TableHead>
                  <TableBody>
                    {bankstatements.map((statement, index) => (
                      <TableRow key={index}>
                        <TableCell>{statement.date}</TableCell>
                        <TableCell>{statement.purpose}</TableCell>
                        <TableCell>{statement.withdrawal}</TableCell>
                        <TableCell>{statement.deposit}</TableCell>
                      </TableRow>
                    ))}
                  </TableBody>
                </Table>
              </TableContainer>


            </div>
          )}
        </div>
      }
    </div>

  );
};

export default AccountStatements;
