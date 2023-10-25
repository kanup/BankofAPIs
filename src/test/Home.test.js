import React from 'react';
import { render, screen } from '@testing-library/react';
import { MemoryRouter } from 'react-router-dom'; // MemoryRouter is used for testing routing
import Home from './Home';

test('renders the welcome message', () => {
  render(<Home isLoggedIn={false} />, { wrapper: MemoryRouter }); // isLoggedIn is false
  const welcomeMessage = screen.getByText('Welcome to Bank of APIs');
  expect(welcomeMessage).toBeInTheDocument();
});

test('displays account statements feature', () => {
  render(<Home isLoggedIn={true} />, { wrapper: MemoryRouter }); // isLoggedIn is true
  const accountStatementsFeature = screen.getByText('Account Statements');
  expect(accountStatementsFeature).toBeInTheDocument();
  const exploreButton = screen.getByText('Explore');
  expect(exploreButton).toHaveAttribute('href', '/account-overview');
});

test('displays credit card feature', () => {
  render(<Home isLoggedIn={true} />, { wrapper: MemoryRouter }); // isLoggedIn is true
  const creditCardFeature = screen.getByText('Credit Card');
  expect(creditCardFeature).toBeInTheDocument();
  const exploreButton = screen.getByText('Explore');
  expect(exploreButton).toHaveAttribute('href', '/creditcardHome');
});
