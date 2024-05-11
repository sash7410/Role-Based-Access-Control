// src/pages/DashboardPage.js
import React from 'react';
import { Link } from 'react-router-dom';

function DashboardPage() {
    return (
        <div>
            <h1>Dashboard</h1>
            <nav>
                <ul>
                    <li><Link to="/attendance">Attendance</Link></li>
                    <li><Link to="/users">User Info</Link></li>
                </ul>
            </nav>
        </div>
    );
}

export default DashboardPage;
