<template>
  <div class="container-fluid py-4" id="side">
    <sidenav1></sidenav1>
<!--    <sidenav></sidenav>-->

        <el-tabs type="border-card" style="margin-left: 270px;width: 1300px;padding: 0px">
  <el-tab-pane label="All orders">
    <div class="row" style="">
      <div class="container" style="width: 1500px;">
      <div >
          <el-card :body-style="{ paddingLeft: '-80px', background:'#51a655' ,shadow:'always', }" class="card2">
  <div style="padding-top: 10px">
    <span class="span2">All Orders</span>
  </div>
            </el-card>
        <div style="padding-top: 100px;margin-left: -40px">
       <el-table
    :data="allorders"
    stripe
    style="width: 100%; color: #00acc1">
    <el-table-column
      prop="paymentdate"
      label="Generate Date"
      width="150">
    </el-table-column>
         <el-table-column
      prop="username"
      label="User name"
      width="140">
    </el-table-column>
    <el-table-column
      prop="facilitytype"
      label="Facility"
      width="140">
    </el-table-column>
    <el-table-column
      prop="activitytype"
      label="Activity"
      width="140">
    </el-table-column>
    <el-table-column
      prop="time"
      label="Date"
      width="140">
    </el-table-column>
    <el-table-column
      prop="address"
      label="Time period"
      width="140">
    </el-table-column>
    <el-table-column
      label="operations"
      width="160">
      <template slot-scope="scope">
        <el-button @click="Delete(scope.$index, scope.row)" type="danger" size="small">Delete</el-button>
      </template>
    </el-table-column>
</el-table>
        </div>
          </div>
      </div>
    </div>
    </el-tab-pane>
  <el-tab-pane label="Unpaid">
    <div class="row" style="">
      <div class="container" style="width: 1500px;">
      <div >
          <el-card :body-style="{ padding: 'px', background:'#51a655' ,shadow:'always', }" class="card2">
  <div style="padding-top: 10px">
    <span class="span2">Unpaid Orders</span>
  </div>
            </el-card>
        <div style="padding-top: 100px;margin-left: -40px">
       <el-table
    :data="unpaidorders"
    stripe
    style="width: 100%; color: #00acc1">
<!--         <el-table-column-->
<!--      prop="id"-->
<!--      label="Order Id"-->
<!--      width="150">-->
<!--    </el-table-column>-->
    <el-table-column
      prop="paymentdate"
      label="Generate Date"
      width="150">
    </el-table-column>
         <el-table-column
      prop="username"
      label="User name"
      width="140">
    </el-table-column>
    <el-table-column
      prop="facilitytype"
      label="Facility"
      width="140">
    </el-table-column>
    <el-table-column
      prop="activitytype"
      label="Activity"
      width="140">
    </el-table-column>
    <el-table-column
      prop="time"
      label="Date"
      width="140">
    </el-table-column>
    <el-table-column
      prop="address"
      label="Time period"
      width="140">
    </el-table-column>
    <el-table-column
      label="operations"
      width="160">
      <template slot-scope="scope">
        <el-button type="primary" size="small" @click="changePaid(scope.$index, scope.row)">paid</el-button>
        <el-button @click="Delete(scope.$index, scope.row)" type="danger" size="small">Delete</el-button>
      </template>
    </el-table-column>
</el-table>
        </div>
          </div>
      </div>
    </div>
  </el-tab-pane>
  <el-tab-pane label="Unused">
    <div class="row" style="">
      <div class="container" style="width: 1500px;">
      <div >
          <el-card :body-style="{ padding: 'px', background:'#51a655' ,shadow:'always', }" class="card2">
  <div style="padding-top: 10px">
    <span class="span2">Unused Orders</span>
  </div>
            </el-card>
        <div style="padding-top: 100px;margin-left: -40px">
       <el-table
    :data="unusedorders"
    stripe
    style="width: 100%; color: #00acc1">
    <el-table-column
      prop="paymentdate"
      label="Generate Date"
      width="150">
    </el-table-column>
         <el-table-column
      prop="username"
      label="User name"
      width="140">
    </el-table-column>
    <el-table-column
      prop="facilitytype"
      label="Facility"
      width="140">
    </el-table-column>
    <el-table-column
      prop="activitytype"
      label="Activity"
      width="140">
    </el-table-column>
    <el-table-column
      prop="time"
      label="Date"
      width="140">
    </el-table-column>
    <el-table-column
      prop="address"
      label="Time period"
      width="140">
    </el-table-column>
    <el-table-column
      label="operations"
      width="180">
      <template slot-scope="scope">
         <el-button type="primary" size="small" @click="WriteOff(scope.$index, scope.row)">write off</el-button>
        <el-button @click="Delete(scope.$index, scope.row)" type="danger" size="small">Delete</el-button>
      </template>
    </el-table-column>
