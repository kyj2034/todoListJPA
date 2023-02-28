import axios from 'axios';

const instance = axios.create({
  baseURL: `${window.location.origin}/todolist/`,
});

instance.interceptors.request.use((config) => {
  config.headers.accept = 'application/json';
  config.headers['Content-Type'] = 'application/json; charset=UTF-8';
  return config;
}, (error) => {
  console.debug(error);
  return Promise.reject(error);
});

instance.interceptors.response.use((response) => response, (error) => {
  console.debug(error);
  return Promise.reject(error);
});

export default {
  instance,
};
