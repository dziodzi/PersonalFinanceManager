<template>
  <div>
    <h2>Категории</h2>

    <span @click="fetchData" style="cursor: pointer;">Загрузить категории️</span>
    <!-- Таблица с категориями -->

    <table class="table">
      <thead>
      <tr>
        <th>ID</th>
        <th>Название</th>
        <!--  <th>Лимит</th> -->
          <td style="color: white">Удалить</td>
        </tr>
        </thead>
        <tbody>
        <tr v-for="category in categories " :key="category.id">
          <td>{{ category.id }}</td>
          <td>
            {{ category.name }}
          </td>
          <!--   <td>{{ category.spendLimit }}</td> -->
           <td>
             <span @click="deleteCategory(category).then(fetchData)" style="cursor: pointer;">❌️</span>
           </td>
         </tr>
         </tbody>
       </table>

       <!-- Кнопка "Добавить новую категорию" вне таблицы -->
    <div>
      <!-- Если добавление новой категории активно, покажем инпут и кнопку "Добавить" -->
      <template v-if="addingNewCategory">
        <input v-model="newCategoryName" placeholder="Введите название"/>
       <!-- <input v-model="newCategoryLimit" placeholder="Введите лимит"/> -->
        <button @click="addNewCategory().then(fetchData)" class="btn btn-outline-dark">Добавить</button>
      </template>
      <!-- Если добавление новой категории не активно, покажем кнопку "Добавить новую категорию" -->
      <template v-else>
        <button @click="startAddingNewCategory" class="btn btn-outline-dark">Добавить новую категорию</button>
      </template>
    </div>
  </div>
</template>

<script>

import * as api from '@/api';

export default {

  data() {
    return {
      categories: [],
      addingNewCategory: false,
      newCategoryName: "",
      newCategoryLimit: 30,
      getResult: null,
    };
  },

  methods: {
    async fetchData() {
      var data = await api.categories.getAll();

      console.log(data);

      this.categories = data.map(category => ({
        id: category.id,
        name: category.name,
        spendLimit: category.spendLimit,
        originalName: "",
      }));
    },

    startAddingNewCategory() {
      // Начинаем добавление новой категории
      this.addingNewCategory = true;
    },

    async addNewCategory() {
      const data = ({
        name: this.newCategoryName,
        spendLimit: this.newCategoryLimit,
        userId: '1'
      });

      await api.categories.create(data);

      // Сбрасываем состояние добавления новой категории
      this.addingNewCategory = false;
      this.newCategoryName = "";
      this.newCategoryLimit = 30;

    },

    async deleteCategory(category) {
      try {
        await api.categories.delete(category.id);

      } catch (err) {
        this.getResult = err.message;
      }
    },
  },
};
</script>
