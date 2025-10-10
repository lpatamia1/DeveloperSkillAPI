<div align="center">
  
# 🧩 Developer Skill API

### A Clean, Scalable Java Spring Boot REST API  

![Java](https://img.shields.io/badge/Java-006400?style=for-the-badge&logo=java&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-006400?style=for-the-badge&logo=springboot&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-006400?style=for-the-badge&logo=apachemaven&logoColor=white)
![JSON](https://img.shields.io/badge/JSON-006400?style=for-the-badge&logo=json&logoColor=white)

</div>

---

<div align="center">

## 📖 Overview

</div>

**DeveloperSkillAPI** is a backend project built with **Spring Boot** to demonstrate clean API architecture, **OOP design principles**, and structured **JSON data handling**.  
It’s designed for learning, interviews, or as part of a developer portfolio to showcase backend proficiency and RESTful design.

---
<div align="center">

## 🚀 Features
</div>

- **RESTful Endpoints** — Organized using MVC patterns  
- **OOP Architecture** — Separation between controllers, services, and models  
- **JSON Integration** — Automatic serialization/deserialization for API communication  
- **Error Handling** — Custom exception responses for better debugging  
- **Extensibility** — Built to easily add new endpoints or modules  

---

<div align="center">

## 🛠️ Tech Stack

</div>  

<div align="center">

<table>
  <tr>
    <th>Category</th>
    <th>Technology</th>
  </tr>
  <tr>
    <td><b>Language</b></td>
    <td>Java</td>
  </tr>
  <tr>
    <td><b>Framework</b></td>
    <td>Spring Boot</td>
  </tr>
  <tr>
    <td><b>Build Tool</b></td>
    <td>Maven</td>
  </tr>
  <tr>
    <td><b>Data Format</b></td>
    <td>JSON</td>
  </tr>
  <tr>
    <td><b>Testing</b></td>
    <td>JUnit, Postman</td>
  </tr>
</table>

</div>

---

<div align="center">

## 📬 API Endpoints

</div>

| Method | Endpoint | Description |
|--------|-----------|-------------|
| GET | `/api/developers` | Fetch all developers |
| GET | `/api/developers/{id}` | Retrieve a developer by ID |
| POST | `/api/developers` | Add a new developer |
| PUT | `/api/developers/{id}` | Update an existing developer |
| DELETE | `/api/developers/{id}` | Delete a developer |

---

<div align="center">

## 🧠 Example Request & Response

</div>

```json
{
  "name": "Lily Patamia",
  "title": "Java Developer",
  "skills": ["Spring Boot", "API Design", "JSON"],
  "certifications": ["Oracle Java SE"]
}

```
<div align="center">

## 📖 Example Response

</div>

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
---

<div align="center">

## 💡 Purpose

</div>

This API showcases backend proficiency, API documentation, and clean architecture principles suitable for developer portfolios or technical interviews.

---

<div align="center">

## 💻 How to Run Locally

</div>

1. **Clone the repository**
   ```bash
   git clone https://github.com/lpatamia1/DeveloperSkillAPI.git
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
   ```
Test endpoints using Postman or cURL:
```bash
curl -X GET http://localhost:8080/api/developers
```
---

<div align="center"> <sub>Built with ☕, 💙, and clean architecture principles.</sub> </div> 
