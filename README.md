
# CSVUploader

CSVUploader is a simple backend application built with Java and Spring Boot that allows uploading, storing, and retrieving CSV file data using an in-memory database. This application provides REST APIs for managing CSV records and ensures robust error handling and validation.

---

## Features

- **Upload CSV File**:
  Upload a CSV file, parse its contents, and store the data in an in-memory H2 database.

- **Fetch All Records**:
  Retrieve all stored records from the database.

- **Fetch Record by Code**:
  Fetch a specific record using its unique `code` field.

- **Delete All Records**:
  Clear all records from the database.

- **Validation**:
  Ensures uploaded CSV files meet the required format and validates unique `code` fields.

- **Error Handling**:
  Provides meaningful error messages for bad requests or server errors.

---

## Technologies Used

- **Java 17**
- **Spring Boot**
- **H2 In-Memory Database**
- **Apache Commons CSV**
- **Docker**

---

## Installation and Setup

1. Unzip the repository:
   ```bash
   cd CSVUploader
   ```

2. Run the applications using Docker Compose:
   ```bash
   docker-compose up
   ```

3. Access the application at:
   ```
   http://localhost:8080
   ```

---

## API Endpoints

### 1. **Upload CSV**
   - **Endpoint**: `POST /api/csv/upload`
   - **Description**: Uploads a CSV file and stores its data in the database.
   - **Request Body**: `multipart/form-data` with the `file` field.

### 2. **Fetch All Records**
   - **Endpoint**: `GET /api/csv`
   - **Description**: Retrieves all stored records.
   - **Response**: JSON array of records.

### 3. **Fetch Record by Code**
   - **Endpoint**: `GET /api/csv/{code}`
   - **Description**: Retrieves a specific record using its unique `code`.
   - **Response**: JSON object of the record.

### 4. **Delete All Records**
   - **Endpoint**: `DELETE /api/csv`
   - **Description**: Deletes all records from the database.

---

## Example Usage

### 1. Uploading a CSV File
Use Postman to upload a file:
- **Method**: `POST`
- **URL**: `http://localhost:8080/api/csv/upload`
- **Body**: `form-data` with key `file` and value as the CSV file.

### 2. Fetching All Records
- **Method**: `GET`
- **URL**: `http://localhost:8080/api/csv`

### 3. Fetching a Record by Code
- **Method**: `GET`
- **URL**: `http://localhost:8080/api/csv/{code}`

### 4. Deleting All Records
- **Method**: `DELETE`
- **URL**: `http://localhost:8080/api/csv`

---

## Known Limitations
- CSV files must have valid headers as per the required schema.
- In-memory database data will be lost when the application stops.

---

## Future Enhancements
- Add support for persistent databases (e.g., PostgreSQL, MySQL).
- Provide user authentication for API endpoints.
- Enhance support for larger files and pagination.

---
