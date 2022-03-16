Spring-Boot-w-SQL-Server-Impl-

Use POSTMAN to ping endpoints with JSON IN http body.
Default URL: http://localhost:8080/api 

REST API Endpoints:

TYPE | MAPPING

GET    | /tutorials/getAll
GET    | /tutorials/getById-
GET    | /tutorials/getByTitle
POST   | /tutorials/displayAll
DELETE | /tutorials/deleteById-
DELETE | /tutorials/deleteAll
GET    | /tutorials/findByPublished

DB to use: 
(port 3306 MySQL Workbench DB testdb)
DB:     jdbc:mysql://${MYSQL_HOST:localhost}:3306/testdb
Driver: com.mysql.cj.jdbc.Driver



