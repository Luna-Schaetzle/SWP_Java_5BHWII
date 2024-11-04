<template>
    <div class="todo-detail">
      <h2>Task-Details</h2>
      <div v-if="todo">
        <p><strong>Name:</strong> {{ todo.name }}</p>
        <p><strong>Beschreibung:</strong> {{ todo.beschreibung || 'Keine Beschreibung vorhanden.' }}</p>
        <p><strong>Status:</strong> {{ todo.erledigt ? 'Erledigt' : 'Nicht erledigt' }}</p>
        <button @click="toggleStatus(todo.id)">
          {{ todo.erledigt ? 'Als nicht erledigt markieren' : 'Als erledigt markieren' }}
        </button>
        <router-link to="/">Zurück zur Liste</router-link>
      </div>
      <div v-else>
        <p>Task nicht gefunden.</p>
        <router-link to="/">Zurück zur Liste</router-link>
      </div>
    </div>
  </template>
  
  <script>
  import { computed } from 'vue';
  import store from '../store/todos';
  
  export default {
    name: 'TodoDetail',
    props: {
      id: {
        type: String,
        required: true,
      },
    },
    setup(props) {
      const todo = computed(() => {
        const idNum = parseInt(props.id);
        return store.getTodoById(idNum);
      });
  
      const toggleStatus = (id) => {
        store.toggleTodo(id);
      };
  
      return {
        todo,
        toggleStatus,
      };
    },
  };
  </script>
  
  <style scoped>
  .todo-detail {
    max-width: 600px;
    margin: 50px auto;
    padding: 20px;
    border: 1px solid #ddd;
    border-radius: 4px;
  }
  
  h2 {
    text-align: center;
    margin-bottom: 20px;
  }
  
  p {
    font-size: 18px;
    margin: 10px 0;
  }
  
  button {
    padding: 8px 16px;
    font-size: 16px;
    cursor: pointer;
    margin-right: 10px;
  }
  
  a {
    text-decoration: none;
    color: #42b983;
  }
  
  a:hover {
    text-decoration: underline;
  }
  </style>
  