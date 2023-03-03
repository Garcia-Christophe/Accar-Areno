<template>
    <div class="utilisateurs">
      <h1>Liste des utilisateurs</h1>
      <p id="status"></p>
      <p id="message"></p>
      <table>
        <thead>
          <tr>
            <th>IdUtilisateur</th>
            <th>Nom</th>
            <th>Mot de passe</th>
            <th></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="utilisateur in utilisateurs" :key="utilisateur.idUtilisateur">
            <td>{{ utilisateur.idUtilisateur }}</td>
            <td>{{ utilisateur.nom }}</td>
            <td>{{ utilisateur.mdp }}</td>
            <td>
              <button @click="editUtilisateur(utilisateur)">Modifier</button>
              <button @click="deleteUtilisateur(utilisateur)">Supprimer</button>
            </td>
          </tr>
        </tbody>
      </table>
      <div>
        <h2>Ajouter un utilisateur</h2>
        <form @submit.prevent="addUtilisateur">
          <label>Nom :</label>
          <input type="text" v-model="nouvelUtilisateur.nom" required />
          <br />
          <label>Mot de passe :</label>
          <input type="text" v-model="nouvelUtilisateur.mdp" />
          <br />
          <br />
          <button type="submit">Ajouter</button>
        </form>
      </div>
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
    </div>
  </template>
  
  <script>
  import axios from "axios";
  
  export default {
    data() {
      return {
        utilisateurs: [],
        nouvelUtilisateur: {
          nom: null,
          mdp: null,
        },
        utilisateurSelectionne: null,
        isAdmin: "",
        url: "http://localhost:8079/accarareno/utilisateurs",
      };
    },
    mounted() {
      this.getUtilisateurs();
    },
    methods: {
      getUtilisateurs() {
        axios
          .get(this.url)
          .then((response) => {
            console.log(response);
            document.getElementById("status").innerHTML =
              "Status : " + response.data.status;
            document.getElementById("message").innerHTML =
              "Message : " + response.data.message;
            this.utilisateurs = response.data.data;
          })
          .catch((error) => {
            console.log(error);
          });
      },
      addUtilisateur() {
        let link = this.url + "?";
        let unChangement = false;
        if (this.nouvelUtilisateur.nom != null) {
          link += "nom=" + this.nouvelUtilisateur.nom;
          unChangement = true;
        }
        if (this.nouvelUtilisateur.mdp != null) {
          if (unChangement) {
            link += "&";
          }
          link += "mdp=" + this.nouvelUtilisateur.mdp;
          unChangement = true;
        }
        axios
          .post(link)
          .then((response) => {
            document.getElementById("status").innerHTML =
              "Status : " + response.data.status;
            document.getElementById("message").innerHTML =
              "Message : " + response.data.message;
            this.nouvelUtilisateur = {
              nom: null,
              mdp: null,
            };
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
          })
          .catch((error) => {
            console.log(error);
          });
      },
    },
  };
  </script>
  