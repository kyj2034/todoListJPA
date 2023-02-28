<template>
  <section>
    <!-- 컴포넌트는 반드시 부모 하나로 감싸야 한다. -->
    <transition-group
      tag="ul"
      name="list"
    >
      <!-- 렌더링 되는 요소의 삽입, 제거 및 순서 변경을 애니메이션으로 만드는 내장 구성요소 -->
      <!-- transition-group 을 사용하기 위해선 요소에 key 값을 지정해줘야 한다.  -->
      <li
        v-for="({todo, finish, key}, idx) in todoList"
        class="shadow"
        :key="key"
        :class="finish ? 'checked' : '' "
      >
        <label class="checkbox">
          <input
            type="checkbox"
            id="chkBox"
            @change="finTodo({key, todo, finish, idx})"
          />
          <span class="checkbox-icon"></span>
          <span class="checkbox-text">
            {{ todo }}
          </span>
        </label>
        <div class="btnGroup">
          <!-- 수정할 목록을 선택하는 라디오 버튼 -->
          <span class="fixBtn">
            <input
              class="radio"
              type="radio"
              name="check"
              v-model="chkUpdIdx"
              :value="idx"
              v-show="!finish && getUpdating"
              @change="setChkUpdIdx()"
            />
          </span>
          <!-- 삭제할 목록을 선택하는 체크박스 -->
          <label class="checkbox" v-show="getDeleting">
            <i class="far fa-trash-alt" aria-hidden="true"></i>
            <input
              type="checkbox"
              id="chkid"
              v-model="chkDelIdx"
              :value="idx"
              @change="setDelList()"
            />
            <span class="checkbox-icon"></span>
          </label>
        </div>
      </li>
    </transition-group>
  </section>
</template>

<script>
import { mapGetters, mapActions } from 'vuex';
import iziToast from 'izitoast';

const todoListStorePrefix = 'todoList/todoListStore';
const editModeStorePrefix = 'editMode/editModeStore';


export default {
  components: {
  },
  data: () => ({
    updTodoVal: '',
    chkDelIdx: [],
    chkUpdIdx: Number,
  }),
  created() {
    this.loadTodoAct();
  },
  computed: {
    // to Fix : 수정모드 on/off Boolean, showCheck : 수정버튼 on/off Boolean, chkIdx : 체크한 아이템 리스트 인덱스
    // getTodoList : 투두리스트(배열)를 가져옴
    ...mapGetters({
      getTodoList: `${todoListStorePrefix}/getTodoList`,
      getChkUpdIdx: `${todoListStorePrefix}/getChkUpdIdx`,
      getChkDelIdx: `${todoListStorePrefix}/getChkDelIdx`,
      getUpdating: `${editModeStorePrefix}/getUpdating`,
      getDeleting: `${editModeStorePrefix}/getDeleting`,
    }),
    todoList: {
      get() {
        return this.getTodoList;
      },
      set(val) {
        this.setTodoListAct(val);
      },
    },
  },
  // removeTodo : Todo 삭제, checkedItem : 체크된 아이템 인덱스와 수정버튼이 보이도록 on, changeItem: 리스트 아이템을 수정
  methods: {
    ...mapActions(todoListStorePrefix,
    [
      'setChkUpdIdxAct',
      'setChkDelIdxAct',
      'finishTodoAct',
      'loadTodoAct',
      'setTodoListAct',
    ]),
    /**
     * 체크된 리스트의 인덱스 값을 받아 해당 리스트의 값을 수정한 뒤, 다시 리스트를 갱신하는 메소드
     *
     * @param {Number} idx
     */
    setChkUpdIdx() {
      this.setChkUpdIdxAct(this.chkUpdIdx);
    },
    setDelList() {
      this.setChkDelIdxAct(this.chkDelIdx);
    },
    finTodo({ key, todo, finish, idx}) {
      const finItem = { key, todo, finish: !finish, idx};
      const fin = this.finishTodoAct(finItem);

      if( fin ){
        iziToast.success({
          title : '성공!',
          message : '업데이트 성공 !'});
      }
      else{
        iziToast.error({
          title : '오류!',
          message : '업데이트 실패 !'
        });
        this.setTodoListAct([]);
      }
    },
  },
};
</script>

<style scoped>
    @import "izitoast/dist/css/iziToast.min.css";

    .radio{
      cursor: pointer;
    }
    .checkbox-text{
      margin-left: 1em;
    }
    .checkbox input {
      display: none;
    }
    .checkbox-icon {
      width : 20px;
      vertical-align: middle;
      cursor:pointer;
      display: inline-block;
      height: 20px;
      background-color: transparent;
      border: 1px solid rgb(68, 71, 159);
      border: 1px solid rgb(68, 71, 159);
      position: relative;
      cursor: pointer;
    }
    .checkbox-icon::before, .checkbox-icon::after {
      content: ''; /* 가상요소 필수값*/
      display: inline-block; /*크기 지정*/
      width: 1px;
      height: 0; /*체크박스가 체크가 되면 변화값으로 커지게 하기 위해 (일단 화면에는 나타나지 않음)*/
      background-color: rgb(68, 71, 159);
      position: absolute; /* 체크박스 테두리 기준으로 위치조정 가능 */
      transform-origin: left top; /* 기울기 지정, 기준점을 왼쪽 상단 모서리로 (기본값은 중심임)*/
    }
    /*가상요소 before*/
    .checkbox-icon::before {
      top: 9px; /* 위치값 top*/
      left: 2px; /* 위치값 left*/
      transform: rotate(-45deg); /* 회전값 */
    }
    /* 가상요소 after */
    .checkbox-icon::after {
      top: 16px; /* 위치값 top */
      left: 9px; /* 위치값 left */
      transform: rotate(-135deg); /* 회전값 */
    }

    /* 체크되었을 때 테투리 설정 */
    .checkbox input:checked + .checkbox-icon {
      border-color: rgb(68, 71, 159);
      border-color: rgb(68, 71, 159);
    }

    /* 체크되었을 때 가상요소 before*/
    .checkbox input:checked + .checkbox-icon::before {
      height: 10px; /* 높이값 지정 */
      transition: all 0.15s ease; /* 0.15초 변화시간 줌 */
    }

    /* 체크되었을 때 가상요소 after */
    .checkbox input:checked + .checkbox-icon::after {
      height: 20px; /* 높이값 지정 */
      transition: all 0.15s ease 0.15s; /* 0.15초 변화시간 + 딜레이 시간 줌 */
    }
    .checked {
      color : rgb(221, 221, 221);
    }
    input:focus {
        outline: none;
    }
    input {
        border-style: none;
        font-size: 0.9rem;
    }
    .sortable-ghost {background: linear-gradient(0.25turn, #b9b3d0, #ffffff);}
    .list-enter-active, .list-leave-active {
        transition: all 1s;
    }
    .list-enter, .list-leave-to {
        opacity: 0;
        /*transform: translateY(30px);*/
    }

    ul {
        list-style-type: none;
        padding-left: 0px;
        margin-top: 0;
        text-align: left;
    }
    li {
        display: flex;
        min-height: 50px;
        height: 50px;
        line-height: 50px;
        margin: 0.5rem 0;
        padding: 0 0.9rem;
        background: white;
        border-radius: 5px;
    }
    .btnGroup {
        margin-left: auto;
    }
    .fixBtn {
        color: #5c6286;
        margin-right: 20px;
    }
    .removeBtn {
      color: #393939;
    }
</style>
