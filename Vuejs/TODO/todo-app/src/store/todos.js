import { reactive, onMounted, watch } from 'vue';

const state = reactive({
  todos: [],
});

const loadTodos = () => {
  const gespeicherteTodos = localStorage.getItem('todos');
  if (gespeicherteTodos) {
    state.todos = JSON.parse(gespeicherteTodos);
  } else {
    // Initiale TODOs
    state.todos = [
      { id: 1, name: 'Vue.js lernen', erledigt: false, beschreibung: 'Lerne die Grundlagen von Vue.js und erstelle eine einfache Anwendung.' },
      { id: 2, name: 'Einkaufen gehen', erledigt: true, beschreibung: 'Milch, Brot, Eier und Obst kaufen.' },
    ];
  }
};

const saveTodos = () => {
  localStorage.setItem('todos', JSON.stringify(state.todos));
};

// Laden der TODOs beim Start
onMounted(loadTodos);

// Speichern der TODOs bei Änderungen
watch(
  () => state.todos,
  () => {
    saveTodos();
  },
  { deep: true }
);

const addTodo = (name, beschreibung = '') => {
  const newTodo = {
    id: Date.now(),
    name: name.trim(),
    erledigt: false,
    beschreibung,
  };
  state.todos.push(newTodo);
};

const toggleTodo = (id) => {
  const todo = state.todos.find((t) => t.id === id);
  if (todo) {
    todo.erledigt = !todo.erledigt;
  }
};

const getTodoById = (id) => {
  return state.todos.find((t) => t.id === id);
};

export default {
  state,
  addTodo,
  toggleTodo,
  getTodoById,
};
