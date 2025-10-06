# 🧩 DeveloperSkillAPI

**Java Spring Boot REST API**

A backend project built to demonstrate clean API design, object-oriented programming principles, and JSON data handling in a maintainable architecture.

## 🚀 Features
- **RESTful API Endpoints** — Designed using Spring Boot and MVC patterns.  
- **OOP Principles** — Clear separation of concerns between controllers, services, and models.  
- **JSON Integration** — Consistent request and response formatting with automatic serialization/deserialization.  
- **Scalable Architecture** — Easy to extend with new features and modules.  
- **Error Handling** — Built-in exception responses for cleaner debugging and API usage.  

## 🛠️ Tech Stack
- **Language:** Java  
- **Framework:** Spring Boot  
- **Build Tool:** Maven / Gradle  
- **Data Format:** JSON  
- **Testing:** JUnit / Postman

  
## 📬 Example API Calls

| **Method** | **Endpoint** | **Description** |
|-------------|--------------|-----------------|
| `GET` | `/api/developers` | Fetch all developers |
| `GET` | `/api/developers/{id}` | Get a developer by ID |
| `POST` | `/api/developers` | Add a new developer |
| `PUT` | `/api/developers/{id}` | Update existing developer |
| `DELETE` | `/api/developers/{id}` | Delete developer by ID |


Sample POST request:
```
{
  "name": "Lily Patamia",
  "title": "Java Developer",
  "skills": ["Spring Boot", "API Design", "JSON"],
  "certifications": ["Oracle Java SE"]
}
```
## 📖 Example Endpoint
```http
GET /api/skills
```
Response:
```json
[
  {
    "id": 1,
    "name": "Java",
    "level": "Intermediate"
  },
  {
    "id": 2,
    "name": "Spring Boot",
    "level": "Beginner"
  }
]
```
## 💡 Purpose

This API showcases backend proficiency, API documentation, and clean architecture principles suitable for developer portfolios or technical interviews.

## 💻 How to Run Locally

1. **Clone the repository**
   ```bash
   git clone https://github.com/<your-username>/DeveloperSkillAPI.git
   cd DeveloperSkillAPI
2. **Build and run the application**
   ```bash
   mvn spring-boot:run
3. **Access the API**
   ```bash
   http://localhost:8080/api/developers
4. **Test endpoints using Postman or curl**
   ```bash
   curl -X GET http://localhost:8080/api/developers

