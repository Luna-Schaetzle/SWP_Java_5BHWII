<script setup lang="ts">
import { ref } from 'vue'
import TestComponent from '../components/TestComponent.vue'
let counter = ref(0)
</script>

<template>
  <main>
    <h1>Test</h1>
    <!--Hier sollte eine Komponente sein-->
    <test-component Property="Property Test" :counter="counter" />

    <button @click="counter++">Increment</button>
    <button @click="counter--">Decrement</button>
  </main>
</template>
