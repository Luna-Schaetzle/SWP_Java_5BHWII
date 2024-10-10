<script setup lang="ts">
import { ref, useTemplateRef } from 'vue'
import TestComponent from '../components/TestComponent.vue'
let counter = ref(0)
let inputValue = ref("placeholder")

const TestComponentRef = useTemplateRef("tc")

const myData = [
  {prop : "1"},
  {prop : "2"},
  {prop : "3"},
  {prop : "4"},
]

function printSomething() {
  console.log(TestComponentRef.value?.printSomethingInside())
  console.log("The vlaue of inputValue is: " + inputValue.value)
}
</script>

<template>
  <main>
    <h1>Test</h1>
    <button @click="printSomething">Print Something</button>
    <!--Hier sollte eine Komponente sein-->
    <test-component ref="tc" Property="Property Test" :counter="counter" />
    <input v-model="inputValue" />
    <p>{{ inputValue }}</p>
    <button @click="counter++">Increment</button>
    <button @click="counter--">Decrement</button>
    <h1 v-if="counter > 5">Counter is greater than 5</h1>
    <h1 v-else>Counter is less than 5</h1>
    <div v-for="d in myData" :key="d.prop">
      <test-component :Property="d.prop" :counter="counter" />

    </div>
  </main>
</template>
