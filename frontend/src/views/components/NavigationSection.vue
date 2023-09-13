<template>
  <div class="wrapper">
    <div id="navigation" style="float: right">
      <div class="navigation-example" :style="bgImage">
        <div class="md-layout">
          <div class="md-layout-item">


            <md-toolbar class="md-transparent">
              <div class="md-toolbar-row" style="float: left">
                <div class="md-toolbar-section-start" style="margin-left: -200px">
                  <h3 class="md-title" style="margin-left: -800px"  @click="jumphome()">Best Gym</h3>
                </div>
                <div class="md-toolbar-section-end">
                  <md-button
                      class="md-just-icon md-simple md-white md-toolbar-toggle"
                  >
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                  </md-button>

                  <div class="md-collapse">
                    <md-list>
                      <md-list-item href="javascript:void(0)">
                        <p>Discover</p>
                      </md-list-item>

                      <md-list-item v-if="flag==1" href="javascript:void(0)">
                        <p>Hi, {{user.username}}</p>
                      </md-list-item>
                      <md-list-item v-else href="javascript:void(0)">
                        <p @click="jumplog()">log in</p>
                      </md-list-item>
                      <md-list-item href="javascript:void(0)">
                        <div class="md-collapse">
                          <div class="md-autocomplete">
                            <md-autocomplete
                                class="search has-white"
                                v-model="selectedEmployee"
                                :md-options="employees"
                                :md-open-on-focus="false"
                            >
                              <label>Search...</label>
                            </md-autocomplete>
                          </div>
                        </div>
                      </md-list-item>
                      <li class="md-list-item">
                        <a
                            href="javascript:void(0)"
                            class="md-list-item-router md-list-item-container md-button-clean"
                        >
                          <div class="md-list-item-content">
                            <md-button class="md-rose md-just-icon md-round"
                            ><md-icon>email</md-icon></md-button
                            >
                          </div>
                        </a>
                      </li>

                      <li class="md-list-item">
                        <a
                            href="javascript:void(0)"
                            class="md-list-item-router md-list-item-container md-button-clean dropdown"
                        >
                          <div class="md-list-item-content">
                            <drop-down direction="down" class="profile-photo">
                              <!--                              <el-dropdown split-button type="primary" @click="goCenter">-->
                              <div
                                  class="profile-photo-small"
                                  slot="title"
                                  data-toggle="dropdown"
                              >
                                <img :src="img" alt="Circle Image" />
                              </div>


                              <ul class="dropdown-menu">
                                <li class="dropdown-header">Dropdown header</li>
                                <li>
                                  <a  @click='goCenter()'>Me</a>
                                </li>
                                <li>
                                  <a  class="dropdown-item" @click="logout()"
                                  >Sign Out</a
                                  >
                                </li>
                              </ul>
                              <!--                              <el-dropdown-menu slot="dropdown" @click='goCenter()'>-->
                              <!--                                  <el-dropdown-item @click='goCenter()'>Me</el-dropdown-item>-->
                              <!--                                  <el-dropdown-item>狮子头</el-dropdown-item>-->
                              <!--                                  <el-dropdown-item>螺蛳粉</el-dropdown-item>-->
                              <!--                                  <el-dropdown-item disabled>双皮奶</el-dropdown-item>-->
                              <!--                                  <el-dropdown-item divided>蚵仔煎</el-dropdown-item>-->
                              <!--  </el-dropdown-menu>-->
                              <!--                                </el-dropdown>-->
                            </drop-down>

                          </div>
                        </a>
                      </li>
                    </md-list>
                  </div>
                </div>
              </div>
            </md-toolbar>


          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  mounted() {
    this.init();
  },
  methods: {
    jumplog(){
      this.$router.push("/login");
    },
    jumphome(){
      this.$router.push("/home");
    },
    init(){
      if(window.localStorage.getItem('user')){
        this.flag=1
      }
    },
    // 跳转页面方法
    goCenter() {
      // this.$router.push("/user");
      if(JSON.parse(window.localStorage.getItem('user'))){
        this.$router.push("/user");
        console.log(12345555)
      }else {
        // console.log(1234567)
        this.$message({
          type:'error',
          message:'Please log in first!'
        })
      }
    },
    logout(){
      localStorage.clear()
      this.$axios({
        url:'/user/logout',
        method:'post',
        data:{
          userId:JSON.parse(window.localStorage.getItem('user')),
        }
      }).then(res=>{
        console.log(333333333333)
        console.log(res)
        if(res){
          console.log(1111111111111)
          this.$message({
            type:'success',
            message:'log out successfully!'
          })
          this.$router.push('/login')
        }else{
          this.$message({
            type:'error',
            message:'log out failed'
          })
        }
      })
    },
  },
  data() {
    return {
      user:JSON.parse(window.localStorage.getItem('user')),
      selectedEmployee: "",
      employees: [
        "Jim Halpert",
        "Dwight Schrute",
        "Michael Scott",
        "Pam Beesly",
        "Angela Martin",
        "Kelly Kapoor",
        "Ryan Howard",
        "Kevin Malone"
      ],
      flag:0,
    };
  },
  props: {

    img: {
      type: String,
      default: require("@/assets/img/faces/avatar.jpg")
    }
  },
  computed: {
    bgImage() {
      return {
        backgroundImage: `url(${this.image})`
      };
    }
  }
};
</script>

<style lang="css"></style>