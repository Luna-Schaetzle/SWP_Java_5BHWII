<!-- src/views/HomeView.vue -->
<template>
  <div class="home">
    <TodoList />
  </div>
</template>

<script>
import TodoList from '../components/TodoList.vue';

export default {
  name: 'HomeView', // Geänderter Name
  components: {
    TodoList,
  },
};
</script>

<style scoped>
.home {
  padding: 20px;
}
</style>
