<template>
  <div class="wrapper">

    <div id="images">
<!--      <div class="title">-->
<!--        <h2>Images</h2>-->
<!--      </div>-->
      <div class="md-layout">
        <div
            class="md-layout-item md-size-20 md-xsmall-size-100 ml-auto"
            :class="{ 'text-center': responsive }"
        >
          <img
              :src="image1"
              alt="Thumbnail Image"
              class="img-raised rounded-circle"
              :class="{ 'responsive-image': responsive }"
          />
          <h4>Fitness Room</h4>
        </div>
        <div
            class="md-layout-item md-size-20 md-xsmall-size-100 ml-auto"
            :class="{ 'text-center': responsive }"
        >
          <img
              :src="image2"
              alt="Thumbnail Image"
              class="img-raised rounded-circle"
              :class="{ 'responsive-image': responsive }"
          />
          <h4>Swimming pool</h4>
        </div>
        <div
            class="md-layout-item md-size-20 md-xsmall-size-100 ml-auto"
            :class="{ 'text-center': responsive }"
        >
          <img
              :src="image3"
              alt="Thumbnail Image"
              class="img-raised rounded-circle"
              :class="{ 'responsive-image': responsive }"
          />
          <h4>Sports Hall</h4>
        </div>

        <div
          class="md-layout-item md-size-20 md-xsmall-size-100 ml-auto"
          :class="{ 'text-center': responsive }"
        >
          <img
            :src="image4"
            alt="Thumbnail Image"
            class="img-raised rounded-circle"
            :class="{ 'responsive-image': responsive }"
          />
          <h4>Squash courts</h4>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      image1: require("@/assets/img/fitness_room.jpg"),
      image2: require("@/assets/img/swimming.png"),
      image3: require("@/assets/img/sportshall.jpg"),
      image4: require("@/assets/img/ball.jpg"),
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

<style lang="css"></style>
