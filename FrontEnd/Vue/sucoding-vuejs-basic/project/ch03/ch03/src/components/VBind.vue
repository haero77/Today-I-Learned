<template>
  <div :class="className">Hello Vue.js!!</div>
</template>

<script>
export default {
  name: 'HelloWorld',
  data() {
    return {
      className: 'red-color',
    }
  },
}
</script>

<style>
.red-color {
  color: red;
}
</style>