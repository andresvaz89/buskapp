/* -- Crear tabla para los artistas
CREATE TABLE artist (
    id INT PRIMARY KEY AUTO_INCREMENT,
    email VARCHAR(255) NOT NULL UNIQUE,
    username VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
);

-- Crear tabla para canciones con un campo id único
CREATE TABLE song (
    id INT PRIMARY KEY AUTO_INCREMENT,
    original_artist VARCHAR(255) NOT NULL,
    genre VARCHAR(255) NOT NULL,
    title VARCHAR(255) NOT NULL,
    song_key VARCHAR(10) NOT NULL,
    artist_id INT NOT NULL,
    FOREIGN KEY (artist_id) REFERENCES artist(id) ON DELETE CASCADE
);

-- Crear tabla para setlist
CREATE TABLE setlist (
    id INT PRIMARY KEY AUTO_INCREMENT,
    gig_date DATE NOT NULL,
    artist_id INT NOT NULL,
    FOREIGN KEY (artist_id) REFERENCES artist(id) ON DELETE CASCADE
);

-- Crear tabla para gigs (con relación a canciones)
CREATE TABLE gigs (
    gig_id INT NOT NULL,
    song_id INT NOT NULL,
    PRIMARY KEY (gig_id, song_id),
    FOREIGN KEY (song_id) REFERENCES song(id) ON DELETE CASCADE,
    FOREIGN KEY (gig_id) REFERENCES setlist(id) ON DELETE CASCADE
);

-- Insertar datos en la tabla artist
INSERT INTO artist (email, username, password) VALUES 
('juan.perez@example.com', 'Juan Pérez', 'password123'),
('ana.gomez@example.com', 'Ana Gómez', 'password456');

-- Insertar datos en la tabla song
INSERT INTO song (original_artist, genre, title, song_key, artist_id) VALUES
('The Beatles', 'Rock', 'Hey Jude', 'C', 1),
('Elvis Presley', 'Rock and Roll', 'Jailhouse Rock', 'E', 1),
('Eric Clapton', 'Blues', 'Tears in Heaven', 'A', 1),
('Bob Dylan', 'Folk', 'Knockin’ on Heaven’s Door', 'G', 1),
('Queen', 'Rock', 'Bohemian Rhapsody', 'G', 2),
('Adele', 'Pop', 'Rolling in the Deep', 'C', 2),
('Coldplay', 'Alternative', 'Fix You', 'E', 2),
('Beyoncé', 'Pop', 'Halo', 'F', 2);

-- Insertar datos en la tabla setlist
INSERT INTO setlist (gig_date, artist_id) VALUES
('2024-12-15', 1),
('2024-12-20', 2);

-- Insertar datos en la tabla gigs
INSERT INTO gigs (gig_id, song_id) VALUES
(1, 1),
(1, 2),
(1, 3),
(1, 4),
(2, 5),
(2, 6),
(2, 7),
(2, 8);
 */