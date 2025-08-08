# Database Schema Design for Smart Clinic Management System

## MySQL Database Design
The MySQL database stores structured data for the Smart Clinic System, using a relational schema to enforce consistency and integrity for core entities.

### Patients Table
- **Purpose**: Stores patient information for identification and contact.
- **Columns**:
  - `patient_id` (BIGINT, PRIMARY KEY, AUTO_INCREMENT) - Unique identifier for each patient.
  - `first_name` (VARCHAR(50), NOT NULL) - Patient's first name.
  - `last_name` (VARCHAR(50), NOT NULL) - Patient's last name.
  - `email` (VARCHAR(100), UNIQUE, NOT NULL) - Contact email, unique to prevent duplicates.
  - `phone` (VARCHAR(15)) - Optional phone number for contact.
- **Constraints**: `email` is unique to ensure no duplicate accounts; `first_name` and `last_name` are mandatory.

### Doctors Table
- **Purpose**: Manages doctor profiles and specialties.
- **Columns**:
  - `doctor_id` (BIGINT, PRIMARY KEY, AUTO_INCREMENT) - Unique identifier for each doctor.
  - `first_name` (VARCHAR(50), NOT NULL) - Doctor's first name.
  - `last_name` (VARCHAR(50), NOT NULL) - Doctor's last name.
  - `specialty` (VARCHAR(100), NOT NULL) - Doctor's medical specialty (e.g., Cardiology).
  - `email` (VARCHAR(100), UNIQUE, NOT NULL) - Contact email, unique for login.
- **Constraints**: `email` is unique; all fields except `doctor_id` are mandatory for complete profiles.

### Appointments Table
- **Purpose**: Tracks scheduled appointments between patients and doctors.
- **Columns**:
  - `appointment_id` (BIGINT, PRIMARY KEY, AUTO_INCREMENT) - Unique identifier for each appointment.
  - `patient_id` (BIGINT, FOREIGN KEY REFERENCES Patients(patient_id), NOT NULL) - Links to patient.
  - `doctor_id` (BIGINT, FOREIGN KEY REFERENCES Doctors(doctor_id), NOT NULL) - Links to doctor.
  - `appointment_date` (DATETIME, NOT NULL) - Date and time of appointment.
  - `status` (ENUM('SCHEDULED', 'COMPLETED', 'CANCELLED'), NOT NULL, DEFAULT 'SCHEDULED') - Appointment status.
- **Constraints**: Foreign keys ensure valid patient and doctor references; `status` uses ENUM for controlled values.

### Admins Table
- **Purpose**: Stores admin user details for system management.
- **Columns**:
  - `admin_id` (BIGINT, PRIMARY KEY, AUTO_INCREMENT) - Unique identifier for each admin.
  - `username` (VARCHAR(50), UNIQUE, NOT NULL) - Unique username for login.
  - `password` (VARCHAR(255), NOT NULL) - Hashed password for security.
  - `email` (VARCHAR(100), UNIQUE, NOT NULL) - Contact email, unique for identification.
- **Constraints**: `username` and `email` are unique to prevent duplicates; all fields are mandatory.

## MongoDB Collection Design
The MongoDB collection handles flexible, unstructured data for prescriptions, allowing nested fields and arrays for dynamic medical records.

### Prescriptions Collection
- **Purpose**: Stores prescription details with flexible structure for medications and instructions.
- **Justification**: MongoDB is chosen for prescriptions due to varying formats (e.g., different medications, dosages, or notes), which benefit from a document-based model.
- **Example Document**:
```json
{
  "_id": "prescription_001",
  "patient_id": 101,
  "doctor_id": 201,
  "issue_date": "2025-08-08T10:00:00Z",
  "medications": [
    {
      "name": "Amoxicillin",
      "dosage": "500mg",
      "frequency": "Twice daily",
      "duration": "7 days"
    },
    {
      "name": "Ibuprofen",
      "dosage": "200mg",
      "frequency": "As needed",
      "duration": "3 days"
    }
  ],
  "notes": "Take with food to avoid stomach upset."
}
```
- **Structure Notes**: The `_id` is a unique identifier; `patient_id` and `doctor_id` link to MySQL tables; `medications` is an array for multiple drugs; `notes` allows free-text instructions.
