<template>
  <div class="hello">
    <p>Bienvenue sur l'application web</p>
    <h1 id="titre-accueil">{{ titre }}</h1>

    <h2>Présentation de l'équipe</h2>
    <p>Professeur : Éric <strong>Cariou</strong></p>
    <p>Professeur : Lévy <strong>Marques</strong></p>
    <p>Développeuse : Alexia <strong>Sorin</strong></p>
    <p>Développeur : Abderraouf <strong>Chebbah</strong></p>
    <p>Développeur : Christophe <strong>Garcia</strong></p>
  </div>
</template>

<script>
export default {
  name: "HelloWorld",
  props: {
    titre: String,
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="scss">
h3 {
  margin: 40px 0 0;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #daae6b;
}
#titre-accueil {
  color: #daae6b;
}
td {
  max-width: 200px;
}
</style>
