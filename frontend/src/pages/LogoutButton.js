// // src/components/LogoutButton.js
// import React from 'react';
// import { useUser } from '../contexts/UserContext';
// import { useNavigate } from 'react-router-dom';

// function LogoutButton() {
//     const { logout } = useUser();
//     const navigate = useNavigate();

//     const handleLogout = () => {
//         logout();
//         navigate('/login'); // Redirect to the login page after logout
//     };

//     return (
//         <button onClick={handleLogout}>Logout</button>
//     );
// }

// export default LogoutButton;
