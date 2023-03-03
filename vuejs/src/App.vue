<template>
  <nav v-if="isLoggedInAdmin">
    <router-link to="/">Accueil</router-link> |  
    <router-link to="/concerts">Concerts</router-link> |
    <router-link to="/soirees">Soirees</router-link> |
    <router-link to="/salles">Salles</router-link> |
    <router-link to="/groupes">Groupes</router-link> |
    <router-link to="/artistes">Artistes</router-link> |
    <router-link to="/ressources">Ressources</router-link>|
    <router-link to="/utilisateurs">Utilisateurs</router-link>|
    <router-link to="/compte">Mon Compte</router-link>|
    <router-link to="/connexion">Connexion</router-link>
  </nav>
  <nav v-else-if="isLoggedIn">
    <router-link to="/">Accueil</router-link> |
    <router-link to="/concerts">Concerts</router-link> |
    <router-link to="/soirees">Soirees</router-link> |
    <router-link to="/salles">Salles</router-link> |
    <router-link to="/groupes">Groupes</router-link> |
    <router-link to="/artistes">Artistes</router-link> |
    <router-link to="/ressources">Ressources</router-link>|
    <router-link to="/compte">Mon Compte</router-link>|
    <router-link to="/connexion">Connexion</router-link>
  </nav>
  <nav v-else>
    <router-link to="/">Accueil</router-link> |
    <router-link to="/concerts">Concerts</router-link> |
    <router-link to="/soirees">Soirees</router-link> |
    <router-link to="/salles">Salles</router-link> |
    <router-link to="/groupes">Groupes</router-link> |
    <router-link to="/artistes">Artistes</router-link> |
    <router-link to="/ressources">Ressources</router-link>|
    <router-link to="/connexion">Connexion</router-link>
  </nav>
  <router-view />
</template>

<script>
export default {
  data() {
    return {
      isLoggedInAdmin: "",
      isLoggedIn: "",
    };
  },
  mounted() {
    this.isLogin();

  },
  methods: {
    isLogin() {
        // Récupérez le nom d'utilisateur dans le stockage local du navigateur
        const username = localStorage.getItem('nom');
        // Vérifiez si l'utilisateur est connecté
        if (username==="admin") {
            this.isLoggedInAdmin = true;
            this.isLoggedIn=true;
        }else if (username!==null){
            this.isLoggedInAdmin = false;
            this.isLoggedIn=true;
        }else{
            this.isLoggedInAdmin = false;
            this.isLoggedIn=false;
        }
        console.log(this.isLoggedInAdmin);
    },
  },
};
</script>

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
