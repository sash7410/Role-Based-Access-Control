import React, { useEffect, useState } from 'react';
import axios from 'axios';

function UserList() {
    const [users, setUsers] = useState([]);
    const [error, setError] = useState(""); // To handle potential errors more gracefully

    useEffect(() => {
        axios.get('http://localhost:8765/users')
            .then(response => {
                if (response.data.success) { // Checking if the success field is present
                    setUsers(response.data.success); // Assuming success contains the array of users
                } else if (response.data.error) {
                    setError(response.data.error.message); // Handling potential errors by setting an error message
                }
            })
            .catch(error => {
                console.error('Error fetching users:', error);
                setError(error.message || "Unknown error fetching users");
            });
    }, []);

    return (
        <div>
            <h1>Users</h1>
            {error && <p style={{ color: 'red' }}>{error}</p>}
            <ul>
                {Array.isArray(users) ? users.map(user => (
                    <li key={user.userId}>
                        <strong>{user.username}</strong> 
                        <ul>
                            {user.roles.map(role => (
                                <li key={role.roleId}>
                                    {role.name} 
                                    <ul>
                                        {role.permissions.map(permission => (
                                            <li key={permission.permissionId}>{permission.permissionName} </li>
                                        ))}
                                    </ul>
                                </li>
                            ))}
                        </ul>
                    </li>
                )) : <p>No users found or data is not available.</p>}
            </ul>
        </div>
    );
}

export default UserList;
