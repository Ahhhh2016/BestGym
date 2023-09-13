<template>
  <div class="wrapper" style="background-color: rgba(47,46,46,0.56)">
    <parallax
        class="section page-header header-filter"
        :style="headerStyle"
    >
      <navigation style="padding-top: 350px; width: 1230px;margin-left: 800px"></navigation>
    </parallax>
    <div class="main main-raised" style="background: rgba(34,34,34,0.8)">


      <!--      <Carousel></Carousel>-->
      <section class="section section-lg section-shaped overflow-hidden my-0">
        <div class="shape shape-style-1 shape-default shape-skew">
          <span></span>
          <span></span>
          <span></span>
          <span></span>
          <span></span>
          <span></span>
        </div>
        <div class="container py-0 pb-lg">
          <div class="row justify-content-between align-items-center">
            <div class="col-lg-5 mb-5 mb-lg-0">
              <h1 class="text-white font-weight-light">{{ detail.facilityType}}</h1>
              <p class="lead text-white mt-4" style="font-size: 18px">{{detail.description}}</p>

            </div>
            <div class="col-lg-6 mb-lg-auto">
              <div class="rounded shadow-lg overflow-hidden transform-perspective-right">
                <b-carousel id="carousel1"
                            controls
                            indicators>
                  <!-- Text slides with image -->
                  <b-carousel-slide :img-src="detail.image1Address"></b-carousel-slide>
                  <b-carousel-slide :img-src="detail.image2Address"></b-carousel-slide>
                  <b-carousel-slide :img-src="detail.image3Address"></b-carousel-slide>
                </b-carousel>
              </div>
            </div>
          </div>
        </div>
      </section>
      <!--    <Images></Images>-->
      <div class="container">

        <div class="md-layout-item md-size-90 md-small-size-100 ">
          <el-table
              :data="timetable"
              row-key="id"
              border
              v-model="time"
              style="margin-left: 40px"
          >
            <el-table-column
                prop="day"
                label=""
                width="128">
            </el-table-column>
            <el-table-column
                prop="eight"
                label="8:00-10:00"
                width="150">
            </el-table-column>
            <el-table-column
                prop="ten"
                label="10:00-12:00"
                width="150">
            </el-table-column>
            <el-table-column
                prop="fourteen"
                label="14:00-16:00"
                width="150">
            </el-table-column>
            <el-table-column
                prop="sixteen"
                label="16:00-18:00"
                width="150">
            </el-table-column>
            <el-table-column
                prop="twenty"
                label="20:00-22:00"
                width="150">
            </el-table-column>
          </el-table>
          <div class="wrapper">
            <div id="nav-tabs">
              <div class="md-layout">
                <div class="md-layout-item md-size-150 md-small-size-100">
                  <nav-tabs-card no-label>
                    <template slot="content">
                      <md-tabs class="md-primary" md-alignment="left" style="width:1000px;margin-top: 60px">
                        <md-tab id="tab-home" md-label="General use" md-icon="face">
                          <div class="md-layout">
                            <div class="md-layout-item md-size-80">
                              <div class="title">
                              </div>
                              <div class="block" style="margin-bottom: 50px">
                                <span class="demonstration" style="margin-right: 20px">Please select data:</span>
                                <el-date-picker
                                    v-model="daterange1"
                                    type="daterange"
                                    range-separator="to"
                                    start-placeholder="Start"
                                    end-placeholder="End"
                                    format="yyyy-MM-dd"
                                    value-format="yyyy-MM-dd"
                                    :picker-options="pickerOptions1"
                                    @change="searchInfo">
                                </el-date-picker>
                              </div>

                              <span class="demonstration" style="margin-left: -120px;margin-top: 300px;margin-right: 20px">Please choose the time:</span>
                              <el-select v-model="timePeriod" placeholder="Please choose the period" style="margin-bottom: 50px" >
                                <el-option
                                    v-for="item in options"
                                    :key="item.timePeriod"
                                    :label="item.label"
                                    :value="item.timePeriod">
                                </el-option>
                              </el-select>
                            </div>
                          </div>
                          <el-button  type="success" @click="Order1()" style="margin-left: 0px">Confirm order</el-button>
                        </md-tab>

                        <md-tab id="tab-pages" md-label="Activity" md-icon="chat">
                          <div class="md-layout">
                            <div class="md-layout-item md-size-80">
                              <div style="margin-top: 40px">
                                <span class="demonstration" style="margin-right: 20px;margin-top: 150px;margin-left: -310px">Please select activity:</span>
                                <div style="margin-left: -310px;margin-top: 20px">
                                  <label><input v-model="radio1" type="radio" value="全部" >全部</label>
                                  <label><input v-model="radio1" type="radio" value="部分" >部分</label>
                                  <label><input v-model="radio1" type="radio" value="零散">零散</label>
                                </div>
                              </div>
                              <div style="margin-top: 40px">
                                <span class="demonstration" style="margin-left: -90px;margin-right: 20px">Please enter the number:</span>
                                <el-input v-model="input" placeholder="Please enter the number of people" style="width: 200px;"></el-input>
                              </div>
                              <div class="block" style="margin-top: 30px;margin-left: -120px">
                                <span class="demonstration" style="margin-top: 55px;margin-right: 0px;margin-left: 130px">Please select date:</span>
                                <el-date-picker
                                    v-model="daterange2"
                                    type="daterange"
                                    range-separator="to"
                                    start-placeholder="Start"
                                    end-placeholder="End"
                                    format="yyyy-MM-dd"
                                    value-format="yyyy-MM-dd"
                                    :picker-options="pickerOptions1"
                                    @change="searchInfo"
                                    style="margin-left:20px;">
                                </el-date-picker>
                              </div>
                              <span class="demonstration" style="margin-left: -100px;margin-top: 300px;margin-right: 20px">Please choose the time:</span>
                              <el-select v-model="timePeriod" placeholder="Please choose the period" style="margin-bottom: 50px;margin-top: 40px" >
                                <el-option
                                    v-for="item in options"
                                    :key="item.timePeriod"
                                    :label="item.label"
                                    :value="item.timePeriod">
                                </el-option>
                              </el-select>

                            </div>
                          </div>

                          <el-button  type="success" @click="Order2()" style="margin-left: 0px">Confirm order</el-button>
                        </md-tab>

                        <md-tab id="tab-posts" md-label="Lessons" md-icon="build">

                          <el-row>
                            <el-col :span="12">
                              <div class="grid-content bg-purple">
                                <el-table
                                    :data="lessonTable"
                                    border
                                    style="">
                                  <el-table-column
                                      prop="date"
                                      label="Date"
                                  >
                                  </el-table-column>
                                  <el-table-column
                                      prop="time"
                                      label="Time"
                                  >
                                  </el-table-column>
                                  <el-table-column
                                      prop="lesson"
                                      label="Lesson"
                                  >
                                  </el-table-column>
                                  <el-table-column
                                      prop="coach"
                                      label="Coach"
                                  >
                                  </el-table-column>
                                </el-table></div></el-col>
                            <el-col :span="10"  :offset="2"><div class="grid-content ql-bg-yellow">

                              <el-row>
                                <el-col :span="8">
                                  <div class="grid-content ">
                                    <h5>Choose class:</h5>
                                    <div style="margin-left: -10px;margin-top: 20px">
                                      <label><input v-model="radio2" type="radio" value="全部" >全部</label>
                                      <label><input v-model="radio2" type="radio" value="部分" >部分</label>
                                      <label><input v-model="radio2" type="radio" value="零散">零散</label>
                                    </div>
                                  </div>
                                </el-col>
                              </el-row>
                              <el-row>
                                <el-col :span="12">
                                  <div class="block" style="margin-bottom: 50px">
                                    <span class="demonstration" style="margin-right: 20px">Please select data:</span>
                                    <el-date-picker
                                        v-model="daterange2"
                                        type="daterange"
                                        range-separator="to"
                                        start-placeholder="Start"
                                        end-placeholder="End"
                                        format="yyyy-MM-dd"
                                        value-format="yyyy-MM-dd"
                                        :picker-options="pickerOptions1"
                                        @change="searchInfo">
                                    </el-date-picker>
                                  </div>
                                  <div class="grid-content ">
                                    <h5>Please choose coach:</h5>
                                    <el-select v-model="timePeriod" placeholder="Please choose the period" style="margin-bottom: 50px;margin-top: 40px" >
                                      <el-option
                                          v-for="item in options"
                                          :key="item.timePeriod"
                                          :label="item.label"
                                          :value="item.timePeriod">
                                      </el-option>
                                    </el-select>
                                  </div> </el-col>
                              </el-row>
                              <el-row>
                                <el-col :span="12">
                                  <div class="grid-content ">
                                    <h5>Please choose time:</h5>
                                    <el-select v-model="value" placeholder="Please choose the period" >
                                      <el-option
                                          v-for="item in lessonoptions"
                                          :key="item.timePeriod"
                                          :label="item.label"
                                          :value="item.value">
                                      </el-option>
                                    </el-select>
                                  </div></el-col>
                              </el-row><el-row> <el-button  type="success" @click="Order2()" style="margin-top: 50px">Confirm order</el-button></el-row>
                            </div></el-col></el-row>

                        </md-tab>
                      </md-tabs>
                    </template>
                  </nav-tabs-card>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>





      <AppFooter></AppFooter>
    </div>  </div>
