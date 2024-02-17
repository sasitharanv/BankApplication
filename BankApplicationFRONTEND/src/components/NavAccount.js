import React from 'react';

function NavAccount() {
  const handleNoticesClick = () => {
    // Call the fetchNotification function when "NOTICES" is clicked
    Notification();
  };

  return (
    <div>
      <nav className="navbar bg-dark border-bottom border-body" data-bs-theme="dark">
        <div className="container-fluid col-md-6">
          <a className="navbar-brand" href="/Home">
            Eazy Bank
          </a>
        </div>
        <div className="container-fluid col-md-6">
          <a className="navbar-brand" href="/Home">
            HOME
          </a>
          <a className="navbar-brand" href="/Login">
            LOGIN
          </a>
          <a className="navbar-brand" href="/Contact">
            CONTACTUS
          </a>
          <a
            className="navbar-brand"
            href="#"
            onClick={handleNoticesClick} // Call the function on click
          >
            NOTICES
          </a>
        </div>
      </nav>
    </div>
  );
}

export default NavAccount;
