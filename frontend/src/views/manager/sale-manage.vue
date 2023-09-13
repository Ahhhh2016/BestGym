<template >
  <div class="py-4 container-fluid" id="side">
    <sidenav1></sidenav1>
    <div class="row">
      <div class="col-md-12 offset-2">

<div class="echart" id="mychart" :style="myChartStyle"></div>
<DashboardDefault/>  </div>
  </div>
  </div>
</template>

<style>
body{
  overflow: scroll;
}
</style>

<script>
import sidenav1 from '../../examples/Sidenav1/index'
import DashboardDefault from "@/views/Dashboard";
import * as echarts from "echarts";
export default {
  data() {
    return {
      xData: ["Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"], //横坐标
      yData: [23, 24, 18, 25, 27, 28, 25], //数据
      myChartStyle: { float: "left", width: "100%", height: "400px" }, //图表样式
    };
  },
  mounted() {
    if(JSON.parse(window.localStorage.getItem('user'))) {
      if (JSON.parse(window.localStorage.getItem('user')).role == 2) {
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
    this.initEcharts();
  },
  methods: {
    initEcharts() {
      var date = new Date();
      var seperator1 = "-";
      var year = date.getFullYear();
      var month = date.getMonth() + 1;
      var strDate = date.getDate();
      if (month >= 1 && month <= 9) {
        month = "0" + month;
      }
      if (strDate >= 0 && strDate <= 9) {
        strDate = "0" + strDate;
      }
      var currentdate = year + seperator1 + month + seperator1 + strDate;
      console.log(currentdate)
      this.$axios({
        url: '',
        method: 'get',
        params: {
          date: currentdate
        }
      }).then(res => {
        if (res) {
          console.log(res)
          this.xData = res.data.data;
          this.yData=res.data.data;
        }
      })
      // 基本柱状图
      const option = {
        xAxis: {
          data: this.xData
        },
        yAxis: {},
        series: [
          {
            type: "bar", //形状为柱状图
            data: this.yData
          }
        ]
      };
      const myChart = echarts.init(document.getElementById("mychart"));
      myChart.setOption(option);
      //随着屏幕大小调节图表
      window.addEventListener("resize", () => {
        myChart.resize();
      });
    },
    function() {
      var date = new Date();
      var seperator1 = "-";
      var year = date.getFullYear();
      var month = date.getMonth() + 1;
      var strDate = date.getDate();
      if (month >= 1 && month <= 9) {
        month = "0" + month;
      }
      if (strDate >= 0 && strDate <= 9) {
        strDate = "0" + strDate;
      }
      var currentdate = year + seperator1 + month + seperator1 + strDate;
      return currentdate;

    },
  },

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

  components:{
    DashboardDefault,
    sidenav1,
      // ChartBars,
      // ChartLine,
      // ChartLineTasks,
      // MiniCards,
      // ProjectsCard,
      // OrdersCard,
      // Navbar

  },
  // name: "tables",
};
</script>
<style>
#side{
  background: url("../../assets/img/bg.png") no-repeat;
  height:100%;
  width:100%;
  overflow: hidden;
  background-size:cover;
}
</style>