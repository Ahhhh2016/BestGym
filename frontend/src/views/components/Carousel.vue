<template>
  <section class="section section-lg section-shaped overflow-hidden my-0">
    <div class="shape shape-style-1 shape-default shape-skew">
      <span></span>
      <span></span>
      <span></span>
      <span></span>
      <span></span>
      <span></span>
    </div>
    <div class="container py-0 pb-lg">
      <div class="row justify-content-between align-items-center">
        <div class="col-lg-5 mb-5 mb-lg-0">
          <h1 class="text-white font-weight-light">Fitness Room</h1>
          <p class="lead text-white mt-4" style="font-size: 18px">Our fitness room contains the following facilities: Whole body fitness equipment: such as 10 items of comprehensive training equipment and and function fitness equipment. Local fitness equipment: such as fitness bicycle, rowing device,heavy hammer puller, heel lifting exercise device. Small fitness equipment: such as dumbbells, spring pullers, fitness plates, elastic bars, grip strength devices</p>

        </div>
        <div class="col-lg-6 mb-lg-auto">
          <div class="rounded shadow-lg overflow-hidden transform-perspective-right">
            <b-carousel id="carousel1"
                        controls
                        indicators>
              <!-- Text slides with image -->
              <b-carousel-slide img-src="img/img5.png"></b-carousel-slide>
              <b-carousel-slide img-src="img/img4.png"></b-carousel-slide>
              <b-carousel-slide img-src="img/img6.png"></b-carousel-slide>
            </b-carousel>
          </div>
        </div>
      </div>
    </div>
  </section>
</template>
<script>
import { BCarousel } from "bootstrap-vue/esm/components/carousel/carousel";
import { BCarouselSlide } from "bootstrap-vue/esm/components/carousel/carousel-slide";

export default {
  components: {
    BCarousel,
    BCarouselSlide
  }
};
</script>
<style>
</style>
