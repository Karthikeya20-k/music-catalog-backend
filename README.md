# 🎵 Music Catalog Insights Platform

## About the Project

This project is a full-stack Music Catalog application built using Spring Boot and React. It allows users to search for songs using the iTunes Search API, save their favorite songs to a personal library, and view insights about their music collection.

Each user has their own account and library, so all saved songs, favorites, ratings, notes, and analytics are private.

---

## Tech Stack

### Backend
- Java 21
- Spring Boot
- Spring Security
- JWT Authentication
- Spring Data JPA
- MySQL

### Frontend
- React.js
- Material UI
- Axios

### External API
- Apple iTunes Search API

---

## Why I Chose Songs

For this assignment, I chose **Songs** because they provide more user interaction than albums or artists. Users can search songs, save them, mark favorites, rate them, add personal notes, and receive recommendations based on their listening preferences.

---

## Features

### Authentication
- User registration
- Secure login using JWT
- Password encryption with BCrypt

### Song Search
- Search songs using the iTunes Search API
- View song details
- Listen to song previews

### Personal Library
- Save songs to a personal library
- Separate library for every user
- Delete saved songs
- Mark songs as favorites
- Rate songs
- Add personal notes

### Dashboard & Analytics
- Total songs
- Total artists
- Total genres
- Favorite songs
- Average rating
- Charts for genres, artists, ratings, and song activity

### AI Music Assistant
The AI Assistant analyzes the user's library to identify the most listened genre and recommends artists based on listening habits.

### Profile
- View logged-in user information
- Logout securely

---

## Database

### User

- id
- name
- email
- password

### Song

- id
- trackId
- trackName
- artistName
- collectionName
- artworkUrl
- primaryGenreName
- releaseDate
- trackCount
- favorite
- userRating
- userNotes
- createdAt
- updatedAt

---

## Project Structure

```
Backend
├── Controller
├── Service
├── Repository
├── Entity
├── Security
└── Configuration

Frontend
├── Components
├── Pages
├── Axios Configuration
└── Material UI
```

---

## Running the Project

### Backend

```bash
mvn spring-boot:run
```

### Frontend

```bash
npm install
npm start
```

---

## Future Improvements

If I continue developing this project, I would like to add:

- Dark mode
- Playlist creation
- Better AI recommendations
- Export library to PDF/Excel
- Pagination and filtering
- Cloud deployment

---

## Conclusion

This project helped me gain practical experience with full-stack development, REST APIs, Spring Security, JWT authentication, React, and MySQL. It also gave me hands-on experience in integrating third-party APIs and building user-specific features from end to end.