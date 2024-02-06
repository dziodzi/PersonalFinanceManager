<template>
  <div>
    <form @submit.prevent="saveExpense">
      <div class="mb-3">
        <label for="categoryDropdown" class="form-label">Выберите категорию:</label>
        <select @click="fetchData" class="form-select" id="categoryDropdown" v-model="selectedCategory">
          <option v-for="category in categories" :key="category.id" :value="category.name">{{ category.name }}, id: {{ category.id }}</option>
        </select>
      </div>
      <div class="mb-3">
        <label for="amountInput" class="form-label">Введите сумму:</label>
        <input type="number" class="form-control" id="amountInput" v-model="expenseAmount">
      </div>
      <button type="submit" class="btn btn-primary">Сохранить</button>
    </form>

    <div v-if="showAlert" class="alert alert-success mt-3" role="alert">
      Расход успешно создан!
    </div>
  </div>
</template>

<script>

//TODO: Auth check

import * as api from '@/api';

export default {

  data() {
    return {
      selectedCategory: '',
      expenseAmount: null,
      showAlert: false,
      categories: [],
    };
  },
  methods: {

    async fetchData() {
      const data = await api.categories.getAll();

      console.log(data);

      this.categories = data.map(category => ({
        id: category.id,
        name: category.name,
        spendLimit: category.spendLimit,
        originalName: "",
      }));
    },

    async saveExpense() {
      const data = ({
        amount: this.expenseAmount, date: new Date(),
        description: 'myExpense',
        operationType: 'Expense',
        categoryName: this.selectedCategory,
        userId: '1',
      });

      await api.operations.create(data);


      this.showAlert = true;

      setTimeout(() => {
        this.showAlert = false;
      }, 2000);
    }
  },
};
</script>
