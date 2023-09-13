<template>
  <div class="wrapper">
    <div id="navigation">
      <div class="navigation-example" :style="bgImage">
        <div class="md-layout">
          <div class="md-layout-item">


            <md-toolbar class="md-transparent">
              <div class="md-toolbar-row">
                <div class="md-toolbar-section-start">
                  <h3 class="md-title">Best Gym</h3>
                </div>
                <div class="md-toolbar-section-end">
                  <md-button
                      class="md-just-icon md-simple md-white md-toolbar-toggle"
                  >
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                  </md-button>

                  <div class="md-collapse">
                    <md-list>
                      <md-list-item href="javascript:void(0)">
                        <p>Discover</p>
                      </md-list-item>

                      <md-list-item href="javascript:void(0)">
                        <p>Whishlist</p>
                      </md-list-item>
                      <md-list-item href="javascript:void(0)">
                        <div class="md-collapse">
                          <div class="md-autocomplete">
                            <md-autocomplete
                                class="search has-white"
                                v-model="selectedEmployee"
                                :md-options="employees"
                                :md-open-on-focus="false"
                            >
                              <label>Search...</label>
                            </md-autocomplete>
                          </div>
                        </div>
                      </md-list-item>
                      <li class="md-list-item">
                        <a
                            href="javascript:void(0)"
                            class="md-list-item-router md-list-item-container md-button-clean"
                        >
                          <div class="md-list-item-content">
                            <md-button class="md-rose md-just-icon md-round"
                            ><md-icon>email</md-icon></md-button
                            >
                          </div>
                        </a>
                      </li>

                      <li class="md-list-item">
                        <a
                            href="javascript:void(0)"
                            class="md-list-item-router md-list-item-container md-button-clean dropdown"
                        >
                          <div class="md-list-item-content">
                            <drop-down direction="down" class="profile-photo">
                              <div
                                  class="profile-photo-small"
                                  slot="title"
                                  data-toggle="dropdown"
                              >
                                <img :src="img" alt="Circle Image" />
                              </div>
                              <ul class="dropdown-menu dropdown-menu-right">
                                <li class="dropdown-header">Dropdown header</li>
                                <li>
                                  <a href="#pablo" class="dropdown-item"  @click="goCenter">Me</a>
                                </li>
                                <li>
                                  <a href="#pablo" class="dropdown-item"
                                  >Settings and other stuff</a
                                  >
                                </li>
                                <li>
                                  <a href="#pablo" class="dropdown-item"
                                  >Sign Out</a
                                  >
                                </li>
                              </ul>
                            </drop-down>
                          </div>
                        </a>
                      </li>
                    </md-list>
                  </div>
                </div>
              </div>
            </md-toolbar>


          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  methods: {
    // 跳转页面方法
    goCenter() {
      this.$router.push("/user");
    },
  },
  data() {
    return {
      selectedEmployee: "",
      employees: [
        "Jim Halpert",
        "Dwight Schrute",
        "Michael Scott",
        "Pam Beesly",
        "Angela Martin",
        "Kelly Kapoor",
        "Ryan Howard",
        "Kevin Malone"
      ]
    };
  },
  props: {

    img: {
      type: String,
      default: require("@/assets/img/faces/avatar.jpg")
    }
  },
  computed: {
    bgImage() {
      return {
        backgroundImage: `url(${this.image})`
      };
    }
  }
};
</script>

<style lang="css"></style>