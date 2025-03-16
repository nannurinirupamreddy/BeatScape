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