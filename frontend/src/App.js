// src/App.js
import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import ErrorBoundary from './components/ErrorBoundary';
import ProtectedRoute from './components/ProtectedRoute';
import Navigation from './components/Navigation';
import AttendanceList from './pages/AttendanceList';
import EditAttendance from './pages/EditAttendance';
import UserList from './pages/UserList';
import RoleList from './pages/RoleList';
import LoginPage from './pages/LoginPage';

function App() {
    const isAuthenticated = true; // This should be dynamically set based on your auth logic

    return (
        <Router>
            <ErrorBoundary>
                <Navigation />
                <Routes>
                    <Route path="/" element={<AttendanceList />} />
                    <Route path="/edit/:id" element={<EditAttendance />} />
                    <Route path="/users" element={
                        <ProtectedRoute isAuthenticated={isAuthenticated}>
                            <UserList />
                        </ProtectedRoute>
                    } />
                    <Route path="/roles" element={
                        <ProtectedRoute isAuthenticated={isAuthenticated}>
                            <RoleList />
                        </ProtectedRoute>
                    } />
                    <Route path="/login" element={<LoginPage onLogin={() => {}} />} />
                </Routes>
            </ErrorBoundary>
        </Router>
    );
}

export default App;
