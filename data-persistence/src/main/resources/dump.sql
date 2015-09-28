-- Table: role
-- DROP TABLE role;

CREATE TABLE role
(
  id serial NOT NULL,
  name character varying(50) NOT NULL,
  CONSTRAINT role_pkey PRIMARY KEY (id)
);

-- Table: users
-- DROP TABLE users;

CREATE TABLE users
(
  id serial NOT NULL,
  login character varying(50) NOT NULL,
  name character varying(150) NOT NULL,
  email character varying(150) NOT NULL,
  password character varying(150) NOT NULL,
  CONSTRAINT users_pkey PRIMARY KEY (id)
);

-- Table: user_role
-- DROP TABLE user_role;

CREATE TABLE user_role
(
  user_id integer NOT NULL,
  role_id integer NOT NULL,
  CONSTRAINT user_role_pkey PRIMARY KEY (user_id, role_id),
  CONSTRAINT role_userrole_foreign FOREIGN KEY (role_id)
      REFERENCES role (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT user_userroles_foreign FOREIGN KEY (user_id)
      REFERENCES users (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);


insert into users (EMAIL, LOGIN, NAME, PASSWORD, ID) values ('dorrisfroelich@gmail.com','dorris', 'Dorris Froelich','123456',1);
insert into users (EMAIL, LOGIN, NAME, PASSWORD, ID) values ('dawnvansickle@gmail.com','dawn', 'Dawn Vansickle','123456',2);
insert into users (EMAIL, LOGIN, NAME, PASSWORD, ID) values ('thurmanvanhouten@gmail.com','thurman','Thurman Vanhouten','123456',3);

insert into role(id, name) values (1,'ROLE_USER');
insert into role(id, name) values (2,'ROLE_ADMIN');
insert into role(id, name) values (3,'ROLE_GUEST');

insert into user_role(user_id, role_id) values (1,1);
insert into user_role(user_id, role_id) values (1,2);
insert into user_role(user_id, role_id) values (2,1);
insert into user_role(user_id, role_id) values (3,1);