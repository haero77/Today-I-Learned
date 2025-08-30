<template>
  <p v-if="visible">이 요소는 렌더링</p>
  <p v-if="unvisible">이 요소는 렌더링 X</p>
</template>

<script>
export default {
  name: 'HelloWorld',
  data() {
    return {
      visible: true,
      unvisible: false,
    }
  },
}
</script>