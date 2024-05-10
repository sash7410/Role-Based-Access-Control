import React, { useState } from 'react';
import axios from 'axios';

function LoginPage({ onLogin }) {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [user, setUser] = useState(null); // Store user details after login
    const [error, setError] = useState('');

    const handleLogin = async (event) => {
        event.preventDefault();
        const loginData = { username, password };

        try {
            const response = await axios.post('http://localhost:8765/users/login', loginData);
            setUser(response.data.success); // Store the user data on successful login
            onLogin(response.data); // Optionally handle login at a higher level
            setError(''); // Clear any previous errors
        } catch (error) {
            console.error('Login error:', error);
            setError('Login failed. Check your username and password.');
            setUser(null); // Clear any previous user data
        }
    };

    return (
        <div>
            <h1>Login</h1>
            {!user ? ( // Only show login form if user is not logged in
                <form onSubmit={handleLogin}>
                    <div>
                        <label>Username:</label>
                        <input type="text" value={username} onChange={e => setUsername(e.target.value)} />
                    </div>
                    <div>
                        <label>Password:</label>
                        <input type="password" value={password} onChange={e => setPassword(e.target.value)} />
                    </div>
                    <button type="submit">Login</button>
                    {error && <p style={{ color: 'red' }}>{error}</p>}
                </form>
            ) : (
                <div>
                    <h2>User Details</h2>
                    <p><strong>Username:</strong> {user.username}</p>
                    <div>
                        <strong>Roles:</strong>
                        <ul>
                            {user.roles.map(role => (
                                <li key={role.roleId}>
                                    {role.name}
                                    <ul>
                                        {role.permissions.map(permission => (
                                            <li key={permission.permissionId}>{permission.permissionName}</li>
                                        ))}
                                    </ul>
                                </li>
                            ))}
                        </ul>
                    </div>
                </div>
            )}
        </div>
    );
}

export default LoginPage;
