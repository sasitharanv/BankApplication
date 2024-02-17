import React from 'react';
import Nav from '../components/Nav';
import { Link } from 'react-router-dom';
import businessloan from '../images/business loan.jpg';
import healthloan from '../images/healthlaon.jpg';
import homeloan from '../images/homeloan.jpg';
import personalloan from '../images/personalloan.jpg';
import studentloan from '../images/studentloan.jpg';

function Home() {
  return (
    <div>
      <Nav Notification={Notification} ></Nav>


      <div className="container fluid mt-3">
        <div className="row">
          <div className="col-md-4 mb-3">
            <div className="card shadow" style={{ width: "300px", height: "400px" }}>
              <Link to="/complete-guidance-ticket" className="btn btn-outline-danger">
                <center>
                  <img
                    src={businessloan}
                    className="card-img-top"
                    style={{ width: "100%", height: "200px", objectFit: "cover" }}
                    alt="Business Loan"
                  ></img>
                </center>
                <div className="card-body">
                  <h6>Business Loan</h6>
                </div>
              </Link>
            </div>
          </div>

          <div className="col-md-4 mb-3">
            <div className="card shadow" style={{ width: "300px", height: "400px" }}>
              <Link to="/complete-guidance-ticket" className="btn btn-outline-danger">
                <center>
                  <img
                    src={studentloan}
                    className="card-img-top"
                    style={{ width: "100%", height: "200px", objectFit: "cover" }}
                    alt="Student Loan"
                  ></img>
                </center>
                <div className="card-body">
                  <h6>Student Loan</h6>
                </div>
              </Link>
            </div>
          </div>

          <div className="col-md-4 mb-3">
            <div className="card shadow" style={{ width: "300px", height: "400px" }}>
              <Link to="/complete-guidance-ticket" className="btn btn-outline-danger">
                <center>
                  <img
                    src={personalloan}
                    className="card-img-top"
                    style={{ width: "100%", height: "200px", objectFit: "cover" }}
                    alt="Personal Loan"
                  ></img>
                </center>
                <div className="card-body">
                  <h6>Personal Loan</h6>
                </div>
              </Link>
            </div>
          </div>
        </div>

        <div className="row">
          <div className="col-md-4 mb-3">
            <div className="card shadow" style={{ width: "300px", height: "400px" }}>
              <Link to="/complete-guidance-ticket" className="btn btn-outline-danger">
                <center>
                  <img
                    src={healthloan}
                    className="card-img-top"
                    style={{ width: "100%", height: "200px", objectFit: "cover" }}
                    alt="Health Loan"
                  ></img>
                </center>
                <div className="card-body">
                  <h6>Health Loan</h6>
                </div>
              </Link>
            </div>
          </div>

          <div className="col-md-4 mb-3">
            <div className="card shadow" style={{ width: "300px", height: "400px" }}>
              <Link to="/complete-guidance-ticket" className="btn btn-outline-danger">
                <center>
                  <img
                    src={homeloan}
                    className="card-img-top"
                    style={{ width: "100%", height: "200px", objectFit: "cover" }}
                    alt="Home Loan"
                  ></img>
                </center>
                <div className="card-body">
                  <h6>Home Loan</h6>
                </div>
              </Link>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}

export default Home;
