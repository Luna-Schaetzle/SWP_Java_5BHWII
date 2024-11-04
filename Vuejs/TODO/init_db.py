# init_db.py
import sqlite3

def init_db():
    conn = sqlite3.connect('todos.db')
    cursor = conn.cursor()
    cursor.execute('''
        CREATE TABLE IF NOT EXISTS todos (
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            headline TEXT NOT NULL,
            date TEXT NOT NULL,
            text TEXT NOT NULL,
            ifdone BOOLEAN NOT NULL DEFAULT 0
        )
    ''')
    conn.commit()
    conn.close()
    print("Datenbank initialisiert.")

if __name__ == '__main__':
    init_db()
