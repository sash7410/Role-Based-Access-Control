import React, { useEffect, useState } from 'react';
import axios from 'axios';

function AttendanceList() {
  const [attendances, setAttendances] = useState([]);
  const [error, setError] = useState("");

  useEffect(() => {
    const userId = 1; // Example user ID, adjust based on your actual logic/application needs
    axios.get(`http://localhost:8765/attendance?userId=${userId}`)
        .then(response => {
            if (response.data.success) {
                setAttendances(response.data.success);
            } else if (response.data.error) {
                setError(response.data.error.message);
            }
        })
        .catch(error => {
            console.error('Error fetching data:', error);
            setError(error.message || "Unknown error");
        });
}, []);


  return (
    <div>
      <h1>Attendance Records</h1>
      {error && <p className="error">Error: {error}</p>}
      <ul>
        {attendances.map((attendance, index) => (
          <li key={index}>{attendance.userName} - {attendance.date} - {attendance.status ? 'Present' : 'Absent'}</li>
        ))}
      </ul>
    </div>
  );
}

export default AttendanceList;