</template>

<script>
import { NavTabsCard } from "@/components";
import 'video.js/dist/video-js.css'
import AppFooter from "@/layout/AppFooter";
import Navigation from "./components/NavigationSection";
import { BCarousel } from "bootstrap-vue/esm/components/carousel/carousel";
import { BCarouselSlide } from "bootstrap-vue/esm/components/carousel/carousel-slide";
export default {
  mounted() {
    this.initPage();
    this.initTimeTable();

  },
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
    AppFooter,
    // BookCard,
    // Images,
    // Carousel,
    Navigation,
    NavTabsCard,
    BCarousel,
    BCarouselSlide


  },
  bodyClass: "profile-page",
  data() {
    var validatePass2 = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('Please enter the password again'));
      } else if (value !== this.dynamicValidateForm.pass) {
        callback(new Error('The passwords entered twice do not match!'));
      } else {
        callback();
      }
    };
    return {
      activityname:"",
      radio1:"",
      radio2:"",
      activitydate:"",
      daterange1:"",
      daterange2:"",
      input:"",
      startTime:"",
      radio3:"",
      coachoptions: [{
        value: 'Option 1',
        label: 'Carl'
      }, {
        value: 'Option 2',
        label: 'Devin'
      }, {
        value: 'Option 3',
        label: 'Andre'
      }, {
        value: 'Option 4',
        label: 'Matt'
      }, {
        value: 'Option 5',
        label: 'Alex'
      }],
      value: '',
      options: [{
        timePeriod: '10:00-12:00',
        label: '10:00-12:00'
      }, {
        timePeriod: '12:00-14:00',
        label: '12:00-14:00'
      }, {
        timePeriod: '14:00-16:00',
        label: '14:00-16:00'
      }, {
        timePeriod: '16:00-18:00',
        label: '16:00-18:00'
      }, {
        timePeriod: '18:00-20:00',
        label: '18:00-20:00'
      },{
        timePeriod: '20:00-22:00',
        label: '20:00-22:00'
      }],
      lessonoptions: [{
        timePeriod: '10:00-12:00',
        label: '10:00-12:00'
      }, {
        timePeriod: '14:00-16:00',
        label: '14:00-16:00'
      }, {
        timePeriod: '16:00-20:00',
        label: '16:00-20:00'
      }],
      lessonTable: [{
        date: 'Monday',
        time:'10:00-12:00',
        lesson: 'Swimming Class',
        coach: 'Carl'
      }, {
        date: 'Monday',
        time:'14:00-16:00',
        lesson: 'Swimming Class',
        coach: 'Carl'
      }, {
        date: 'Wednesday',
        time:'10:00-12:00',
        lesson: 'Aerobic class',
        coach: 'Devin'
      }, {
        date: 'Thursday',
        time:'18:00-20:00',
        lesson: 'Aerobic class',
        coach: 'Devin'
      }, {
        date: 'Friday',
        time:'10:00-12:00',
        lesson: 'Anaerobic class',
        coach: 'Carl'
      }, {
        date: 'Friday',
        time:'18:00-20:00',
        lesson: 'Anaerobic class',
        coach: 'Andre'
      }, {
        date: 'Saturday',
        time:'18:00-20:00',
        lesson: 'Boxing class',
        coach: 'Alan'
      }, {
        date: 'Saturday',
        time:'18:00-20:00',
        lesson: 'Squash class',
        coach: 'Matt'
      }, {
        date: 'Sunday',
        time:'10:00-12:00',
        lesson: 'Yoga class',
        coach: 'Alex'
      },{
        date: 'Sunday',
        time:'18:00-20:00',
        lesson: 'Yoga class',
        coach: 'Alex'
      }],
      timePeriod: '',
      timetable:[],
      detail:[],
      activities:[],
      checkbox1: false,
      checkbox2: false,
      checkbox3: false,
      classicModal: false,
      order:{},
      dynamicValidateForm: {
        pass: '',
        checkPass: '',
        email: ''
      },
      rules: {
        email:[
          { required: true, message: 'Please input the email address', trigger: 'blur' },
          { type: 'email', message: 'Please input the correct email address', trigger: ['blur', 'change'] }
        ],
        pass: [
          { required: true, message: 'Please enter password', trigger: 'blur' },
          // { validator: validatePass, trigger: 'blur' }
        ],
        checkPass: [
          { required: true, message: 'Please enter confirm password', trigger: 'blur' },
          { validator: validatePass2, trigger: 'blur' }
        ]
      },
      tabPane1: [
        { image: require("@/assets/img/examples/studio-1.jpg") },
        { image: require("@/assets/img/examples/studio-2.jpg") },
        { image: require("@/assets/img/examples/studio-4.jpg") },
        { image: require("@/assets/img/examples/studio-5.jpg") }
      ],
      tabPane2: [
        { image: require("@/assets/img/examples/olu-eletu.jpg") },
        { image: require("@/assets/img/examples/clem-onojeghuo.jpg") },
        { image: require("@/assets/img/examples/cynthia-del-rio.jpg") },
        { image: require("@/assets/img/examples/mariya-georgieva.jpg") },
        { image: require("@/assets/img/examples/clem-onojegaw.jpg") }
      ],
      tabPane3: [
        { image: require("@/assets/img/examples/mariya-georgieva.jpg") },
        { image: require("@/assets/img/examples/studio-3.jpg") },
        { image: require("@/assets/img/examples/clem-onojeghuo.jpg") },
        { image: require("@/assets/img/examples/olu-eletu.jpg") },
        { image: require("@/assets/img/examples/studio-1.jpg") }
      ]
    };
  },
  methods: {
    searchInfo(val) {
      this.startTime = val;
      console.log(this.startTime);
    },
    initActivity(){
      this.$axios({
        url: '/facility/detail',
        method: 'get',
        params:{
          facilityId:this.$route.query.facilityId
        }
      }).then(res => {
        console.log(9)
        if (res) {
          console.log(8)
          console.log(res)
          this.activities = res.data.data;
        }
      })
    },
    initPage(){
      this.$axios({
        url: '/facility/detail',
        method: 'get',
        params:{
          facilityId:this.$route.query.facilityId
        }
      }).then(res => {
        console.log(9)
        if (res) {
          console.log(8)
          console.log(res)
          this.detail = res.data.data;
        }
      })    },
    initTimeTable(){
      this.$axios({
        url: '/timetable/getTimetable',
        method: 'get',
        params:{
          facilityId:this.$route.query.facilityId
        }
      }).then(res => {
        if (res) {
          console.log(res)
          this.timetable = res.data.data;
        }
      })
    },
    Order1() {
      console.log(JSON.parse(window.localStorage.getItem('user')).id)
      console.log(this.daterange)
      console.log(this.detail.facilityType)
      console.log(JSON.stringify(this.daterange1))
      console.log(99999999999)
      this.$axios({
        url: '/activityOrder/setUpOrder',
        method: 'post',
        params:{
          number:1,
          userId:JSON.parse(window.localStorage.getItem('user')).id,
          facilityType:this.detail.facilityType,
          activityType:"general use",
          daterange: JSON.stringify(this.daterange1),

        }
      }).then(res => {
        // console.log(JSON.parse(window.localStorage.getItem('user')).id)
        console.log(1)
        if (res) {
          console.log(1085209787)
          console.log(res)
          this.order = res.data.data;
        }
      })
      this.$router.push({path:"/detail/order",
        query:{facilityType:this.detail.facilityType,
          activityType:'general use'

        }});
    },
    Order2() {
      this.$axios({
        url: '/activityOrder/setUpOrder',
        method: 'post',
        params:{
          userid:JSON.parse(window.localStorage.getItem('user')).id,
          facilityId: 1,
          number: this.input,
          activityType: this.radio,
          timePeriod: this.timePeriod,
          date: this.activitydate,
          daterange:this.daterange2

        }
      }).then(res => {
        console.log(JSON.parse(window.localStorage.getItem('user')).id)
        console.log(1)
        console.log(this.input)
        console.log(this.radio)
        console.log(this.timePeriod)
        console.log(this.activitydate)

        // console.log(JSON.parse(window.localStorage.getItem('user')).id)
        // console.log(111111111111111)
        if (res) {
          console.log(222222222222222222)
          console.log(res)
          this.cards = res.data.data;
        }
      })
    },
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
      default: require("@/assets/img/1111.png")
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

<style lang="scss" scoped>
//.login-container {
//  border-radius: 15px;
//  background-clip: padding-box;
//  width: 350px;
//  background: #fff;
//  border: 1px solid #eaeaea;
//  z-index: 1;
//  position: absolute;
//  margin-left: 530px;
//  margin-top: 195px;
//  padding-top: 100px;
//  padding-left: 30px;
//  padding-right: 30px;
//  height: 270px;
//
//  /*box-shadow: 0 0 20px #cac6c6;*/
//}
.section {
  padding: 0;
}

.profile-tabs::v-deep {
  .md-card-tabs .md-list {
    justify-content: center;
  }

  [class*="tab-pane-"] {
    margin-top: 3.213rem;
    padding-bottom: 50px;

    img {
      margin-bottom: 2.142rem;
    }
  }
}
</style>