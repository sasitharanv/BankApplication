import React, { useState } from 'react';
import Nav from '../components/Nav';

function Contact() {
  const [formData, setFormData] = useState({
    name: '',
    email: '',
    subject: '',
    mobileNumber: '',
    message: '',
  });

  const [mobileNumberError, setMobileNumberError] = useState('');

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };

  const validateMobileNumber = (value) => {
    const mobilePattern = /^\d{10}$/; // 10-digit pattern
    return mobilePattern.test(value);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    // Validate the mobile number before submission
    if (!validateMobileNumber(formData.mobileNumber)) {
      setMobileNumberError('Mobile number must be 10 digits');
      return;
    }

    try {
      const response = await fetch('http://localhost:8080/contactus', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        credentials: 'include',
        body: JSON.stringify(formData),
      });

      if (response.ok) {
        setFormData({
          name: '',
          email: '',
          subject: '',
          mobileNumber: '',
          message: '',
        });
        setMobileNumberError('');
        console.log('Message sent successfully');
      } else {
        console.error('Error sending message');
      }
    } catch (error) {
      console.error('Error:', error);
    }
  };

  return (
    <div>
      <Nav />
      <div className=" contact-us mt-3">
        <h1>Contact Us</h1>
        <form onSubmit={handleSubmit}>
          <div className="form-group">
            <label htmlFor="name">Name</label>
            <input
              type="text"
              id="name"
              name="name"
              value={formData.name}
              onChange={handleChange}
              required
            />
          </div>
          <div className="form-group">
            <label htmlFor="email">Email</label>
            <input
              type="email"
              id="email"
              name="email"
              value={formData.email}
              onChange={handleChange}
              required
            />
          </div>
          <div className="form-group">
            <label htmlFor="subject">Subject</label>
            <input
              type="text"
              id="subject"
              name="subject"
              value={formData.subject}
              onChange={handleChange}
              required
            />
          </div>
          <div className="form-group">
            <label htmlFor="mobileNumber">Mobile Number</label>
            <input
              type="text"
              id="mobileNumber"
              name="mobileNumber"
              value={formData.mobile_number}
              onChange={handleChange}
              required
              pattern="\d{10}" // 10-digit pattern
              title="Mobile number must be 10 digits" // Error message
            />
            {mobileNumberError && (
              <div className="error-message">{mobileNumberError}</div>
            )}
          </div>
          <div className="form-group">
            <label htmlFor="message">Message</label>
            <textarea
              id="message"
              name="message"
              value={formData.message}
              onChange={handleChange}
              required
            ></textarea>
          </div>
          <button type="submit">Submit</button>
        </form>
      </div>
    </div>
  );
}

export default Contact;
