<template>
<body id="poster">
  <el-menu
    :default-active="activeIndex"
    class="el-menu-demo"
    mode="horizontal"
    @select="handleSelect"
  >
    <el-menu-item
      class="el-menu-item"
      style="color: white; font-weight: bold; font-size: 17px"
      index="1"
    >My Gym</el-menu-item>

    <el-menu-item index="3" style="color: white; float: right">Home</el-menu-item>
    <!--    <el-menu-item index="4"><a href="https://www.ele.me" target="_blank" style="float: right">订单管理</a></el-menu-item>-->
  </el-menu>
  <div class="line"></div>
  <el-card
    :body-style="{ padding: '0px', background:'#51a655' ,shadow:'always'}"
    style="margin-top: 150px; margin-bottom: -200px; width: 200px; height: 90px; margin-left: 640px; background-color: #51a655; border-color: #51a655;z-index: 2;position: absolute; box-shadow: 0 0 20px rgba(59,57,57,0.56)"
  >
    <div style="padding-top: 33px">
      <span style="color: white; font-size: 18px; font-weight: bold">Register</span>
    </div>
  </el-card>
  <el-form
    :model="dynamicValidateForm"
    :rules="rules"
    ref="dynamicValidateForm"
    label-width="140px"
    class="login-container"
    label-position="left"
  >
    <el-form-item
      label="Username"
      prop="name"
      :rules="[
      { required: true, message: '请输入用户名', trigger: 'blur' },
    ]"
    >
      <el-input v-model="dynamicValidateForm.name" placeholder="Username"></el-input>
    </el-form-item>
    <el-form-item prop="email" label="Email">
      <el-input v-model="dynamicValidateForm.email"></el-input>
    </el-form-item>
    <el-form-item
      label="Password"
      prop="pass"
      :rules="[
      { required: true, message: '请输入密码', trigger: 'blur' },
    ]"
    >
      <el-input type="password" v-model="dynamicValidateForm.pass" autocomplete="off"></el-input>
    </el-form-item>
    <el-form-item
      class="confirm"
      label="Confirm password"
      id="selectForm"
      prop="checkPass"
      style="font-size: 5px"
    >
      <el-input type="password" v-model="dynamicValidateForm.checkPass" autocomplete="off"></el-input>
    </el-form-item>
    <el-form-item>
      <el-button
        class="button1"
        type="primary"
        @click="submitForm('dynamicValidateForm')"
        style="margin-bottom: 400px"
      >Create</el-button>
    </el-form-item>
  </el-form>
</body>
</template>
<script>
import { postRequest } from '@/utils/api';

