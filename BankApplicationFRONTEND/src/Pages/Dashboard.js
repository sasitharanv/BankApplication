import React from 'react';

const Dashboard = ({ isAuthenticated, onLogout }) => {
  return (
    <div>
      <h2>Welcome to the Dashboard</h2>
      <button onClick={onLogout}>Logout</button>
    </div>
  );
};

export default Dashboard;
