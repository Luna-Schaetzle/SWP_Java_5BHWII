<script setup lang="ts">
import { computed } from 'vue';

defineProps<{
  Property: string
  counter: number
}>()
const Text = 'const Text'

function printSomethingInside() {
  return "Something inside"
}

const germanToday = computed(() => {
  const date = new Date()
  return date.toLocaleDateString('de-DE')
})

defineExpose({ printSomethingInside })

</script>

<template>
  <h1>Test Component</h1>
  <h2>{{ Text }}</h2>
  <h3>{{ Property }}</h3>
  <!--h1>{{ propertyplushello }}</h1-->
  <h2>{{ counter }}</h2>
</template>
