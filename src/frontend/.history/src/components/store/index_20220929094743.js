import Vue from 'vue';
import Vuex from 'vuex';

import todoListStore from './todoList/todoListStore.js';
import editModeStore from './editMode/editModeStore.js';
import _ from 'lodash';

Vue.use(Vuex);

export default new Vuex.Store({
  modules: _.merge(
    {
      'todoList/todoListStore': todoListStore,
      'editMode/editModeStore': editModeStore,
    }
  )
});
