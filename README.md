# Session-manager
An Android app built with MVVM architecture, Room Database, and CameraX to manage sessions during which user has captured some images using his mobile phone . All the details is directly being stored inside user file storage. User can create session and delete session and can even view all his previous sessions.

## Features

 **Session Management**
  - Create new sessions with details (ID, Name, Age).
  - Prevent duplicate session IDs.
  - Store sessions in Room Database.

 **Image Capture**
  - Capture session images using **CameraX**.
  - Images are stored in session-specific folders.

 **Search & View**
  - Search sessions by ID.
  - View session details and all images for a session.
  - Full-screen image preview with dismiss button.

 **All Saved Sessions**
  - View all saved sessions in a list.
  - Open details of each session with images.
  - Delete sessions (removes from DB and file storage).

 **User Experience**
  - Handles empty states (e.g., "No images found", "No saved sessions").
  - Clean MVVM structure with Repository + ViewModel.

---

## Tech Stack

- **Language**: Kotlin  
- **Architecture**: MVVM (Room + ViewModel + LiveData/Coroutines)  
- **Database**: Room Persistence Library  
- **Camera**: CameraX API  
- **UI**: XML layouts + RecyclerView + Glide for image loading

## Project Structure
  app/
 ├── data/
 │   ├── db/ (Room Database, DAO)
 │   └── model/ (Session entity)
 ├── repository/ (SessionRepository)
 ├── ui
 │   ├── session/ (Start, Capture, Search, Detail, AllSessions)
 │   └── adapter/ (ImageAdapter, SessionListAdapter)
 └── viewmodel/ (SessionViewModel)
 └──Main Screen
 └──Splash Screen

 ### Prerequisites
- Android Studio (hedgehog version)
- Android SDK 24+
- Gradle 8+

### Installation
1. Clone the repo
2. Open in Android Studio.
3. Sync Gradle files.
4. Run on emulator/device with camera access.

Note- All Dependencies required is in gradle App level file.


