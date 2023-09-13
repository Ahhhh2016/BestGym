<template >
  <div class="container-fluid mt-4" style="overflow: visible; position: relative;" id="side">
    <sidenav1></sidenav1>
    <div class="row">
      <div class="col-md-12 offset-4" style="width: 1000px">
<!--        <billing-card />-->
        <el-input style="width: 300px;margin-bottom: 30px"
    placeholder="Add Facility"
    suffix-icon="el-icon-plus"
    v-model="fac.name">
  </el-input>
            <el-button @click="addFacility"  icon="el-icon-plus" style="margin-bottom: 30px" type="primary" plain>Add</el-button>
        <div class="card">

    <div class="card-header pb-0 px-3">
      <h6 class="mb-0">Edit the information of facilities</h6>
    </div>
           <div style="padding: 100px">
       <el-table
    :data="facilities"
    stripe
    style="width: 100%; color: #00acc1">
    <el-table-column
      prop="id"
      label="Facility Id"
      width="150">
    </el-table-column>
    <el-table-column
      prop="facilityType"
      label="Facility Type"
      width="160">
    </el-table-column>
    <el-table-column
      prop="maxCapacity"
      label="Max Capacity"
      width="160">
    </el-table-column>
    <el-table-column
      prop="numFacility"
      label="Facility Number"
      width="160">
    </el-table-column>
    <el-table-column
      label="Operations"
      width="150">
      <template slot-scope="scope">
        <el-button @click="ShowEdit(scope.row)" type="text" size="small">Edit</el-button>
        <el-button @click="Delete(scope.$index, scope.row)" type="text" size="small">Delete</el-button>
      </template>
    </el-table-column>
</el-table>
        </div>
    <div class="card-body pt-4 p-3">

      <el-dialog
  title="Edit facility"
  :visible.sync="dialogVisible"
  width="30%">
        <el-form v-model="update" :model="update" :rules="rules" ref="update" label-width="120px"  label-position="left">
  <el-form-item label="Facility Type" prop="facilityType" >
    <el-input v-model="update.facilityType" class ="update" ></el-input>
  </el-form-item>
          <el-form-item label="Max Capacity" prop="maxCapacity" >
    <el-input v-model="update.maxCapacity" class="update"></el-input>
  </el-form-item>
          <el-form-item label="Num" prop="numFacility" >
    <el-input v-model="update.numFacility" class="update"></el-input>
  </el-form-item>
</el-form>
  <span slot="footer" class="dialog-footer">
    <el-button @click="dialogVisible = false">Cancel</el-button>
    <el-button type="primary" @click="doUpdate()">Confirm</el-button>
  </span>
</el-dialog>
    </div>

  </div>

        </div>
      <div class="col-md-12 offset-5">
        </div>
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

export default {
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
    this.initFacilitis();
  },
  methods:{
    addFacility(){
      if(this.fac.name){
        this.postrequest('',this.fac).then(resp=>{
          if (resp){
            this.initFacilitis()
          }

        })
      }
      else{
      this.$message.error('Device information cannot be empty!');
    }
    },
      initFacilitis() {
        this.$axios({
          url: '/facility/findAllFacility',
          method: 'get',
        }).then(res => {
          if (res) {
            console.log(res.data.data)
            this.facilities = res.data.data;
          }
        })
      },
      ShowEdit(data){
        this.update=data
        this.dialogVisible = true;
      },
    doUpdate(){
        this.$refs.update.validate((valid)=>{
        if(valid){
          console.log(1234567)
                this.$axios({
                    url:'/employee/modifyFacility',
                    method:'post',
                    data:this.update
                }).then(res=>{
                  console.log(998888)
                  console.log(res)
                    if(res.data.code==0){
                        this.$message({
                            type:'success',
                            message:'Modify successfully!'
                        })
                        this.$router.push('/home')
                    }else{
                        this.$message({
                            type:'error',
                            message:'Fail to edit!'
                        })
                    }
                })
        }else{
          this.$message.error('Please enter all fields!');
          return false;
        }
        this.dialogVisible = false;
      });

    }
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
  // name: "Billing",
  components: {
    sidenav1
  },
  data() {
    return {
      rules: {
        maxCapacity: [
          { required: true,trigger:'blur'},
          // { min: 3, max: 10, message: 'The length should between 3 to 10.', trigger: 'blur' },

        ],
        facilityType: [
          { required: true, trigger: 'blur' },
          // { min: 3, max: 10, message: 'The length should between 3 to 10.', trigger: 'blur' },
        ],
        numFacility:[
          {required:true, trigger:'blur'}
        ]
      },
      fac:{
        name:''
      },
      ruleForm: {
        username: '',
        password: ''
      },
      updateFac:{

      },
      salary: {
        iconName: "account_balance",
        classIcon: "text-white",
        title: "Salary",
        desc: "Belong Interactive",
        price: "+$2000",
      },
      paypal: {
        iconName: "account_balance_wallet",
        classIcon: "text-white",
        title: "Paypal",
        desc: "Freelance Payment",
        price: "$455.00",
      },
      facilities:[],
      dialogVisible:false,
      update:[],
    };
  },
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