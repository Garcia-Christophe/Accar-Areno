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
            <button @click="editConcert(concert)">Modifier</button>
            <button @click="deleteConcert(concert)">Supprimer</button>
          </td>
        </tr>
      </tbody>
    </table>
    <div>
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
  </div>
</template>

<script>
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
      url: "http://localhost:8080/concerts",
    };
  },
  mounted() {
    this.getConcerts();
  },
  methods: {
    getConcerts() {
      axios
        .get(this.url)
        .then((response) => {
          document.getElementById("status").innerHTML =
            "Status : " + response.status >= 200 && response.status < 300
              ? "OK"
              : "KO";
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
      console.log(this.nouveauConcert);
      axios
        .post(this.url, { data: this.nouveauConcert })
        .then((response) => {
          document.getElementById("status").innerHTML =
            "Status : " + response.status >= 200 && response.status < 300
              ? "OK"
              : "KO";
          document.getElementById("message").innerHTML =
            "Message : " + response.status;
          this.getConcerts();
          this.nouveauConcert = {
            date: null,
            heure: null,
            duree: null,
            idGroupe: null,
            idSoiree: null,
          };
        })
        .catch((error) => {
          console.log(error);
        });
    },
    editConcert(concert) {
      this.concertSelectionne = concert;
    },
    updateConcert() {
      axios
        .put(this.url + "/" + this.concertSelectionne.idConcert, {
          data: this.concertSelectionne,
        })
        .then((response) => {
          document.getElementById("status").innerHTML =
            "Status : " + response.status >= 200 && response.status < 300
              ? "OK"
              : "KO";
          document.getElementById("message").innerHTML =
            "Message : " + response.status;
          this.groupeSelectionne = null;
          this.getConcerts();
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
            "Status : " + response.status >= 200 && response.status < 300
              ? "OK"
              : "KO";
          document.getElementById("message").innerHTML =
            "Message : " + response.status;
          this.getConcerts();
        })
        .catch((error) => {
          console.log(error);
        });
    },
  },
};
</script>
