<template>
  <div class="accueil">
    <img alt="Logo AccarAreno" src="../assets/logoAccarAreno.png" />
    <Presentation titre="Accar Areno" />
  </div>
</template>

<script>
// @ is an alias to /src
import Presentation from "@/components/Presentation.vue";

export default {
  name: "AccueilView",
  components: {
    Presentation,
  },
};
</script>
