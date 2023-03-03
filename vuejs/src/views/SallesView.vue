<template>
  <div class="salles">
    <h1>Liste des salles</h1>
    <p id="status"></p>
    <p id="message"></p>
    <table>
      <thead>
        <tr>
          <th>IdSalle</th>
          <th>Nom</th>
          <th>Adresse</th>
          <th>Capacité</th>
          <th>NomGestionnaire</th>
          <th>PrénomGestionnaire</th>
          <th>Association</th>
          <th></th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="salle in salles" :key="salle.idSalle">
          <td>{{ salle.idSalle }}</td>
          <td>{{ salle.nom }}</td>
          <td>{{ salle.adresse }}</td>
          <td>{{ salle.capacite }}</td>
          <td>{{ salle.nomGest }}</td>
          <td>{{ salle.prenomGest }}</td>
          <td>{{ salle.association }}</td>
          <td>
            <button  v-if="isLoggedInAdmin" @click="editSalle(salle)">Modifier</button>
            <button  v-if="isLoggedInAdmin" @click="deleteSalle(salle)">Supprimer</button>
            <button @click="salleVue = salle">Voir plus</button>
          </td>
        </tr>
      </tbody>
    </table>
    <div  v-if="isLoggedInAdmin">
      <h2>Ajouter une salle</h2>
      <form @submit.prevent="addSalle">
        <label>Nom :</label>
        <input type="text" v-model="nouvelleSalle.nom" required />
        <br />
        <label>Adresse :</label>
        <input type="text" v-model="nouvelleSalle.adresse" required />
        <br />
        <label>Capacité :</label>
        <input type="number" v-model="nouvelleSalle.capacite" required />
        <br />
        <label>Nom gestionnaire :</label>
        <input type="text" v-model="nouvelleSalle.nomGest" required />
        <br />
        <label>Prénom gestionnaire :</label>
        <input type="text" v-model="nouvelleSalle.prenomGest" required />
        <br />
        <label>Association :</label>
        <input type="text" v-model="nouvelleSalle.association" required />
        <br />
        <button type="submit">Ajouter</button>
      </form>
    </div>
    <div v-if="salleSelectionnee">
      <h2>Modifier la salle</h2>
      <form @submit.prevent="updateSalle">
        <label>Nom :</label>
        <input type="text" v-model="salleSelectionnee.nom" required />
        <br />
        <label>Adresse :</label>
        <input type="text" v-model="salleSelectionnee.adresse" required />
        <br />
        <label>Capacité :</label>
        <input type="number" v-model="salleSelectionnee.capacite" required />
        <br />
        <label>Nom gestionnaire :</label>
        <input type="text" v-model="salleSelectionnee.nomGest" required />
        <br />
        <label>Prénom gestionnaire :</label>
        <input type="text" v-model="salleSelectionnee.prenomGest" required />
        <br />
        <label>Association :</label>
        <input type="text" v-model="salleSelectionnee.association" required />
        <br />
        <button type="submit">Modifier</button>
        <button @click="salleSelectionnee = null">Annuler</button>
      </form>
    </div>
    <VoirElement v-if="salleVue" :key="salleVue.idSalle" :element="salleVue" />
  </div>
</template>

<script>
import VoirElement from "@/components/VoirElement.vue";
import axios from "axios";

export default {
  data() {
    return {
      salles: [],
      nouvelleSalle: {
        nom: null,
        adresse: null,
        capacite: null,
        nomGest: null,
        prenomGest: null,
        association: null,
      },
      salleSelectionnee: null,
      salleVue: null,
      url: "http://localhost:8080/salles",
      isLoggedInAdmin: "",
    };
  },
  mounted() {
    this.isLoginAdmin();
    this.getSalles();
  },
  methods: {
    getSalles() {
      axios
        .get(this.url)
        .then((response) => {
          document.getElementById("status").innerHTML =
            "Status : " +
            (response.status >= 200 && response.status < 300 ? "OK" : "KO");
          document.getElementById("message").innerHTML =
            "Message : " + response.status;
          this.salles = response.data;
        })
        .catch((error) => {
          console.log(error);
        });
    },
    addSalle() {
      axios
        .post(this.url, this.nouvelleSalle)
        .then((response) => {
          document.getElementById("status").innerHTML =
            "Status : " +
            (response.status >= 200 && response.status < 300 ? "OK" : "KO");
          document.getElementById("message").innerHTML =
            "Message : " + response.status;
          this.nouvelleSalle = {
            nom: null,
            adresse: null,
            capacite: null,
            nomGest: null,
            prenomGest: null,
            association: null,
          };
          this.getSalles();
        })
        .catch((error) => {
          console.log(error);
        });
    },
    editSalle(salle) {
      this.salleSelectionnee = salle;
    },
    updateSalle() {
      axios
        .put(
          this.url + "/" + this.salleSelectionnee.idSalle,
          this.salleSelectionnee
        )
        .then((response) => {
          document.getElementById("status").innerHTML =
            "Status : " +
            (response.status >= 200 && response.status < 300 ? "OK" : "KO");
          document.getElementById("message").innerHTML =
            "Message : " + response.status;
          this.salleSelectionnee = null;
        })
        .catch((error) => {
          console.log(error);
        });
    },
    deleteSalle(salle) {
      axios
        .delete(this.url + "/" + salle.idSalle)
        .then((response) => {
          document.getElementById("status").innerHTML =
            "Status : " +
            (response.status >= 200 && response.status < 300 ? "OK" : "KO");
          document.getElementById("message").innerHTML =
            "Message : " + response.status;
          this.getSalles();
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
