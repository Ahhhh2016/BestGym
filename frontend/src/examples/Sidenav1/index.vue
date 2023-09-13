<template>
  <aside
    id="sidenav-main"
    class="sidenav navbar navbar-vertical1 navbar-expand-xs border-0 border-radius-xl my-3 ms-3"
    :class="`${
      this.$store.state.isRTL
        ? 'me-3 rotate-caret fixed-end'
        : 'fixed-start ms-3'
    } ${this.$store.state.sidebarType}`"
  >
    <div class="sidenav-header">
      <i
        class="top-0 p-3 cursor-pointer fas fa-times text-secondary opacity-5 position-absolute end-0 d-none d-xl-none"
        aria-hidden="true"
        id="iconSidenav"
      ></i>
      <a class="m-0 navbar-brand" href="/">
        <img
          :src="
            this.$store.state.sidebarType === 'bg-white' ||
            (this.$store.state.sidebarType === 'bg-transparent' &&
              !this.$store.state.isDarkMode)
              ? logoDark
              : logo
          "
          class="navbar-brand-img h-100"
          alt="main_logo"
        />
        <span class="ms-2 font-weight-bold text-white"
          >Manager Platform</span
        >
      </a>
    </div>
    <hr class="horizontal light mt-0 mb-2" />
    <SidenavList/>
  </aside>

</template>
<script>
import SidenavList from "./SidenavList.vue";
import logo from "@/assets/img/logo-ct.png";
import logoDark from "@/assets/img/logo-ct-dark.png";

export default {
  // eslint-disable-next-line vue/multi-word-component-names
  name: "index",
  components: {
    SidenavList,
  },
  data() {
    return {
      logo,
      logoDark,
    };
  },
  props: ["custom_class"],
};
</script>
