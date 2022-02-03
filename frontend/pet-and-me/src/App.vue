<template>
  <div id="app" class="container">
    <Navbar/>
    <router-view/>
  </div>
</template>
<script>
import Navbar from '@/views/navbar/Navbar'

export default {
  components: {
    Navbar
  }
}
</script>

<style>
html,
body {
  margin: 0px;
  padding: 0px;
  box-sizing: border-box;
  font-family: "Quicksand", sans-serif;
  font-size: 62.5%;
  font-size: 10px;
}

</style>