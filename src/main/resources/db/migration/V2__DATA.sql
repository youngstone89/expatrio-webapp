INSERT INTO roles(name) VALUES('ROLE_ADMIN');
INSERT INTO roles(name) VALUES('ROLE_CUSTOMER');
INSERT INTO users (email,password,username)
VALUES ('admin@expatrio.com','$2a$10$aJEScajS63z/K.XLhFOuIuLW.z.ftK5DbwpUnmhdcUSnT6AABVr92','admin');

INSERT INTO user_roles (user_id,role_id)
VALUES (1,1);