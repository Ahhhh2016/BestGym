<template>
  <div class="container-fluid mt-4" style="overflow: visible; position: relative;" id="side">
    <sidenav1></sidenav1>
<!--    <sidenav></sidenav>-->
    <div class="row" style="">
      <div class="container" style="width: 1500px">
      <div class="col-md-14 offset-3" style="background-color: rgba(18,19,19,0.42)">
<!--        <div style="height: 300px; width: 200px; background-color: #1AB7EA;z-index: 2;position: absolute"></div>-->
<!--        <div style="z-index: 1;margin-top: -500px;background-color: red; height: 800px;position: relative">-->
          <el-card :body-style="{ padding: 'px', background:'#51a655' ,shadow:'always', }" class="card2">
  <div style="padding-top: 10px">
    <span class="span2">User Management</span>
  </div>

</el-card>
        <div style="padding: 100px">
       <el-table
    :data="users"
    stripe
    style="width: 100%; color: #00acc1">
    <el-table-column
      prop="id"
      label="User Id"
      width="150">
    </el-table-column>
    <el-table-column
      prop="username"
      label="Username"
      width="120">
    </el-table-column>
    <el-table-column
      prop="membership"
      label="Membership"
      width="120">
    </el-table-column>
    <el-table-column
      prop="cardNumber"
      label="Card"
      width="120">
    </el-table-column>
    <el-table-column
      prop="email"
      label="Email"
      width="300">
    </el-table-column>
    <el-table-column
      prop="phoneNumber"
      label="Phone Number"
      width="120">
    </el-table-column>
    <el-table-column
      fixed="right"
      label="Operations"
      width="100">
      <template slot-scope="scope">
        <el-button @click="handleClick(scope.row)" type="text" size="small">Edit</el-button>
        <el-button @click="Delete(scope.$index, scope.row)" type="text" size="small">Delete</el-button>
      </template>
    </el-table-column>
</el-table>
        </div>
          </div>
      </div>
    </div>
      </div>
</template>

<script>
import sidenav1 from '../../examples/Sidenav1/index'
export default {
  beforeMount() {
    this.$store.state.hideConfigButton = false;
    this.$store.state.showNavbar = false;
    this.$store.state.showSidenav1 = false;
    this.$store.state.showFooter = false;
  },
  beforeUnmount() {
    this.$store.state.hideConfigButton = false;
    this.$store.state.showNavbar = false;
    this.$store.state.showSidenav = false;
    this.$store.state.showFooter = false;
  },
  mounted() {
    if(JSON.parse(window.localStorage.getItem('user'))) {
      if (JSON.parse(window.localStorage.getItem('user')).role == 1) {
        console.log(111)
      } else {
        this.$router.push("/home");
        this.$message({
          type: 'error',
          message: "You don't have the permission"
        })
      }
    }
    else {
      this.$router.push("/login");
       this.$message({
                type:'error',
                message:'Please log in first!'
              })
    }
    this.initUsers();
  },
  methods:{
      initUsers() {
        this.$axios({
          url: '/employee/listUser',
          method: 'get',
        }).then(res => {
          if (res) {
            this.users = res.data.data;
          }
        })
      },
    Delete(index, data){
      this.$axios({
          url: '/employee/deleteUser',
          method: 'get',
          params:{
          userId: data.id
        }
        }).then(res => {
          console.log(data.id)
          if (res) {
            console.log(1111)
            this.initUsers()
            if(res.data.code==0){
              this.$message({
                type:'success',
                message:'Successfully modify the status!'
              })
            }
          }
        })
},
    },
  data() {
    return {
      users:[],
    }
  },
  components:{
    sidenav1
  },
  // name: "tables",
};
</script>
<style>
#side{
  background: url("../../assets/img/bg.png") no-repeat;
  height: 100%;
  max-height: 1500px;
  overflow: hidden;
  background-size:cover;
  position: relative;
}
.card2{
margin-top: 30px;
  margin-bottom: -250px;
  width: 800px;
  height: 80px;
  margin-left: 200px;
  background-color: #5d3e65;
  border-color: #51a655;
  z-index: 2;
  position: absolute;
  box-shadow: 0 0 20px rgba(59,57,57,0.56)
}
 .span2{
   color: white;
   font-size: 18px;
   font-weight: bold;
   position: center;
   margin-left: 250px;
   padding-bottom: 80px;
 }
</style>
