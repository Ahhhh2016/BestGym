
<template>
<body id="poster">
  <el-menu
    :default-active="activeIndex"
    class="el-menu-demo"
    mode="horizontal"
    @select="handleSelect"
    router
  >
    <el-menu-item
      class="el-menu-item"
      style="color: white; font-weight: bold; font-size: 17px"
      index="1"
    >My Gym</el-menu-item>

    <el-menu-item index="/home" style="color: white; float: right">Home</el-menu-item>
    <!--  <el-menu-item index="4"><a href="https://www.ele.me" target="_blank" style="float: right">订单管理</a></el-menu-item>-->
  </el-menu>
  <div class="line"></div>
  <el-card :body-style="{ padding: '0px', background:'#51a655' ,shadow:'always'}" class="card1">
    <div style="padding: 10px 40px 10px 40px; ">
      <span class="login_title" style="color: white; margin-left: 70px">Log in</span>
    </div>
  </el-card>
  <el-form
    :model="ruleForm"
    :rules="rules"
    ref="ruleForm"
    label-width="100px"
    class="login-container1"
    label-position="left"
  >
    <el-form-item label="Account" prop="name">
      <i class="el-icon-user" style="margin-left: -40px; margin-right: 18px;margin-top: -30px"></i>
      <el-input v-model="ruleForm.username" placeholder="Account"></el-input>
    </el-form-item>
    <el-form-item label="Password" prop="pass">
      <i class="el-icon-unlock" style="margin-left: -28px; margin-right: 14px"></i>
      <el-input
        type="password"
        placeholder="Password"
        v-model="ruleForm.password"
        autocomplete="off"
        style="width: 290px"
      ></el-input>
    </el-form-item>
    <el-form-item label="Verification Code" prop="code" label-width="150px">
      <i class="el-icon-unlock" style="margin-left: -28px; margin-right: 14px"></i>
      <el-input
        type="text"
        placeholder="Click code to change"
        v-model="ruleForm.code"
        autocomplete="off"
        style="width: 180px"
      ></el-input>
      <img :src="captchaUrl" @click="update()" />
    </el-form-item>
    <el-form-item>
      <div
        style="padding-top: 0px; padding-left: 50px; padding-bottom: 0px;margin-top: -15px; height: 30px"
      >
        <router-link
          to="/register"
          class="link1"
          style="color: #51a655;
  font-size: 13px;
  font-weight: bold"
        >No account? register now!</router-link>
      </div>
    </el-form-item>
    <el-form-item style="padding-bottom: 50px">
      <el-button class="button1" type="primary" @click="submitForm()">Get started</el-button>
    </el-form-item>
  </el-form>
</body>
</template>

<script>
import { request } from '@/utils/axios';
export default {
  beforeMount() {
    this.$store.state.hideConfigButton = false;
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
  data() {
    var validatePass = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入密码'));
      } else {
        if (this.ruleForm.checkPass !== '') {
          this.$refs.ruleForm.validateField('checkPass');
        }
        callback();
      }
    };
    return {
      captchaUrl: '/captcha?time=' + new Date(),
      ruleForm: {
        username: '',
        password: '',
        code: ''
      },
      user: {},
      rules: {
        username: [
          { required: true, trigger: 'blur' }
          // { min: 3, max: 10, message: 'The length should between 3 to 10.', trigger: 'blur' },
        ],
        password: [
          { required: true, trigger: 'blur' },
          // { min: 3, max: 10, message: 'The length should between 3 to 10.', trigger: 'blur' },
          { validator: validatePass, trigger: 'blur' }
        ],
        code: [{ required: true, trigger: 'blur' }]
      }
    };
  },
  methods: {
    update() {
      this.captchaUrl = '/captcha?time=' + new Date();
    },
    submitForm() {
      this.$refs.ruleForm.validate((valid) => {
        if (valid) {
          console.log(this.ruleForm);
          const _this = this;
          request({
            url: '/auth/login',
            method: 'post',
            data: _this.ruleForm
          })
            .then((res) => {
              console.log('HHHHH11111');
              console.log(res);
              _this.user = res.data.data;
              console.log(res.data.data);
              console.log(_this.user);
              localStorage.clear();
              localStorage.setItem('user1', JSON.stringify(res.data.data));
              if (res.data.code == 200) {
                localStorage.setItem('token', res.data.data.accessToken);
                this.$message({
                  type: 'success',
                  message: 'login successfully!'
                });
                if (res.data.data.role == '1') {
                  this.$router.push('/employee/user-emp');
                } else if (res.data.data.role == '2') {
                  this.$router.push('/manager/user-manage');
                } else {
                  this.$router.push('/home');
                }
              } else {
                alert('HHHH');
                this.$message({
                  type: 'error',
                  message: res.data.msg
                });
              }
            })
            .error((res) => {
              console.log('errorrrr');
              this.$message({
                type: 'error',
                message: res.data.msg
              });
            });
        } else {
          this.$message.error('Please enter all fields!');
          return false;
        }
      });
    }
  }
};
</script>



<style>
#poster {
  background: url('../../assets/img/bk3.png') no-repeat;
  background-position: center;
  height: 100%;
  width: 100%;
  background-size: cover;
  position: fixed;
}
body {
  margin: 0px;
}
.login-container1 {
  border-radius: 15px;
  background-clip: padding-box;
  width: 460px;
  background: #fff;
  border: 1px solid #eaeaea;
  z-index: 1;
  position: absolute;
  margin-left: 510px;
  margin-top: 195px;
  padding-top: 100px;
  padding-left: 30px;
  padding-right: 30px;
  height: 400px;

  /*box-shadow: 0 0 20px #cac6c6;*/
}
.login_title {
  margin: 0px auto 340px auto;
  text-align: center;
  font-size: 35px;
  color: rgba(252, 249, 249, 0.94);
}

.card1 {
  font-size: 20px;
  font-weight: bolder;
  margin-top: 150px;
  margin-bottom: -200px;
  width: 360px;
  hight: 60px;
  margin-left: 560px;
  background-color: #51a655;
  border-color: #51a655;
  z-index: 2;
  position: absolute;
  box-shadow: 0 0 30px #cac6c6;
}

.button1 {
  background-color: #51a655;
  margin-left: 30px;
  margin-top: 0px;
  border-color: #51a655;
  box-shadow: 0 0 20px #cac6c6;
}

.link1 {
  color: #51a655;
  font-size: 13px;
  font-weight: bold;
  margin-left: 70px;
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

.span1 {
  color: white;
  font-size: 18px;
  font-weight: bold;
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


