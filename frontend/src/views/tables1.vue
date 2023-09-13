<template>
  <div class="container-fluid py-4">
    <sidenav></sidenav>
<!--    <sidenav></sidenav>-->
    <div class="row" style="background-color: #00c853">
<!--      <div class="container" style="width: 1500px;height:400px;background-color: #1a2035">-->
      <div class="col-md-18 offset-4" style="background-color: #00a5bb">
        <TabSection2></TabSection2>

      </div>
    </div>
      </div>

</template>

<script>
import sidenav from '../examples/Sidenav/index'
import TabSection2 from "@/views/components/TabSection2";
export default {
  data() {
    return {
      tableData: [{
        date: '2016-05-02',
        name: '王小虎',
        province: '上海',
        city: '普陀区',
        address: '上海市普陀区金沙江路 1518 弄',
        zip: 200333
      }, {
        date: '2016-05-04',
        name: '王小虎',
        province: '上海',
        city: '普陀区',
        address: '上海市普陀区金沙江路 1517 弄',
        zip: 200333
      }, {
        date: '2016-05-01',
        name: '王小虎',
        province: '上海',
        city: '普陀区',
        address: '上海市普陀区金沙江路 1519 弄',
        zip: 200333
      }, {
        date: '2016-05-03',
        name: '王小虎',
        province: '上海',
        city: '普陀区',
        address: '上海市普陀区金沙江路 1516 弄',
        zip: 200333
      }]
    }
  },
  components:{
    sidenav,
    TabSection2
  },
  name: "tables",
};
</script>
