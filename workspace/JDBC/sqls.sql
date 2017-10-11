CREATE TABLE APP.PERSONAS(
ID INT GENERATED ALWAYS AS IDENTITY,
NOMBRE VARCHAR(255),
APELLIDO VARCHAR(255),
EDAD INT,
PRIMARY KEY (ID)
);

DROP TABLE APP.PERSONAS;

insert into APP.PERSONAS(NOMBRE, APELLIDO, EDAD) values('Victor','Herrero', 22);

SELECT * FROM APP.PERSONAS;

delete from APP.PERSONAS where ID = 1;