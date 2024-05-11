// src/App.js
import React from 'react';
import { BrowserRouter as Router, Routes, Route, Navigate } from 'react-router-dom';
import { UserProvider } from './contexts/UserContext';
import LoginPage from './pages/LoginPage';
import DashboardPage from './pages/DashboardPage';
import AttendanceList from './pages/AttendanceList';
import MarkAttendance from './pages/MarkAttendance';
import UpdateAttendance from './pages/UpdateAttendance';
import UserList from './pages/UserList';        // Make sure to import UserList
import RoleList from './pages/RoleList';  
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
                    <Route path="/users" element={<UserList />} />          // Add route for UserList
                    <Route path="/roles" element={<RoleList />} />          // Add route for RoleList
                    {/* Other routes */}
                </Routes>
            </Router>
        </UserProvider>
    );
}

export default App;
