<template>
    <div class="utilisateur">
      <h1>Mon compte</h1>
      <p id="status"></p>
      <p id="message"></p>
      <table>
        <thead>
          <tr>
            <th>Id Utilisateur</th>
            <th>Nom</th>
            <th>Mot de passe</th>
            <th></th>
          </tr>
        </thead>
        <tbody>
          <tr v-if="utilisateur!=null">
            <td>{{ utilisateur.idUtilisateur }}</td>
            <td>{{ utilisateur.nom }}</td>
            <td>{{ utilisateur.mdp }}</td>
            <td>
              <button @click="editUtilisateur(utilisateur)">Modifier</button>
              <button @click="deleteUtilisateur(utilisateur)">Supprimer</button>
              <button @click="utilisateurVu = utilisateur">Voir plus</button>
            </td>
          </tr>
        </tbody>
      </table>
      <div v-if="utilisateurSelectionne">
        <h2>Modifier l'utilisateur</h2>
        <form @submit.prevent="updateUtilisateur">
          <label>Nom :</label>
          <input type="text" v-model="utilisateurSelectionne.nom" :readonly="isAdmin" />
          <br />
          <label>Mot de passe :</label>
          <input type="text" v-model="utilisateurSelectionne.mdp" />
          <br />
          <button type="submit">Modifier</button>
          <button @click="utilisateurSelectionne = null">Annuler</button>
        </form>
      </div>
      <VoirElement
        v-if="utilisateurVu"
        :key="utilisateurVu.idUtilisateur"
        :element="utilisateurVu"
      />
    </div>
  </template>
  
  <script>
  import VoirElement from "@/components/VoirElement.vue";
  import axios from "axios";
  
  export default {
    data() {
      return {
        utilisateur: null,
        utilisateurSelectionne: null,
        utilisateurVu: null,
        isAdmin: "",
        url: "http://localhost:8079/accarareno/utilisateurs",
      };
    },
    mounted() {
      this.getUtilisateur();
    },
    methods: {
      getUtilisateur() {
        axios
          .get(this.url+"?id="+localStorage.getItem('idUtilisateur'))
          .then((response) => {
            console.log(response);
            document.getElementById("status").innerHTML =
              "Status : " + response.data.status;
            document.getElementById("message").innerHTML =
              "Message : " + response.data.message;
            this.utilisateur = response.data.data;
          })
          .catch((error) => {
            console.log(error);
          });
      },
      editUtilisateur(utilisateur) {
        this.utilisateurSelectionne = utilisateur;
        if(this.utilisateurSelectionne.nom==="admin"){
            this.isAdmin=true;
        }else{
            this.isAdmin=false;
        }
      },
      updateUtilisateur() {
        let link = this.url + "?id=" + this.utilisateurSelectionne.idUtilisateur + "&";
        let unChangement = false;
        if (this.utilisateurSelectionne.nom != null && !this.isAdmin) {
          link += "nom=" + this.utilisateurSelectionne.nom;
          unChangement = true; 
        }
        if (this.utilisateurSelectionne.mdp != null) {
          if (unChangement) {
            link += "&";
          }
          link += "mdp=" + this.utilisateurSelectionne.mdp;
          unChangement = true;
        }
        axios
          .put(link)
          .then((response) => {
            document.getElementById("status").innerHTML =
              "Status : " + response.data.status;
            document.getElementById("message").innerHTML =
              "Message : " + response.data.message;
            this.utilisateurSelectionne = null;
          })
          .catch((error) => {
            console.log(error);
          });
      },
      deleteUtilisateur(utilisateur) {
        axios
          .delete(this.url + "?id=" + utilisateur.idUtilisateur)
          .then((response) => {
            document.getElementById("status").innerHTML =
              "Status : " + response.data.status;
            document.getElementById("message").innerHTML =
              "Message : " + response.data.message;
            if(response.data.status==="OK"){
                // Supprimez le nom d'utilisateur du stockage local
                localStorage.removeItem('nom');
                localStorage.removeItem('idUtilisateur');
                // Mettez à jour la variable isLoggedIn
                this.isLoggedIn = false;
                location.reload();
            }
          })
          .catch((error) => {
            console.log(error);
          });
      },
    },
    components: { VoirElement },
  };
  </script>
  