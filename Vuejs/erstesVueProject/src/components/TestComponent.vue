<script setup lang="ts">
defineProps<{
  Property: string
  counter: number
}>()
const Text = 'const Text'
</script>

<template>
  <h1>Test Component</h1>
  <h2>{{ Text }}</h2>
  <h3>{{ Property }}</h3>
  <h2>{{ counter }}</h2>
</template>
