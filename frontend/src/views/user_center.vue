<template>
  <div class="wrapper">
    <parallax class="section page-header header-filter" :style="headerStyle">
      <div class="section section-navbars">
        <navigation1 style="padding-top: 100px; width: 1500px; margin-top: 260px"></navigation1>
      </div>
    </parallax>
    <div class="main main-raised" style="background: rgba(34,34,34,0.8)">
      <div class="section profile-content">
        <div class="container">
          <div class="md-layout">
            <div class="md-layout-item md-size-50 mx-auto">
              <div class="profile">
                <div class="avatar">
                  <img
                    :src="img"
                    alt="Circle Image"
                    class="img-raised rounded-circle img-fluid"
                    style="margin-top: 48px"
                  />
                </div>
                <div class="name">
                  <h3 class="title" style="margin-top: 50px">{{user.username}}</h3>
                  <h6>Designer</h6>
                  <md-button href="javascript:void(0)" class="md-just-icon md-simple md-dribbble">
                    <i class="fab fa-dribbble"></i>
                  </md-button>
                  <md-button href="javascript:void(0)" class="md-just-icon md-simple md-twitter">
                    <i class="fab fa-twitter"></i>
                  </md-button>
                  <md-button href="javascript:void(0)" class="md-just-icon md-simple md-pinterest">
                    <i class="fab fa-pinterest"></i>
                  </md-button>
                </div>
              </div>
            </div>
          </div>
          <div class="description text-center">
            <p>
              An artist of considerable range, Chet Faker — the name taken by
              Melbourne-raised, Brooklyn-based Nick Murphy — writes, performs
              and records all of his own music, giving it a warm, intimate feel
              with a solid groove structure.
            </p>
          </div>
          <div class="md-layout md-large-size-90">
            <div class="md-layout-item">
              <div class="profile-tabs">
                <tabs
                  :tab-name="['Account', 'Order', 'Membership']"
                  :tab-icon="['camera', 'palette', 'favorite']"
                  plain
                  nav-pills-icons
                  color-button="primary"
                >
                  <!-- here you can add your content for tab-content -->
                  <template slot="tab-pane-1">
                    <div
                      class="md-layout-item md-size-110 md-small-size-50"
                      style="background: white"
                    >
                      <tabs
                        :tab-name="['Informstion', 'My Card','Modify']"
                        :tab-icon="['dashboard', 'schedule','schedule']"
                        plain
                        flex-column
                        nav-pills-icons
                        color-button="primary"
                      >
                        <!-- here you can add your content for tab-content -->
                        <template slot="tab-pane-1">
                          <el-descriptions
                            title="User information"
                            direction="vertical"
                            :column="5"
                            border
                            style="margin-left: 90px"
                          >
                            <el-descriptions-item label="User name">{{user.username}}</el-descriptions-item>
                            <el-descriptions-item label="Email">{{user.email}}</el-descriptions-item>
                            <el-descriptions-item
                              label="Phone number"
                              :span="2"
                            >{{user.phoneNumber}}</el-descriptions-item>
                            <el-descriptions-item label="Remaining days of membership">
                              <el-tag size="small">{{user.leftDate}}</el-tag>
                            </el-descriptions-item>
                            <el-descriptions-item label="Membership information">Annul membership</el-descriptions-item>
                          </el-descriptions>
                        </template>
                        <template slot="tab-pane-2">
                          <div>
                            <div style="border-radius: 30px">
                              <div class="card" style="width: 800px">
                                <div class="card-header pb-0 px-3">
                                  <h6 class="mb-0">Added cards</h6>
                                </div>
                                <div class="card-body pt-4 p-3">
                                  <ul class="list-group">
                                    <li
                                      class="list-group-item border-0 d-flex p-4 mb-2 bg-gray-100 border-radius-lg"
                                      v-for="item in cards"
                                      v-bind:key="item"
                                    >
                                      <div class="d-flex flex-column">
                                        <!--            <h6 class="mb-3 text-sm">Oliver Lia</h6>-->
                                        <span class="mb-2 text-xs">
                                          Bank name:
                                          <span
                                            class="text-dark font-weight-bold ms-sm-2"
                                          >{{item.bankname}}</span>
                                        </span>
                                        <span class="mb-2 text-xs">
                                          Card number:
                                          <span
                                            class="text-dark ms-sm-2 font-weight-bold"
                                            property="facilityType"
                                          >{{item.number}}</span>
                                        </span>
                                      </div>
                                      <div class="ms-auto text-end">
                                        <el-button @click="DeleteCard(item)" type="danger">
                                          <i
                                            class="far fa-trash-alt me-2"
                                            aria-hidden="true"
                                            style="font-weight: bold"
                                          ></i>Delete
                                        </el-button>
                                      </div>
                                    </li>
                                  </ul>
                                </div>
                              </div>
                              <div style="margin-bottom: -50px">
                                <el-button
                                  icon="el-icon-plus"
                                  type="success"
                                  style="margin-left: 300px;margin-top: 30px;margin-bottom: -50px"
                                  @click="dialogVisible=true"
                                >
                                  <i aria-hidden="true"></i>Add new card
                                </el-button>
                                <el-dialog
                                  title="Add card"
                                  :visible.sync="dialogVisible"
                                  width="30%"
                                  :append-to-body="true"
                                >
                                  <el-form
                                    v-model="AddForm"
                                    :model="AddForm"
                                    :rules="rules"
                                    ref="AddForm"
                                    label-width="120px"
                                    label-position="left"
                                    style="width: 350px"
                                  >
                                    <el-form-item label="Bank Name" prop="bankname" style>
                                      <el-input
                                        v-model="AddForm.bankname"
                                        placeholder="Please enter bank name"
                                        style
                                      ></el-input>
                                    </el-form-item>
                                    <el-form-item label="Card Number" prop="number">
                                      <el-input
                                        v-model="AddForm.number"
                                        placeholder="Please enter card number"
                                      ></el-input>
                                    </el-form-item>
                                  </el-form>
                                  <span slot="footer" class="dialog-footer">
                                    <el-button @click="dialogVisible = false">取 消</el-button>
                                    <el-button type="primary" @click="doAdd()">确 定</el-button>
                                  </span>
                                </el-dialog>
                              </div>
                            </div>

                            <!--                      <login-card  class="md-layout md-large-size-400" style="width: 700px;margin-left: 100px;margin-top: 60px">-->
                            <!--                        <h4 slot="title" class="card-title">Bank card</h4>-->
                            <!--                        <md-field class="md-form-group" slot="description">-->
                            <!--                          <p>-->
                            <!--                          Bank name:<br/>-->
                            <!--                            Card number:<br>-->
                            <!--                          </p>-->
                            <!--                        </md-field>-->
                            <!--                      </login-card>-->
                            <br />
                            <br />
                          </div>
                        </template>
                        <template slot="tab-pane-3">
                          <div
                            class="md-layout-item md-size-150 md-big-size-100 md-small-size-50 md-medium-size-25"
                          >
                            <el-form
                              :model="dynamicValidateForm"
                              :rules="rules"
                              ref="dynamicValidateForm"
                              label-width="140px"
                              class="login-container"
                              label-position="left"
                              style="margin-left: 100px"
                            >
                              <p
                                style="font-weight: bold; float: left; margin-bottom: 40px"
                              >Modify your information if you need:</p>
                              <el-form-item prop="email" label="email">
                                <el-input v-model="dynamicValidateForm.email"></el-input>
                              </el-form-item>

                              <el-form-item prop="phoneNumber" label="Phone Number">
                                <el-input v-model="dynamicValidateForm.phoneNumber"></el-input>
                              </el-form-item>

                              <el-form-item
                                label="Password"
                                prop="password"
                                :rules="[
      { required: true, message: '请输入密码', trigger: 'blur' },
    ]"
                              >
                                <el-input
                                  type="password"
                                  v-model="dynamicValidateForm.password"
                                  autocomplete="off"
                                ></el-input>
                              </el-form-item>
                              <el-form-item
                                class="confirm"
                                label="Confirm password"
                                id="selectForm"
                                prop="checkPass"
                                style="font-size: 5px"
                              >
                                <el-input
                                  type="password"
                                  v-model="dynamicValidateForm.checkPass"
                                  autocomplete="off"
                                ></el-input>
                              </el-form-item>
                              <el-form-item>
                                <el-button
                                  class="button1"
                                  type="primary"
                                  @click="submitForm('dynamicValidateForm')"
                                  style="margin-bottom: 200px"
                                >Modify</el-button>
                              </el-form-item>
                            </el-form>
                          </div>
                        </template>
                      </tabs>
                    </div>
                  </template>
                  <template slot="tab-pane-2">
                    <el-tabs type="border-card" style="width: 1150px;padding: 0px">
                      <el-tab-pane label="All orders">
                        <div class="row" style>
                          <div class="container" style="width: 1500px">
                            <div>
                              <el-card
                                :body-style="{ padding: 'px', background:'#51a655' ,shadow:'always', }"
                                class="card2"
                              >
                                <div style="padding-top: 10px">
                                  <span class="span2">Order Management</span>
                                </div>
                              </el-card>
                              <div style="padding-top: 100px;">
                                <el-table :data="orders" stripe style="width: 100%; color: #00acc1">
                                  <el-table-column
                                    prop="paymentdate"
                                    label="Generate Date"
                                    width="150"
                                  ></el-table-column>
                                  <el-table-column prop="username" label="User name" width="140"></el-table-column>
                                  <el-table-column prop="facilitytype" label="Facility" width="140"></el-table-column>
                                  <el-table-column prop="activitytype" label="Activity" width="140"></el-table-column>
                                  <el-table-column prop="time" label="Date" width="140"></el-table-column>
                                  <el-table-column prop="address" label="Time period" width="140"></el-table-column>
                                  <el-table-column prop="usagestatus" label="Status" width="100"></el-table-column>
                                  <el-table-column label="operations" width="160">
                                    <template slot-scope="scope">
                                      <el-button
                                        @click="Delete(scope.$index, scope.row)"
                                        type="danger"
                                        size="small"
                                      >Delete</el-button>
                                    </template>
                                  </el-table-column>
                                </el-table>
                              </div>
                            </div>
                          </div>
                        </div>
                      </el-tab-pane>
                      <el-tab-pane label="Unpaid">
                        <div class="row" style>
                          <div class="container" style="width: 1500px;height:400px;">
                            <div>
                              <el-card
                                :body-style="{ padding: 'px', background:'#51a655' ,shadow:'always', }"
                                class="card2"
                              >
                                <div style="padding-top: 10px">
                                  <span class="span2">Order Management</span>
                                </div>
                              </el-card>
                              <div style="padding-top: 100px;">
                                <el-table
                                  :data="unpaidorders"
                                  stripe
                                  style="width: 100%; color: #00acc1"
                                >
                                  <el-table-column
                                    prop="paymentdate"
                                    label="Generate Date"
                                    width="150"
                                  ></el-table-column>
                                  <el-table-column prop="username" label="User name" width="140"></el-table-column>
                                  <el-table-column prop="facilitytype" label="Facility" width="140"></el-table-column>
                                  <el-table-column prop="activitytype" label="Activity" width="140"></el-table-column>
                                  <el-table-column prop="time" label="Date" width="140"></el-table-column>
                                  <el-table-column prop="address" label="Time period" width="140"></el-table-column>
                                  <el-table-column prop="usagestatus" label="Status" width="100"></el-table-column>
                                  <el-table-column label="operations" width="160">
                                    <template slot-scope="scope">
                                      <el-button
                                        @click="Delete(scope.$index, scope.row)"
                                        type="danger"
                                        size="small"
                                      >Delete</el-button>
                                    </template>
                                  </el-table-column>
                                </el-table>
                              </div>
                            </div>
                          </div>
                        </div>
                      </el-tab-pane>
                      <el-tab-pane label="Unused">
                        <div class="row" style>
                          <div class="container" style="width: 1500px;height:400px;">
                            <div>
                              <el-card
                                :body-style="{ padding: 'px', background:'#51a655' ,shadow:'always', }"
                                class="card2"
                              >
                                <div style="padding-top: 10px">
                                  <span class="span2">Order Management</span>
                                </div>
                              </el-card>
                              <div style="padding-top: 100px;">
                                <el-table
                                  :data="unusedorders"
                                  stripe
                                  style="width: 100%; color: #00acc1"
                                >
                                  <el-table-column
                                    prop="paymentdate"
                                    label="Generate Date"
                                    width="150"
                                  ></el-table-column>
                                  <el-table-column prop="username" label="User name" width="140"></el-table-column>
                                  <el-table-column prop="facilitytype" label="Facility" width="140"></el-table-column>
                                  <el-table-column prop="activitytype" label="Activity" width="140"></el-table-column>
                                  <el-table-column prop="time" label="Date" width="140"></el-table-column>
                                  <el-table-column prop="address" label="Time period" width="140"></el-table-column>
                                  <el-table-column prop="usagestatus" label="Status" width="100"></el-table-column>
                                  <el-table-column label="operations" width="160">
                                    <template slot-scope="scope">
                                      <el-button
                                        @click="Delete(scope.$index, scope.row)"
                                        type="danger"
                                        size="small"
                                      >Delete</el-button>
                                    </template>
                                  </el-table-column>
                                </el-table>
                              </div>
                            </div>
                          </div>
                        </div>
                      </el-tab-pane>
                      <el-tab-pane label="Used">
                        <div class="row" style>
                          <div class="container" style="width: 1500px;height:400px;">
                            <div>
                              <el-card
                                :body-style="{ padding: 'px', background:'#51a655' ,shadow:'always', }"
                                class="card2"
                              >
                                <div style="padding-top: 10px">
                                  <span class="span2">Order Management</span>
                                </div>
                              </el-card>
                              <div style="padding-top: 100px;">
                                <el-table
                                  :data="usedorders"
                                  stripe
                                  style="width: 100%; color: #00acc1"
                                >
                                  <el-table-column
                                    prop="paymentdate"
                                    label="Generate Date"
                                    width="150"
                                  ></el-table-column>
                                  <el-table-column prop="username" label="User name" width="140"></el-table-column>
                                  <el-table-column prop="facilitytype" label="Facility" width="140"></el-table-column>
                                  <el-table-column prop="activitytype" label="Activity" width="140"></el-table-column>
                                  <el-table-column prop="time" label="Date" width="140"></el-table-column>
                                  <el-table-column prop="address" label="Time period" width="140"></el-table-column>
                                  <el-table-column prop="usagestatus" label="Status" width="100"></el-table-column>
                                  <el-table-column label="operations" width="160">
                                    <template slot-scope="scope">
                                      <el-button
                                        @click="Delete(scope.$index, scope.row)"
                                        type="danger"
                                        size="small"
                                      >Delete</el-button>
                                    </template>
                                  </el-table-column>
                                </el-table>
                              </div>
                            </div>
                          </div>
                        </div>
                      </el-tab-pane>
                    </el-tabs>
                  </template>
                  <template slot="tab-pane-3">
                    <div class="container" style="width: 800px">
                      <div class="md-layout">
                        <div
                          class="md-layout-item md-size-66 md-xsmall-size-100 mx-auto text-center"
                        >
                          <h2
                            class="title text-center"
                            style="color: rgba(252,249,249,0.94)"
                          >Become our membership!</h2>
                          <h5
                            class="description"
                          >Fitness is the only way to pay.Just 100% of what you can see in return</h5>
                          <h5 class="description">The body will never be ashamed of you</h5>
                          <h5
                            class="description"
                          >You are experiencing and creating change. Every time you give, it makes sense</h5>
                        </div>
                      </div>
                      <div class="features">
                        <div class="md-layout">
                          <div class="md-layout-item md-medium-size-33 md-small-size-100">
                            <md-card class="bg-white" style="padding: 10px">
                              <img src="../assets/img/mon.png" />

                              <h4 class="info-title text-center">Monthly Membership</h4>
                              <ul>
                                <li>Price:$100</li>
                                <li>Days:30</li>
                                <li>Jurisdiction:</li>
                              </ul>
                            </md-card>
                          </div>
                          <div class="md-layout-item md-medium-size-33 md-small-size-100">
                            <md-card class="bg-white" style="padding: 10px">
                              <img src="../assets/img/an.png" />

                              <h4 class="info-title text-center">Quarterly membership</h4>
                              <ul>
                                <li>Price:$300</li>
                                <li>Days:90</li>
                                <li>Jurisdiction:</li>
                              </ul>
                            </md-card>
                          </div>
                          <div class="md-layout-item md-medium-size-33 md-small-size-100">
                            <md-card class="bg-white" style="padding: 10px">
                              <img src="../assets/img/year.png" />

                              <h4 class="info-title text-center">Annul</h4>
                              <h4 class="info-title text-center" style="margin-top: -8px">Membership</h4>
                              <ul>
                                <li>Price:$1000</li>
                                <li>Days:365</li>
                                <li>Jurisdiction:</li>
                              </ul>
                            </md-card>
                          </div>
                        </div>
                        <div class="md-layout">
                          <div class="md-layout-item md-large-size-5"></div>
                          <div class="md-layout-item md-small-size-90">
                            <md-button
                              class="md-primary md-round"
                              @click="classicModal = true"
                              style="margin-left: 280px"
                            >
                              <md-icon>library_books</md-icon>Classic
                            </md-button>
                            <modal v-if="classicModal" @close="classicModalHide">
                              <template slot="header">
                                <h3 class="modal-title">Pay for your membership</h3>
                                <md-button
                                  class="md-simple md-just-icon md-round modal-default-button"
                                  @click="classicModalHide"
                                >
                                  <md-icon>clear</md-icon>
                                </md-button>
                              </template>

                              <template slot="body">
                                <h3>Username:{{user.username}}</h3>

                                <!--                              <div class="flex-column">-->
                                <!--                                <md-checkbox v-model="checkbox1">Monthly</md-checkbox></div>-->
                                <!--                              <div class="flex-column">-->
                                <!--                                <md-checkbox v-model="checkbox2">Quarterly</md-checkbox></div>-->
                                <!--                              <div class="flex-column">-->
                                <!--                                <md-checkbox v-model="checkbox3">Annul  </md-checkbox></div>-->

                                <!--                              <el-checkbox-group v-model="Membership" @change="handleMembership">-->
                                <!--      <el-checkbox v-for="mem in mems" :label="mem"  :key="mem"> {{ mem }} </el-checkbox>-->
                                <!--    </el-checkbox-group>-->

                                <div>
                                  <el-radio-group>
                                    <label>
                                      <input type="radio" value="Monthly" @click="getRadioVal()" />Monthly
                                    </label>
                                    <label>
                                      <input type="radio" value="Quarterly" @click="getRadioVal()" />Quarterly
                                    </label>
                                    <label>
                                      <input type="radio" value="Annual" @click="getRadioVal()" />Annual
                                    </label>
                                  </el-radio-group>
                                </div>
                              </template>

                              <template slot="footer">
                                <md-button class="md-simple md-success" @click="PayMembership()">Pay</md-button>
                                <md-button
                                  class="md-danger md-simple"
                                  @click="classicModalHide"
                                >Close</md-button>
                              </template>
                            </modal>
                          </div>
                        </div>
                      </div>
                    </div>
                  </template>
                </tabs>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <main-footer></main-footer>
  </div>
