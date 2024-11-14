<script setup lang="ts">
import { RouterLink, RouterView } from 'vue-router'
</script>

<template>
  <nav>
    <RouterLink to="/" class="nav-link">Home</RouterLink>
    <RouterLink to="/about" class="nav-link">About</RouterLink>
    <RouterLink to="/todo" class="nav-link">Todo</RouterLink>
  </nav>
  <main>
    <RouterView />
  </main>
</template>

<style scoped>
/* Navbar Styling */
nav {
  display: flex;
  justify-content: center;
  align-items: center;
  background-color: #333; /* Dunkler Hintergrund */
  padding: 1rem;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  z-index: 1000;
}

/* Links Styling */
.nav-link {
  color: #ffffff; /* Weißer Text */
  text-decoration: none;
  padding: 0.5rem 1rem;
  font-size: 1rem;
  font-weight: 500;
  margin: 0 0.5rem;
  border-radius: 5px;
  transition:
    background-color 0.3s,
    color 0.3s;
}

/* Hover-Effekte */
.nav-link:hover {
  background-color: #4caf50; /* Grüner Hover-Hintergrund */
  color: #ffffff;
}

.nav-link.router-link-exact-active {
  background-color: #45a049; /* Aktiver Link Hintergrund */
}

/* Main Content Abstand unter der Navbar */
main {
  font-family: Arial, sans-serif;
  background-color: #45a049 /* Grüner Hintergrund */;
}
</style>
