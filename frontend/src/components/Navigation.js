// src/components/Navigation.js
import React from 'react';
import { Link } from 'react-router-dom';

function Navigation() {
    return (
        <nav>
            <ul>
                <li><Link to="/dashboard">Dashboard</Link></li>
                <li><Link to="/attendance">View Attendance</Link></li>
                <li><Link to="/mark-attendance">Mark Attendance</Link></li>
                <li><Link to="/update-attendance">Update Attendance</Link></li>
                <li><Link to="/users">Users</Link></li>     
                <li><Link to="/roles">Roles</Link></li>  
                <li><Link to="/login">Login</Link></li>
            </ul>
        </nav>
    );
}

export default Navigation;
