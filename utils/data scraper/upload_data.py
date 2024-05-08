import pandas as pd
import psycopg2

# Read CSV file into pandas DataFrame
df = pd.read_csv('stats.csv')

# Connect to PostgreSQL database
conn = psycopg2.connect(
    dbname='your_database_name',
    user='your_username',
    password='your_password',
    host='localhost'
)

# Open a cursor to perform database operations
cur = conn.cursor()

# Iterate over DataFrame rows and insert data into PostgreSQL table
for index, row in df.iterrows():
    cur.execute(
        "INSERT INTO team_stats (team_name, column1, column2, ...) VALUES (%s, %s, %s, ...)",
        (row['team_name'], row['column1'], row['column2'], ...)
    )

# Commit the transaction
conn.commit()

# Close communication with the PostgreSQL database
cur.close()
conn.close()
