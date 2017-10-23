create table pos_users (username VARCHAR(100), id INT, enc_passwd VARCHAR(128));
create table pos_admin_users (username VARCHAR(100), id INT, enc_password VARCHAR(128));
create table pos_top_admin_users (username VARCHAR(100), id INT, enc_password VARCHAR(128));
create table users_mappings (firstname VARCHAR(100), middlename VARCHAR(100), lastname VARCHAR(100), username VARCHAR(100), id INT, user_level VARCHAR(20));