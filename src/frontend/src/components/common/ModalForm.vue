Skip to content
Product
Team
Enterprise
Explore
Marketplace
Pricing
Search
Sign in
Sign up
Dev-HyunSang
/
Doit-Vue.js-Todo-List
Public
Code
Issues
Pull requests
1
Actions
Projects
Wiki
Security
Insights
Doit-Vue.js-Todo-List/src/components/common/Modal.vue

HyunSang Park [UPDATE🚀] Vue Modal 추가
Latest commit 52a7599 on 13 Mar 2021
 History
 0 contributors
89 lines (80 sloc)  1.85 KB

<template>
  <transition name="modal">
    <div class="modal-mask">
      <div class="modal-wrapper">
        <div class="modal-container">
          <div class="modal-header">
            <slot name="header">
            </slot>
          </div>

          <div class="modal-body">
            <slot name="body">
            </slot>
          </div>

          <div class="modal-footer">
            <slot name="footer">
              <button class="modal-default-button" @click="$emit('close')">
              </button>
            </slot>
          </div>
        </div>
      </div>
    </div>
  </transition>
</template>
<style>
  .modal-mask {
    position: fixed;
    z-index: 9998;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background-color: rgba(0, 0, 0, 0.5);
    display: table;
    transition: opacity 0.3s ease;
  }
  .modal-wrapper {
    display: table-cell;
    vertical-align: middle;
  }
  .modal-container {
    width: 300px;
    margin: 0px auto;
    padding: 20px 30px;
    background-color: #fff;
    border-radius: 12px;
    box-shadow: 0 2px 8px rgba(0, 0, 0, 0.33);
    transition: all 0.3s ease;
    font-family: Helvetica, Arial, sans-serif;
  }
  .modal-header h3 {
    margin-top: 0;
    text-align: center;
    color: #42b983;
  }
  .modal-body {
    margin: 20px 0;
  }
  .modal-default-button {
    float: right;
  }
  /*
  * The following styles are auto-applied to elements with
  * transition="modal" when their visibility is toggled
  * by Vue.js.
  *
  * You can easily play with the modal transition by editing
  * these styles.
  */
  .modal-enter {
    opacity: 0;
  }
  .modal-leave-active {
    opacity: 0;
  }
  .modal-enter .modal-container,
  .modal-leave-active .modal-container {
    -webkit-transform: scale(1.1);
    transform: scale(1.1);
  }
</style>
