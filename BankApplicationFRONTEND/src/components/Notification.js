import React, { useState, useEffect } from 'react';

function Notification() {
  const [showNotifications, setShowNotifications] = useState(false);
  const [notifications, setNotifications] = useState([]);
  const [loading, setLoading] = useState(false);

  // Function to fetch notifications when the "Notifications" button is clicked
  const Notification = () => {
    setLoading(true);
    // Replace with your backend API endpoint
    fetch('http://localhost:8080/notices')
      .then((response) => response.json())
      .then((data) => {
        setNotifications(data);
        setLoading(false);
      })
      .catch((error) => {
        console.error('Error fetching notifications:', error);
        setLoading(false);
      });
  };

  useEffect(() => {
    if (showNotifications) {
      fetchNotifications();
    }
  }, [showNotifications]);

  return (
    <div className="navbar">
      <button onClick={() => setShowNotifications(!showNotifications)}>
        Notifications
      </button>
      {showNotifications && (
        <div className="notifications">
          <h3>Notifications</h3>
          {loading ? (
            <p>Loading notifications...</p>
          ) : notifications.length > 0 ? (
            notifications.map((notification) => (
              <div key={notification.id} className="notification">
                {notification.message}
              </div>
            ))
          ) : (
            <p>No notifications to display.</p>
          )}
        </div>
      )}
    </div>
  );
}

export default Notification;
