<template>
  <div class="voir-element">
    <h2>Visualisation : {{ this.el.type }}</h2>
    <p v-for="(value, propertyName) in this.el" v-bind:key="propertyName">
      <strong>{{ propertyName }}</strong> : {{ value }}
    </p>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "VoirElement",
  props: {
    element: Object,
  },
  data() {
    return {
      el: {},
    };
  },
  mounted() {
    let i = 0;
    for (const [key, value] of Object.entries(this.element)) {
      // On récupère le type de l'élément et les propriétés (!= idForeingKey)
      if (i == 0) {
        this.el.type = key.substring(2).toLowerCase();
      } else if (!key.startsWith("id")) {
        this.el[key] = value;
      }
      i++;
    }

    if (this.el.type === "groupe") {
      // affiche les artistes du groupe
      let url = "http://localhost:8079/accarareno/artistes";
      axios
        .get(url)
        .then((response) => {
          let artistes = response.data.data.filter(
            (artiste) => artiste.idGroupe === this.element.idGroupe
          );
          let index = 1;
          for (let artiste of artistes) {
            this.el["artiste_" + index] =
              artiste.nom + " (id " + artiste.idArtiste + ")";
            index++;
          }

          console.log(this.el);
        })
        .catch((error) => {
          console.log(error);
        });
    } else if (this.el.type === "artiste") {
      // affiche le nom du groupe de l'artiste
      let url = "http://localhost:8079/accarareno/groupes?id=" + this.element.idGroupe;
      axios
        .get(url)
        .then((response) => {
          this.el["groupe"] =
            response.data.data.nom + " (id " + this.element.idGroupe + ")";
        })
        .catch((error) => {
          console.log(error);
        });
    } else if (this.el.type === "billet") {
      // affiche l'utilisateur qui a acheté le billet et la soirée à laquelle il est associé
      let url = "http://localhost:8080/soirees/" + this.element.idSoiree;
      axios
        .get(url)
        .then((response) => {
          this.el["soiree"] =
            response.data[0].nom + " (id " + this.element.idSoiree + ")";
        })
        .catch((error) => {
          console.log(error);
        });
      url = "http://localhost:8080/salles/" + this.element.idSalle;
      axios
        .get(url)
        .then((response) => {
          this.el["salle"] =
            response.data[0].nom + " (id " + this.element.idSalle + ")";
        })
        .catch((error) => {
          console.log(error);
        });
    } else if (this.el.type === "concert") {
      // affiche le nom du groupe et le nom de la soiree
      let url = "http://localhost:8079/accarareno/groupes?id=" + this.element.idGroupe;
      axios
        .get(url)
        .then((response) => {
          this.el["groupe"] =
            response.data.data.nom + " (id " + this.element.idGroupe + ")";
        })
        .catch((error) => {
          console.log(error);
        });
      url = "http://localhost:8080/soirees/" + this.element.idSoiree;
      axios
        .get(url)
        .then((response) => {
          this.el["soiree"] =
            response.data[0].nom + " (id " + this.element.idSoiree + ")";
        })
        .catch((error) => {
          console.log(error);
        });
    } else if (this.el.type === "salle") {
      // affiche le nom des soirees qui ont lieu dans la salle
      let url = "http://localhost:8080/soirees";
      axios
        .get(url)
        .then((response) => {
          let soirees = response.data.filter(
            (soiree) => soiree.idSalle === this.element.idSalle
          );
          let index = 1;
          for (let soiree of soirees) {
            this.el["soiree_" + index] =
              soiree.nom + " (id " + soiree.idSoiree + ")";
            index++;
          }
        })
        .catch((error) => {
          console.log(error);
        });
    } else if (this.el.type === "soiree") {
      // affiche le nom de la salle où a lieu la soirée
      let url = "http://localhost:8080/salles/" + this.element.idSalle;
      axios
        .get(url2)
        .then((response) => {
          this.el["salle"] =
            response.data[0].nom + " (id " + this.element.idSalle + ")";
        })
        .catch((error) => {
          console.log(error);
        });
    } else if (this.el.type === "utilisateur") {
      // affiche les billets de l'utilisateur
      let url2 = url + "billets?idUtilisateur="+this.element.idUtilisateur;
      axios
        .get(url2)
        .then((response) => {
          let billets = response.data.data;
          let index = 1;
          for (let billet of billets) {
            this.el["billet_" + index] =
              billet.prix +
              "€ (nomSoiree " +
              billet.nomSoiree +
              ", id " +
              billet.idBillet +
              ")";
            index++;
          }
        })
        .catch((error) => {
          console.log(error);
        });
    }
  },
};
</script>
