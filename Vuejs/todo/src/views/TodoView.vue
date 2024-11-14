<script setup lang="ts">
import { ref } from 'vue'
import AllTodos from '@/components/AllTodos.vue'
import SingleTodo from '@/components/SingleTodo.vue'
import CreateTodo from '@/components/CreateTodo.vue'

const selectedTodo = ref(null)
const isCreating = ref(false)

function selectTodo(todo: null) {
  selectedTodo.value = todo
}

function goBack() {
  selectedTodo.value = null
  isCreating.value = false
}

function startCreating() {
  isCreating.value = true
}
</script>

<template>
  <main>
    <!-- Todo Übersicht -->
    <AllTodos
      v-if="!selectedTodo && !isCreating"
      @todo-clicked="selectTodo"
      @create-todo="startCreating"
    />

    <!-- Einzelnes Todo anzeigen -->
    <SingleTodo v-if="selectedTodo" :todo="selectedTodo" @back="goBack" />

    <!-- Neues Todo erstellen -->
    <CreateTodo v-if="isCreating" @todo-created="goBack" />
  </main>
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
  font-family: Arial, sans-serif;
  background-color: #45a049;
}

/* Allgemeiner Button-Stil */
button {
  background-color: #4caf50;
  color: #fff;
  padding: 10px 20px;
  margin-top: 10px;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  font-size: 16px;
  transition: background-color 0.3s;
}

button:hover {
  background-color: #45a049;
}

/* Todo-Liste Stil */
.todo-list {
  width: 100%;
  max-width: 800px;
  background-color: #fff;
  list-style: none;
  padding: 0;
  margin: 0;
  border-radius: 8px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

.todo-item {
  padding: 15px;
  border-bottom: 1px solid #ddd;
  cursor: pointer;
  transition: background-color 0.3s;
}

.todo-item:hover {
  background-color: #f9f9f9;
}

.todo-item:last-child {
  border-bottom: none;
}

/* Einzelansicht und Erstellansicht Stil */
.single-todo,
.create-todo {
  width: 100%;
  max-width: 600px;
  background-color: #fff;
  padding: 20px;
  border-radius: 8px;
  margin-top: 20px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

@media (min-width: 768px) {
  main {
    padding: 40px;
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
