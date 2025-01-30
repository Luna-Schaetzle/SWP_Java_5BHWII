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
    await axios.put(`http://localhost:3000/todos?id=eq.${todo.id}`, todo)
  } catch (error) {
    console.error('Fehler beim Aktualisieren des Todos:', error)
  }
}

function createTodo() {
  emit('create-todo')
}
</script>

<template>
  <div class="todo-container">
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
main {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  height: 100vh;
  max-width: 100%;
  padding: 20px;
  font-family: 'Roboto', sans-serif;
  background-color: #eef2f6;
}

/* Allgemeiner Button-Stil */
button {
  background-color: #5b8def;
  color: #fff;
  padding: 12px 24px;
  margin-top: 15px;
  border: none;
  border-radius: 8px;
  cursor: pointer;
  font-size: 16px;
  font-weight: 500;
  box-shadow: 0 4px 12px rgba(91, 141, 239, 0.3);
  transition:
    background-color 0.3s ease,
    box-shadow 0.3s ease;
}

button:hover {
  background-color: #497ace;
  box-shadow: 0 6px 16px rgba(91, 141, 239, 0.4);
}

button:active {
  background-color: #3b65b1;
  box-shadow: 0 3px 8px rgba(91, 141, 239, 0.2);
}

/* Stil für Todo-Liste */
.todo-list {
  width: 100%;
  max-width: 800px;
  background-color: #ffffff;
  border-radius: 12px;
  box-shadow: 0 4px 20px rgba(0, 0, 0, 0.08);
  padding: 10px 0;
  margin: 0;
  list-style: none;
}

.todo-item {
  padding: 20px;
  border-bottom: 1px solid #e0e6ed;
  cursor: pointer;
  font-size: 18px;
  color: #333;
  transition:
    background-color 0.3s ease,
    transform 0.2s ease;
}

.todo-item:hover {
  background-color: #f7faff;
  transform: translateY(-2px);
}

.todo-item:last-child {
  border-bottom: none;
}

/* Einzelansicht und Erstellansicht Stil */
.single-todo,
.create-todo {
  width: 100%;
  max-width: 600px;
  background-color: #ffffff;
  padding: 25px;
  border-radius: 12px;
  margin-top: 25px;
  box-shadow: 0 4px 20px rgba(0, 0, 0, 0.1);
  color: #444;
  transition: box-shadow 0.3s ease;
}

.single-todo:hover,
.create-todo:hover {
  box-shadow: 0 6px 24px rgba(0, 0, 0, 0.15);
}

@media (min-width: 768px) {
  main {
    padding: 50px;
  }

  .todo-list,
  .single-todo,
  .create-todo {
    max-width: 700px;
  }
}

@media (min-width: 1200px) {
  .todo-list,
  .single-todo,
  .create-todo {
    max-width: 1000px;
  }
}
</style>
