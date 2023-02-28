import Vue from 'vue';
import App from './App.vue';

// store.js를 불러오는 코드
// removeTodo : Todo 삭제, checkedItem : 체크된 아이템 인덱스와 수정버튼이 보이도록 on, changeItem: 리스트 아이템을 수정
import store from './store/index';

// 함수를 파라미터로 받고 이 함수에 App을 넘겨받아 HTML 구조를 생성한다.
new Vue({
  store,
  render: (h) => h(App),
}).$mount('#app');
// h 는 hyperscript 의 약자로 Virtual DOM에서 관용적으로 사용되는 표현이다.
// render : h => h(App)
// 즉, render : function (createElement) {
//     return createElement(App);
// }
//  render: fucntion (h) {
//     return h(App);
// }
//  render: h => {return h(App)}
//  render : h => h(App)
//
