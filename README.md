# Role-Based-Access-Control Attendance Management System
This Attendance Management System is designed to handle role-based access control (RBAC) for users within educational institutions or businesses. The system provides functionalities to manage attendance records, users, roles, and permissions through a web interface.

# Getting Started
# Prerequisites
- Node.js
- npm or yarn
- Java (for Spring Boot)
- Maven (for building the Java project)
- A running instance of the backend server (usually developed with Spring Boot)
- MySql - recommend Sequel ACE in mac
- Clone the repository:

git clone <backend-repo-url>
cd <backend-project-directory>

# Build the project:

mvn clean install
# Start the project:
- start by running the eureka server -> role microservice -> user microservice -> attendance microservice -> api gateway as the tables can be automatically created without any problem
  
mvn spring-boot:run

- Ensure that the server is running on the correct port, typically configured in the application.properties or application.yml files in the Spring Boot project. all the API's can be accesed throgh the gateway port 8765

# Frontend Setup

- Install dependencies:

npm install

- Start the development server:

sql
Copy code
npm start
This will launch the frontend application typically at http://localhost:3000.

- Environment Configuration
- Ensure that the frontend application is configured to communicate with the backend API. This is typically done through environment variables or a configuration file that specifies the backend URL.

After setting up both the backend and frontend:

- Navigate to the login page at http://localhost:3000/login.
- Log in using valid credentials to access the dashboard.
- Explore functionalities such as viewing, marking, and updating attendance, as well as viewing user roles and permissions.

# Features
- User Authentication
- Role-Based Access Control (RBAC)
- Attendance marking and updates
- User and role management
  
# Technology Stack
- Frontend: React, React Router
- Backend: Spring Boot, Java
- Database: MySQL
