import React, { useEffect, useState } from 'react';
import axios from 'axios';

function RoleList() {
    const [roles, setRoles] = useState([]);
    const [error, setError] = useState("");

    useEffect(() => {
        axios.get('http://localhost:8765/roles-permissions/roles')
            .then(response => {
                if (response.data.success) {
                    setRoles(response.data.success);
                } else if (response.data.error) {
                    setError(response.data.error.message); 
                }
            })
            .catch(error => {
                console.error('Error fetching roles:', error);
                setError(error.message || "Unknown error fetching roles");
            });
    }, []);

    return (
        <div>
            <h1>Roles</h1>
            {error && <p style={{ color: 'red' }}>{error}</p>}
            <ul>
                {Array.isArray(roles) ? roles.map(role => (
                    <li key={role.roleId}>
                        <strong>{role.name}</strong>
                        <ul>
                            {role.permissions.map(permission => (
                                <li key={permission.permissionId}>{permission.permissionName}</li>
                            ))}
                        </ul>
                    </li>
                )) : <li>No roles found or roles data is not available.</li>}
            </ul>
        </div>
    );
}

export default RoleList;
