# RestApiForVladimir
CREATE TABLE users (
  id serial not null,
  token varchar(50) primary key,
  email varchar(50),
  password varchar(20),
  name varchar(15),
  role varchar(10)
);

CREATE TABLE messages (
  id serial not null primary key,
  text_of_msg text,
  timestamp_of_msg timestamp without time zone,
  user_token varchar(50),
  FOREIGN KEY (user_token) REFERENCES users(token)
);
