// src/components/MarkAttendance.js
import React, { useState } from 'react';
import axios from 'axios';
import { useUser } from '../contexts/UserContext';

function MarkAttendance() {
    const { user } = useUser();
    const [date, setDate] = useState('');
    const [status, setStatus] = useState(false);
    const [message, setMessage] = useState('');

    const handleSubmit = async (event) => {
        event.preventDefault();
        try {
            const response = await axios.post(`http://localhost:8765/attendance?userId=${user.userId}`, {
                userId: user.userId,
                date,
                status
            });
            setMessage('Attendance marked successfully!');
            console.log(response.data);
        } catch (error) {
            setMessage('Failed to mark attendance!');
            console.error('Error:', error);
        }
    };

    return (
        <div>
            <h2>Mark Attendance</h2>
            <form onSubmit={handleSubmit}>
                <input type="date" value={date} onChange={e => setDate(e.target.value)} required />
                <select value={status} onChange={e => setStatus(e.target.value === 'true')}>
                    <option value="true">Present</option>
                    <option value="false">Absent</option>
                </select>
                <button type="submit">Submit</button>
            </form>
            {message && <p>{message}</p>}
        </div>
    );
}

export default MarkAttendance;
