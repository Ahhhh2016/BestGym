<template>
  <div class="container-fluid">
    <sidenav1></sidenav1>
    <div class="row ">
      <div class="col-lg-12 position-relative z-index-2">
        <div class="row">
          <div class="col-lg-3 col-md-6 col-sm-6 ">
            <mini-cards
              title="Today's Money"
              value="$53k"
              percentage="+55%"
              iconName="weekend"
              detail="than last week"
              iconClass="text-white"
              iconBackground="bg-gradient-dark"
            />
          </div>
          <div class="col-lg-3 col-md-6 col-sm-6 mt-lg-0 mt-4">
            <mini-cards
              title="Today's Users"
              value="2,300"
              percentage="+3%"
              iconName="person"
              detail="than last month"
              iconClass="text-white"
              iconBackground="bg-gradient-primary"
            />
          </div>
          <div class="col-lg-3 col-md-6 col-sm-6 mt-lg-0 mt-4">
            <mini-cards
              title="New Clients"
              value="3,462"
              percentage="-2%"
              valueColor="text-danger"
              iconName="person"
              detail="than yesterday"
              iconClass="text-white"
              iconBackground="bg-gradient-success"
            />
          </div>
          <div class="col-lg-3 col-md-6 col-sm-6 mt-lg-0 mt-4">
            <mini-cards
              title="Sales"
              value="$103,430"
              percentage="+5%"
              iconName="weekend"
              detail="Just updated"
              iconClass="text-white"
              iconBackground="bg-gradient-info"
            />
          </div>
        </div>
        <div class="row mt-4">
          <div class="col-lg-4 col-md-6 mt-4">
            <chart-bars />
          </div>
          <div class="col-lg-4 col-md-6 mt-4">
            <chart-line />
          </div>
          <div class="col-lg-4 mt-4">
            <chart-line-tasks />
          </div>
        </div>
      </div>
    </div>


  </div>
</template>
<script>
import ChartBars from "./components/ChartBars.vue";
import ChartLine from "./components/ChartLine.vue";
import ChartLineTasks from "./components/ChartLineTasks.vue";
import MiniCards from "./components/MiniCards.vue";
import sidenav1 from '../examples/Sidenav1/index'

export default {
   beforeMount() {
    this.$store.state.hideConfigButton = false;
    this.$store.state.showNavbar1 = true;
    // this.$store.state.showSidenav1 = true;
    this.$store.state.showSidenav1 = true;
    this.$store.state.showFooter = false;
  },
  beforeUnmount() {
    this.$store.state.hideConfigButton = false;
    this.$store.state.showNavbar1 = true;
    this.$store.state.showSidenav = true;
    this.$store.state.showFooter = true;
  },
  name: "dashboard-default",
  components: {
    ChartBars,
    ChartLine,
    ChartLineTasks,
    MiniCards,
    sidenav1
  },
};
</script>
