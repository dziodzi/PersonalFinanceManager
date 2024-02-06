<template>
  <nav style="background: #736a67" class="navbar navbar-expand-md navbar-dark mb-4">
    <div class="container-fluid">
      <router-link to="/" class="navbar-brand">Личный кабинет</router-link>
      <ul class="navbar-nav me-auto mb-4 mb-md-0">
        <li class="nav-item">
          <router-link to="/educational-resources" class="nav-link">Образовательные ресурсы</router-link>
        </li>
      </ul>
      <div>
        <ul class="navbar-nav me-auto mb-2 mb-md-0" v-if="!auth">
          <li class="nav-item">
            <router-link to="/login" class="nav-link">Войти</router-link>
          </li>
          <li class="nav-item">
            <router-link to="/register" class="nav-link">Зарегистрироваться</router-link>
          </li>
        </ul>

        <ul class="navbar-nav me-auto mb-2 mb-md-0" v-if="auth">
          <li class="nav-item">
            <a href="#" class="nav-link" @click="logout">Выйти</a>
          </li>
        </ul>
      </div>
    </div>
  </nav>
</template>

<script>
import {computed} from 'vue';
import {useStore} from 'vuex';

export default {
  setup() {
    const store = useStore();

    const auth = computed(() => store.state.isAuthenticated);

    const logout = () => {
      store.dispatch('logout');
    };

    return {
      auth,
      logout,
    };
  },
};
</script>
