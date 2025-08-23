<template>
  <div v-html="message"></div>
</template>

<script>
export default {
  data() {
    return {
      message: '<h1>Hello Vue.js!!</h1>'
    }
  },
}
</script>