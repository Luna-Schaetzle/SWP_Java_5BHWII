create role web_anon nologin;

create schema api;
 
grant usage on schema api to web_anon;

CREATE TABLE api.todos (showError
    id SERIAL PRIMARY KEY,         -- Eindeutige ID für jeden Eintrag
    title VARCHAR(255) NOT NULL,   -- Titel des Todos
    description TEXT,              -- Beschreibung des Todos
    due_date DATE,                 -- Fälligkeitsdatum
    location VARCHAR(255),         -- Ort
    person VARCHAR(255),           -- Zuständige Person
    category VARCHAR(255),         -- Kategorie
    erledigt BOOLEAN DEFAULT FALSE -- Status: erledigt oder nicht, Standardwert ist FALSE
);

--- Test Daten ---
INSERT INTO api.todos (title, description, due_date, location, person, category, erledigt) VALUES
('Einkaufen', 'Milch, Brot und Eier kaufen', '2025-01-25', 'Supermarkt', 'Luna', 'Haushalt', FALSE),
('Projektbericht schreiben', 'Kapitel 3 und 4 fertigstellen', '2025-01-30', 'Home Office', 'Florian', 'Arbeit', FALSE),
('Sport', 'Laufen im Park', '2025-01-26', 'Stadtpark', 'Luna', 'Fitness', FALSE),
('Geburtstagsgeschenk', 'Geschenk für Gabriel kaufen', '2025-02-01', 'Einkaufszentrum', 'Luna', 'Persönlich', FALSE),
('Meeting vorbereiten', 'Präsentation und Notizen erstellen', '2025-01-28', 'Büro', 'Gabriel', 'Arbeit', FALSE),
('Arzttermin', 'Jahrescheck-up beim Arzt', '2025-01-27', 'Praxis Dr. Müller', 'Luna', 'Gesundheit', FALSE),
('Wohnung putzen', 'Wohnzimmer und Küche reinigen', '2025-01-29', 'Zuhause', 'Luna', 'Haushalt', FALSE),
('Programmierung üben', 'Vue.js Projekt erweitern', '2025-01-30', 'Home Office', 'Luna', 'Bildung', FALSE),
('Bücher zurückgeben', 'Bibliotheksbücher zurückgeben', '2025-02-03', 'Bibliothek', 'Luna', 'Persönlich', FALSE),
('Filmeabend', 'Film für den DnD-Abend vorbereiten', '2025-01-26', 'Zuhause', 'Luna', 'Freizeit', FALSE);



drop table api.todos 

insert into api.todos (task) values
  ('finish tutorial 0'), ('pat self on back');

grant select on api.todos to web_anon;

--- dont use this in production
grant insert,update,delete on api.todos to web_anon;

create role authenticator noinherit login password '1234';
grant web_anon to authenticator;

select * from api.todos;

create role web_user nologin;
grant usage on schema api to web_user;
GRANT USAGE ON SCHEMA public TO web_user;
GRANT SELECT, INSERT, UPDATE, DELETE ON TABLE api.todos TO web_user;
GRANT SELECT, INSERT, UPDATE, DELETE ON TABLE api.todos TO public;


