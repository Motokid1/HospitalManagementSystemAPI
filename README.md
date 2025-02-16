# Hithro Hospital Management Role-Based Access Control API

## Overview
The Hithro Role-Based Access Control API is a RESTful service designed to manage user roles and permissions within a healthcare system. It allows administrators to register users and assign them roles such as Patient, Receptionist, Doctor, Director, and Admin. The API enables role-based access to different functionalities.

## Base URL
```
http://localhost:2024
```

## Endpoints

### Admin Controller
| Method | Endpoint | Description |
|--------|-------------------------------|------------------------------|
| PUT | `/api/hithro/admin/update/{id}` | Update an user by ID |
| POST | `/api/hithro/admin/register` | Register a new user |
| GET | `/api/hithro/admin/getAll` | Retrieve all users |
| GET | `/api/hithro/admin/get/{id}` | Get user details by ID |
| DELETE | `/api/hithro/admin/delete/{id}` | Delete an user by ID |

### Receptionist Controller
| Method | Endpoint | Description |
|--------|----------------------------------|---------------------------------|
| POST | `/api/hithro/reception/addPatient` | Add a new patient |

### Patient Controller
| Method | Endpoint | Description |
|--------|--------------------------------|-------------------------------|
| GET | `/api/hithro/patient/viewPatient/{patientId}` | View patient details by ID |

### Doctor Controller
| Method | Endpoint | Description |
|--------|---------------------------------|--------------------------------|
| GET | `/api/hithro/doctor/viewPatient/{patientId}` | View patient details by ID |
| GET | `/api/hithro/doctor/viewAllPatients` | View all patients |

### Director Controller
| Method | Endpoint | Description |
|--------|--------------------------------|------------------------------|
| GET | `/api/hithro/director/viewPatient/{patientId}` | View patient details by ID |
| GET | `/api/hithro/director/viewDoctor/{doctorId}` | View doctor details by ID |
| GET | `/api/hithro/director/viewAllPatients` | View all patients |
| GET | `/api/hithro/director/viewAllDoctors` | View all doctors |
| DELETE | `/api/hithro/director/deletePatient/{userId}` | Delete a patient by ID |
| DELETE | `/api/hithro/director/deleteDoctor/{userId}` | Delete a doctor by ID |

## Data Models

### Admin
```json
{
  "id": "UUID",
  "user": User
}
```

### Director
```json
{
  "id": "UUID",
  "user": User,
  "department": "String"
}
```

### Doctor
```json
{
  "id": "UUID",
  "user": User,
  "specialisation": "String"
}
```

### Patient
```json
{
  "id": "UUID",
  "user": User,
  "medicalHistory": "String",
  "currentMedications": "String",
  "primaryDoctor": "UUID"
}
```

### Receptionist
```json
{
  "id": "UUID",
  "user": User,
  "shiftTimings": "String"
}
```

### User
```json
{
  "userId": "UUID",
  "email": "String",
  "password": "String",
  "name": "String",
  "phno": "String",
  "dob": "String",
  "address": "String",
  "doctor": Doctor,
  "admin": Admin,
  "director": Director,
  "patient": Patient,
  "receptionist": Receptionist,
  "role": "String"
}
```

## Setup & Run Instructions
1. Clone the repository:
   ```sh
   git clone <repo-url>
   ```
2. Navigate to the project directory:
   ```sh
   cd hithro-api
   ```
3. Build and run the Spring Boot application:
   ```sh
   mvn spring-boot:run
   ```
4. Access Swagger UI for API documentation:
   ```
   http://localhost:2024/swagger-ui.html
   ```

## License
This project is licensed under the MIT License.

