// src/router/index.js
import { createRouter, createWebHistory } from 'vue-router';
import HomeView from '../views/HomeView.vue'; // Geänderter Import
import TodoDetail from '../views/TodoDetail.vue';

const routes = [
  {
    path: '/',
    name: 'Home', // Sie können den Routen-Namen ebenfalls anpassen, z.B. 'HomeView'
    component: HomeView, // Geänderter Komponentenname
  },
  {
    path: '/todo/:id',
    name: 'TodoDetail',
    component: TodoDetail,
    props: true,
  },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;
