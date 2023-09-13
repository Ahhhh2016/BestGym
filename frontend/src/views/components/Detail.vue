<template>
  <div class="wrapper">
    <div id="nav-tabs">
      <div class="md-layout text-left">
        <div
            class="md-layout-item md-size-30 md-xsmall-size-100 ml-auto"
            :class="{ 'text-center': responsive }"
        >

        </div>
        <div class="md-layout-item md-size-70 md-small-size-100 ">
          <h4 style="margin-left: 330px; margin-top: -25px; margin-bottom: 90px">Fitness Room</h4>
          <nav-tabs-card no-label>
            <template slot="content">
              <md-tabs class="md-primary" md-alignment="left">
                <md-tab id="tab-home" md-label="Introduction" md-icon="face" >
                  <p>
                    Our fitness room contains the following facilities: Whole body fitness equipment: such as 10 items of comprehensive training equipment and and function fitness equipment.
                    Local fitness equipment: such as fitness bicycle, rowing device,heavy hammer puller, heel lifting exercise device.
                    Small fitness equipment: such as dumbbells, spring pullers, fitness plates, elastic bars, grip strength devices
                  </p>



                </md-tab>

                <md-tab id="tab-pages" md-label="Detail" md-icon="build">

                <h3>Capacity:</h3>
                 <h3>Facility:</h3>
                </md-tab>

                <md-tab id="tab-posts" md-label="Activity" md-icon="chat">
                  <p style="font-size: 16px">
                    Activities include organized, group instructional programs such as spinning classes, yoga, and martial arts; organized and impromptu team sports; and individual fitness opportunities such as cardiovascular training and weight training. Individual activities may be self-guided or conducted under the supervision of a trainer.
                  </p>
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

export default {
  components: {
    NavTabsCard
  },
  data() {
    return {
      imagefit: require("@/assets/img/fitness_room.jpg"),
      responsive: false
    };
  },
  methods: {
    onResponsiveInverted() {
      if (window.innerWidth < 600) {
        this.responsive = true;
      } else {
        this.responsive = false;
      }
    }
  },
  mounted() {
    this.onResponsiveInverted();
    window.addEventListener("resize", this.onResponsiveInverted);
  },
  beforeDestroy() {
    window.removeEventListener("resize", this.onResponsiveInverted);
  }

};
</script>

<style lang="css">
#app,#app1,#app2,#app3{height:290px;}
.figure{
  height:300px;
  width:300px;
  max-height:300px;
  max-width:300px;
  overflow:hidden;
  position:relative;
  background:#000000;
}
.figure img{
  height:100%;
  width:100%;
  transition:all 0.6s ease;

  opacity:1;
  border-radius: 15px;
}
.figure h2{
  position:absolute;
  color:#fff;
  transform:translate(-360px,0);
  top:35%;
  left:25%;
  transition:all 0.6s ease;
}
.figure:hover img{
  transform:scale(1.2,1.2);
  transition:all 0.6s ease;
  opacity:0.6;
  border-radius: 15px;
}
.figure:hover h2{
  transform:translate(0,0);
  transition:all 0.6s ease;
}
</style>