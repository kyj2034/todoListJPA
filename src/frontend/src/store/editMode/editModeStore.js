
const state = () => ({
  updating: false,
  deleting: false,
  showModal: false,
  showAddForm: false,
});

const actions = {
  setUpdatingAct: ({ commit }) => commit('setUpdating'),
  setDeletingAct: ({ commit }) => commit('setDeleting'),
  setShowModalAct: ({ commit }, payload) => commit('setShowModal', payload),
  setShowAddFormAct: ({ commit }, payload) => commit('setShowAddForm', payload),
}

const mutations = {
  setUpdating: (state) => { state.updating = !state.updating; },
  setDeleting: (state) => { state.deleting = !state.deleting; },
  setShowModal: (state, payload) => { state.showModal = payload; },
  setShowAddForm: (state, payload) => { state.showAddForm = payload; },
} 

const getters = {
  getUpdating: (state) => state.updating,
  getDeleting: (state) => state.deleting,
  getShowModal: (state) => state.showModal,
  getShowAddForm: (state) => state.showAddForm,
}

export default {
  namespaced:true,
  state,
  actions,
  mutations,
  getters,
};
