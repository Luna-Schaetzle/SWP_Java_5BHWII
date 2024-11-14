<script setup lang="ts">
import { ref } from 'vue'
import { defineEmits } from 'vue'
import axios from 'axios'

interface Todo {
  id?: number // ID wird vom Server vergeben
  title: string
  description: string
  dueDate: string
  location: string
  person: string
  category: string
  erledigt: boolean
}

const emit = defineEmits(['todo-created'])

const title = ref('')
const description = ref('')
const dueDate = ref('')
const location = ref('')
const person = ref('')
const category = ref('')

async function createTodo() {
  const newTodo: Todo = {
    title: title.value,
    description: description.value,
    dueDate: dueDate.value,
    location: location.value,
    person: person.value,
    category: category.value,
    erledigt: false,
  }

  try {
    await axios.post('http://localhost:3000/todos', newTodo)
    emit('todo-created')
  } catch (error) {
    console.error('Fehler beim Erstellen des Todos:', error)
  }
}
</script>

<template>
  <div>
    <h1>Neues Todo erstellen</h1>
    <form @submit.prevent="createTodo">
      <div>
        <label>Titel:</label>
        <input v-model="title" required />
      </div>
      <div>
        <label>Beschreibung:</label>
        <textarea v-model="description"></textarea>
      </div>
      <div>
        <label>Fälligkeitsdatum:</label>
        <input type="date" v-model="dueDate" />
      </div>
      <div>
        <label>Ort:</label>
        <input v-model="location" />
      </div>
      <div>
        <label>Person:</label>
        <input v-model="person" />
      </div>
      <div>
        <label>Kategorie:</label>
        <input v-model="category" />
      </div>
      <button type="submit">Erstellen</button>
    </form>
  </div>
</template>

<style scoped>
h1 {
  text-align: center;
  color: #333;
  margin-bottom: 30px;
}

form {
  max-width: 600px;
  margin: 0 auto;
}

form > div {
  margin-bottom: 15px;
}

label {
  display: block;
  margin-bottom: 5px;
  font-weight: bold;
  color: #555;
}

input,
textarea {
  width: 100%;
  padding: 10px;
  font-size: 1em;
  border: 1px solid #ccc;
  border-radius: 4px;
}

textarea {
  resize: vertical;
  min-height: 100px;
}

button {
  width: 100%;
  padding: 12px;
  font-size: 1em;
  background-color: #28a745;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  margin-top: 10px;
}

button:hover {
  background-color: #218838;
}
</style>
