<template style="overflow: visible;height: 3000px;
  ">
  <div class="wrapper" style="background-color: rgba(217,215,215,0.71); overflow: visible;">
    <parallax class="page-header header-filter" :style="headerStyle">
      <div class="section section-navbars">
        <navigation style="padding-top: 100px; width: 1330px"></navigation>
      </div>
      <div class="md-layout">
        <div class="md-layout-item">
          <div class="image-wrapper">
            <div class="brand">
              <h1 style="color: white">Best Gym</h1>
              <h3 style="color: white; margin-top: 20px">We provide you with a fantastic experience</h3>
            </div>
          </div>
        </div>
      </div>
    </parallax>
    <div class="main main-raised">
      <div class="section section-basic">
        <div class="container">
          <div class="title">
            <img :src="leaf4" alt="leaf4" class="leaf4" v-show="leafShow" />
            <img :src="leaf3" alt="leaf3" class="leaf3" v-show="leafShow" />
            <img :src="leaf2" alt="leaf2" class="leaf2" v-show="leafShow" />
            <img :src="leaf1" alt="leaf1" class="leaf1" v-show="leafShow" />
            <div class="tim-typo">
              <h1 class="title" style="margin-left: 420px">Join us
              </h1>
            </div>
            <h4 style="margin-left: 300px">“Best GYM IS A FAMILY THAT ENCOURAGES, </h4>
            <h4 style="margin-left: 470px">LIFTS UP, </h4>
            <h4 style="margin-left: 120px">TEACHES EACH OTHER AND IS ALWAYS WELCOMING NEW MEMBERS TO THE FAMILY.”</h4>
          </div>
          <javascript-components></javascript-components>
        </div>
      </div>

      <div class="section section-javascript">
        <div class="container" style="margin-top: -50px; margin-left: 120px">
          <typography-images></typography-images>
        </div>
      </div>
      <div class="container" style="background-color: rgb(245,207,103);margin-left: 0px;padding-right: 0px;width: 1460px;max-width: 1500px">
        <el-row :gutter="40" style="width: 1400px; margin: 0" >
          <el-col :span="7"><div class="grid-content" style="margin-top: 90px">
            <img src="../assets/img/coach.png"  >
          </div></el-col>
          <div>
            <el-col :span="14"><div class="grid-content">
              <div class="tim-typo">
                <h1 class="title">Our Coaches
                </h1>
                <h6 style="margin-top: -30px">We have a very rich coaching resource</h6>
              </div>
            </div></el-col>
            <el-col :span="8"><div class="grid-content">
              <h4 style="font-size: 16px">"Aside from physical strength and conditioning, I have gained mental strength, wisdom, and relationships.
                Through fitness I am learning to live, love and appreciate the moment."</h4>
              <div  style="width:80px; height:85px; border-radius:100%; overflow:hidden;margin-left: 150px">
                <img src="../assets/img/coach1.png" >
              </div>
            </div></el-col>
            <el-col :span="8"><div class="grid-content">
              <h4 style="font-size: 16px">"Aside from physical strength and conditioning, I have gained mental strength, wisdom, and relationships.
                Through fitness I am learning to live, love and appreciate the moment."</h4>
              <div  style="width:80px; height:85px; border-radius:100%; overflow:hidden;margin-left: 150px;">
                <img src="../assets/img/coach2.png" >
              </div>
            </div></el-col>
            <el-col :span="8"><div class="grid-content">
              <h4 style="font-size: 16px">"Aside from physical strength and conditioning, I have gained mental strength, wisdom, and relationships.
                Through fitness I am learning to live, love and appreciate the moment."</h4>
              <div  style="width:80px; height:85px; border-radius:100%; overflow:hidden;margin-left: 150px">
                <img src="../assets/img/coach3.png" >
              </div>
            </div></el-col>
            <el-col :span="8"><div class="grid-content">
              <h4 style="font-size: 16px">"Aside from physical strength and conditioning, I have gained mental strength, wisdom, and relationships.
                Through fitness I am learning to live, love and appreciate the moment."</h4>
              <div  style="width:80px; height:85px; border-radius:100%; overflow:hidden;margin-left: 150px">
                <img src="../assets/img/coach4.png" >
              </div>
            </div></el-col>
          </div>
        </el-row>
      </div>

      <div class="section">
        <div class="container text-center">
          <TabsSection></TabsSection>
        </div>
      </div>
      <div class="sharing-area text-center" style="margin-top: -10px; padding-bottom: 90px">
        <div class="md-layout">
          <div class="md-layout-item">
            <h3>Thank you for supporting us!</h3>
          </div>
        </div>
        <md-button class="md-twitter"
        ><i class="fab fa-twitter"></i>Tweet</md-button
        >
        <md-button class="md-facebook"
        ><i class="fab fa-facebook-square"></i> Share</md-button
        >
        <md-button class="md-google"
        ><i class="fab fa-google-plus"></i> Share</md-button
        >
        <md-button class="md-github"
        ><i class="fab fa-github"></i> Star</md-button
        >
      </div>
    </div>
    <MainFooter></MainFooter>
  </div>
