<template>
  <div class="artistes">
    <h1>Liste des artistes</h1>
    <p id="status"></p>
    <p id="message"></p>
    <table>
      <thead>
        <tr>
          <th>IdArtiste</th>
          <th>Nom</th>
          <th>VilleOrigine</th>
          <th>DateNaissance</th>
          <th>IdGroupe</th>
          <th></th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="artiste in artistes" :key="artiste.idArtiste">
          <td>{{ artiste.idArtiste }}</td>
          <td>{{ artiste.nom }}</td>
          <td>{{ artiste.villeOrigine }}</td>
          <td>{{ artiste.dateNaissance }}</td>
          <td>{{ artiste.idGroupe }}</td>
          <td>
            <button @click="editArtiste(artiste)">Modifier</button>
            <button @click="deleteArtiste(artiste)">Supprimer</button>
            <button @click="artisteVu = artiste">Voir</button>
          </td>
        </tr>
      </tbody>
    </table>
    <div>
      <h2>Ajouter un artiste</h2>
      <form @submit.prevent="addArtiste">
        <label>Nom :</label>
        <input type="text" v-model="nouvelArtiste.nom" required />
        <br />
        <label>Ville d'origine :</label>
        <input type="text" v-model="nouvelArtiste.villeOrigine" />
        <br />
        <label>Date de naissance :</label>
        <input type="date" v-model="nouvelArtiste.dateNaissance" />
        <br />
        <label>Id du groupe :</label>
        <input type="number" v-model="nouvelArtiste.idGroupe" />
        <br />
        <button type="submit">Ajouter</button>
      </form>
    </div>
    <div v-if="artisteSelectionne">
      <h2>Modifier l'artiste</h2>
      <form @submit.prevent="updateArtiste">
        <label>Nom :</label>
        <input type="text" v-model="artisteSelectionne.nom" />
        <br />
        <label>Ville d'origine :</label>
        <input type="text" v-model="artisteSelectionne.villeOrigine" />
        <br />
        <label>Date de naissance :</label>
        <input type="date" v-model="artisteSelectionne.dateNaissance" />
        <br />
        <label>Id du groupe :</label>
        <input type="number" v-model="artisteSelectionne.idGroupe" />
        <br />
        <button type="submit">Modifier</button>
        <button @click="artisteSelectionne = null">Annuler</button>
      </form>
    </div>
    <VoirElement
      v-if="artisteVu"
      :key="artisteVu.idArtiste"
      :element="artisteVu"
    />
  </div>
</template>

<script>
import VoirElement from "@/components/VoirElement.vue";
import axios from "axios";

export default {
  data() {
    return {
      artistes: [],
      nouvelArtiste: {
        nom: null,
        villeOrigine: null,
        dateNaissance: null,
        idGroupe: null,
      },
      artisteSelectionne: null,
      artisteVu: null,
      url: "http://localhost:8079/accarareno/artistes",
    };
  },
  mounted() {
    this.getArtistes();
  },
  methods: {
    getArtistes() {
      axios
        .get(this.url)
        .then((response) => {
          document.getElementById("status").innerHTML =
            "Status : " + response.data.status;
          document.getElementById("message").innerHTML =
            "Message : " + response.data.message;
          this.artistes = response.data.data;
        })
        .catch((error) => {
          console.log(error);
        });
    },
    addArtiste() {
      let link = this.url + "?";
      let unChangement = false;
      if (this.nouvelArtiste.nom != null) {
        link += "nom=" + this.nouvelArtiste.nom;
        unChangement = true;
      }
      if (this.nouvelArtiste.villeOrigine != null) {
        if (unChangement) {
          link += "&";
        }
        link += "villeOrigine=" + this.nouvelArtiste.villeOrigine;
        unChangement = true;
      }
      if (this.nouvelArtiste.dateNaissance != null) {
        if (unChangement) {
          link += "&";
        }
        link += "dateNaissance=" + this.nouvelArtiste.dateNaissance;
        unChangement = true;
      }
      if (this.nouvelArtiste.idGroupe != null) {
        if (unChangement) {
          link += "&";
        }
        link += "idGroupe=" + this.nouvelArtiste.idGroupe;
      }
      axios
        .post(link)
        .then((response) => {
          document.getElementById("status").innerHTML =
            "Status : " + response.data.status;
          document.getElementById("message").innerHTML =
            "Message : " + response.data.message;
          this.getArtistes();
          this.nouvelArtiste = {
            nom: null,
            villeOrigine: null,
            dateNaissance: null,
            idGroupe: null,
          };
        })
        .catch((error) => {
          console.log(error);
        });
    },
    editArtiste(artiste) {
      this.artisteSelectionne = artiste;
    },
    updateArtiste() {
      let link = this.url + "?id=" + this.artisteSelectionne.idArtiste + "&";
      let unChangement = false;
      if (this.artisteSelectionne.nom != null) {
        link += "nom=" + this.artisteSelectionne.nom;
        unChangement = true;
      }
      if (this.artisteSelectionne.villeOrigine != null) {
        if (unChangement) {
          link += "&";
        }
        link += "villeOrigine=" + this.artisteSelectionne.villeOrigine;
        unChangement = true;
      }
      if (this.artisteSelectionne.dateNaissance != null) {
        if (unChangement) {
          link += "&";
        }
        link += "dateNaissance=" + this.artisteSelectionne.dateNaissance;
        unChangement = true;
      }
      if (this.artisteSelectionne.idGroupe != null) {
        if (unChangement) {
          link += "&";
        }
        link += "idGroupe=" + this.artisteSelectionne.idGroupe;
      }
      axios
        .put(link)
        .then((response) => {
          document.getElementById("status").innerHTML =
            "Status : " + response.data.status;
          document.getElementById("message").innerHTML =
            "Message : " + response.data.message;
          this.artisteSelectionne = null;
          this.getArtistes();
        })
        .catch((error) => {
          console.log(error);
        });
    },
    deleteArtiste(artiste) {
      axios
        .delete(this.url + "?id=" + artiste.idArtiste)
        .then((response) => {
          document.getElementById("status").innerHTML =
            "Status : " + response.data.status;
          document.getElementById("message").innerHTML =
            "Message : " + response.data.message;
          this.getArtistes();
        })
        .catch((error) => {
          console.log(error);
        });
    },
  },
  components: { VoirElement },
};
</script>
