// src/pages/UserList.js
import React, { useEffect, useState, useContext } from 'react';
import axios from 'axios';
import { useUser } from '../contexts/UserContext'; // Import the context to access the user data

function UserList() {
    const { user } = useUser(); // Get the current user from context
    const [userData, setUserData] = useState(null);
    const [error, setError] = useState("");

    useEffect(() => {
        if (user && user.userId) {
            axios.get(`http://localhost:8765/users/${user.userId}`)
                .then(response => {
                    if (response.data.success) {
                        setUserData(response.data.success);
                    } else if (response.data.error) {
                        setError(response.data.error.message);
                    }
                })
                .catch(error => {
                    console.error('Error fetching user:', error);
                    setError(error.message || "Unknown error fetching user");
                });
        }
    }, [user]); // Depend on user context to re-fetch when it updates

    return (
<div>
            <h1>User Details</h1>
            {error && <p style={{ color: 'red' }}>{error}</p>}
            {userData ? (
                <div>
                    <strong>{userData.username}</strong> 
                    <ul>
                        {userData.roles.map(role => (
                            <li key={role.roleId}>
                                <strong>{role.name}</strong> 
                                <ul>
                                    {role.permissions.map(permission => (
                                        <li key={permission.permissionId}>{permission.permissionName}</li>
                                    ))}
                                </ul>
                            </li>
                        ))}
                    </ul>
                </div>
            ) : <p>No user data found or data is not available.</p>}
        </div>
    );
}

export default UserList;