</el-table>
        </div>
          </div>
      </div>
    </div>
  </el-tab-pane>
  <el-tab-pane label="Used">
    <div class="row" style="">
      <div class="container" style="width: 1500px;">
      <div >
          <el-card :body-style="{ padding: 'px', background:'#51a655' ,shadow:'always', }" class="card2">
  <div style="padding-top: 10px">
    <span class="span2">Used Orders</span>
  </div>
            </el-card>
        <div style="padding-top: 100px;margin-left: -40px">
       <el-table
    :data="usedorders"
    stripe
    style="width: 100%; color: #00acc1">
    <el-table-column
      prop="paymentdate"
      label="Generate Date"
      width="150">
    </el-table-column>
         <el-table-column
      prop="username"
      label="User name"
      width="140">
    </el-table-column>
    <el-table-column
      prop="facilitytype"
      label="Facility"
      width="140">
    </el-table-column>
    <el-table-column
      prop="activitytype"
      label="Activity"
      width="140">
    </el-table-column>
    <el-table-column
      prop="time"
      label="Date"
      width="140">
    </el-table-column>
    <el-table-column
      prop="address"
      label="Time period"
      width="140">
    </el-table-column>
    <el-table-column
      label="operations"
      width="160">
      <template slot-scope="scope">
        <el-button @click="Delete(scope.$index, scope.row)" type="danger" size="small">Delete</el-button>
      </template>
    </el-table-column>
</el-table>
        </div>
          </div>
      </div>
    </div>
  </el-tab-pane>
</el-tabs>
      </div>


<!--          </div>-->
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
    this.initAllOrders();
    this.initUnpaidOrders();
    this.initUnusedOrders();
    this.initUsedOrders();
  },
  methods:{
    WriteOff(index, data){
      this.$axios({
          url: '/activityOrder/modifyUsageStatus',
          method: 'get',
          params:{
          orderid: data.id
        }
        }).then(res => {
          console.log(data.id)
          if (res) {
            this.initUnpaidOrders()
            this.initUnusedOrders()
            this.initUsedOrders()
            if(res.data.code==0){
              this.$message({
                type:'success',
                message:'Successfully modify the status!'
              })
            }

          }
        })

},
    Delete(index, data){
      this.$axios({
          url: '/activityOrder/deleteOrder',
          method: 'get',
          params:{
          orderId: data.id
        }
        }).then(res => {
          console.log(data.id)
          if (res) {
            console.log(1111)
            this.initAllOrders()
            this.initUnpaidOrders()
            this.initUnusedOrders()
            this.initUsedOrders()
            if(res.data.code==0){
              this.$message({
                type:'success',
                message:'Successfully delete the order!'
              })
            }
          }
        })
},
    changePaid(index, data){
      this.$axios({
          url: '/activityOrder/modifyPaymentStatus',
          method: 'get',
          params:{
          orderid: data.id
        }
        }).then(res => {
          console.log(data.id)
          if (res) {
            this.initUnpaidOrders()
            this.initUnusedOrders()
            this.initUsedOrders()
            if(res.data.code==0){
              this.$message({
                type:'success',
                message:'Successfully modify the status!'
              })
            }
          }
        })

    },
      initAllOrders() {
        this.$axios({
          url: '/activityOrder/allOrder',
          method: 'get',
          params:{
          paymentStatus:0
        }
        }).then(res => {
          console.log(111111111111)
          if (res) {
            this.allorders = res.data.data;
            console.log(2222222222222)
          }
        })
      },
    initUnpaidOrders() {
        this.$axios({
          url: '/activityOrder/findByStatus',
          method: 'get',
          params:{
          paymentStatus:0,
          usageStatus:0
        }
        }).then(res => {
          console.log(111111111111)
          if (res) {
            this.unpaidorders = res.data.data;
            console.log(2222222222222)
          }
        })
      },
    initUsedOrders() {
        this.$axios({
          url: '/activityOrder/findByStatus',
          method: 'get',
          params:{
          paymentStatus:1,
          usageStatus:1
        }
        }).then(res => {
          console.log(111111111111)
          if (res) {
            this.usedorders = res.data.data;
            console.log(2222222222222)
          }
        })
      },
    initUnusedOrders() {
        this.$axios({
          url: '/activityOrder/findByStatus',
          method: 'get',
          params:{
          paymentStatus:1,
          usageStatus:0
        }
        }).then(res => {
          console.log(111111111111)
          if (res) {
            this.unusedorders = res.data.data;
            console.log(2222222222222)
          }
        })
      }
    },
  data() {
    return {
      allorders:[],
      unpaidorders:[],
      unusedorders:[],
      usedorders:[]
      // tableData: [{
      //   date: '2022-04-06',
      //   name1: 'Rebecca',
      //   name: 'Fitness',
      //   province: 'Table tennis',
      //   city: '2022-04-08',
      //   address: '8:00-10:00',
      //   status: 'unused'
      // }, {
      //   date: '2022-04-10',
      //   name1: 'sxc',
      //   name: 'Swimming poor',
      //   province: 'swim practice',
      //   city: '2022-04-12',
      //   address: '14:00-16:00',
      //   status: 'finished'
      // }, {
      //   date: '2022-04-13',
      //   name1: 'Tom',
      //   name: 'Fitness room',
      //   province: 'treadmill',
      //   city: '2022-04-14',
      //   address: '16:00-18:00',
      //   status: 'unused'
      // }, {
      //   date: '2022-04-14',
      //   name1: 'xiatianrui',
      //   name: 'Sports hall',
      //   province: 'badminton',
      //   city: '2022-04-16',
      //   address: '16:00-18:00',
      //   status: 'unused'
      // }]
    }
  },
  components:{
    sidenav1
  },
  name: "tables",
};
</script>
<style>
#side{
  background: url("../../assets/img/bg.png") no-repeat;
  height: 1000px;
  width:100%;
  overflow: hidden;
  background-size:cover;
}
.card2{
margin-top: 30px;
  margin-bottom: -250px;
  width: 800px;
  height: 80px;
  margin-left: 130px;
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
   margin-left: 200px;
   padding-bottom: 80px;
 }
</style>