</template>

<script>
// import BasicElements from "./components/BasicElementsSection";
import Navigation from "./components/NavigationSection";

import TypographyImages from "./components/TypographyImagesSection";
import JavascriptComponents from "./components/JavascriptComponentsSection";
// import { LoginCard } from "@/components";
import TabsSection from "@/views/components/TabsSection";
import MainFooter from "@/layout/MainFooter";

export default {
  beforeMount() {
    this.$store.state.hideConfigButton = true;
    this.$store.state.showNavbar = false;
    this.$store.state.showSidenav = false;
    this.$store.state.showFooter = false;
  },
  beforeUnmount() {
    this.$store.state.hideConfigButton = false;
    this.$store.state.showNavbar = true;
    this.$store.state.showSidenav = true;
    this.$store.state.showFooter = true;
  },
  components: {
    MainFooter,
    TabsSection,
    Navigation,
    TypographyImages,
    JavascriptComponents,
  },
  // name: "home",
  bodyClass: "index-page",
  props: {
    image: {
      type: String,
      default: require("@/assets/img/index.png")
    },
    leaf4: {
      type: String,
      default: require("@/assets/img/leaf4.png")
    },
    leaf3: {
      type: String,
      default: require("@/assets/img/leaf3.png")
    },
    leaf2: {
      type: String,
      default: require("@/assets/img/leaf2.png")
    },
    leaf1: {
      type: String,
      default: require("@/assets/img/leaf1.png")
    },
    signup: {
      type: String,
      default: require("@/assets/img/city.jpg")
    },
    landing: {
      type: String,
      default: require("@/assets/img/landing.jpg")
    },
    profile: {
      type: String,
      default: require("@/assets/img/profile.jpg")
    }
  },
  data() {
    return {
      emps:[],
      user:[],
      firstname: null,
      email: null,
      password: null,
      leafShow: false,
      image1: require("@/assets/img/img2.png"),
      image2: require("@/assets/img/img2.png"),
      image3: require("@/assets/img/img2.png"),
      image4: require("@/assets/img/img2.png"),
      responsive: false
    };
  },
  mounted(){
    this.initUser();
    this.onResponsiveInverted();
    window.addEventListener("resize", this.onResponsiveInverted);
    this.leafActive();
    window.addEventListener("resize", this.leafActive);
    this.initFacility();
  },

  methods: {
    initUser(){
      console.log(111111)
      console.log(JSON.parse(window.localStorage.getItem('user1')).username)
      this.$axios({
        url: '/user/getUser',
        method: 'get',
        params:{
          username:JSON.parse(window.localStorage.getItem('user1')).username
        }
      }).then(res => {
        if (res) {
          console.log(res)
          this.user = res.data.data;
          localStorage.setItem("user",JSON.stringify(res.data.data))
        }
      })
    },
    onResponsiveInverted() {
      if (window.innerWidth < 600) {
        this.responsive = true;
      } else {
        this.responsive = false;
      }
    },
    leafActive() {
      if (window.innerWidth < 768) {
        this.leafShow = false;
      } else {
        this.leafShow = true;
      }
    },

  },
  computed: {
    headerStyle() {
      return {
        backgroundImage: `url(${this.image})`
      };
    },
    signupImage() {
      return {
        backgroundImage: `url(${this.signup})`
      };
    }
  },
  beforeDestroy() {
    window.removeEventListener("resize", this.onResponsiveInverted);
    window.removeEventListener("resize", this.leafActive);
  }
};
</script>
<style lang="scss">
body{
  overflow: auto;
}
.section-download {
  .md-button + .md-button {
    margin-left: 5px;
  }
}

@media all and (min-width: 991px) {
  .btn-container {
    display: flex;
  }
}
</style>