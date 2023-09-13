<template>
  <div class="wrapper">
    <div id="nav-tabs">
      <h3>Navigation Tabs</h3>
      <div class="md-layout">
        <div class="md-layout-item md-size-150 md-small-size-100">
          <h3><small>Tabs with Icons on Card</small></h3>
          <nav-tabs-card no-label>
            <template slot="content">
              <md-tabs class="md-primary" md-alignment="left">
                <md-tab id="tab-home" md-label="General use" md-icon="face">
                 <div class="md-layout">
            <div class="md-layout-item md-size-80">
              <div class="title">
                <h3>DatePicker</h3>
              </div>
<!--               <div class="block">-->
<!--    <span class="demonstration">默认</span>-->
<!--    <el-date-picker-->
<!--      v-model="transdate"-->
<!--      type="date"-->
<!--      value-format="yyyy-MM-dd"-->
<!--      :picker-options="pickerOptions1"-->
<!--      @change="searchInfo"-->
<!--      placeholder="选择日期">-->
<!--    </el-date-picker>-->
<!--  </div>-->
              <div class="block" style="margin-bottom: 50px">
    <span class="demonstration" style="margin-right: 20px">Please select data:</span>
    <el-date-picker
      v-model="daterange"
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
                  <el-select v-model="value" placeholder="Please choose the period" style="margin-bottom: 50px" >
    <el-option
      v-for="item in options"
      :key="item.value"
      :label="item.label"
      :value="item.value">
    </el-option>
  </el-select>
              </div>
          </div>
                </md-tab>

                <md-tab id="tab-pages" md-label="Activity" md-icon="chat">
                  <div class="md-layout">
            <div class="md-layout-item md-size-80">
              <div class="title">
                <h3>DatePicker</h3>
              </div>

              <div class="block">
    <span class="demonstration">默认</span>
    <el-date-picker
      v-model="value1"
      type="date"
      placeholder="选择日期">
    </el-date-picker>
  </div>

            </div>
          </div>
                  <div class="md-layout-item md-size-140">
                  <p style="margin-left: -500px">Please choose the time:</p>
                  </div>
                  <el-select v-model="value" placeholder="Please choose the period" style="margin-left: -440px" >
    <el-option
      v-for="item in options"
      :key="item.value"
      :label="item.label"
      :value="item.value">
    </el-option>
  </el-select>
                </md-tab>

                <md-tab id="tab-posts" md-label="Lessons" md-icon="build">
                  <div class="container">
    <div class="content_video">
      <div class="content_video">
        <!-- 使用组件video-player -->
        <video-player
          class="video-player vjs-custom-skin"
          ref="videoPlayer"
          :playsinline="true"
          :options="playerOptions"
        ></video-player>
      </div>
    </div>
  </div>
                  <div class="md-layout-item md-size-140">
                  <p style="margin-left: -500px">Please choose the time:</p>
                  </div>
                  <el-select v-model="value" placeholder="Please choose the period" style="margin-left: -440px" >
    <el-option
      v-for="item in options"
      :key="item.value"
      :label="item.label"
      :value="item.value">
    </el-option>
  </el-select>

                </md-tab>
              </md-tabs>
            </template>
          </nav-tabs-card>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { NavTabsCard } from "@/components";
// 组件内引用vue-video-player插件
import { videoPlayer } from 'vue-video-player'
import 'video.js/dist/video-js.css'


export default {
  data() {
      return {
        daterange:"",
        startTime:"",
        options: [{
          value: '选项1',
          label: '10:00-12:00'
        }, {
          value: '选项2',
          label: '12:00-14:00'
        }, {
          value: '选项3',
          label: '14:00-16:00'
        }, {
          value: '选项4',
          label: '16:00-18:00'
        }, {
          value: '选项5',
          label: '18:00-20:00'
        },{
          value: '选项5',
          label: '20:00-22:00'
        }],
        value: '',
        playerOptions: {
        playbackRates: [0.7, 1.0, 1.5, 2.0], // 播放速度
        autoplay: false, // 如果true,浏览器准备好时开始回放。
        muted: false, // 默认情况下将会消除任何音频。
        loop: false, // 导致视频一结束就重新开始。
        preload: 'auto', // 建议浏览器在<video>加载元素后是否应该开始下载视频数据。auto浏览器选择最佳行为,立即开始加载视频（如果浏览器支持）
        language: 'zh-CN',
        aspectRatio: '4:3', // 将播放器置于流畅模式，并在计算播放器的动态大小时使用该值。值应该代表一个比例 - 用冒号分隔的两个数字（例如"16:9"或"4:3"）
        fluid: true, // 当true时，Video.js player将拥有流体大小。换句话说，它将按比例缩放以适应其容器。
          sources: [
          {
            src: '../src/assets/52e837b1735ed9398d521fafd4c6aed2.mp4', // 路径
            type: 'video/mp4' // 类型
          },],
        poster: '../src/assets/logo.png', // 你的封面地址
        // width: document.documentElement.clientWidth,
        notSupportedMessage: '此视频暂无法播放，请稍后再试', // 允许覆盖Video.js无法播放媒体源时显示的默认信息。
        controlBar: {
          timeDivider: true,
          durationDisplay: true,
          remainingTimeDisplay: false,
          fullscreenToggle: true // 全屏按钮
        }
      }
      }
    },
  methods:{
    searchInfo(val) {
      this.startTime = val;
      console.log(this.startTime);
      }
  },
  components: {
    NavTabsCard,
    videoPlayer
  }
};
</script>
<style>
.content_video .video-js .vjs-big-play-button {
    font-size: 1.5em ;
    width: 2.5em;
    border-radius: 50%;
    top: 40%;
    left: 45%;
  }
  .content_video {
    text-align: left;
    padding: 0 7%;
  }

</style>
<style lang="css"></style>