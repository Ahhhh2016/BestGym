// import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import MaterialKit from "./plugins/material-kit";
import MaterialDashboard from "./plugins/material-dashboard";
import Argon from "./plugins/argon-kit";
import Vue from "vue";
import {postRequest} from "@/utils/api";
import {putRequest} from "@/utils/api";
import {getRequest} from "@/utils/api";
import {deleteRequest} from "@/utils/api";
import Video from 'video.js'
import 'video.js/dist/video-js.css'
import { request } from '@/utils/axios'
Vue.prototype.$axios = request

Vue.prototype.$video = Video


Vue.use(ElementUI);
Vue.use(MaterialKit);
Vue.use(Argon);
Vue.use(MaterialDashboard);
Vue.config.productionTip = false
//插件形式使用请求
Vue.prototype.postRequest = postRequest();
Vue.prototype.putRequest = putRequest();
Vue.prototype.getRequest = getRequest();
Vue.prototype.deleteRequest = deleteRequest();

//路由导航守卫
// router.beforeEach((to, from, next)=>{
//   //判断是否是管理员登录，如果是，则初始化菜单，如果是用户登录，则跳转到用户首页，否则跳转到登录页
//   if(window.sessionStorage.getItem('tokenStr')){
//     initMenu(router, store);
//     next();
//   }else {
//     if(to.path=='/login'){
//       next();
//     }
//   }
// })

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
