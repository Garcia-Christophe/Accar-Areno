import { createRouter, createWebHistory } from "vue-router";
import AccueilView from "../views/AccueilView.vue";

const routes = [
  {
    path: "/",
    name: "accueil",
    component: AccueilView,
  },
  {
    path: "/groupes",
    name: "groupes",
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () =>
      import(/* webpackChunkName: "groupes" */ "../views/GroupesView.vue"),
  },
  {
    path: "/artistes",
    name: "artistes",
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () =>
      import(/* webpackChunkName: "artistes" */ "../views/ArtistesView.vue"),
  },
  {
    path: "/ressources",
    name: "ressources",
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () =>
      import(/* webpackChunkName: "artistes" */ "../views/RessourcesView.vue"),
  },
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

export default router;
