<template>
  <div
    class="clearAllContainer"
    @click="clearAll"
  >
    <span class="clearAllBtn" >전체삭제</span>
  </div>
</template>

<script>
import { mapActions } from 'vuex';
import iziToast from 'izitoast';

const todoListStorePrefix = 'todoList/todoListStore';

export default {
  data: () => ({
    showClearBtn: false,
  }),
  methods: {
    ...mapActions(todoListStorePrefix, ['clearAllAct']),
    clearAll(){
      const clearRes = this.clearAllAct();

      if(clearRes){

        iziToast.success({
          title:"전체 삭제 성공 !",
        })

      }
      else{
      
        iziToast.error({
          title:"전체 삭제 실패 !",
        })
        
      }
    }
  },
};
</script>

<style scoped>
  .clearAllContainer {
    width: 8.5rem;
    height: 50px;
    line-height: 50px;
    background-color: white;
    border-radius: 5px;
    margin: 0 auto;
  }
  .clearAllContainer {
    box-shadow: 3px 3px 3px rgb(141, 138, 138);
    transition-duration: 0.3s;
    font-weight: 800;
    color: #e20303;
    display: black;
    cursor: pointer;
  }
  .clearAllContainer:active{
    box-shadow:none;
  }
</style>
