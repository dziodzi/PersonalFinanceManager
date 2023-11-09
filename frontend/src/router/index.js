import {createRouter, createWebHistory} from 'vue-router'
import Home from '@/views/HomePage.vue';
import Login from '@/views/LoginPage.vue';
import Register from '@/views/RegisterPage.vue';

const routes = [
    {path: '/', component: Home},
    {path: '/login', component: Login},
    {path: '/register', component: Register},
]

const router = createRouter({
    history: createWebHistory(process.env.BASE_URL),
    routes
})

export default router