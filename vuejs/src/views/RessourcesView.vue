<template>
  <div class="ressources">
    <h1>Liste des Ressources</h1>
    <p id="status"></p>
    <p id="message"></p>
    <label for="searchInput">Recherche de ressources :</label>
    <input
      style="width: 400px"
      type="text"
      v-model="searchInput"
      id="searchInput"
      placeholder="Un groupe, un concert, une soirée, une salle..."
    />
    <button @click="getRessources()">Rechercher</button>
    <table>
      <thead>
        <tr>
          <th>Type</th>
          <th>Auteur</th>
          <th>Date</th>
          <th>URL</th>
          <th></th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="res in ressources" :key="res.url">
          <td>{{ res.type }}</td>
          <td>{{ res.auteur }}</td>
          <td>{{ res.date }}</td>
          <td>
            <img
              v-if="res.type === 'image'"
              :src="res.url"
              height="100"
              width="100"
              style="overflow: hidden"
            />
            <a v-else :href="res.url">L'article</a>
          </td>
          <td v-if="isLoggedInAdmin">
            <button @click="editRessource(res)">Modifier</button>
            <button @click="deleteRessource(res)">Supprimer</button>
            <button @click="removeRessource(res)">Retirer</button>
          </td>
        </tr>
      </tbody>
    </table>
    <div v-if="isLoggedInAdmin">
      <h2>Ajouter une ressource</h2>
      <form @submit.prevent="addRessource">
        <div class="form-group">
          <label for="url">URL</label>
          <input
            type="text"
            class="form-control"
            v-model="ressource.url"
            id="url"
            placeholder="https://"
            required
          />
        </div>
        <div class="form-group">
          <label for="type">Type</label>
          <input
            type="text"
            class="form-control"
            v-model="ressource.type"
            id="type"
            placeholder="image ou article"
            required
          />
        </div>
        <div class="form-group">
          <label for="author">Auteur</label>
          <input
            type="text"
            class="form-control"
            v-model="ressource.auteur"
            id="author"
            placeholder="Nom de l'auteur"
            required
          />
        </div>
        <div class="form-group">
          <label for="date">Date</label>
          <input
            type="date"
            class="form-control"
            v-model="ressource.date"
            id="date"
            placeholder="Date de publication"
            required
          />
        </div>
        <button type="submit">Ajouter</button>
      </form>
    </div>
    <div v-if="ressourceSelectionnee">
      <h2>Modifier la ressource</h2>
      <form @submit.prevent="updateRessource">
        <label>Url :</label>
        <input type="text" v-model="ressourceSelectionnee.url" />
        <br />
        <label>Type :</label>
        <input type="text" v-model="ressourceSelectionnee.type" />
        <br />
        <label>Auteur :</label>
        <input type="text" v-model="ressourceSelectionnee.auteur" />
        <br />
        <label>Date :</label>
        <input type="date" v-model="ressourceSelectionnee.date" />
        <br />
        <label>Ajouter un sujet :</label>
        <select
          name="sujets"
          id="sujets"
          :value="ressourceSelectionnee.sujetType"
          @change="(e) => (ressourceSelectionnee.sujetType = e.target.value)"
        >
          <option value="groupe" selected>Groupe</option>
          <option value="concert">Concert</option>
          <option value="soiree">Soiree</option>
          <option value="salle">Salle</option>
        </select>
        <input type="text" v-model="ressourceSelectionnee.sujet" />
        <button type="submit">Modifier</button>
        <button @click="ressourceSelectionnee = null">Annuler</button>
      </form>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      ressources: [],
      ressource: {
        url: "",
        type: "",
        auteur: "",
        date: "",
      },
      ressourceSelectionnee: null,
      previousUrl: null,
      searchInput: "",
      url: "http://localhost:8079/accarareno/ressources",
      isLoggedInAdmin: "",
    };
  },
  mounted() {
    this.isLoginAdmin();
  },
  methods: {
    getRessources() {
      let link = this.url + "?" + "nom=" + this.searchInput;
      axios
        .get(link)
        .then((response) => {
          document.getElementById("status").innerHTML =
            "Status : " + response.data.status;
          document.getElementById("message").innerHTML =
            "Message : " + response.data.message;
          this.ressources = response.data.data;
        })
        .catch((error) => {
          console.log(error);
        });
    },
    addRessource() {
      let link =
        this.url +
        "?url=" +
        encodeURIComponent(this.ressource.url) +
        "&type=" +
        this.ressource.type +
        "&auteur=" +
        this.ressource.auteur +
        "&date=" +
        this.ressource.date;
      axios
        .post(link)
        .then((response) => {
          document.getElementById("status").innerHTML =
            "Status : " + response.data.status;
          document.getElementById("message").innerHTML =
            "Message : " + response.data.message;
          this.getRessources();
          this.ressource = {
            url: "",
            type: "",
            auteur: "",
            date: "",
          };
        })
        .catch((error) => {
          console.log(error);
        });
    },
    editRessource(ressource) {
      this.ressourceSelectionnee = ressource;
      this.previousUrl = this.ressourceSelectionnee.url;
    },
    updateRessource() {
      let link =
        this.url +
        "?previousUrl=" +
        encodeURIComponent(this.previousUrl) +
        "&url=" +
        encodeURIComponent(this.ressourceSelectionnee.url) +
        "&type=" +
        this.ressourceSelectionnee.type +
        "&auteur=" +
        this.ressourceSelectionnee.auteur +
        "&date=" +
        this.ressourceSelectionnee.date +
        "&sujetType=" +
        this.ressourceSelectionnee.sujetType +
        "&sujet=" +
        this.ressourceSelectionnee.sujet;
      axios
        .put(link)
        .then((response) => {
          document.getElementById("status").innerHTML =
            "Status : " + response.data.status;
          document.getElementById("message").innerHTML =
            "Message : " + response.data.message;
          this.ressourceSelectionnee = null;
          this.previousUrl = null;
          this.getRessources();
        })
        .catch((error) => {
          console.log(error);
        });
    },
    deleteRessource(res) {
      let link = this.url + "?url=" + encodeURIComponent(res.url);
      axios
        .delete(link)
        .then((response) => {
          document.getElementById("status").innerHTML =
            "Status : " + response.data.status;
          document.getElementById("message").innerHTML =
            "Message : " + response.data.message;
          this.getRessources();
        })
        .catch((error) => {
          console.log(error);
        });
    },
    removeRessource(res) {
      let link =
        this.url +
        "?url=" +
        encodeURIComponent(res.url) +
        "&nom=" +
        this.searchInput;
      axios
        .delete(link)
        .then((response) => {
          document.getElementById("status").innerHTML =
            "Status : " + response.data.status;
          document.getElementById("message").innerHTML =
            "Message : " + response.data.message;
          this.getRessources();
        })
        .catch((error) => {
          console.log(error);
        });
    },
    isLoginAdmin() {
        // Récupérez le nom d'utilisateur dans le stockage local du navigateur
        const username = localStorage.getItem('nom');
        // Vérifiez si l'utilisateur est connecté
        if (username==="admin") {
            this.isLoggedInAdmin = true;
        }else{
            this.isLoggedInAdmin = false;
        }
        console.log(this.isLoggedInAdmin);
    },
  },
};
</script>
