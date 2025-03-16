# BeatScape: Playlist Simulator
This project is a command-line interface playlist simulator that allows users to do many things like listed below.

## Features:

- Login
- Register
- Retrieve Playlists
- Create Playlists
- Delete Playlists
- Display Songs from Selected Playlist
- Add Songs to Playlist
- Upload Updated Playlist to Database
- Remove Songs from Playlist
- Display Selected Song
- Delete User Account

## Classes:

### Main:

The `Main` class provides a console-based interface for logging into the app, creating playlists, deleting playlists, and many more.

### App:

The `App` class provides core functionality for managing playlists and user accounts in the BeatScape playlist simulator. It handles database connections.

### PlayList:

The `PlayList` class represents a collection of songs in the form of linked list. It allows users to add, remove, and retrieve songs.

#### Features of `PlayList` class:

--- Adding Songs to Playlist
--- Removing Songs by Name
--- Checking if a Song Already Exists
--- Clearing the Entire Playlist
--- Tracking the Current Song (Cursor)
--- Displaying All Songs in the Playlist

### Node:

The `Node` class represents a single node in the linked list of song objects. Each Node contains name of the song, and pointer to the next song.

## Database Configuration

The database connection details are specified in the `App` class in the `src` folder. Make sure to update the following variables with your own database configuration:

```java
String url = "jdbc:mysql://localhost:3306/pupilspacebeatscape";
String user = "root";
String password = "[YOUR-PASSWORD]";
```

# How to Run

- Make sure you have MySQL Workbench installed and running.

- Create a database named beatscape and 3 tables named `user_accounts`, `playlists`, `songs` with the following schema:

```SQL:

create database beatscape;

use beatscape;

CREATE TABLE user_accounts(
	id INT PRIMARY KEY UNIQUE NOT NULL AUTO_INCREMENT,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE playlists(
	id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL UNIQUE,
    user_id INT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES user_accounts(id) ON DELETE CASCADE
);

CREATE TABLE songs(
	id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255),
    playlist_id INT NOT NULL,
    FOREIGN KEY (playlist_id) REFERENCES playlists(id) ON DELETE CASCADE
);
```
- You can also copy and paste the queries from the file `SQL Database Creation Queries.sql` file in `src` folder.

- Update the database configuration in the `App` class.

- Compile and run the `Main` class.

# License

This project is licensed under MIT License.
