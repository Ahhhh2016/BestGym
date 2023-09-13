import Vue from 'vue'
import Router from "vue-router";
import VueRouter from 'vue-router'
import MainFooter from "../layout/MainFooter.vue";
import AppFooter from "@/layout/AppFooter";
import Index from "../views/index.vue";
import User from '../views/user_center.vue';
import Detail from '../views/detail.vue';
import MUser from '../views/manager/user-manage'
import EUser from '../views/employee/user-manage-employee'
import Dashboard from "@/views/Dashboard";
import Edit from "@/views/manager/facility-manage";
import MSale from "@/views/manager/sale-manage";
import MOrder from "@/views/manager/order-manage";
import EOrder from '../views/employee/order-manage-employee'
import Order from "../views/Order.vue";
import Membership from "@/views/Membership";

Vue.use(VueRouter)

export default new Router({

  routes: [
    {
      path: '/login',   //登录页
      name: 'login',
      component: () => import('../views/login/login.vue'),
      hidden: true,
    },
    {
      path: '/register',   //注册
      name: 'register',

      component: () => import('../views/login/user_register.vue'),
      hidden: true,
    },
      {
      path: '/detail/order',   //manager管理用户
      name: '/detail/order',
      components: {default: Order, footer: AppFooter},
      // component: () => import('../views/user-manage.vue'),
      hidden: true,
    },
      {
      path: '/user/membership',   //manager管理用户
      name: '/user/membership',
      components: {default: Membership, footer: AppFooter},
      // component: () => import('../views/user-manage.vue'),
      hidden: true,
    },
      {
      path: '/manager/user-manage',   //manager管理用户
      name: '/manager/user-manage',
      components: {default: MUser, footer: AppFooter},
      // component: () => import('../views/user-manage.vue'),
      hidden: true,
    },
      {
      path: '/manager/sale-manage',   //manager管理收入
      name: '/manager/sale-manage',
      components: {default: MSale, footer: AppFooter},
      // component: () => import('../views/user-manage.vue'),
      hidden: true,
    },
      {
      path: '/manager/order-manage',   //manager管理订单
      name: '/manager/order-manage',
      components: {default: MOrder, footer: AppFooter},
      // component: () => import('../views/user-manage.vue'),
      hidden: true,
    },
      {
      path: '/employee/user-emp',   //employee管理用户
      name: '/employee/user-emp',
      components: {default: EUser, footer: AppFooter},
      // component: () => import('../views/user-manage.vue'),
      hidden: true,
    },
      {
      path: '/employee/order-emp',   //employee管理订单
      name: '/employee/order-emp',
      components: {default: EOrder, footer: AppFooter},
      // component: () => import('../views/user-manage.vue'),
      hidden: true,
    },
       {
      path: '/dashboard',  //图表
      name: 'dashboard',
      components: {default: Dashboard, footer: AppFooter},
      // component: () => import('../views/user-manage.vue'),
      hidden: true,
    },
      {
      path: '/manager/fac-man',  //manager编辑器械
      name: '/manager/fac-man',
      components: {default: Edit, footer: AppFooter},
      // component: () => import('../views/user-manage.vue'),
      hidden: true,
    },
      {
      path: '/detail',   //详情页
      name: 'detail',
        components: {default: Detail, footer: AppFooter},
      hidden: true,
    },
    {
      path: '/user',   //个人主页
      name: 'user',
      components: {default: User, footer: MainFooter},
      props: {
        footer: {backgroundColor: "black"}
      },
      hidden: true,
    },
      {
      path: '/dashboard',   //employee管理用户
      name: '/employee/user-emp',
      components: {default: Dashboard, footer: AppFooter},
      // component: () => import('../views/user-manage.vue'),
      hidden: true,
    },
    {
      path: '/home',   //首页
      name: 'home',
      components: {default: Index, footer: MainFooter},
      // components: {footer: MainFooter },
      props: {
        footer: {backgroundColor: "black"}
      },
      hidden:true,
    },
    // {
    //   path: '/manager',   //首页
    //   name: 'manager',
    //   component: () => import('../views/manager-index.vue'),
    //   children: [   //子路由
    //     {
    //       path: '/test1',   //首页
    //       name: 'test1',
    //       component: () => import('../views/test1.vue')
    //     },
    //     {
    //       path: '/test2',   //首页
    //       name: 'test2',
    //       component: () => import('../views/test2.vue')
    //     }
    //   ]
    // },

  ],
  scrollBehavior: to => {
    if (to.hash) {
      return { selector: to.hash };
    } else {
      return { x: 0, y: 0 };
    }
  }
});

// const router = new VueRouter({
//   routes
// })
//
// export default router