</template>

<script>
const memOption = ['Monthly', 'Annual', 'Quaterly'];
let member;
console.log(member);
import { Tabs } from '@/components';
// import LoginCard from "../components/cards/LoginCard";
// import TabSection1 from "@/views/components/TabSection1";
import MainFooter from '@/layout/MainFooter';
import { Modal } from '@/components';
import Navigation1 from './components/UserNav';
// import { NavTabsCard } from "@/components";
// eslint-disable-next-line no-undef
import { request } from '@/utils/axios';
export default {
  mounted() {
    this.initOrders();
    this.initUnpaidOrders();
    this.initUnusedOrders();
    this.initUsedOrders();
  },
  beforeMount() {
    this.$store.state.hideConfigButton = true;
    this.$store.state.showNavbar = false;
    this.$store.state.showSidenav = false;
    this.$store.state.showFooter = false;
  },
  beforeUnmount() {
    this.$store.state.hideConfigButton = false;
    this.$store.state.showNavbar = true;
    this.$store.state.showSidenav = true;
    this.$store.state.showFooter = true;
  },
  components: {
    MainFooter,
    // TabSection1,
    // LoginCard,
    Tabs,
    Modal,
    Navigation1
    // NavTabsCard
  },
  bodyClass: 'profile-page',
  data() {
    var validatePass2 = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请再次输入密码'));
      } else if (value !== this.dynamicValidateForm.password) {
        callback(new Error('两次输入密码不一致!'));
      } else {
        callback();
      }
    };
    return {
      Membership: [],
      mems: memOption,
      dialogVisible: false,
      cards: [],
      orders: [],
      unpaidprders: [],
      unusedorders: [],
      usedorders: [],
      //this_username:window.localStorage.getItem('user.username'),
      user: JSON.parse(window.localStorage.getItem('user')),
      checkbox1: false,
      checkbox2: false,
      checkbox3: false,
      classicModal: false,
      dynamicValidateForm: {
        username: '',
        password: '',
        checkPass: '',
        email: '',
        phoneNumber: ''
      },
      AddForm: {
        userid: '',
        bankname: '',
        number: ''
      },
      rules: {
        email: [
          { required: false, message: '请输入邮箱地址', trigger: 'blur' },
          {
            type: 'email',
            message: '请输入正确的邮箱地址',
            trigger: ['blur', 'change']
          }
        ],
        phoneNumber: [
          { required: false, message: '请输入phone number', trigger: 'blur' }
        ],
        password: [
          { required: false, message: '请输入密码', trigger: 'blur' }
          // { validator: validatePass, trigger: 'blur' }
        ],
        checkPass: [
          { required: false, message: '请输入确认密码', trigger: 'blur' },
          { validator: validatePass2, trigger: 'blur' }
        ]
      },
      tabPane1: [
        { image: require('@/assets/img/examples/studio-1.jpg') },
        { image: require('@/assets/img/examples/studio-2.jpg') },
        { image: require('@/assets/img/examples/studio-4.jpg') },
        { image: require('@/assets/img/examples/studio-5.jpg') }
      ],
      tabPane2: [
        { image: require('@/assets/img/examples/olu-eletu.jpg') },
        { image: require('@/assets/img/examples/clem-onojeghuo.jpg') },
        { image: require('@/assets/img/examples/cynthia-del-rio.jpg') },
        { image: require('@/assets/img/examples/mariya-georgieva.jpg') },
        { image: require('@/assets/img/examples/clem-onojegaw.jpg') }
      ],
      tabPane3: [
        { image: require('@/assets/img/examples/mariya-georgieva.jpg') },
        { image: require('@/assets/img/examples/studio-3.jpg') },
        { image: require('@/assets/img/examples/clem-onojeghuo.jpg') },
        { image: require('@/assets/img/examples/olu-eletu.jpg') },
        { image: require('@/assets/img/examples/studio-1.jpg') }
      ]
    };
  },
  created() {
    (this.dynamicValidateForm.username = this.user.username),
      (this.AddForm.userid = this.user.id);

    request({
      url: '/card/showAllCards',
      method: 'get',
      params: {
        id: JSON.parse(window.localStorage.getItem('user')).id
      }
    }).then((res) => {
      console.log(JSON.parse(window.localStorage.getItem('user')).id);
      console.log(111111111111111);
      if (res) {
        console.log(222222222222222222);
        console.log(res);
        this.cards = res.data.data;
      }
    });
  },
  methods: {
    getRadioVal() {
      member = event.target.value;
      console.log(member);
    },
    handleMembership(value) {
      console.log(value);
      this.Membership = value;
    },
    PayMembership() {
      request({
        url: '/membershipOrder/enter',
        method: 'get',
        params: {
          uid: JSON.parse(window.localStorage.getItem('user')).id,
          type: member
        }
      }).then((res) => {
        console.log('xiatianrui');
        console.log(member);
        if (res) {
          console.log(1111);
          if (res.data.code == 0) {
            this.$router.push('/detail/order');
            localStorage.removeItem('user');
            localStorage.setItem('user', JSON.stringify(res.data.data));
          }
          if (res.data.code == -1) {
            this.$message({
              type: 'error',
              message: 'You already have membership!'
            });
            this.$router.push('/home');
          }
          this.$router.push('/detail/order');
        }
      });
    },
    Delete(index, data) {
      request({
        url: '/activityOrder/deleteOrder',
        method: 'get',
        params: {
          orderId: data.id
        }
      }).then((res) => {
        console.log(data.id);
        if (res) {
          console.log(1111);
          this.initOrders();
          this.initUnpaidOrders();
          this.initUnusedOrders();
          this.initUsedOrders();
          if (res.data.code == 0) {
            this.$message({
              type: 'success',
              message: 'Successfully delete the order!'
            });
          }
        }
      });
    },
    initOrders() {
      request({
        url: '/activityOrder/viewOrder',
        method: 'get',
        params: {
          userId: JSON.parse(window.localStorage.getItem('user')).id
        }
      }).then((res) => {
        console.log(JSON.parse(window.localStorage.getItem('user')).id);
        console.log(11111111777199999111111);
        if (res) {
          console.log(222222222222222222);
          console.log(res);
          this.orders = res.data.data;
        }
      });
    },
    initUnpaidOrders() {
      request({
        url: '/activityOrder/findByUserStatus',
        method: 'get',
        params: {
          userid: JSON.parse(window.localStorage.getItem('user')).id,
          paymentStatus: 0,
          usageStatus: 0
        }
      }).then((res) => {
        console.log(JSON.parse(window.localStorage.getItem('user')).id);
        console.log(1889898989898981111111);
        if (res) {
          console.log(22898989892222222222);
          console.log(res);
          this.unpaidorders = res.data.data;
        }
      });
    },
    initUnusedOrders() {
      request({
        url: '/activityOrder/findByUserStatus',
        method: 'get',
        params: {
          userid: JSON.parse(window.localStorage.getItem('user')).id,
          paymentStatus: 1,
          usageStatus: 0
        }
      }).then((res) => {
        console.log(JSON.parse(window.localStorage.getItem('user')).id);
        console.log(111111117771111111);
        if (res) {
          console.log(222222222222222222);
          console.log(res);
          this.unusedorders = res.data.data;
        }
      });
    },
    initUsedOrders() {
      request({
        url: '/activityOrder/findByUserStatus',
        method: 'get',
        params: {
          userid: JSON.parse(window.localStorage.getItem('user')).id,
          paymentStatus: 1,
          usageStatus: 1
        }
      }).then((res) => {
        console.log(JSON.parse(window.localStorage.getItem('user')).id);
        console.log(111111117771111111);
        if (res) {
          console.log(222222222222222222);
          console.log(res);
          this.usedorders = res.data.data;
        }
      });
    },
    DeleteCard(item) {
      request({
        url: '/card/deleteCard',
        method: 'post',
        data: item
      }).then((res) => {
        console.log(item);
        console.log(111111111111111);
        if (res) {
          console.log(222222222222222222565656565);
          console.log(res);
          this.$router.go(0);
        }
      });
    },
    jumppay() {
      this.$router.push('/user/membership');
    },

    classicModalHide() {
      this.classicModal = false;
    },
    doAdd() {
      this.$refs.AddForm.validate((valid) => {
        if (valid) {
          //const _this=this
          request({
            url: '/card/addCard',
            method: 'post',
            data: this.AddForm
          }).then((res) => {
            console.log(this.AddForm);
            if (res) {
              console.log('oh yes');
              console.log(res.data.data);
              this.$message({
                type: 'success',
                message: 'Add successfully!'
              });
              this.$router.go(0);
            } else {
              this.$message({
                type: 'error',
                message: 'add failed'
              });
            }
          });
          alert('submit!');
        } else {
          console.log('error submit!!');
          return false;
        }
      });
    },
    submitForm() {
      this.$refs.dynamicValidateForm.validate((valid) => {
        if (valid) {
          console.log(this.dynamicValidateForm);

          const _this = this;
          request({
            url: '/user/modify',
            method: 'post',
            data: _this.dynamicValidateForm
          }).then((res) => {
            console.log('yes');
            if (res.data.code == 0) {
              console.log('oh yes');
              console.log(res.data.data);
              localStorage.removeItem('user');
              localStorage.setItem('user', JSON.stringify(res.data.data));
              this.$message({
                type: 'success',
                message: 'Modify successfully!'
              });
              this.$router.go(0);
            } else {
              this.$message({
                type: 'error',
                message: 'modify failed'
              });
            }
          });
          alert('submit!');
        } else {
          console.log('error submit!!');
          return false;
        }
      });
    }
  },
  props: {
    header: {
      type: String,
      default: require('@/assets/img/img7.png')
    },
    img: {
      type: String,
      default: require('@/assets/img/faces/christian.jpg')
    }
  },
  computed: {
    headerStyle() {
      return {
        backgroundImage: `url(${this.header})`
      };
    }
  }
};
</script>

<style lang="scss" scoped>
//.login-container {
//  border-radius: 15px;
//  background-clip: padding-box;
//  width: 350px;
//  background: #fff;
//  border: 1px solid #eaeaea;
//  z-index: 1;
//  position: absolute;
//  margin-left: 530px;
//  margin-top: 195px;
//  padding-top: 100px;
//  padding-left: 30px;
//  padding-right: 30px;
//  height: 270px;
//
//  /*box-shadow: 0 0 20px #cac6c6;*/
//}
.section {
  padding: 0;
}

.profile-tabs::v-deep {
  .md-card-tabs .md-list {
    justify-content: center;
  }

  [class*='tab-pane-'] {
    margin-top: 3.213rem;
    padding-bottom: 50px;

    img {
      margin-bottom: 2.142rem;
    }
  }
}
</style>
