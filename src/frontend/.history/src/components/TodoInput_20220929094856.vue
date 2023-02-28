<template>
  <div class="inputBox shadow" v-show="getShowAddForm">
    <input
      type="text"
      v-model= "newTodoVal"
      placeholder="오늘 할 일을 입력하세요."
      v-on:keyup.enter="addTodoItem"
    >
    <span class="addContainer" v-on:click="addTodoItem"> <!-- 대신 클릭 이벤트를 받을 태그 -->
      <i class="addBtn fas fa-plus" aria-hidden="true"></i> <!-- 어썸 아이콘의 [+] 아이콘을 추가 -->
    </span>
    <modal v-if="getShowModal" @close="setShowModalAct(false)">
      <h3 slot="header">잠깐!</h3>
      <span slot="footer" @click="setShowModalAct(false)">
        할 일을 입력하세요.
        <i class ="closeModalBtn fas fa-times" aria-hidden="true"></i>
      </span>
    </modal>
  </div>
</template>

<script>
  
import { mapGetters, mapActions } from 'vuex';
import { v4 } from 'uuid';
import Modal from './common/ModalForm.vue';
import iziToast from 'izitoast';

const todoListStorePrefix = 'todoList/todoListStore';
const editModeStorePrefix = 'editMode/editModeStore';

export default {
  components: {
    Modal,
  },
  data: () => ({
    newTodoVal: '',
  }),
  computed: { // showModal : 모달 on/off Boolean
    ...mapGetters({
      getShowModal: `${editModeStorePrefix}/getShowModal`,
      getShowAddForm: `${editModeStorePrefix}/getShowAddForm`,
    }),
  },
  methods: {
    ...mapActions(todoListStorePrefix
      ,[
        'addTodoAct', ]),
    ...mapActions(editModeStorePrefix
      ,[
        'setShowModalAct',
    ]),
    addTodoItem() {
      let add = null;
      if (this.newTodoVal !== '') { // 인풋박스에 값이 있을 때만 저장
        // && 결과가 옳을 경우, 두번째 값을 반환. 즉, 공백 문자열이 제거된 아이템이 반환
        const value = this.newTodoVal && this.newTodoVal.trim();
        const item = { todo: value, finish: false, key: this.createUuid() };
        add =this.addTodoAct(item);
      } else {
        this.setShowModalAct(!this.getShowModal); // 텍스트 미입력 시 모달 동작
      }
      this.newTodoVal = '';
      console.debug(add);

      if(add){
        iziToast.success(
          {
            title : '성공',
            message : '할 일 등록 완료 !'
          }
        )
      }
      else{
        iziToast.error(
          {
            title: '오류',
            message: '할 일 등록 실패 !'
          }
        )
      }
    },
    createUuid: () => {
      const tokens = v4().split('-');
      return tokens[2] + tokens[1] + tokens[0] + tokens[3] + tokens[4];
    },
  },
};
</script>

<style scoped>
  input:focus{
    outline: none;
  }
  .inputBox{
    background:white;
    height: 50px;
    line-height: 50px;
    border-radius: 5px;

  }
  .inputBox input{
    border-style: none;
    font-size: 0.9rem;
  }
  .addContainer{
    float: right;
    background: linear-gradient(to right, #6478FB, #8763FB);
    display: block;
    width: 3rem;
    border-radius: 0 5px 5px 0;
    box-shadow: 3px 3px 3px rgb(141, 138, 138);
    transition-duration: 0.3s;
  }
  .addContainer:active {
    box-shadow:none;
  }
  .addBtn{

    color: white;
    vertical-align: middle;
  }
</style>
