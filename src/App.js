import './App.css';
import React, { useState } from 'react';
import { BrowserRouter as Router, Routes, Route, useNavigate } from 'react-router-dom';
import Login from './components/Login/Login'; 
import Header from './components/header/Header';
import Signup from './components/Signup/Signup';
import AccountStatements from './components/AccountStatements/AccountStatements';
import MoneyTransfer from './components/MoneyTransfer/MoneyTransfer';
import CreditcardHome from './components/Creditcard/CreditcardHome/CreditcardHome'
import ForgotPassword from './components/ForgotPassword/ForgotPassword';
import ChangePassword from './components/ChangePassword/ChangePassword';
import Home from './components/Home/Home';
import { useEffect } from 'react';
import AccountsOverview from './components/AccountStatements/AccountsOverview';
import AddAccount from './components/AddAccount/AddAccount';
import Contact from './components/ContactUs/Contact'

const App = () => {
  const [forgotValue, setForgotValue] = useState('');
  const [newPassword, setNewPassword] = useState('');
  const [users,setUsers] = useState([]);
  const [isLoggedIn, setIsLoggedIn] = useState(false);
  const [currentuser,setCurrentuser] = useState({
    id : 0,
    firstName : '',
    lastName : '',
    email : '',
    phoneNumber : '',
    dob : '',
    password : '',
  });
  

  useEffect(() => {
    getUsers();
  },[])

  const getUsers = async () => {
    try{
      const response = await fetch('http://localhost:9200/users/getUsers');
      if(!response.ok){
        console.log(response);
      }
      else{
        const gettingusers = await response.json();
        console.log(gettingusers)
        setUsers(gettingusers);
      }
    }catch(e){
      console.log(e);
    }
    
  } 

  const headers = new Headers();
  headers.append('Content-Type', 'application/json')

  const addUser = async (user) => {
  
    console.log(user);
    const findUser = users.find(u => u.phoneNumber === user.phoneNumber);
    if(findUser){
      return false;
    }
    const response = await fetch('http://localhost:9200/users/addUser',{
      method : 'POST',
      headers : headers,
      body : JSON.stringify(user)
    });
    if(!response.ok){
      console.log(response)
    }
    const newUser = await response.json()
    setUsers([...users,newUser]);
    return true;
  }

  const login = (phoneNumber, password) => {
    const finduser = users.find(u => u.phoneNumber=== phoneNumber && u.password === password)
    if( finduser){
      setCurrentuser(finduser);
      setIsLoggedIn(true);
      return true;
    }
    else {
      console.log('Invalid Credentials')
      setIsLoggedIn(false)
      return false;
    }
    
  } 


  const handleForgotValue = (fv) => {
    const findUser = users.find(u => u.phoneNumber === fv);
    if(findUser){
      setForgotValue(fv);
      sendOtp();
      return true;
    }
    else{
      return false;
    }
  }

  const handleSetNewPassword = async(np) => {
    let findUser = users.find(u => u.phoneNumber === forgotValue);
    findUser.password = np;
    setNewPassword(np);
    const response = await fetch('http://localhost:9200/users/changePassword',{
      method : 'PUT',
      headers : headers,
      body : JSON.stringify(findUser)
    })

    setUsers((prevUsers) =>
      prevUsers.map((user) =>
        user.id === findUser.id ? { ...user, password: newPassword } : user
      )
    );
    console.log(np);
    setTimeout(() => {
      console.log(users);
    },5000)
  }
  const sendOtp = () => {
    
  }

  const logout = () => {
    setCurrentuser({
      id : 0,
      firstName : '',
      lastName : '',
      email : '',
      phoneNumber : '',
      dob : '',
      password : ''
    })
    setIsLoggedIn(false);
  }

  return (
    <Router>
      <Header isLoggedIn={isLoggedIn} logout={logout}/>
      <Routes>
        <Route path="/" element={<Home isLoggedIn = {isLoggedIn}/>} />
        <Route path='/account-overview' element={<AccountsOverview isLoggedIn={isLoggedIn} phoneNumber={currentuser.phoneNumber}/>}/>
        <Route path='/creditcardHome' element={<CreditcardHome phoneNumber={currentuser.phoneNumber}/>}/>
        <Route path="/login" element={<Login login={login}/>} />
        <Route path="/signup" element={<Signup addUser={addUser}/>} />
        <Route path="/forgot" element={<ForgotPassword forgotValueChange={(fv) => handleForgotValue(fv)} />} />
        <Route path="/change" element={<ChangePassword handlesetNewPassword={(np) => handleSetNewPassword(np)}/>}/>
        <Route path="/transfer" isLoggedIn={isLoggedIn} element={<MoneyTransfer/>}/>
        <Route path="/addaccount" element={<AddAccount/>}/>
        <Route path="/contact" element={<Contact/>}/>


        {/* Add more routes here */}
      </Routes>
      {/* <Footer/> */}
    </Router>
  );
};

export default App;
