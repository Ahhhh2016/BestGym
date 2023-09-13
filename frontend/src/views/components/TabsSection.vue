<template>
  <div class="wrapper">
    <div id="nav-tabs">
      <div class="md-layout text-left">
        <div
            class="md-layout-item md-size-30 md-xsmall-size-100 ml-auto"
            :class="{ 'text-center': responsive }"
        >
          <div id="app">
            <figure class="figure  rounded">
              <a @click="Jump1()">
                <img
                    :src="imagefit"
                    alt="Raised Image"
                    class="img-raised rounded"
                    :class="{ 'responsive-image': responsive }"

                />
              </a>
              <h2>Learn More</h2></figure>
          </div>
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
      <div class="md-layout">

        <div class="md-layout-item md-size-70 md-small-size-100">
          <h3><small>Swimming Pool</small></h3>
          <nav-tabs-card no-label>
            <template slot="content">
              <md-tabs class="md-primary" md-alignment="left">
                <md-tab id="tab-home" md-label="Introduction" md-icon="face">
                  <p>
                    Our swimming pool is an outdoor swimming pool of size 18by36feet, which consists of ten separate lanes. We sanitize the swimming pool daily to ensure it is clean and usable.
                    Our swimming pool is divided into shallow water area and deep water area. The water depth of the shallow water area is about 1.4 meters, which is suitable for children and people with poor swimming skills. The deep water area is about 1.8 meters deep, suitable for adults and people with superb swimming skills.
                  </p>
                </md-tab>

                <md-tab id="tab-pages" md-label="Detail" md-icon="chat">
                  <p>
                    I think that’s a responsibility that I have, to push
                    possibilities, to show people, this is the level that things
                    could be at. I will be the leader of a company that ends up
                    being worth billions of dollars, because I got the answers.
                    I understand culture. I am the nucleus. I think that’s a
                    responsibility that I have, to push possibilities, to show
                    people, this is the level that things could be at.
                  </p>
                </md-tab>

                <md-tab id="tab-posts" md-label="Activity" md-icon="build">
                  <p>
                    I think that’s a responsibility that I have, to push
                    possibilities, to show people, this is the level that things
                    could be at. So when you get something that has the name
                    Kanye West on it, it’s supposed to be pushing the furthest
                    possibilities. I will be the leader of a company that ends
                    up being worth billions of dollars, because I got the
                    answers. I understand culture. I am the nucleus.
                  </p>
                </md-tab>
              </md-tabs>
            </template>
          </nav-tabs-card>
        </div>
        <div
            class="md-layout-item md-size-30 md-xsmall-size-100 ml-auto"
            :class="{ 'text-center': responsive }"
        >
          <div id="app1">
            <figure class="figure  rounded">
              <a @click="Jump2()">
                <img
                    :src="imagesw"
                    alt="Raised Image"
                    class="img-raised rounded"
                    :class="{ 'responsive-image': responsive }"

                />
                <h2>Learn More</h2>
              </a></figure>
          </div>

        </div>
      </div>
      <div class="md-layout">
        <div
            class="md-layout-item md-size-30 md-xsmall-size-100 ml-auto"
            :class="{ 'text-center': responsive }"
        >
          <div id="app2">
            <figure class="figure  rounded">
              <a @click="Jump3()">
                <img
                    :src="imagesp"
                    alt="Raised Image"
                    class="img-raised rounded"
                    :class="{ 'responsive-image': responsive }"

                />
                <h2>Learn More</h2>
              </a></figure>
          </div>
        </div>

        <div class="md-layout-item md-size-70 md-small-size-100">
          <h3><small>Sports Hall</small></h3>
          <nav-tabs-card no-label>
            <template slot="content">
              <md-tabs class="md-primary" md-alignment="left">
                <md-tab id="tab-home" md-label="Introduction" md-icon="face">
                  <p>
                    Our sports hall provides the opportunity for the development of skills for indoor court games, individual activities and the practice of some skills and techniques for outdoor activity.
                    Our sports hall provides members with indoor basketball courts, indoor badminton courts and other indoor sports venues. We also provide a series of sports equipment and auxiliary equipment, such as ball machines, etc.
                  </p>
                </md-tab>

                <md-tab id="tab-pages" md-label="Detail" md-icon="chat">
                  <p>
                    I think that’s a responsibility that I have, to push
                    possibilities, to show people, this is the level that things
                    could be at. I will be the leader of a company that ends up
                    being worth billions of dollars, because I got the answers.
                    I understand culture. I am the nucleus. I think that’s a
                    responsibility that I have, to push possibilities, to show
                    people, this is the level that things could be at.
                  </p>
                </md-tab>

                <md-tab id="tab-posts" md-label="Activity" md-icon="build">
                  <p>
                    The activities offered by our sports hall include team basketball games, team tennis games, and we also provide some personal training courses, such as tennis skills training, badminton skills training. We also provide table tennis venues for related activities, and indoor fencing venues are available.
                  </p>
                </md-tab>
              </md-tabs>
            </template>
          </nav-tabs-card>
        </div>
      </div>
      <div class="md-layout">

        <div class="md-layout-item md-size-70 md-small-size-100">
          <h3><small>Squash Courts </small></h3>
          <nav-tabs-card no-label>
            <template slot="content">
              <md-tabs class="md-primary" md-alignment="left">
                <md-tab id="tab-home" md-label="Introduction" md-icon="face">
                  <p>
                    Our squash court is a playing surface surrounded by four walls. The court surface contains a front line separating the front and back of the court and a half court line, separating the left and right sides of the back portion of the court, creating three 'boxes': the front half, the back left quarter and the back right quarter. Both the back two boxes contain smaller service boxes.

                    The court's four walls are divided into a front wall, two side walls, and a back wall. An 'out line' runs along the top of the front wall, descending along the side walls to the back wall. The bottom line of the front wall marks the top of the 'tin', a half meter-high metal area.
                  </p>
                </md-tab>

                <md-tab id="tab-pages" md-label="Detail" md-icon="build">
                  <p>
                    I think that’s a responsibility that I have, to push
                    possibilities, to show people, this is the level that things
                    could be at. I will be the leader of a company that ends up
                    being worth billions of dollars, because I got the answers.
                    I understand culture. I am the nucleus. I think that’s a
                    responsibility that I have, to push possibilities, to show
                    people, this is the level that things could be at.
                  </p>
                </md-tab>

                <md-tab id="tab-posts" md-label="Activity" md-icon="chat">
                  <p>
                    Activities on our squash courts include one-on-one squash lessons with personal trainers, group appointments for team building activities, squash matches between different organisations and so on.
                  </p>
                </md-tab>
              </md-tabs>
            </template>
          </nav-tabs-card>
        </div>
        <div
            class="md-layout-item md-size-30 md-xsmall-size-100 ml-auto"
            :class="{ 'text-center': responsive }"
        >

          <div id="app3">
            <figure class="figure  rounded">
              <a @click="Jump4()">
                <img
                    :src="imagesq"
                    alt="Raised Image"
                    class="img-raised rounded"
                    :class="{ 'responsive-image': responsive }"

                />
                <h2>Learn More</h2>
              </a></figure>
          </div>
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
      fac:[],
      imagefit: require("@/assets/img/fitness_room.jpg"),
      imagesw: require("@/assets/img/swimming.png"),
      imagesp: require("@/assets/img/sportshall.jpg"),
      imagesq: require("@/assets/img/ball.jpg"),
      responsive: false
    };
  },
  methods: {
    Jump1() {
      this.$router.push({name:'detail',query: {facilityId:'1'}});
    },
    Jump2() {
      this.$router.push({name:'detail',query: {facilityId:'2'}});
    },
    Jump3() {
      this.$router.push({name:'detail',query: {facilityId:'3'}});
    },
    Jump4() {
      this.$router.push({name:'detail',query: {facilityId:'4'}});
    },
    onResponsiveInverted() {
      if (window.innerWidth < 600) {
        this.responsive = true;
      } else {
        this.responsive = false;
      }
    },
    initFacility(){
      this.getRequest('/').then(resp=>{
        if(resp){
          this.fac = resp.data()
        }
      })
    }
  },
  mounted() {
    this.initFacility();
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
