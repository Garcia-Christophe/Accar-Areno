<template>
  <div class="soirees">
    <h1>Liste des soirées</h1>
    <p id="status"></p>
    <p id="message"></p>
    <table>
      <thead>
        <tr>
          <th>IdSoiree</th>
          <th>Nom</th>
          <th>IdSalle</th>
          <th></th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="soiree in soirees" :key="soiree.idSoiree">
          <td>{{ soiree.idSoiree }}</td>
          <td>{{ soiree.nom }}</td>
          <td>{{ soiree.idSalle }}</td>
          <td>
            <button  v-if="isLoggedInAdmin" @click="editSoiree(soiree)">Modifier</button>
            <button  v-if="isLoggedInAdmin" @click="deleteSoiree(soiree)">Supprimer</button>
            <button @click="soireeVue = soiree">Voir plus</button>
          </td>
        </tr>
      </tbody>
    </table>
    <div  v-if="isLoggedInAdmin">
      <h2>Ajouter une soirée</h2>
      <form @submit.prevent="addSoiree">
        <label>Nom :</label>
        <input type="text" v-model="nouvelleSoiree.nom" required />
        <br />
        <label>Id salle :</label>
        <input type="number" v-model="nouvelleSoiree.idSalle" required />
        <br />
        <button type="submit">Ajouter</button>
      </form>
    </div>
    <div v-if="soireeSelectionnee">
      <h2>Modifier la soirée</h2>
      <form @submit.prevent="updateSoiree">
        <label>Nom :</label>
        <input type="text" v-model="soireeSelectionnee.nom" />
        <br />
        <label>Id salle :</label>
        <input type="number" v-model="soireeSelectionnee.idSalle" />
        <br />
        <button type="submit">Modifier</button>
        <button @click="soireeSelectionnee = null">Annuler</button>
      </form>
    </div>
    <VoirElement
      v-if="soireeVue"
      :key="soireeVue.idSoiree"
      :element="soireeVue"
    />
  </div>
</template>

<script>
import VoirElement from "@/components/VoirElement.vue";
import axios from "axios";

export default {
  data() {
    return {
      soirees: [],
      nouvelleSoiree: {
        nom: null,
        idSalle: null,
      },
      soireeSelectionnee: null,
      soireeVue: null,
      url: "http://localhost:8080/soirees",
      isLoggedInAdmin: "",
    };
  },
  mounted() {
    this.isLoginAdmin();
    this.getSoirees();
  },
  methods: {
    getSoirees() {
      axios
        .get(this.url)
        .then((response) => {
          document.getElementById("status").innerHTML =
            "Status : " +
            (response.status >= 200 && response.status < 300 ? "OK" : "KO");
          document.getElementById("message").innerHTML =
            "Message : " + response.status;
          this.soirees = response.data;
        })
        .catch((error) => {
          console.log(error);
        });
    },
    addSoiree() {
      axios
        .post(this.url, this.nouvelleSoiree)
        .then((response) => {
          document.getElementById("status").innerHTML =
            "Status : " +
            (response.status >= 200 && response.status < 300 ? "OK" : "KO");
          document.getElementById("message").innerHTML =
            "Message : " + response.status;
          this.nouvelleSoiree = {
            nom: null,
            idSalle: null,
          };
          this.getSoirees();
        })
        .catch((error) => {
          console.log(error);
        });
    },
    editSoiree(soiree) {
      this.soireeSelectionnee = soiree;
    },
    updateSoiree() {
      axios
        .put(
          this.url + "/" + this.soireeSelectionnee.idSoiree,
          this.soireeSelectionnee
        )
        .then((response) => {
          document.getElementById("status").innerHTML =
            "Status : " +
            (response.status >= 200 && response.status < 300 ? "OK" : "KO");
          document.getElementById("message").innerHTML =
            "Message : " + response.status;
          this.soireeSelectionnee = null;
        })
        .catch((error) => {
          console.log(error);
        });
    },
    deleteSoiree(soiree) {
      axios
        .delete(this.url + "/" + soiree.idSoiree)
        .then((response) => {
          document.getElementById("status").innerHTML =
            "Status : " +
            (response.status >= 200 && response.status < 300 ? "OK" : "KO");
          document.getElementById("message").innerHTML =
            "Message : " + response.status;
          this.getSoirees();
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
  components: { VoirElement },
};
</script>
