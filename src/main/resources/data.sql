

-- Tabla 'musico'
INSERT INTO musico (id, email, nombre, password) VALUES 
(1, 'juan.perez@example.com', 'Juan Pérez', 'password123'),
(2, 'ana.gomez@example.com', 'Ana Gómez', 'password456');

-- Tabla 'cancion'
INSERT INTO cancion (id, artista_original, genero, titulo, tonalidad, musico_id) VALUES
(1, 'The Beatles', 'Rock', 'Hey Jude', 'C', 1),
(2, 'Elvis Presley', 'Rock and Roll', 'Jailhouse Rock', 'E', 1),
(3, 'Eric Clapton', 'Blues', 'Tears in Heaven', 'A', 1),
(4, 'Bob Dylan', 'Folk', 'Knockin’ on Heaven’s Door', 'G', 1),
(5, 'Queen', 'Rock', 'Bohemian Rhapsody', 'G', 2),
(6, 'Adele', 'Pop', 'Rolling in the Deep', 'C', 2),
(7, 'Coldplay', 'Alternative', 'Fix You', 'E', 2),
(8, 'Beyoncé', 'Pop', 'Halo', 'F', 2);

-- Tabla 'repertorio'
INSERT INTO repertorio (id, fecha_concierto, musico_id) VALUES
(1, '2024-12-15', 1),
(2, '2024-12-20', 2);

-- Tabla 'repertorio_cancion'
INSERT INTO repertorio_cancion (repertorio_id, cancion_id) VALUES
(1, 1),
(1, 2),
(1, 3),
(1, 4),
(2, 5),
(2, 6),
(2, 7),
(2, 8);
