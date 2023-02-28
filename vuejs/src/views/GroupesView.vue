<template>
  <div class="groupes">
    <h1>Liste des groupes</h1>
    <p id="status"></p>
    <p id="message"></p>
    <table>
      <thead>
        <tr>
          <th>IdGroupe</th>
          <th>Nom</th>
          <th>NbArtistes</th>
          <th></th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="groupe in groupes" :key="groupe.idGroupe">
          <td>{{ groupe.idGroupe }}</td>
          <td>{{ groupe.nom }}</td>
          <td>{{ groupe.nbArtistes }}</td>
          <td>
            <button @click="editGroupe(groupe)">Modifier</button>
            <button @click="deleteGroupe(groupe)">Supprimer</button>
          </td>
        </tr>
      </tbody>
    </table>
    <div>
      <h2>Ajouter un groupe</h2>
      <form @submit.prevent="addGroupe">
        <label>Nom :</label>
        <input type="text" v-model="nouveauGroupe.nom" required />
        <br />
        <button type="submit">Ajouter</button>
      </form>
    </div>
    <div v-if="groupeSelectionne">
      <h2>Modifier le groupe</h2>
      <form @submit.prevent="updateGroupe">
        <label>Nom :</label>
        <input type="text" v-model="groupeSelectionne.nom" />
        <br />
        <button type="submit">Modifier</button>
        <button @click="groupeSelectionne = null">Annuler</button>
      </form>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      groupes: [],
      nouveauGroupe: {
        nom: null,
      },
      groupeSelectionne: null,
      url: "http://localhost:8079/accarareno/groupes",
    };
  },
  mounted() {
    this.getGroupes();
  },
  methods: {
    getGroupes() {
      axios
        .get(this.url)
        .then((response) => {
          console.log(response);
          document.getElementById("status").innerHTML =
            "Status : " + response.data.status;
          document.getElementById("message").innerHTML =
            "Message : " + response.data.message;
          this.groupes = response.data.data;
        })
        .catch((error) => {
          console.log(error);
        });
    },
    addGroupe() {
      let link = this.url + "?";
      if (this.nouveauGroupe.nom != null) {
        link += "nom=" + this.nouveauGroupe.nom;
      }
      axios
        .post(link)
        .then((response) => {
          document.getElementById("status").innerHTML =
            "Status : " + response.data.status;
          document.getElementById("message").innerHTML =
            "Message : " + response.data.message;
          this.getGroupes();
          this.nouveauGroupe = {
            nom: null,
          };
        })
        .catch((error) => {
          console.log(error);
        });
    },
    editGroupe(groupe) {
      this.groupeSelectionne = groupe;
    },
    updateGroupe() {
      let link = this.url + "?id=" + this.groupeSelectionne.idGroupe;
      if (this.groupeSelectionne.nom != null) {
        link += "&nom=" + this.groupeSelectionne.nom;
      }
      axios
        .put(link)
        .then((response) => {
          document.getElementById("status").innerHTML =
            "Status : " + response.data.status;
          document.getElementById("message").innerHTML =
            "Message : " + response.data.message;
          this.groupeSelectionne = null;
          this.getGroupes();
        })
        .catch((error) => {
          console.log(error);
        });
    },
    deleteGroupe(groupe) {
      axios
        .delete(this.url + "?id=" + groupe.idGroupe)
        .then((response) => {
          document.getElementById("status").innerHTML =
            "Status : " + response.data.status;
          document.getElementById("message").innerHTML =
            "Message : " + response.data.message;
          this.getGroupes();
        })
        .catch((error) => {
          console.log(error);
        });
    },
  },
};
</script>
