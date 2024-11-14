<script setup lang="ts">
import { ref, onMounted } from 'vue'
import { defineEmits } from 'vue'
import axios from 'axios'

interface Todo {
  id: number
  title: string
  description: string
  dueDate: string
  location: string
  person: string
  category: string
  erledigt: boolean
}

const emit = defineEmits(['todo-clicked', 'create-todo'])
const todos = ref<Todo[]>([])

function todoClicked(todo: Todo) {
  emit('todo-clicked', todo)
}

async function loadTodos() {
  try {
    const response = await axios.get('http://localhost:3000/todos')
    todos.value = response.data
  } catch (error) {
    console.error('Fehler beim Laden der Todos:', error)
  }
}

onMounted(() => {
  loadTodos()
})

async function updateTodo(todo: Todo) {
  try {
    await axios.put(`http://localhost:3000/todos/${todo.id}`, todo)
  } catch (error) {
    console.error('Fehler beim Aktualisieren des Todos:', error)
  }
}

function createTodo() {
  emit('create-todo')
}
</script>

<template>
  <div>
    <h1>Alle Todos</h1>
    <button @click="createTodo">Neues Todo erstellen</button>
    <ul>
      <li v-for="todo in todos" :key="todo.id">
        <input
          type="checkbox"
          v-model="todo.erledigt"
          @change="updateTodo(todo)"
        />
        <span
          @click="todoClicked(todo)"
          :style="{
            textDecoration: todo.erledigt ? 'line-through' : 'none',
            cursor: 'pointer',
          }"
        >
          {{ todo.title }}
        </span>
      </li>
    </ul>
  </div>
</template>

<style scoped>
h1 {
  text-align: center;
  color: #333;
}

button {
  display: block;
  margin: 20px auto;
  padding: 10px 20px;
  font-size: 1em;
  background-color: #28a745;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

button:hover {
  background-color: #218838;
}

ul {
  list-style-type: none;
  padding: 0;
}

li {
  display: flex;
  align-items: center;
  padding: 12px;
  border-bottom: 1px solid #ddd;
}

li:hover {
  background-color: #f9f9f9;
}

input[type='checkbox'] {
  margin-right: 15px;
  transform: scale(1.2);
}

span {
  flex-grow: 1;
  font-size: 1.1em;
  color: #555;
}

span:hover {
  text-decoration: underline;
}

span[style*='line-through'] {
  color: #999;
}
</style>
