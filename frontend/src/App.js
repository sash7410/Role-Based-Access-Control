import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import Navigation from './Navigation';
import AttendanceList from './AttendanceList';
import EditAttendance from './EditAttendance';
import UserList from './UserList';
import RoleList from './RoleList';

function App() {
    return (
        <Router>
            <div>
                <Navigation />
                <Routes>
                    <Route path="/" element={<AttendanceList />} />
                    <Route path="/edit/:id" element={<EditAttendance />} />
                    <Route path="/users" element={<UserList />} />
                    <Route path="/roles" element={<RoleList />} />
                </Routes>
            </div>
        </Router>
    );
}

export default App;
