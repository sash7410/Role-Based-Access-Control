// src/App.js
import React from 'react';
import { BrowserRouter as Router, Routes, Route, Navigate } from 'react-router-dom';
import { UserProvider } from './contexts/UserContext';
import LoginPage from './pages/LoginPage';
import DashboardPage from './pages/DashboardPage';
import AttendanceList from './pages/AttendanceList';
import MarkAttendance from './pages/MarkAttendance';
import UpdateAttendance from './pages/UpdateAttendance';
// Other imports...

function App() {
    return (
        <UserProvider>
            <Router>
                <Routes>
                    <Route path="/" element={<Navigate replace to="/login" />} />
                    <Route path="/login" element={<LoginPage />} />
                    <Route path="/dashboard" element={<DashboardPage />} />
                    <Route path="/attendance" element={<AttendanceList />} />
                    <Route path="/mark-attendance" element={<MarkAttendance />} />
                    <Route path="/update-attendance/:attendanceId" element={<UpdateAttendance />} />
                    {/* Other routes */}
                </Routes>
            </Router>
        </UserProvider>
    );
}

export default App;
