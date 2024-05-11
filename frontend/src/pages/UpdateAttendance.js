// src/components/UpdateAttendance.js
import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useParams } from 'react-router-dom';

function UpdateAttendance() {
    const { attendanceId } = useParams();  // Using useParams to get the attendanceId from the URL
    const [date, setDate] = useState('');
    const [status, setStatus] = useState(false);
    const [message, setMessage] = useState('');
    const [userId, setUserId] = useState(1);  // Assuming you have a way to get the userId, set this accordingly

    useEffect(() => {
        // Fetch the current details of the attendance to update (optional, for pre-filling form data)
        const fetchCurrentAttendance = async () => {
            try {
                const response = await axios.get(`http://localhost:8765/attendance/${attendanceId}?userId=${userId}`);
                const attendance = response.data.success;
                setDate(attendance.date);
                setStatus(attendance.status);
            } catch (error) {
                setMessage('Failed to fetch attendance details!');
                console.error('Fetch error:', error);
            }
        };

        fetchCurrentAttendance();
    }, [attendanceId, userId]);

    const handleUpdate = async () => {
        try {
            const response = await axios.put(`http://localhost:8765/attendance/${attendanceId}?userId=${userId}`, {
                userId,  // Ensure this is the correct ID for who is updating the record
                date,
                status
            });
            setMessage('Attendance updated successfully!');
        } catch (error) {
            setMessage('Failed to update attendance!');
            console.error('Update error:', error);
        }
    };

    return (
        <div>
            <h2>Update Attendance</h2>
            <input type="date" value={date} onChange={e => setDate(e.target.value)} required />
            <select value={status} onChange={e => setStatus(e.target.value === 'true')}>
                <option value="true">Present</option>
                <option value="false">Absent</option>
            </select>
            <button onClick={handleUpdate}>Update</button>
            {message && <p>{message}</p>}
        </div>
    );
}

export default UpdateAttendance;
