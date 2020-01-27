INSERT INTO roles(name) VALUES('ROLE_ADMIN');
INSERT INTO roles(name) VALUES('ROLE_CUSTOMER');
INSERT INTO users (email,password,username)
VALUES ('admin@expatrio.com','$2a$10$aJEScajS63z/K.XLhFOuIuLW.z.ftK5DbwpUnmhdcUSnT6AABVr92','admin');

INSERT INTO users (email,password,username)
VALUES ('customer1@expatrio.com','$2a$10$aJEScajS63z/K.XLhFOuIuLW.z.ftK5DbwpUnmhdcUSnT6AABVr92','customer1');

INSERT INTO users (email,password,username)
VALUES ('customer2@expatrio.com','$2a$10$aJEScajS63z/K.XLhFOuIuLW.z.ftK5DbwpUnmhdcUSnT6AABVr92','customer2');

INSERT INTO users (email,password,username)
VALUES ('customer3@expatrio.com','$2a$10$aJEScajS63z/K.XLhFOuIuLW.z.ftK5DbwpUnmhdcUSnT6AABVr92','customer3');

INSERT INTO users (email,password,username)
VALUES ('customer4@expatrio.com','$2a$10$aJEScajS63z/K.XLhFOuIuLW.z.ftK5DbwpUnmhdcUSnT6AABVr92','customer4');

INSERT INTO users (email,password,username)
VALUES ('customer5@expatrio.com','$2a$10$aJEScajS63z/K.XLhFOuIuLW.z.ftK5DbwpUnmhdcUSnT6AABVr92','customer5');


INSERT INTO user_roles (user_id,role_id)
VALUES (1,1);

INSERT INTO user_roles (user_id,role_id)
VALUES (2,2);

INSERT INTO user_roles (user_id,role_id)
VALUES (3,2);

INSERT INTO user_roles (user_id,role_id)
VALUES (4,2);

INSERT INTO user_roles (user_id,role_id)
VALUES (5,2);

INSERT INTO user_roles (user_id,role_id)
VALUES (6,2);