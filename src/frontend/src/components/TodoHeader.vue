<template>
  <section>
    <h3>오늘은</h3>
    <div id="date"> {{ today }}</div>
  </section>
</template>

<script>

export default {
  data: () => ({
    today: '',
  }),
  created() {
    this.getDateAct();
  },
  methods: {
    /**
     * 날짜를 가져오는 moment.js를 사용한 메소드
     *
     * @param {*} state
     */
    getDateAct() {
      // eslint-disable-next-line global-require
      const moment = require('moment');
      this.today = moment().format('YYYY.MM.DD');
    },
  },
};
</script>

<style>
h1 {
  text-align: left;
  font-size: xx-large;
  color: #2F3B52;
  font-weight: 900;
  margin: 2.5rem 0 1.5rem;
}
h3 {
  color: #464b56;
  text-align: right;
  margin: 0 0 0 0;
  padding: 0 0 0 0;
}
#date {
  text-align: right;
    color: #6e798d;
    font-weight: 900;
    font-size: 60px;
    margin: 0 0 1.5rem;
}
</style>
