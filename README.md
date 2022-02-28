# Disney Api Alkemy Challenge
Realizada con el fin de participar en la aceleración de Alkemy 2022

## Presentación ##
Mi nombre es Sergio Suárez, soy Ingeniero de Sistemas y Telecomunicaciones de la Universidad de Manizales-Colombia, este proyecto fue elaborado con el objeto de participar en la aceleración de Alkemy 2022, asi mismo para colocar a prueba mis conocimientos y aprender nuevos conceptos de Java para la elaboración de APIs, el proyecto consta de una API RestFull en Java, Maven, Spring boot, Mysql, JPA, Spring Secutiry y JWT.**Disney Api**

### Necesidad ###
Desarrollar una API para explorar el mundo de Disney, la cual permitirá conocer y modificar los personajes que lo componen y entender en qué películas estos participaron. Por otro lado, deberá exponer la información para que cualquier frontend pueda consumirla.
- Utilizar Spring Boot.
- No es necesario armar el Frontend.
- Las rutas deberán seguir el patrón REST.
- Utilizar la librería Spring Security.

## Solución ##

### PUERTO APP ###
La aplicación esta corriendo por el puerto: 8084

### DOCUMENTACION APIs: ###
La Api se encuentra documentada en Postman, el archivo para importar que contiene los endpoint para realizar las pruebas se encuentra la raiz del proyecto en la carpeta postman.

### INSTRUCCIONES MySQL ###
MOTOR: MySQL
CREAR BASE DE DATOS: api_disney
EJECUTAR EL PROYECTO PARA QUE CREE LAS TABLAS DE LAS ENTIDADES.

**REALIZAR LAS INSERCIONES: en MySQL Workbench**

roles

INSERT INTO `api_disney`.`roles` (`id`, `name`) VALUES ('1', 'ROLE_ADMIN');

INSERT INTO `api_disney`.`roles` (`id`, `name`) VALUES ('2', 'ROLE_USER');

users

INSERT INTO `api_disney`.`users` (`id`, `email`, `name`, `password`, `username`) VALUES ('1', 'admin@gmail.com', 'admin', 'password', 'admin');

INSERT INTO `api_disney`.`users` (`id`, `email`, `name`, `password`, `username`) VALUES ('2', 'user@gmail.com', 'user', 'password', 'user');

users_roles

INSERT INTO `api_disney`.`users_roles` (`user_id`, `role_id`) VALUES ('1', '1');

INSERT INTO `api_disney`.`users_roles` (`user_id`, `role_id`) VALUES ('2', '2');

characters

INSERT INTO `api_disney`.`characters` (`id`, `age`, `history`, `name`, `weight`) VALUES ('1', '28', 'Any', 'Mike Wazowski', '90');

INSERT INTO `api_disney`.`characters` (`id`, `age`, `history`, `name`, `weight`) VALUES ('2', '24', 'Any', 'Hércules', '70');

movies

INSERT INTO `api_disney`.`movies` (`id`, `creation_date`, `rating`, `title`) VALUES ('1', '2001-11-02', '5', 'Monsters Inc');

INSERT INTO `api_disney`.`movies` (`id`, `creation_date`, `rating`, `title`) VALUES ('2', '2013-06-21', '4', 'Monsters Inc University');

INSERT INTO `api_disney`.`movies` (`id`, `creation_date`, `rating`, `title`) VALUES ('3', '1997-06-13', '5', 'Hércules');

movies_characters

INSERT INTO `api_disney`.`movies_characters` (`movie_id`, `character_id`) VALUES ('1', '1');

INSERT INTO `api_disney`.`movies_characters` (`movie_id`, `character_id`) VALUES ('2', '1');

INSERT INTO `api_disney`.`movies_characters` (`movie_id`, `character_id`) VALUES ('3', '2');


## Fin ##
Sergio Suárez - 2022
