<template>
    <div>
      <h1>Meine TODO-Liste</h1>
      
      <!-- Formular zum Hinzufügen neuer TODOs -->
      <form @submit.prevent="handleAddTodo" class="add-todo-form">
        <input
          v-model="newTodo.name"
          type="text"
          placeholder="Neues TODO hinzufügen"
          required
        />
        <input
          v-model="newTodo.beschreibung"
          type="text"
          placeholder="Beschreibung (optional)"
        />
        <button type="submit">Hinzufügen</button>
      </form>
      
      <!-- Liste der TODOs -->
      <ul class="todo-list">
        <li v-for="todo in todos" :key="todo.id" :class="{ erledigt: todo.erledigt }">
          <input
            type="checkbox"
            v-model="todo.erledigt"
            @change="toggleStatus(todo.id)"
          />
          <router-link :to="{ name: 'TodoDetail', params: { id: todo.id } }">
            <span>{{ todo.name }}</span>
          </router-link>
        </li>
      </ul>
    </div>
  </template>
  
  <script>
  import { reactive } from 'vue';
  import store from '../store/todos';
  
  export default {
    name: 'TodoList',
    setup() {
      const newTodo = reactive({
        name: '',
        beschreibung: '',
      });
  
      const handleAddTodo = () => {
        if (newTodo.name.trim() === '') return;
        store.addTodo(newTodo.name, newTodo.beschreibung);
        newTodo.name = '';
        newTodo.beschreibung = '';
      };
  
      const toggleStatus = (id) => {
        store.toggleTodo(id);
      };
  
      return {
        newTodo,
        todos: store.state.todos,
        handleAddTodo,
        toggleStatus,
      };
    },
  };
  </script>
  
  <style scoped>
  .container {
    max-width: 600px;
    margin: 50px auto;
    padding: 20px;
    border: 1px solid #ddd;
    border-radius: 4px;
  }
  
  h1 {
    text-align: center;
    margin-bottom: 20px;
  }
  
  .add-todo-form {
    display: flex;
    flex-direction: column;
    gap: 10px;
    margin-bottom: 20px;
  }
  
  .add-todo-form input {
    padding: 8px;
    font-size: 16px;
  }
  
  .add-todo-form button {
    padding: 8px 16px;
    font-size: 16px;
    cursor: pointer;
  }
  
  .todo-list {
    list-style: none;
    padding: 0;
  }
  
  .todo-list li {
    display: flex;
    align-items: center;
    padding: 8px 0;
    border-bottom: 1px solid #eee;
  }
  
  .todo-list li.erledigt span {
    text-decoration: line-through;
    color: #888;
  }
  
  .todo-list li input[type="checkbox"] {
    margin-right: 10px;
  }
  
  .todo-list li a {
    text-decoration: none;
    color: inherit;
    flex: 1;
  }
  
  .todo-list li a:hover {
    text-decoration: underline;
  }
  </style>
  