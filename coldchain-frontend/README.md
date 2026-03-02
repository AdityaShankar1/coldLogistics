# ColdChain Portal

A modern, production-ready dashboard for managing vaccine inventory and logistics, built with a focus on modularity and testability.

## ✨ Features
* **Vaccine CRUD**: Create, Read, Update, and Delete vaccine batches.
* **Modern UI**: Built with React, Vite, and Tailwind CSS for a responsive, clean interface.
* **Data Visualization**: Pie chart representation of vaccine type distribution using Chart.js.
* **Regulatory Compliance**: Designed to support regulatory requirements for data integrity and traceability.
* **Architecture**: Built using Hexagonal Architecture (Ports and Adapters) for separation of concerns.
* **Development**: Developed using Test-Driven Development (TDD) for high code quality.
* **Database**: PostgreSQL for persistent data storage.

## 🖼️ Application Screenshot

> **[INSERT YOUR IMAGE HERE VIA GITHUB GUI]**

## 🏗️ Technical Stack
* **Backend**: Java, Spring Boot.
* **Frontend**: React, Vite, Tailwind CSS.
* **Database**: PostgreSQL.
* **Chart**: Chart.js, React-chartjs-2.

## 🚀 Setup & Installation

### Prerequisites
* Node.js (for frontend)
* Java JDK (for backend)
* Docker (for PostgreSQL)

### Backend Setup
1.  Navigate to the backend directory.
2.  Run the PostgreSQL container.
3.  Build and run the Spring Boot application.

### Frontend Setup
1.  Navigate to the `coldchain-frontend` directory.
2.  Install dependencies:
    ```bash
    npm install
    ```
3.  Start the development server:
    ```bash
    npm run dev
    ```

## 🛡️ Compliance
* **Security**: Authentication is currently **Disabled**.
* **Data Integrity**: JPA handles database interactions to prevent SQL injection.

