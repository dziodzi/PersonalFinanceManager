import {createRouter, createWebHistory} from 'vue-router'
import Login from '@/views/LoginPage.vue';
import Register from '@/views/RegisterPage.vue';
import MainPage from '@/views/MainPage.vue';
import store from '../store';

const routes = [
    { path: '/login', component: Login, meta: { guest: true } },
    { path: '/register', component: Register, meta: { guest: true } },
    { path: '/', component: MainPage, meta: { requiresAuth: true } },
]

const router = createRouter({
    history: createWebHistory(process.env.BASE_URL),
    routes
})

router.beforeEach((to, from, next) => {
    const isAuthenticated = store.state.isAuthenticated;

    if (to.matched.some(record => record.meta.requiresAuth) && !isAuthenticated) {
        next('/login');
    } else if (to.matched.some(record => record.meta.guest) && isAuthenticated) {
        next('/');
    } else {
        next();
    }
});

export default router