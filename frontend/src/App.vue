
<template>
  <main
    class="main-content position-relative max-height-vh-100 h-100 overflow-x-hidden"
  >
  <sidenav
    :custom_class="this.$store.state.mcolor"
    :class="[this.$store.state.isRTL ? 'fixed-end' : 'fixed-start']"
    v-if="this.$store.state.showSidenav"
  />
    <navbar
      :class="[navClasses]"
      :textWhite="this.$store.state.isAbsolute ? 'text-white opacity-8' : ''"
      :minNav="navbarMinimize"
      v-if="this.$store.state.showNavbar"
    />
    <router-view />
    <app-footer v-show="this.$store.state.showFooter" />
<!--    <configurator-->
<!--      :toggle="toggleConfigurator"-->
<!--      :class="[-->
<!--        this.$store.state.showConfig ? 'show' : '',-->
<!--        this.$store.state.hideConfigButton ? 'd-none' : '',-->
<!--      ]"-->
<!--    />-->
  </main>
</template>
<script>
import Sidenav from "./examples/Sidenav";
import Navbar from "@/examples/Navbars/Navbar.vue";
import AppFooter from "@/examples/Footer.vue";
import { mapMutations } from "vuex";

export default {

  // beforeUnmount() {
  //   this.$store.state.hideConfigButton = false;
  //   this.$store.state.showNavbar = true;
  //   this.$store.state.showSidenav = true;
  //   this.$store.state.showFooter = true;
  // },
  name: "App",
  components: {
    Sidenav,
    Navbar,
    AppFooter,
  },
  methods: {
    ...mapMutations(["toggleConfigurator", "navbarMinimize"]),
  },
  computed: {
    navClasses() {
      return {
        "position-sticky blur shadow-blur mt-4 left-auto top-1 z-index-sticky": this
          .$store.state.isNavFixed,
        "position-absolute px-4 mx-0 w-100 z-index-2": this.$store.state
          .isAbsolute,
        "px-0 mx-4 mt-4": !this.$store.state.isAbsolute,
      };
    },
  },
  beforeMount() {
    //  this.$store.state.hideConfigButton = true;
    // this.$store.state.showNavbar = false;
    // this.$store.state.showSidenav = false;
    // this.$store.state.showFooter = false;
    // this.$store.state.isTransparent = "bg-transparent";

    const sidenav = document.getElementsByClassName("g-sidenav-show")[0];

    if (window.innerWidth > 1200) {
      sidenav.classList.add("g-sidenav-pinned");
    }
  },
};
</script>