export default {
  data() {
    var validatePass2 = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请再次输入密码'));
      } else if (value !== this.dynamicValidateForm.pass) {
        callback(new Error('两次输入密码不一致!'));
      } else {
        callback();
      }
    };
    return {
      dynamicValidateForm: {
        username: '',
        pass: '',
        checkPass: '',
        email: ''
      },
      rules: {
        username: [
          { required: true, message: 'Please enter username', trigger: 'blur' }
        ],
        email: [
          { required: true, message: '请输入邮箱地址', trigger: 'blur' },
          {
            type: 'email',
            message: '请输入正确的邮箱地址',
            trigger: ['blur', 'change']
          }
        ],
        pass: [
          { required: true, message: '请输入密码', trigger: 'blur' }
          // { validator: validatePass, trigger: 'blur' }
        ],
        checkPass: [
          { required: true, message: '请输入确认密码', trigger: 'blur' },
          { validator: validatePass2, trigger: 'blur' }
        ]
      }
    };
  },
  methods: {
    submitForm() {
      this.$refs.dynamicValidateForm.validate((valid) => {
        if (valid) {
          postRequest('/user/add', this.dynamicValidateForm).then((resp) => {
            if (resp) {
              console.log('111111111111111111111');
              this.$router.replace('/home');
            }
          });
        }
      });
      //         console.log(this.dynamicValidateForm)
      //         const _this=this
      //         request({
      //           url:'/user/add',
      //           method:'post',
      //           data:_this.dynamicValidateForm
      //         }).then(res=>{
      //           console.log(res)
      //           if(res.data.code==0){
      //             this.$message({
      //               type:'success',
      //               message:'register successfully!'
      //             })
      //             this.$router.push('/home')
      //           }else{
      //             this.$message({
      //               type:'error',
      //               message:'register failed'
      //             })
      //           }
      //         })
      //         alert('submit!');
      //       } else {
      //         console.log('error submit!!');
      //         return false;
      //       }
      //     });
      //   },
    }
  }
};
</script>
<!--<script>-->
<!--export default {-->
<!--  data() {-->
<!--    var validatePass = (rule, value, callback) => {-->
<!--      if (value === '') {-->
<!--        callback(new Error('请输入密码'));-->
<!--      } else {-->
<!--        if (this.ruleForm.checkPass !== '') {-->
<!--          this.$refs.ruleForm.validateField('checkPass');-->
<!--        }-->
<!--        callback();-->
<!--      }-->
<!--    };-->
<!--    var validatePass2 = (rule, value, callback) => {-->
<!--      if (value === '') {-->
<!--        callback(new Error('请再次输入密码'));-->
<!--      } else if (value !== this.ruleForm.pass) {-->
<!--        callback(new Error('两次输入密码不一致!'));-->
<!--      } else {-->
<!--        callback();-->
<!--      }-->

<!--    };-->
<!--    return {-->
<!--      ruleForm: {-->
<!--        name: '',-->
<!--        pass: '',-->
<!--        checkPass: '',-->
<!--      },-->
<!--      rules: {-->
<!--        pass: [-->
<!--          { required: true, message: 'Please type in your password', trigger: 'blur' },-->
<!--          { min: 3, max: 10, message: 'The length should between 3 to 10.', trigger: 'blur' },-->
<!--          { validator: validatePass, trigger: 'blur' }-->
<!--        ],-->
<!--        checkpass: [-->
<!--          { required: true, message: 'Please type in your password', trigger: 'blur' },-->
<!--          { min: 3, max: 10, message: 'The length should between 3 to 10.', trigger: 'blur' },-->
<!--          { validator: validatePass2, trigger: 'blur' }-->
<!--        ],-->
<!--        name: [-->
<!--          { required: true, message: 'Please type in your account', trigger: 'blur' },-->
<!--          { min: 3, max: 10, message: 'The length should between 3 to 10.', trigger: 'blur' }-->
<!--        ],-->

<!--      }-->
<!--    };-->
<!--  },-->
<!--  methods: {-->

<!--    submitForm(formName) {-->
<!--      this.$refs[formName].validate((valid) => {-->
<!--        if (valid) {-->
<!--          alert('submit!');-->
<!--          this.$router.push("/home");-->
<!--        } else {-->
<!--          console.log('error submit!!');-->
<!--          return false;-->
<!--        }-->
<!--      });-->
<!--    }-->
<!--  }-->
<!--}-->
<!--</script>-->



<style>
#poster {
  background: url('../../assets/img/profile_city.jpg') no-repeat;
  background-position: center;
  height: 100%;
  width: 100%;
  background-size: cover;
  position: fixed;
}
body {
  margin: 0px;
}
#selectForm >>> .el-form-item__label {
  font-size: 12px;
}
.login-container {
  border-radius: 15px;
  background-clip: padding-box;
  width: 350px;
  background: #fff;
  border: 1px solid #eaeaea;
  z-index: 1;
  position: absolute;
  margin-left: 530px;
  margin-top: 195px;
  padding-top: 100px;
  padding-left: 30px;
  padding-right: 30px;
  height: 270px;

  /*box-shadow: 0 0 20px #cac6c6;*/
}
.login_title {
  margin: 0px auto 40px auto;
  text-align: center;
  font-size: 20px;
  color: #505458;
}

.el-menu-item {
  padding: 0 62px;
  color: #fff;
  font-size: 16px;
}

.el-menu-demo {
  width: 1580px;
  height: 54px;
  /* border-bottom-color:none!important; */
  background-color: rgba(255, 255, 255, 0.01);
  float: left;
  /*margin-right: 8.54%;*/
  color: white;
}
.button1 {
  background-color: #51a655;
  margin-left: -130px;
  margin-top: 17px;
  border-color: #51a655;
  box-shadow: 0 0 20px #cac6c6;
}
/* 点击出来的下划线进行隐藏 */
.el-menu-item.is-active {
  border-bottom: none !important;
  color: rgba(0, 0, 0, 0);
}
/* 整体的下划线进行隐藏 */
.el-menu.el-menu--horizontal {
  border-bottom: none;
}
/* 导航栏的间距等样式 */

/* 点击出来的下划线动效残留进行隐藏 */
.el-menu--horizontal > .el-menu-item {
  border-bottom: none;
}
/* 点击以后的背景色进行隐藏 */
.el-menu--horizontal > .el-menu-item:not(.is-disabled):focus,
.el-menu--horizontal > .el-menu-item:not(.is-disabled):hover,
.el-menu--horizontal > .el-submenu .el-submenu__title:hover {
  background-color: rgba(0, 0, 0, 0);
}
</style>