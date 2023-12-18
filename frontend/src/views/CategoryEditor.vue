<template>
  <div>
    <h2>Категории</h2>

    <!-- Таблица с категориями -->

    <table class="table">
      <thead>
      <tr>
        <th>ID</th>
        <th>Название</th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="category in categories" :key="category.id">
        <td>{{ category.id }}</td>
        <td>
          <!-- Если редактирование активно, покажем инпут для редактирования -->
          <template v-if="category.isEditing">
            <input v-model="category.name"/>
            <span @click="saveCategory(category)" style="cursor: pointer;">✔</span>
            <span @click="cancelEdit(category)" style="cursor: pointer;">✘</span>
          </template>
          <!-- Если редактирование не активно, покажем текст и иконку для редактирования и удаления -->
          <template v-else>
            {{ category.name }}
            <span @click="editCategory(category)" style="cursor: pointer;">✎</span>
            <span @click="deleteCategory(category)" style="cursor: pointer;">🗑️</span>
          </template>
        </td>
      </tr>
      </tbody>
    </table>

    <!-- Кнопка "Добавить новую категорию" вне таблицы -->
    <div>
      <!-- Если добавление новой категории активно, покажем инпут и кнопку "Добавить" -->
      <template v-if="addingNewCategory">
        <input v-model="newCategoryName" placeholder="Введите название"/>
        <button @click="addNewCategory" class="btn btn-outline-dark">Добавить</button>
      </template>
      <!-- Если добавление новой категории не активно, покажем кнопку "Добавить новую категорию" -->
      <template v-else>
        <button @click="startAddingNewCategory" class="btn btn-outline-dark">Добавить новую категорию</button>
      </template>
    </div>
  </div>
</template>

<script>

//TODO: Auth check

import { reactive } from "vue";
import * as api from '@/api';

export default {
  data() {
    return {
      categories: [
      ],
      addingNewCategory: false,
      newCategoryName: "",
      getResult: null,
    };
  },
  methods: {
    async fetchData() {
      const data = await api.categories.getAll();

      this.categories = data.map(category => ({
        id: category.id,
        name: category.name,
        isEditing: false,
        originalName: "",
      }));
    },


    editCategory(category) {
      // Запоминаем оригинальное название перед началом редактирования
      category.originalName = category.name;
      // Запускаем редактирование
      category.isEditing = true;
    },

    async saveCategory(category) {

      try {
        await api.categories.update(category.id, { name: category.name });

        category.isEditing = false;
        category.originalName = category.name;

        await this.fetchData();
      } catch (err) {
        this.getResult = err.message;
      }
    },

    cancelEdit(category) {
      category.isEditing = false;
      category.name = category.originalName;
      this.fetchData()
    },

    startAddingNewCategory() {
      // Начинаем добавление новой категории
      this.addingNewCategory = true;
    },

    addNewCategory() {
      const data = reactive({
        name: '',
        limit: ''
      });

      const submit = async () => {
        await api.categories.create(data);
      }

      // Сбрасываем состояние добавления новой категории
      this.addingNewCategory = false;
      this.newCategoryName = "";

      this.fetchData()

      return {
        data,
        submit
      }
    },

    async deleteCategory(category) {
      try {
        await api.categories.delete(category.id);

        await this.fetchData();
      } catch (err) {
        this.getResult = err.message;
      }
    },
  },
};
</script>
