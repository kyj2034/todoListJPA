import axios from '../../plugin/axios';

const state = {
  newTodoItem: '',
  todoList: [],
  chkIdx: null,
  chkUpdIdx: null,
  chkDelIdx: [],
}

const actions = {

  setTodoItemAct: ({ commit }, payload) => commit('setTodoItem', payload),
  setTodoListAct: ({ commit }, payload) => commit('setTodoList', payload),
  setChkIdxAct: ({commit}, payload) => commit('setChkIdx', payload),
  setChkUpdIdxAct: ({ commit }, payload) => commit('setChkUpdIdx', payload),
  setChkDelIdxAct: ({ commit }, payload) => commit('setChkDelIdx', payload),

  /**
   * TodoInput에서 받아온 todoItem을 localStorage에 set하고 todoList 배열에 push
   *
   * @param {*} commit
   * @param {Object} payload ::: Add 할 todoItem {val: String, finish: Boolean, finish: false}
   */

  async addTodoAct({commit}, payload) { // action에서는 데이터를 푸시하고 삭제하는 기능만!!
    const addRes = await axios.instance({
      method: 'POST',
      data: payload,
    });
      console.debug(addRes);
      commit('saveListData', addRes.data.data);
      return addRes.data.success;
    },
    
  /**
   * localStorage에 있는 모든 데이터를 todoList 안에 담긴 todoItem의 key값으로 삭제
   * 
   * @param {*} commit
   */
  
  async clearAllAct({commit}) {
    commit('setTodoList', []);
    const clearRes = await axios.instance({
      method:'DELETE',
      url:'/all'
    })
    return clearRes.data.success;
  },
  
  /**
   * 선택한 todoList의 key List를 받아와 todoItem 삭제
   *
   * @param {*} commit
   * @param {List<String>} payload ::: 삭제할 투두리스트의 key List
   */

  async removeTodoAct({commit}, payload) {

    commit('removeListData');
    const removeRes = await axios.instance({
      method:'DELETE',
      url:`/${payload}`
    });
    return removeRes.data.success;
  },
  
  /**
   * todoList에서 선택된 index에 위치한 todoItem의 val 값을 수정한다.
   *
   * @param {*} state
   * @param {Object} payload ::: Update할 TodoItem {key: String, val:String, finish:Boolen}
   */

  async updateTodoAct({commit}, payload) {
    const updRes = await axios.instance({
      method: 'PUT',
      url:`/${payload.key}`,
      data: payload
    });
    commit('updateListDataTodo', updRes.data.data.todo);
    return updRes.data.success;
  },
  
  /**
   *완료한 todoList의 Object를 받아와 해당 todoItem의 finish를 false 에서 true로 변경하여 todoItem의 style을 변경시킴
   *
   * @param {*} state
   * @param {Object} payload ::: finish 변경된 TodoItem {key: String, val: String, finish: Boolean}
   */

  async finishTodoAct({commit}, { key, todo, finish, idx}) {
    const finItem = { todo, finish, key };
    commit('updateListDataFin', idx);
    const updRes = await axios.instance({
      method:'PUT',
      
      url:`/${key}`,
      data: finItem
    });
    return updRes.data.success;
  },

  /**
   * localStorage에 저장되어 있는 데이터를 받아와 todoList 배열에 담는다
   *
   * @param {*} state
   */

  async loadTodoAct({ commit }) {  
    const loadRes = await axios.instance({
      method:'GET',
      url:'/all',
    });
    commit('setTodoList', loadRes.data.data);
    return loadRes.data.success;
  },
}

const mutations = {
  /* Setter 기능을 하는 mutation */
  setTodoList: (state, payload) => { state.todoList = payload; },
  setChkIdx: (state, payload) => { state.chkIdx = payload; },
  setChkUpdIdx: (state, payload) => { state.chkUpdIdx = payload; },
  setChkDelIdx: (state, payload) => { state.chkDelIdx = payload; },

  removeListData : (state) =>{
    const list = state.todoList;
    const delList = state.chkDelIdx;
    for(const idx of delList){
      list.splice(idx, 1);
    }
    state.todoList = list;
  },
  saveListData : (state,payload) => {
    const list = state.todoList;
    list.push(payload);
    state.todoList = list;
  },
  updateListDataTodo : (state,payload) => {
    const list = state.todoList;
    list[state.chkUpdIdx].todo = payload;
    state.todoList = list;
  },
  updateListDataFin : (state, payload) =>  {
    const list = state.todoList;
    list[payload].finish = !list[payload].finish;
    state.todoList = list;
  }
}

const getters = {
  getTodoList: (state) => state.todoList,
  getChkIdx: (state) => state.chkIdx,
  getChkUpdIdx: (state) => state.chkUpdIdx,
  getChkDelIdx: (state) => state.chkDelIdx,
}

export default {
  namespaced: true,
  state,
  actions,
  mutations,
  getters,
};

