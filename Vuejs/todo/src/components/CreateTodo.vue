<script setup lang="ts">
import { ref } from 'vue'
import axios from 'axios'

const emit = defineEmits(['back', 'todo-created'])

// Zustand für Fehler-/Erfolgsmeldungen
const errorMessage = ref('')
const successMessage = ref('')

// Formulardaten
const title = ref('')
const description = ref('')
const dueDate = ref('')
const location = ref('')
const person = ref('')
const category = ref('')

async function createTodo() {
  try {
    // Vor dem POST-Request testen:
    axios
      .get('http://localhost:3000/todos')
      .then(response => console.log('Verbindung OK:', response))
      .catch(error => console.error('Verbindungsfehler:', error))
    // Reset messages
    errorMessage.value = ''
    successMessage.value = ''

    // Validierung
    if (!title.value) {
      throw new Error('Titel ist erforderlich')
    }

    const newTodo = {
      title: title.value,
      description: description.value,
      due_date: dueDate.value ? new Date(dueDate.value).toISOString() : null,
      location: location.value,
      person: person.value,
      category: category.value,
      erledigt: false,
    }

    const response = await axios.post('http://localhost:3000/todos', newTodo, {
      headers: {
        'Content-Type': 'application/json',
        Prefer: 'return=representation',
      },
    })

    // Erfolgsmeldung anzeigen
    successMessage.value = 'Todo erfolgreich erstellt!'
    console.log('Erfolgreich erstellt:', response.data)

    // Formular zurücksetzen
    title.value = ''
    description.value = ''
    dueDate.value = ''
    location.value = ''
    person.value = ''
    category.value = ''

    // Parent-Komponente benachrichtigen
    emit('todo-created', response.data)
  } catch (error) {
    // Fehlerbehandlung
    if (axios.isAxiosError(error)) {
      errorMessage.value = error.response?.data?.message || 'Serverfehler'
    } else if (error instanceof Error) {
      errorMessage.value = error.message
    }
    console.error('Fehler:', error)
  }
}
</script>
<template>
  <div>
    <h1>Neues Todo erstellen</h1>

    <!-- Erfolgsmeldung -->
    <div v-if="successMessage" class="success-message">
      {{ successMessage }}
    </div>

    <!-- Fehlermeldung -->
    <div v-if="errorMessage" class="error-message">
      {{ errorMessage }}
    </div>

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

    <button @click="$emit('back')">Zurück</button>
  </div>
</template>

<style scoped>
.success-message {
  color: #155724;
  background-color: #d4edda;
  border-color: #c3e6cb;
  padding: 10px;
  margin: 20px 0;
  border-radius: 4px;
}

.error-message {
  color: #721c24;
  background-color: #f8d7da;
  border-color: #f5c6cb;
  padding: 10px;
  margin: 20px 0;
  border-radius: 4px;
}

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
