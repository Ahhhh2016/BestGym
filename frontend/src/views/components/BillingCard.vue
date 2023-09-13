<template>
  <div class="card">
    <div class="card-header pb-0 px-3">
      <h6 class="mb-0">Edit the information of facilities</h6>
    </div>
    <div class="card-body pt-4 p-3">
      <ul class="list-group">
        <li
          class="list-group-item border-0 d-flex p-4 mb-2 bg-gray-100 border-radius-lg"
          v-for="item in facilities"
          v-bind:key="item"
        >
          <div class="d-flex flex-column">
            <!--            <h6 class="mb-3 text-sm">Oliver Lia</h6>-->
            <span class="mb-2 text-xs">
              Facility id:
              <span class="text-dark font-weight-bold ms-sm-2">{{item.id}}</span>
            </span>
            <span class="mb-2 text-xs">
              Facility name:
              <span class="text-dark ms-sm-2 font-weight-bold">{{item.facilityType}}</span>
            </span>
            <span class="mb-2 text-xs">
              Max Capacity:
              <span class="text-dark ms-sm-2 font-weight-bold">{{item.maxCapacity}}</span>
            </span>
            <span class="mb-2 text-xs">
              Num Facility:
              <span class="text-dark ms-sm-2 font-weight-bold">{{item.numFacility}}</span>
            </span>
          </div>
          <div class="ms-auto text-end">
            <a class="btn btn-link text-danger text-gradient px-3 mb-0" href="javascript:;">
              <i class="far fa-trash-alt me-2" aria-hidden="true"></i>Delete
            </a>
            <el-button @click="ShowEdit(scope.$index, scope.row)">
              <i class="fas fa-pencil-alt text-dark me-2" aria-hidden="true"></i>Edit
            </el-button>
          </div>
        </li>
      </ul>
      <el-dialog title="Edit facility" :visible.sync="dialogVisible" width="30%">
        <span>这是一段信息</span>
        <span slot="footer" class="dialog-footer">
          <el-button @click="dialogVisible = false">取 消</el-button>
          <el-button type="primary" @click="dialogVisible = false">确 定</el-button>
        </span>
      </el-dialog>
    </div>
  </div>
</template>

<script>
import { request } from '@/utils/axios';
export default {
  mounted() {
    this.initFacilitis();
  },
  methods: {
    initFacilitis() {
      request({
        url: '/facility/findAllFacility',
        method: 'get'
      }).then((res) => {
        if (res) {
          console.log(res.data.data);
          this.facilities = res.data.data;
        }
      });
    },
    ShowEdit() {
      this.dialogVisible = true;
    }
  },
  data() {
    return {
      facilities: [],
      dialogVisible: false
    };
  },
  name: 'billing-card'
};
</script>
