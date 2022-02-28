-- roles

INSERT INTO roles (id, name) VALUES ('1', 'ROLE_ADMIN'); 
INSERT INTO api_disney.roles (id, name) VALUES ('2', 'ROLE_USER');

-- users

INSERT INTO users (id, email, name, password, username) VALUES ('1', 'admin@gmail.com', 'admin', 'password', 'admin'); 
INSERT INTO users (id, email, name, password, username) VALUES ('2', 'user@gmail.com', 'user', 'password', 'user');

-- users_roles

INSERT INTO users_roles (user_id, role_id) VALUES ('1', '1'); 
INSERT INTO users_roles (user_id, role_id) VALUES ('2', '2');

-- characters

INSERT INTO characters (id, age, history, name, weight) VALUES ('1', '28', 'Any', 'Mike Wazowski', '90'); 
INSERT INTO characters (id, age, history, name, weight) VALUES ('2', '24', 'Any', 'Hércules', '70');

-- movies

INSERT INTO movies (id, creation_date, rating, title) VALUES ('1', '2001-11-02', '5', 'Monsters Inc'); 
INSERT INTO movies (id, creation_date, rating, title) VALUES ('2', '2013-06-21', '4', 'Monsters Inc University'); 
INSERT INTO movies (id, creation_date, rating, title) VALUES ('3', '1997-06-13', '5', 'Hércules');

-- movies_characters

INSERT INTO movies_characters (movie_id, character_id) VALUES ('1', '1'); 
INSERT INTO movies_characters (movie_id, character_id) VALUES ('2', '1'); 
INSERT INTO movies_characters (movie_id, character_id) VALUES ('3', '2');
