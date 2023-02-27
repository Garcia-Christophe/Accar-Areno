<template>
  <div class="ressources">
    <h1>Liste des groupes</h1>
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

    <ul>
      <li v-for="item in items" :key="item.url">
        {{ item.type }} de {{ item.auteur }} ({{ item.date }}) : {{ item.url }}
      </li>
    </ul>
  </div>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      items: [],
      searchInput: "",
      url: "http://localhost:8080/accarareno/ressources",
    };
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
          this.items = response.data.data;
        })
        .catch((error) => {
          console.log(error);
        });
    },
  },
};
</script>
