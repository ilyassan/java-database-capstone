# Smart Clinic Management System Architecture

## Architecture Summary
The Smart Clinic Management System is a Spring Boot application leveraging a three-tier architecture. The presentation tier uses Thymeleaf templates for server-rendered Admin and Doctor dashboards and REST APIs for modular components like appointments and patient records. The application tier, built with Spring Boot, includes controllers, services, and business logic, ensuring a clear separation of concerns. The data tier integrates MySQL for structured data (patients, doctors, appointments) and MongoDB for flexible prescription records. Controllers delegate to a unified service layer, which interacts with MySQL (via Spring Data JPA) and MongoDB (via Spring Data MongoDB) repositories, enabling scalable and maintainable data operations.

## Numbered Flow of Data and Control
1. Users access the system via Thymeleaf-based dashboards (Admin/Doctor) or REST API clients (e.g., mobile apps for appointments).
2. Requests are routed to appropriate controllers: Thymeleaf controllers for server-rendered views or REST controllers for JSON responses.
3. Controllers validate inputs and delegate business logic to the service layer.
4. The service layer applies business rules, coordinates workflows, and communicates with repositories.
5. Repositories (JPA for MySQL, MongoDB for prescriptions) handle data access and persistence.
6. Data is mapped to Java models: JPA entities for MySQL, document objects for MongoDB.
7. Models are used to render Thymeleaf templates for dashboards or serialized to JSON for REST API responses.
