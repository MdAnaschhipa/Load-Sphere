# RouteProfit Backend ⚙️

A Java Servlet based REST API backend for the RouteProfit Calculator application.

## 🔗 Related Repository
- Frontend: [routeprofit-frontend](https://github.com/anas0710/routeprofit-frontend)

## 🚀 Features
- REST API for route profit calculation
- Load and delivery management
- Fuel and cost tracking
- User authentication with encrypted passwords
- Profit history storage

## 🛠️ Tech Stack
- Java (Servlets)
- JDBC
- MySQL
- Apache Tomcat
- Gson (JSON parsing)
- jBCrypt (password hashing)

## ⚙️ Setup & Installation

### Prerequisites
- JDK 8+
- Apache Tomcat 9+
- MySQL 8+
- Eclipse IDE (Dynamic Web Project)

### Steps

1. Clone the repository
   git clone https://github.com/anas0710/rpc-backend.git

2. Open Eclipse → File → Import → Existing Projects into Workspace
   Select the cloned folder

3. Add required JAR files to WEB-INF/lib/
   - mysql-connector-j.jar
   - gson.jar
   - jbcrypt.jar

4. Create MySQL database
   CREATE DATABASE rpc_db;

5. Configure DB connection
   Open src/main/java/com/rpc/util/DBConnection.java
   Update the following:
   - DB URL
   - Username
   - Password

6. Deploy on Tomcat
   Right click project → Run As → Run on Server
   Select Apache Tomcat

7. Backend runs on
   http://localhost:8080

## 📁 Project Structure
src/main/java/com/rpc/
├── dao/          # Database access layer
├── model/        # Data models
├── servlet/      # API endpoints
└── util/         # DB connection utility

## 🔌 API Endpoints
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | /LoginServlet | User login |
| POST | /RegisterServlet | User registration |
| POST | /CalculateServlet | Calculate profit |
| GET | /LoadListServlet | Get all loads |
| POST | /SaveHistoryServlet | Save profit history |
| GET | /AdminServlet | Admin operations |
| POST | /LogoutServlet | User logout |