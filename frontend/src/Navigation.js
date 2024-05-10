import React from 'react';
import { Link } from 'react-router-dom';

function Navigation() {
    return (
        <nav>
            <ul>
                <li><Link to="/">Attendance Records</Link></li>
                <li><Link to="/users">Users</Link></li>
                <li><Link to="/roles">Roles</Link></li>
            </ul>
        </nav>
    );
}

export default Navigation;
