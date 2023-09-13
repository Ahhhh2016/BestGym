import Vue from 'vue'
import Vuex from 'vuex'
//vuex用于状态管理
Vue.use(Vuex)

export default new Vuex.Store({


  //保存组件的状态
  state: {
    hideConfigButton: false,
    isPinned: true,
    showConfig: false,
    sidebarType: "bg-gradient-dark",
    isRTL: false,
    mcolor: "success",
    isNavFixed: false,
    isAbsolute: false,
    showNavs: true,
    showSidenav: false,
    showSidenav1: false,
    showNavbar: true,
    showFooter: true,
    showMain: true,
    isDarkMode: false,
    routes:[]
  },
  getters: {
  },
  //改变state(同步执行）
  mutations: {
    initRoutes(state, data){
      state.routes=data;
    },
    toggleConfigurator(state) {
      state.showConfig = !state.showConfig;
    },
    navbarMinimize(state) {
      const sidenav_show = document.querySelector(".g-sidenav-show");

      if (sidenav_show.classList.contains("g-sidenav-pinned")) {
        sidenav_show.classList.remove("g-sidenav-pinned");
        state.isPinned = true;
      } else {
        sidenav_show.classList.add("g-sidenav-pinned");
        state.isPinned = false;
      }
    },
    navbarFixed(state) {
      if (state.isNavFixed === false) {
        state.isNavFixed = true;
      } else {
        state.isNavFixed = false;
      }
    },
  },
  //异步执行
  actions: {
  },
  modules: {
  }
})
