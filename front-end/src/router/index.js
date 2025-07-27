import { createRouter, createWebHistory } from 'vue-router'
import Home from '../views/Home.vue'
import SuperLucky from '../views/SuperLucky.vue'
import Normal from '../views/Normal.vue'
import Hazure from '../views/Hazure.vue'

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/SuperLucky',
    name: 'SuperLucky',
    component: SuperLucky
  },
  {
    path: '/Normal',
    name: 'Normal',
    component: Normal
  },
  {
    path: '/hazure',
    name: 'Hazure',
    component: Hazure
  }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router
