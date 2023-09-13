
<template>
  <body id="poster">

  <div class="line"></div>
<div class="row">
  <div class="col-1"></div>
  <div class="col-7">
    <MembershipCard></MembershipCard>
  </div>
</div>

  </body>
</template>

<script>

// import BookCard from "@/views/components/BookCard"
// import AppFooter from "@/layout/AppFooter";
// import Images from "@/views/components/Images";
// import Carousel from "@/views/components/Carousel";
import MembershipCard from "@/views/components/MembershipCard";
export default {
  name:"Order",
  beforeMount() {
    this.$store.state.hideConfigButton = true;
    this.$store.state.showNavbar = false;
    this.$store.state.showSidenav = false;
    this.$store.state.showFooter = false;
  },
  beforeUnmount() {
    this.$store.state.hideConfigButton = false;
    this.$store.state.showNavbar = false;
    this.$store.state.showSidenav = false;
    this.$store.state.showFooter = false;
  },
  components: {
    // Casu,
    // AppFooter,
    // BookCard,
    // Images,
    // Carousel,
    MembershipCard


  },
  bodyClass: "profile-page",

  methods: {
    classicModalHide() {
      this.classicModal = false;
    },
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          alert('submit!');
        } else {
          console.log('error submit!!');
          return false;
        }
      });
    },
  },
  props: {
    header: {
      type: String,
      default: require("@/assets/img/run.png")
    },
    img: {
      type: String,
      default: require("@/assets/img/faces/christian.jpg")
    }
  },
  computed: {
    headerStyle() {
      return {
        backgroundImage: `url(${this.header})`
      };
    }
  }
};
</script>

<style>
#poster {
  background:url("../assets/img/bk3.png") no-repeat;
  background-position: center;
  height: 100%;
  width: 100%;
  background-size: cover;
  position: fixed;
}
body{
  margin: 0px;
}
.login-container1 {
  border-radius: 15px;
  background-clip: padding-box;
  width: 410px;
  background: #fff;
  border: 1px solid #eaeaea;
  z-index: 1;
  position: absolute;
  margin-left: 530px;
  margin-top: 195px;
  padding-top: 100px;
  padding-left: 30px;
  padding-right: 30px;
  height: 350px;

  /*box-shadow: 0 0 20px #cac6c6;*/
}
.login_title {
  margin: 0px auto 340px auto;
  text-align: center;
  font-size: 35px;
  color: rgba(252, 249, 249, 0.94);
}

.card1{
  font-size: 20px;
  font-weight: bolder;
  margin-top: 150px;
  margin-bottom: -200px;
  width:360px;
  hight:60px;
  margin-left: 560px;
  background-color: #51a655;
  border-color: #51a655;
  z-index: 2;
  position: absolute;
  box-shadow: 0 0 30px #cac6c6
}

.button1{
  background-color: #51a655;
  margin-left: 10px;
  margin-top:17px;
  border-color: #51a655;
  box-shadow: 0 0 20px #cac6c6
}

.link1{
  color: #51a655;
  font-size: 13px;
  font-weight: bold
}
.el-menu-item {
  padding: 0 62px;
  color: #fff;
  font-size: 16px;
}

.el-menu-demo{
  width: 1580px;
  height: 54px;
  /* border-bottom-color:none!important; */
  background-color: rgba(255, 255, 255, 0.01);
  float:left;
  /*margin-right: 8.54%;*/
  color: white;


}

.span1{
  color: white;
  font-size: 18px;
  font-weight: bold
}
/* 点击出来的下划线进行隐藏 */
.el-menu-item.is-active {
  border-bottom: none!important;
  color: rgba(0,0,0,0);
}
/* 整体的下划线进行隐藏 */
.el-menu.el-menu--horizontal{
  border-bottom: none;
}
/* 导航栏的间距等样式 */


/* 点击出来的下划线动效残留进行隐藏 */
.el-menu--horizontal>.el-menu-item{
  border-bottom: none;
}
/* 点击以后的背景色进行隐藏 */
.el-menu--horizontal>.el-menu-item:not(.is-disabled):focus, .el-menu--horizontal>.el-menu-item:not(.is-disabled):hover, .el-menu--horizontal>.el-submenu .el-submenu__title:hover {
  background-color: rgba(0,0,0,0);
}
</style>