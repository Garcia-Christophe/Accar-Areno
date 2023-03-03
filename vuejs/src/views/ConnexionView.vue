<template>
  <div v-if="!isLoggedIn">
    <h1>Connexion</h1>
    <form>
      <label for="idUtilisateur">Identifiant:</label>
      <input type="text" id="idUtilisateur" v-model="idUtilisateur">
      <br />
      <label for="mdp">Mot de passe:</label>
      <input type="password" id="mdp" v-model="mdp">
      <br />
      <button @click.prevent="login">Se connecter</button>
    </form>
  </div>
  <div v-else>
      <button @click.prevent="logout">Déconnexion</button>
    </div>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      idUtilisateur: "",
      mdp: "",
      isLoggedIn: "",
      url: "http://localhost:8079/accarareno/utilisateurs",
    };
  },
  mounted() {
    this.isLogin();
  },
  methods: {
    login() {
      axios
        .get(this.url+ "?id=" + this.idUtilisateur)
        .then((response) => {
          if(response.data.data.mdp === this.mdp){
                console.log("connecté");
                localStorage.setItem('nom', response.data.data.nom);
                localStorage.setItem('idUtilisateur', response.data.data.idUtilisateur);
                location.reload();
          }else{
                alert("mdp pas bon");
          }
        })
        .catch((error) => {
          alert("login pas bon");
        });
    },
    isLogin() {
        // Récupérez le nom d'utilisateur dans le stockage local du navigateur
        const username = localStorage.getItem('nom');
        // Vérifiez si l'utilisateur est connecté
        if (username) {
            this.isLoggedIn = true;
        }else{
            this.isLoggedIn = false;
        }
    },
    logout() {
        // Supprimez le nom d'utilisateur du stockage local
        localStorage.removeItem('nom');
        localStorage.removeItem('idUtilisateur');
        // Mettez à jour la variable isLoggedIn
        this.isLoggedIn = false;
        location.reload();
    }
  },
};
</script>
