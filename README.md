# Java PostgreSQL CRUD Application

A simple Java console application demonstrating **CRUD (Create, Read, Update, Delete)** operations with PostgreSQL using JDBC.

## Overview

- **Database Setup:** Automatically creates the database and `users` table if needed.  
- **Separation of Concerns:** `UserDAO` handles all database operations; `Main` handles console input and menu flow.  
- **Secure Queries:** Uses `PreparedStatement` to prevent SQL injection.  
- **CRUD Operations:** Add, view, update, and delete user records via a console menu.  

## Technologies

- Java (JDK 17+)  
- JDBC  
- PostgreSQL
