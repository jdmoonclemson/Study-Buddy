Study Buddy — Web App
📖 Overview

Study Buddy is a simple Spring Boot web application that helps students:

Create a profile and list enrolled courses.

Add availability slots for when they’re free to study.

Search classmates by course.

Get suggested matches based on overlapping availability.

Request and accept/decline study sessions.

The backend is powered by Spring Boot 3 + JPA + H2 (in-memory database). The frontend is a simple HTML/JavaScript UI served from src/main/resources/static/.

⚙️ Requirements

Java 17+ (works on 17, 21, 23)

Maven 3.8+

IntelliJ IDEA or any IDE with Spring Boot support (optional)

▶️ Running the App

Clone or unzip the project into a folder.

In the project root, run:

mvn spring-boot:run


or run StudyBuddyApplication directly in IntelliJ.

Once you see:

Tomcat started on port(s): 8080


open your browser at:

UI: http://localhost:8080/

H2 Console: http://localhost:8080/h2

JDBC URL: jdbc:h2:mem:studybuddy

User: sa

Password: (empty)

🗂️ Project Structure
study-buddy/
├─ pom.xml                     # Maven project config
├─ src/main/java/com/clemson/studybuddy
│   ├─ StudyBuddyApplication.java   # Main entry point
│   ├─ domain/                     # JPA entities (Student, Availability, StudySession)
│   ├─ repo/                       # Spring Data repositories
│   ├─ service/                    # Matching service logic
│   └─ web/                        # REST controllers
├─ src/main/resources
│   ├─ application.properties      # Config (port, DB, H2 console)
│   ├─ data.sql                    # Sample seed data
│   └─ static/                     # Frontend (index.html, app.js)
└─ src/test/java/...               # Unit tests

🔑 Key Endpoints

All REST APIs are prefixed with /api:

Create student:
POST /api/students
Body: { "name": "Alice", "email": "alice@school.edu", "courses":["CPSC-2150"] }

Search by course:
GET /api/students?course=CPSC-2150

Add availability:
POST /api/students/{id}/availability?day=MONDAY&start=14:00&end=16:00

Suggest matches:
GET /api/match?studentId=1&course=CPSC-2150

Create session:
POST /api/sessions?course=CPSC-2150&requesterId=1&inviteeId=2&start=2025-09-01T14:00:00&end=2025-09-01T15:00:00

Respond to session:
POST /api/sessions/{id}/respond?status=ACCEPTED

🖥️ Frontend (UI)

Open http://localhost:8080/
 to access the simple UI:

Create students & add courses

Add availability

Search classmates by course

Get suggested matches

Request and manage sessions

🧪 Testing

Run tests with:

mvn test


Included tests verify the matching algorithm (e.g., overlapping availability).

🚀 Next Steps / Enhancements

Add authentication & login system.

Support notifications & reminders.

Export sessions to calendar (ICS).

Improve UI design with React/Angular or Bootstrap.
