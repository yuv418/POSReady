#!/bin/bash

#this scripts assumes the first arguement is the root's password for mysql

mysql -u root -p$1 < create_users_privleges_databases.sql
mysql -u root -p$1 < employee_table_creator.sql #little bit redundant but I prefer it executed three times in case one fails
mysql -u root -p$1 < item_table_creator.sql

exit 0