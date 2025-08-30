<template>
  <p v-show="condition">이 요소는 렌더링</p>
  <p v-show="!condition">이 요소는 렌더링되지만 감춰짐</p>
</template>

<script>
export default {
  name: 'HelloWorld',
  data() {
    return {
      condition: true,
    }
  },
}
</script>