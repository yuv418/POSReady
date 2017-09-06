create database posready;
create user 'default_u'@'localhost' identified by 'letmeinmysql'
grant all privileges on posready.* to 'default_u'@'localhost';
flush priviledges; 

