#!/bin/bash
USER="root"
PASSWORD="root "
HOST="localhost"
DB_NAME="testdb"
SQL_FILE="queries.sql"

mysql -u "$USER" -p"$PASSWORD" -h "$HOST" -D "$DB_NAME" < "$SQL_FILE"