# NotePad App for Android

![Language](https://img.shields.io/github/languages/top/itsrafay03/NotePad-App?style=for-the-badge&logo=java&color=orange)
![Platform](https://img.shields.io/badge/Platform-Android-brightgreen?style=for-the-badge&logo=android)
![Database](https://img.shields.io/badge/Database-SQLite-blue?style=for-the-badge&logo=sqlite)
![Last Commit](https://img.shields.io/github/last-commit/itsrafay03/NotePad-App?style=for-the-badge)

A simple, clean, and efficient note-taking application built for the Android platform using Java. This app demonstrates core Android development concepts, including UI design with XML, local data persistence with SQLite, and activity lifecycle management.

## 📋 Table of Contents
- [Overview](#-overview)
- [Features](#-features)
- [Screenshots](#-screenshots)
- [Technology Stack](#-technology-stack)
- [Requirements](#-requirements)
- [How to Build and Run](#-how-to-build-and-run)
- [Repository Structure](#-repository-structure)
- [Contributing](#-contributing)
- [Contact](#-contact)

## 📝 Overview
This NotePad app provides users with a straightforward way to create, view, update, and delete notes. All notes are stored locally on the device, ensuring data privacy and offline access. The project is built entirely with native Android components and serves as a great example of implementing a complete CRUD (Create, Read, Update, Delete) functionality with a local SQLite database.

## ✨ Features
- **Create Notes:** Easily add new notes with a title and a detailed description using a dedicated "Add Note" screen.
- **View All Notes:** A clean main screen displays all saved notes in an efficient, scrollable list using a `RecyclerView`.
- **Update Existing Notes:** Tap on any note in the list to open an edit screen where you can modify its title and description.
- **Delete Notes:** Functionality to delete notes is available from the update screen, providing a complete data management lifecycle.
- **Local Data Persistence:** All notes are stored in a local **SQLite database**, ensuring that your data is saved even when the app is closed.
- **Custom UI Components:**
    -   **`RecyclerView` with `CardView`**: For a modern and efficient display of the notes list.
    -   **Custom Adapter (`NotesAdapter`)**: Manages the data flow between the SQLite database and the `RecyclerView`.
    -   **`FloatingActionButton`**: For intuitive access to the "Add Note" screen.
    -   **Menu Options**: Contextual menu items for saving, updating, and deleting notes.
- **Database Management:** A custom `MyDatabaseHelper` class handles all SQLite operations, including table creation, queries, insertions, updates, and deletions.

## 📸 Screenshots
*(It's highly recommended to add screenshots of your app here to visually showcase its features. You can add them to your repository and link them here.)*

| Main Screen | Add Note Screen | Update Note Screen |
| :---: | :---: | :---: |
| *[Your main screen screenshot here]* | *[Your add note screen screenshot here]* | *[Your update note screen screenshot here]* |

## 🛠️ Technology Stack
- **Language:** **Java**
- **Platform:** **Android SDK**
- **Database:** **SQLite**
- **UI:** Android XML Layouts, `RecyclerView`, `CardView`, `FloatingActionButton`
- **Core Components:** `AppCompatActivity`, `Intent`, `ContentValues`, `SQLiteOpenHelper`

## ⚙️ Requirements
- Android Studio (latest version recommended)
- An Android Virtual Device (AVD) or a physical Android device
- JDK (Java Development Kit)

## 🚀 How to Build and Run

Follow these steps to get a local copy up and running.

1.  **Clone the repository:**
    ```bash
    git clone https://github.com/itsrafay03/NotePad-App.git
    ```

2.  **Open in Android Studio:**
    -   Launch Android Studio.
    -   Select **File -> Open**.
    -   Navigate to the cloned `NotePad-App` directory and select it.

3.  **Let Gradle Sync:**
    -   Android Studio will automatically sync the project's dependencies. This may take a few moments.

4.  **Run the App:**
    -   Select an available emulator or connect a physical Android device.
    -   Click the **'Run'** button (▶️) in the top toolbar or press `Shift` + `F10`.

## 📂 Repository Structure
NotePad-App/
├── app/
│ ├── src/main/
│ │ ├── java/com/example/notepad_app/
│ │ │ ├── AddNotesActivity.java # Activity for adding new notes
│ │ │ ├── MainActivity.java # Main screen, displays all notes
│ │ │ ├── MyDatabaseHelper.java # Manages SQLite database operations
│ │ │ ├── NotesAdapter.java # Adapter for the RecyclerView
│ │ │ └── UpdateNotesActivity.java # Activity for updating/deleting notes
│ │ └── res/
│ │ ├── layout/ # XML layout files for activities
│ │ ├── menu/ # XML menu definition files
│ │ └── ... # Other resource folders
└── ... # Gradle and config files
## 🤝 Contributing
Contributions, issues, and feature requests are welcome! Feel free to check the [issues page](https://github.com/itsrafay03/NotePad-App/issues) if you want to contribute.

1.  Fork the Project
2.  Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3.  Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4.  Push to the Branch (`git push origin feature/AmazingFeature`)
5.  Open a Pull Request

## 📧 Contact
Rafay - [@itsrafay03](https://github.com/itsrafay03)

Project Link: [https://github.com/itsrafay03/NotePad-App](https://github.com/itsrafay03/NotePad-App)
