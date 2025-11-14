#!/bin/bash

# MySQL connection details
USER="root"
PASSWORD="root"
HOST="localhost"
DB_NAME="testdb"

# Query to execute
QUERY="SELECT id, username, password FROM user;"

OUTPUT="/tmp/employee_report.csv"

# Execute the query
mysql -u "$USER" -p"$PASSWORD" -h "$HOST" -D "$DB_NAME" -e "$QUERY"  > "$OUTPUT"

