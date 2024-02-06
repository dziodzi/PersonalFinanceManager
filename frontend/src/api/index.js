import axios from 'axios';
import store from "@/store";

const baseURL = 'http://localhost:8080';

const api = axios.create({
    baseURL,
    headers: {
        'Content-Type': 'application/json',
    },
});

const apiAuth = axios.create({
    baseURL: `${baseURL}/api/auth`,
    headers: {
        'Content-Type': 'application/json',
    },
});

// Add an interceptor to include the Authorization header in every request
api.interceptors.request.use(
    (config) => {
        const accessToken = store.state.accessToken;
        if (accessToken) {
            config.headers.Authorization = `Bearer ${accessToken}`;
        }
        return config;
    },
    (error) => Promise.reject(error)
);

apiAuth.interceptors.response.use(
    (response) => response.data,
    (error) => Promise.reject(error)
);

api.interceptors.response.use(
    (response) => response.data,
    (error) => Promise.reject(error)
);

// Wrapper function for requests with global error handling
const makeRequest = async (method, url, data) => {
    try {
        return await method(url, data);
    } catch (error) {
        console.error('API Request Error:', error);
        throw error;
    }
};

export const auth = {
    login: async (userData) => {
        return makeRequest(apiAuth.post, '/login', userData);
    },
    register: async (userData) => {
        return makeRequest(apiAuth.post, '/register', userData);
    },
}

export const categories = {
    create: async (data) => {
        console.log('create');
        console.log(data);
        return makeRequest(api.post, `/category?name=${data.name}&limit=${data.spendLimit}&userId=${data.userId}`);
    },
    getAll: async () => {
        console.log('getAll');
        return makeRequest(api.get, '/category/getAll');
    },
    delete: async (id) => {
        return makeRequest(api.delete, `/category?id=${id}`);
    },

    get: async (id) => {
        return makeRequest(api.get, `/category/byid?id=${id}`);
    },
    getByName: async (name) => {
        return makeRequest(api.get, `/category/byname?name=${name}`);
    },
}

export const operations = {
    create: async (operationData) => {
        return makeRequest(api.post, '/operation', operationData);
    },
    getAll: async () => {
        return makeRequest(api.get, '/operation/getAll');
    },
}
