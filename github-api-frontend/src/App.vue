<template>
  <div id="app">
    <img alt="Vue logo" src="./assets/logo.png">
    <RepositorySearchResultList></RepositorySearchResultList>
  </div>
</template>

<script>
import RepositorySearchResultList from './components/RepositorySearchResultList.vue'

export default {
  name: 'app',
  components: {
    RepositorySearchResultList
  }
}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
