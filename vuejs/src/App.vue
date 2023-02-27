<template>
  <nav>
    <router-link to="/">Accueil</router-link> |
    <router-link to="/groupes">Groupes</router-link> |
    <router-link to="/artistes">Artistes</router-link> |
    <router-link to="/ressources">Ressources</router-link>
  </nav>
  <router-view />
</template>

<style lang="scss">
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

nav {
  padding: 30px;

  a {
    font-weight: bold;
    color: #2c3e50;

    &.router-link-exact-active {
      color: #daae6b;
    }
  }
}
</style>
