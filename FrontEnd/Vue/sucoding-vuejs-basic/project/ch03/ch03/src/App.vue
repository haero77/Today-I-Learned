<template>
  <!-- <HelloWorld /> -->
  <!--  <VBind />-->
<!--  <VIf/>-->
  <VShow/>
</template>

<script>
import HelloWorld from "@/components/HelloWorld.vue";
import VBind from './components/VBind.vue'
import VIf from './components/VIf.vue'
import VShow from './components/VShow.vue'

export default {
  components: {
    HelloWorld,
    VBind,
    VIf,
    VShow,

  }
}
</script>