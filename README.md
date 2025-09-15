# ☕ Your Brew Journal

**Your Brew Journal** to aplikacja webowa dla miłośników kawy (głównie *speciality*), która pozwala odkrywać kawy, oceniać je, dzielić się przepisami i prowadzić swój kawowy dziennik.

Projekt został wykonany w technologii **React.js** (frontend) oraz **Spring Boot** (backend).

---

## Funkcjonalności

### **1. Zakładka Discover**
- Przeglądanie i filtrowanie kaw dostępnych w bazie.
- Filtry w formie **checkboxów**:
    - Coffee bean origin (pochodzenie ziaren),
    - Brewing method (metoda parzenia),
    - Flavour notes (nuty smakowe),
    - Roast level (stopień palenia).
- Możliwość łączenia filtrów.

---

### **2. Rejestracja i logowanie**
- Tworzenie konta użytkownika (**email + hasło**).
- Logowanie z użyciem sesji i Spring Security.
- Przechowywanie haseł w formie zaszyfrowanej (BCrypt).

---

### **3. Ocena i recenzje kaw**
- Zalogowany użytkownik może:
    - Wystawić **ocenę (rating)**,
    - Napisać **recenzję (komentarz)**.
- Komentarze od innych użytkowników widoczne na stronie kawy.
- Średnia ocena kawy liczona automatycznie na podstawie wszystkich opinii.

---

### **4. Zakładka Community Recipes**
- Wyszukiwanie kawy po **nazwie kawy** i **nazwie palarni**.
- Wyświetlanie przepisów dodanych przez innych użytkowników:
    - Metoda parzenia, ustawienia młynka, dawka kawy, ilość wody, temperatura, czas parzenia, notatki.
- Zalogowani użytkownicy mogą:
    - Dodać swój przepis dla danej kawy.

---

### **5. Zakładka My Profile**
- Zalogowany użytkownik może:
    - Zaktualizować **username**, **email** i **hasło**.
    - Zobaczyć swoje **przepisy** i **recenzje**.
    - Edytować lub usuwać przepisy i recenzje.

---

## 🛠 Technologie

### **Frontend**
- [React.js](https://react.dev/) + CSS Modules
- Fetch API do komunikacji z backendem
- Obsługa sesji i cookie (`credentials: include`)
- Walidacja błędów z backendu i wyświetlanie pod polami formularzy

### **Backend**
- [Spring Boot 3.5.5](https://spring.io/projects/spring-boot)
- [Spring Web](https://spring.io/guides/gs/rest-service/) – REST API
- [Spring Data JPA](https://spring.io/projects/spring-data-jpa) – warstwa bazy danych
- [Spring Security](https://spring.io/projects/spring-security) – logowanie, autoryzacja
- [Spring Validation](https://docs.spring.io/spring-framework/reference/core/validation/beanvalidation.html) – walidacja danych (`@Valid`)
- [MySQL](https://www.mysql.com/) – baza danych
- [Lombok](https://projectlombok.org/) – redukcja boilerplate
- [BCryptPasswordEncoder](https://docs.spring.io/spring-security/site/docs/current/api/org/springframework/security/crypto/bcrypt/BCryptPasswordEncoder.html) – szyfrowanie haseł
---

## Uruchomienie

### **Wymagania**
- Java 17+
- Maven 3+
- MySQL
- Node.js + npm/yarn

---


# ☕ Your Brew Journal

**Your Brew Journal** is a web application for coffee lovers (mainly *speciality* and pour-over brewing methods) that allows users to discover coffees, rate them, share brewing recipes, and keep a personal coffee journal.

The project is built with **React.js** (frontend) and **Spring Boot** (backend).

---

### Features

### **1. Discover Tab**
- Browse and filter coffees available in the database.
- Filters available as **checkboxes**:
  - Coffee bean origin,
  - Brewing method,
  - Flavour notes,
  - Roast level.
- Multiple filters can be combined.

---

### **2. User Registration & Login**
- Create a user account (**email + password**).
- Login with session-based authentication using Spring Security.
- Passwords are stored securely using BCrypt hashing.

---

### **3. Coffee Ratings & Reviews**
- Logged-in users can:
  - Give a **rating** (1–5),
  - Write a **review** (comment).
- View comments from other users on each coffee.
- Average coffee rating is calculated automatically from all submitted reviews.

---

### **4. Community Recipes Tab**
- Search for coffee by **name** and **roastery name**.
- View recipes submitted by other users:
  - Brewing method, grinder settings, coffee dose, water volume, temperature, brewing time, notes.
- Logged-in users can:
  - Add their own recipe for a given coffee.
  - Contribute to the community’s coffee knowledge base.

---

### **5. My Profile Tab**
- Logged-in users can:
  - Update **username**, **email**, and **password**.
  - View all their submitted **recipes** and **reviews**.
  - Edit or delete any of their recipes and reviews.
- Profile data updates in real time.

---

## 🛠 Technologies

### **Frontend**
- [React.js](https://react.dev/) + CSS Modules
- Fetch API for backend communication
- Session & cookie handling (`credentials: include`)
- Validation error handling from backend with inline field error display

### **Backend**
- [Spring Boot 3.5.5](https://spring.io/projects/spring-boot)
- [Spring Web](https://spring.io/guides/gs/rest-service/) – REST API
- [Spring Data JPA](https://spring.io/projects/spring-data-jpa) – Database layer
- [Spring Security](https://spring.io/projects/spring-security) – Authentication & authorization
- [Spring Validation](https://docs.spring.io/spring-framework/reference/core/validation/beanvalidation.html) – Data validation (`@Valid`)
- [MySQL](https://www.mysql.com/) – Database
- [Lombok](https://projectlombok.org/) – Boilerplate reduction
- [BCryptPasswordEncoder](https://docs.spring.io/spring-security/site/docs/current/api/org/springframework/security/crypto/bcrypt/BCryptPasswordEncoder.html) – Password hashing

---

## How to Run

### **Requirements**
- Java 17+
- Maven 3+
- MySQL
- Node.js + npm/yarn