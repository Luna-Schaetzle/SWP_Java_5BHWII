<script setup lang="ts">
import { defineProps, defineEmits } from 'vue'
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

const props = defineProps<{ todo: Todo }>()
const emit = defineEmits(['back'])

async function deleteTodo() {
  try {
    // In PostgREST wird der Filter über Query-Parameter gesetzt
    await axios.delete(`http://localhost:3000/todos?id=eq.${props.todo.id}`, {
      headers: {
        'Content-Type': 'application/json', // Header für PostgREST
      },
    })
    emit('back') // Zurück navigieren oder Todos neu laden
  } catch (error) {
    console.error('Fehler beim Löschen des Todos:', error)
  }
}
</script>

<template>
  <div>
    <button @click="$emit('back')">Zurück</button>
    <button @click="deleteTodo">Todo löschen</button>
    <h1>{{ todo.title }}</h1>
    <p v-if="todo.erledigt">Erledigt</p>
    <p v-else>Nicht erledigt</p>
    <p><strong>Beschreibung:</strong> {{ todo.description }}</p>
    <p><strong>Fälligkeitsdatum:</strong> {{ todo.dueDate }}</p>
    <p><strong>Ort:</strong> {{ todo.location }}</p>
    <p><strong>Person:</strong> {{ todo.person }}</p>
    <p><strong>Kategorie:</strong> {{ todo.category }}</p>
  </div>
</template>

<style scoped>
button {
  margin-bottom: 16px;
  padding: 10px 15px;
  font-size: 1em;
  background-color: #007bff;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

button:hover {
  background-color: #0056b3;
}

h1 {
  margin-top: 0;
  color: #333;
  text-align: center;
}

p {
  font-size: 1em;
  color: #555;
  line-height: 1.6;
}

strong {
  color: #333;
}

.status {
  font-weight: bold;
  margin-bottom: 20px;
}

.status.erledigt {
  color: #28a745;
}

.status.nicht-erledigt {
  color: #dc3545;
}
</style>
