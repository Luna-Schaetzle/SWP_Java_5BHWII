# app.py
from flask import Flask, request, jsonify
import sqlite3
from flask_cors import CORS
import os

app = Flask(__name__)
CORS(app)  # Ermöglicht Cross-Origin-Anfragen

DATABASE = 'todos.db'

def get_db_connection():
    conn = sqlite3.connect(DATABASE)
    conn.row_factory = sqlite3.Row  # Ermöglicht das Abrufen von Spaltennamen
    return conn

@app.route('/todos', methods=['GET'])
def get_todos():
    conn = get_db_connection()
    todos = conn.execute("SELECT * FROM todos").fetchall()
    conn.close()
    todos_list = [dict(todo) for todo in todos]
    return jsonify(todos_list)

@app.route('/todos', methods=['POST'])
def add_todo():
    data = request.get_json()
    headline = data.get('headline')
    date = data.get('date')
    text = data.get('text')
    ifdone = data.get('ifdone', False)
    
    if not headline or not date or not text:
        return jsonify({'error': 'Missing fields'}), 400

    conn = get_db_connection()
    cursor = conn.cursor()
    cursor.execute(
        "INSERT INTO todos (headline, date, text, ifdone) VALUES (?, ?, ?, ?)",
        (headline, date, text, ifdone)
    )
    conn.commit()
    new_id = cursor.lastrowid
    conn.close()
    return jsonify({'id': new_id, 'status': 'success'}), 201

@app.route('/todos/<int:todo_id>', methods=['PUT'])
def update_todo(todo_id):
    data = request.get_json()
    headline = data.get('headline')
    date = data.get('date')
    text = data.get('text')
    ifdone = data.get('ifdone')
    
    conn = get_db_connection()
    cursor = conn.cursor()
    cursor.execute(
        "UPDATE todos SET headline = ?, date = ?, text = ?, ifdone = ? WHERE id = ?",
        (headline, date, text, ifdone, todo_id)
    )
    conn.commit()
    conn.close()
    return jsonify({'status': 'success'})

@app.route('/todos/<int:todo_id>', methods=['DELETE'])
def delete_todo(todo_id):
    conn = get_db_connection()
    cursor = conn.cursor()
    cursor.execute("DELETE FROM todos WHERE id = ?", (todo_id,))
    conn.commit()
    conn.close()
    return jsonify({'status': 'success'})

if __name__ == '__main__':
    # Stelle sicher, dass die Datenbank existiert
    if not os.path.exists(DATABASE):
        from init_db import init_db
        init_db()
    app.run(debug=True)
