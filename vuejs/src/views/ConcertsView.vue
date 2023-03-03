<template>
  <div class="concerts">
    <h1>Liste des concerts</h1>
    <p id="status"></p>
    <p id="message"></p>
    <table>
      <thead>
        <tr>
          <th>IdConcert</th>
          <th>Date</th>
          <th>Heure</th>
          <th>Durée</th>
          <th>idGroupe</th>
          <th>idSoiree</th>
          <th></th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="concert in concerts" :key="concert.idConcert">
          <td>{{ concert.idConcert }}</td>
          <td>{{ concert.date }}</td>
          <td>{{ concert.heure }}</td>
          <td>{{ concert.duree }}</td>
          <td>{{ concert.idGroupe }}</td>
          <td>{{ concert.idSoiree }}</td>
          <td>
            <button  v-if="isLoggedInAdmin" @click="editConcert(concert)">Modifier</button>
            <button  v-if="isLoggedInAdmin" @click="deleteConcert(concert)">Supprimer</button>
            <button @click="concertVu = concert">Voir plus</button>
          </td>
        </tr>
      </tbody>
    </table>
    <div  v-if="isLoggedInAdmin" >
      <h2>Ajouter un concert</h2>
      <form @submit.prevent="addConcert">
        <label>Date :</label>
        <input type="date" v-model="nouveauConcert.date" required />
        <br />
        <label>Heure :</label>
        <input type="time" v-model="nouveauConcert.heure" required />
        <br />
        <label>Durée :</label>
        <input type="time" v-model="nouveauConcert.duree" required />
        <br />
        <label>IdGroupe :</label>
        <input type="number" v-model="nouveauConcert.idGroupe" required />
        <br />
        <label>IdSoiree :</label>
        <input type="number" v-model="nouveauConcert.idSoiree" required />
        <br />
        <button type="submit">Ajouter</button>
      </form>
    </div>
    <div v-if="concertSelectionne">
      <h2>Modifier le concert</h2>
      <form @submit.prevent="updateConcert">
        <label>Date :</label>
        <input type="date" v-model="concertSelectionne.date" required />
        <br />
        <label>Heure :</label>
        <input type="time" v-model="concertSelectionne.heure" required />
        <br />
        <label>Durée :</label>
        <input type="time" v-model="concertSelectionne.duree" required />
        <br />
        <label>IdGroupe :</label>
        <input type="number" v-model="concertSelectionne.idGroupe" required />
        <br />
        <label>IdSoiree :</label>
        <input type="number" v-model="concertSelectionne.idSoiree" required />
        <br />
        <button type="submit">Modifier</button>
        <button @click="concertSelectionne = null">Annuler</button>
      </form>
    </div>
    <VoirElement
      v-if="concertVu"
      :key="concertVu.idConcert"
      :element="concertVu"
    />
  </div>
</template>

<script>
import VoirElement from "@/components/VoirElement.vue";
import axios from "axios";

export default {
  data() {
    return {
      concerts: [],
      nouveauConcert: {
        date: null,
        heure: null,
        duree: null,
        idGroupe: null,
        idSoiree: null,
      },
      concertSelectionne: null,
      concertVu: null,
      url: "http://localhost:8080/concerts",
      isLoggedInAdmin: "",
    };
  },
  mounted() {
    this.isLoginAdmin();
    this.getConcerts();
  },
  methods: {
    getConcerts() {
      axios
        .get(this.url)
        .then((response) => {
          document.getElementById("status").innerHTML =
            "Status : " +
            (response.status >= 200 && response.status < 300 ? "OK" : "KO");
          document.getElementById("message").innerHTML =
            "Message : " + response.status;
          this.concerts = response.data;
        })
        .catch((error) => {
          console.log(error);
        });
    },
    addConcert() {
      this.nouveauConcert.heure += ":00";
      this.nouveauConcert.duree += ":00";
      axios
        .post(this.url, this.nouveauConcert)
        .then((response) => {
          document.getElementById("status").innerHTML =
            "Status : " +
            (response.status >= 200 && response.status < 300 ? "OK" : "KO");
          document.getElementById("message").innerHTML =
            "Message : " + response.status;
          this.nouveauConcert = {
            date: null,
            heure: null,
            duree: null,
            idGroupe: null,
            idSoiree: null,
          };
          this.getConcerts();
        })
        .catch((error) => {
          console.log(error);
        });
    },
    editConcert(concert) {
      this.concertSelectionne = concert;
    },
    updateConcert() {
      this.concertSelectionne.heure += ":00";
      this.concertSelectionne.duree += ":00";
      axios
        .put(
          this.url + "/" + this.concertSelectionne.idConcert,
          this.concertSelectionne
        )
        .then((response) => {
          document.getElementById("status").innerHTML =
            "Status : " +
            (response.status >= 200 && response.status < 300 ? "OK" : "KO");
          document.getElementById("message").innerHTML =
            "Message : " + response.status;
          this.groupeSelectionne = null;
        })
        .catch((error) => {
          console.log(error);
        });
    },
    deleteConcert(concert) {
      axios
        .delete(this.url + "/" + concert.idConcert)
        .then((response) => {
          document.getElementById("status").innerHTML =
            "Status : " +
            (response.status >= 200 && response.status < 300 ? "OK" : "KO");
          document.getElementById("message").innerHTML =
            "Message : " + response.status;
          this.getConcerts();
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
