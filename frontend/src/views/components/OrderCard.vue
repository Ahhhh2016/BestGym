<template>
  <div class="wrapper">
    <div id="nav-tabs">
      <div class="md-layout" style="margin-top: 50px">
        <div class="md-layout-item md-size-150 md-small-size-100">
          <nav-tabs-card no-label>
            <template slot="content">
              <md-tabs class="md-primary" md-alignment="left">
                <md-tab id="tab-home" md-label="Order" md-icon="face">
                  <div class="md-layout" id="printMe">
                    <div class="md-layout-item md-size-80" style="margin-left:40px">
                      <div class="title">
                        <h3>Username</h3>
                      </div>
                      <el-descriptions title="Order" direction="vertical" :column="4" border>
                        <el-descriptions-item label="Username" >{{username}}</el-descriptions-item>
                        <el-descriptions-item label="Phone number">{{phoneNumber}}</el-descriptions-item>
                        <el-descriptions-item label="Facility" :span="2">{{facilityType}}</el-descriptions-item>
                        <el-descriptions-item label="Date">
                          <el-tag size="small">2022-5-18</el-tag>
                        </el-descriptions-item>
                        <el-descriptions-item label="Payment">{{activityType}}</el-descriptions-item>
                        <el-descriptions-item label="Payment">￥100.0</el-descriptions-item>
                      </el-descriptions>
                      <div class="row" style="margin: 20px" >
                        <div class="col-3 text-center" >
                          <el-button type="primary" icon="el-icon-edit" @click="Jump()"></el-button></div>
                        <div class="col-6 text-center" >
                          <el-button type="success" plain @click="dialogVisible=true">Go To Pay</el-button></div>
                        <div class="col-3 text-center" >
                          <el-button type="success" icon="el-icon-delete" circle v-print="printObj">Print the receipt</el-button></div>
                      </div>

                    </div>
                  </div>


                </md-tab>

              </md-tabs>
            </template>
          </nav-tabs-card>
        </div>
      </div>
    </div>
    <el-dialog
        title="Prompt"
        :visible.sync="dialogVisible"
        width="30%"
        :before-close="handleClose"
        :append-to-body="true">
      <a @click="creatQrCode" style="position: center; margin-left: 90px">Click here to generate a pay code</a>
      <div class="qrcode" ref="qrCodeUrl" style="margin-left: 150px; margin-top: 30px"></div>
      <span slot="footer" class="dialog-footer">
    <el-button @click="dialogVisible = false">Print receipt</el-button>
    <el-button type="primary" @click="Jumphome()">Finish payment</el-button>
    <el-button type="primary" @click="Jumphome()">Pay By Cash</el-button>
  </span>
    </el-dialog>
  </div>
</template>

<script>
import { NavTabsCard } from "@/components";
import QRCode from "qrcodejs2";
// 组件内引用vue-video-player插件
let handleWindowPrint = (ele, fileName)=>{

  var oIframe = document.createElement('iframe');
  var oScript = document.createElement('script');

  document.body.appendChild(oIframe);

  var titleText = document.head.getElementsByTagName('title')[0].innerText;
  document.head.getElementsByTagName('title')[0].innerText = `${fileName}`;

  oIframe.contentDocument.head.innerHTML = `<meta charset="utf-8">
                                              <title>${fileName}</title>
                                              <meta name="format-detection" content="telephone=no">
                                              <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
                                              <meta name="viewport" content="width=device-width,initial-scale=1.0">`;

  // oIframe.contentWindow.document.body.innerHTML = document.querySelector(ele).outerHTML;
  oIframe.contentDocument.body.innerHTML = document.querySelector(ele).outerHTML;
  oScript.innerHTML = 'window.print();';
  oIframe.contentDocument.body.appendChild(oScript);
  document.body.removeChild(oIframe);
  document.head.getElementsByTagName('title')[0].innerText = titleText;

};

export default {
  handleWindowPrint,
  data(){
    return{
      username:JSON.parse(window.localStorage.getItem('user')).username,
      phoneNumber:JSON.parse(window.localStorage.getItem('user')).phoneNumber,
      facilityType:this.$route.query.facilityType,
      activityType:this.$route.query.activityType,
      printObj: {
        id: "printMe",
        popTitle: 'good print',
        extraCss: 'https://www.google.com,https://www.google.com',
        extraHead: '<meta http-equiv="Content-Language"content="zh-cn"/>'
      },
      dialogVisible:false
    };
  },
  methods: {
    creatQrCode() {
      new QRCode(this.$refs.qrCodeUrl, {
        text: 'https://blog.csdn.net/weixin_42601136', // 需要转换为二维码的内容
        width: 100,
        height: 100,
        colorDark: '#000000',
        colorLight: '#ffffff',
        correctLevel: QRCode.CorrectLevel.H
      })
    },
    Jumphome(){
      this.$router.push("/home");
    },
    Jump(){
      this.$router.push("/detail");
    }
  },

  components: {
    NavTabsCard
  }
};
</script>
<style>

</style>
<style lang="css"></style>