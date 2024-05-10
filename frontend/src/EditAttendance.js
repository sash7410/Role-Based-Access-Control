import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useParams, useNavigate } from 'react-router-dom';

function EditAttendance() {
  const { id } = useParams();
  const navigate = useNavigate();
  const [attendance, setAttendance] = useState(null);

  useEffect(() => {
    axios.get(`http://localhost:8765/attendance/${id}`)
      .then(response => {
        setAttendance(response.data);
      })
      .catch(error => console.error('Error fetching data:', error));
  }, [id]);

  const handleSubmit = event => {
    event.preventDefault();
    axios.put(`http://localhost:8765/attendance/${id}`, attendance)
      .then(() => navigate('/'))
      .catch(error => console.error('Error updating data:', error));
  };

  return (
    <div>
      <h1>Edit Attendance</h1>
      {attendance && (
        <form onSubmit={handleSubmit}>
          <label>
            Status:
            <select value={attendance.status} onChange={e => setAttendance({ ...attendance, status: e.target.value === 'true' })}>
              <option value="true">Present</option>
              <option value="false">Absent</option>
            </select>
          </label>
          <button type="submit">Update</button>
        </form>
      )}
    </div>
  );
}

export default EditAttendance;
