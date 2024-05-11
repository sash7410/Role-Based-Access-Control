import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { useUser } from '../contexts/UserContext';
import { Link } from 'react-router-dom';

function AttendanceList() {
    const { user } = useUser();
    const [attendances, setAttendances] = useState([]);
    const [error, setError] = useState("");
    const [loaded, setLoaded] = useState(false);  // State to control display of data

    const fetchAttendance = async (mode) => {
        let url = 'http://localhost:8765/attendance';
        if (mode === "user" && user && user.userId) {
            url += `/user?userId=${user.userId}`;
        } else if (mode === "all") {
            url += `?userId=${user.userId}`;  // Assuming you need a user ID even when fetching all
        }

        try {
            const response = await axios.get(url);
            setAttendances(response.data.success);
            setLoaded(true);  // Set loaded to true to display the data
        } catch (error) {
            console.error('Error fetching data:', error);
            setError(error.message || "Unknown error fetching attendance");
            setAttendances([]);  // Clear the attendances
            setLoaded(false);
        }
    };

    const handleViewChange = (mode) => {
        setAttendances([]);  // Clear the previous attendances
        setError("");        // Clear any previous errors
        setLoaded(false);    // Reset loaded state
        fetchAttendance(mode);
    };

    return (
        <div>
            <h1>Attendance Records</h1>
            <button onClick={() => handleViewChange("user")}>View My Attendance</button>
            <button onClick={() => handleViewChange("all")}>View All Attendances</button>
            {error && <p className="error">Error: {error}</p>}

            {!loaded && <div>Please select an option to view attendance records.</div>}

            {loaded && attendances.length > 0 && (
                <ul>
                    {attendances.map((attendance, index) => (
                        <li key={index}>
                            {attendance.userName} - {attendance.date} - {attendance.status ? 'Present' : 'Absent'}
                            <Link to={`/update-attendance/${attendance.attendanceId}`}>Update</Link>
                        </li>
                    ))}
                </ul>
            )}

            {loaded && attendances.length === 0 && <div>No attendance records found.</div>}

            <div>
                <Link to="/mark-attendance">Mark New Attendance</Link>
            </div>
        </div>
    );
}

export default AttendanceList;
