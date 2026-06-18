# RouteProfit Frontend 🚛

A modern Angular web application for calculating and tracking profitability of transport/truck routes.

## 🔗 Related Repository
- Backend: [rpc-backend](https://github.com/anas0710/rpc-backend)

## 🚀 Features
- Calculate profit/loss for truck routes
- Manage and track loads and deliveries
- Track fuel costs and trip expenses
- View profit history and dashboard
- User authentication (Login/Register)

## 🛠️ Tech Stack
- Angular 17+
- TypeScript
- HTML5 / CSS3

## ⚙️ Setup & Installation

### Prerequisites
- Node.js (v18+)
- Angular CLI

### Steps
1. Clone the repository
   git clone https://github.com/anas0710/routeprofit-frontend.git

2. Navigate to the project folder
   cd routeprofit-frontend

3. Install dependencies
   npm install

4. Run the development server
   ng serve

5. Open in browser
   http://localhost:4200

> ⚠️ Make sure the backend server is running on port 8080 before using the app.

## 📁 Project Structure
src/
├── app/
│   ├── core/
│   │   ├── guards/       # Auth guards
│   │   └── services/     # API services
│   ├── models/           # Data models
│   └── pages/
│       ├── calculator/   # Profit calculator
│       ├── dashboard/    # Main dashboard
│       ├── history/      # Profit history
│       ├── load-list/    # Load management
│       ├── login/        # Authentication
│       └── register/     # User registration