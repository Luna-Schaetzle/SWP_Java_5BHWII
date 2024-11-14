<script setup lang="ts">
import { ref } from 'vue'
import AllTodos from '@/components/AllTodos.vue'
import SingleTodo from '@/components/SingleTodo.vue'
import CreateTodo from '@/components/CreateTodo.vue'

const selectedTodo = ref(null)
const isCreating = ref(false)

function selectTodo(todo) {
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
    <AllTodos
      v-if="!selectedTodo && !isCreating"
      @todo-clicked="selectTodo"
      @create-todo="startCreating"
    />
    <SingleTodo v-if="selectedTodo" :todo="selectedTodo" @back="goBack" />
    <CreateTodo v-if="isCreating" @todo-created="goBack" />
  </main>
</template>

<style>
main {
  max-width: 800px;
  margin: 0 auto;
  padding: 20px;
  font-family: Arial, sans-serif;
}
</style>
