# ColdChain Portal

A modern, production-ready dashboard for managing vaccine inventory and logistics, built with a focus on modularity, testability, and clean code principles.

## ✨ Features
* **Vaccine CRUD**: Create, Read, Update, and Delete vaccine batches.
* **Modern UI**: Built with React, Vite, and Tailwind CSS for a responsive, clean interface.
* **Data Visualization**: Pie chart representation of vaccine type distribution using Chart.js.
* **Architecture**: Built using **Hexagonal Architecture (Ports and Adapters)** for separation of concerns.
* **Methodology**: Developed using **Test-Driven Development (TDD)** for high code quality and reliability.
* **Database**: PostgreSQL for persistent data storage.

## 🛡️ Design Principles & Compliance

### SOLID Principles Breakdown
* **SRP (Single Responsibility Principle)**: VaccineRecord only holds data; VaccineValidator only checks health rules; AuditLogger only handles history.
* **OCP (Open/Closed Principle)**: We use an ITemperatureProvider interface. If you move from manual entry to IoT sensors, you add a new class without touching existing code.
* **LSP (Liskov Substitution Principle)**: UltraLowFreezer and StandardFridge both inherit from StorageUnit. Any method accepting StorageUnit must work with both without crashing.
* **ISP (Interface Segregation Principle)**: Instead of one giant IBatchManager, we use IReadable, IWriteable, and IDeletable. The Auditor actor only sees the IReadable interface.
* **DIP (Dependency Inversion Principle)**: High-level business logic depends on IBatchRepository, not a specific SQL or MongoDB implementation.

### GRASP Patterns Application
* **Information Expert**: The VaccineBatch knows its own expiryDate. Therefore, the method isViable() belongs inside VaccineBatch.
* **Creator**: The StorageUnit creates a TemperatureLog, because it "contains" or aggregates the logs.
* **Controller**: A VaccineProcessController handles system events (like addNewBatch()) so the UI doesn't talk directly to the database.
* **Low Coupling**: Classes interact through interfaces. StorageUnit doesn't need to know how a VaccineBatch is saved.
* **High Cohesion**: The AuditService does only auditing.
* **Polymorphism**: Handling different vaccine types (mRNA vs. Live Attenuated) via a shared interface rather than if/else blocks.
* **Pure Fabrication**: An ArchiveService handles the "Soft Delete" logic to keep the Vaccine class clean.
* **Indirection**: Using a Repository pattern to sit between the Controller and the Data.
* **Protected Variations**: Wrapping the HIPAA encryption logic in a stable interface so changes in security laws don't break the whole app.

## 🖼️ Application Screenshot

> <img width="1467" height="867" alt="image" src="https://github.com/user-attachments/assets/2f51c291-19ce-4146-bfe0-5909a040b712" />

## 🏗️ Architectural Diagrams
*Insert Mermaid diagrams below to visualize the architecture.*

## UML Class Diagram:
<img width="1254" height="770" alt="image" src="https://github.com/user-attachments/assets/1c1ae4b0-52b8-47ad-9372-efaf2bcbdb26" />

## High Level System Design:
<img width="1417" height="332" alt="image" src="https://github.com/user-attachments/assets/1490b7ea-108c-4a59-a7b4-d3bbe3e6e0d8" />

## 🚀 Setup & Installation

### Prerequisites
* Node.js (for frontend)
* Java 25 JDK (for backend)
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

