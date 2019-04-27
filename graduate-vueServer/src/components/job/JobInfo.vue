<template>
  <div>
    <h1>
      招聘信息
    </h1>
    <el-container>
      <el-main style="padding-left: 0px;padding-top: 0px">
        <el-header style="padding: 0px;display:flex;justify-content:space-between;align-items: center">
          <div style="margin-left: 5px;margin-right: 20px;display: inline">
            <el-button type="primary" size="mini" icon="el-icon-plus"
                       @click="showAddInfoView">
              发布招聘信息
            </el-button>
          </div>
        </el-header>
        <div style="margin-top: 10px">
          <el-table
            :data="jobInfos"
            size="mini"
            border
            stripe
            v-loading="tableLoading"
            style="width: 100%">
            <el-table-column
              prop="id"
              align="center"
              label="编号"
              width="150">
            </el-table-column>
            <el-table-column
              width="400"
              align="center"
              label="招聘信息">
              <template slot-scope="scope">
                <span style="display: inline-block;
                  white-space: nowrap;
                  width: 100%;
                  overflow: hidden;
                  text-overflow:ellipsis;">{{ scope.row.info }}</span>
              </template>
            </el-table-column>
            <el-table-column
              width="200"
              label="创建时间">
              <template slot-scope="scope">{{ scope.row.createDate | formatDate}}</template>
            </el-table-column>
            <el-table-column
              prop="departmentBean.name"
              width="280"
              align="center"
              label="招聘部门">
            </el-table-column>
            <el-table-column label="操作" align="center">
              <el-table-column label="查看" align="center">
                <template slot-scope="scope">
                  <el-button
                    size="mini"
                    @click="handleLook(scope.row)">查看
                  </el-button>
                </template>
              </el-table-column>
              <el-table-column label="删除" align="center">
                <template slot-scope="scope">
                  <el-button
                    size="mini"
                    type="danger"
                    @click="handleDelete(scope.$index, scope.row)">删除
                  </el-button>
                </template>
              </el-table-column>
            </el-table-column>
          </el-table>
        </div>
      </el-main>
    </el-container>
    <div style="text-align: right;margin-top: 10px">
      <el-pagination
        background
        @current-change="currentChange"
        layout="prev, pager, next"
        :total="totalCount">
      </el-pagination>
    </div>
    <el-dialog
      :title="dialogTitle"
      style="padding: 0px;"
      :close-on-click-modal="false"
      :visible.sync="dialogVisible"
      width="35%">
      <el-form ref="form" :model="jobInfo" label-width="25%" label-position="right">
        <el-form-item label="发布招聘信息：">
          <el-input prefix-icon="el-icon-edit" type="textarea" :rows="3" v-model="jobInfo.info" size="mini" placeholder="请输入招聘信息" style="width: 100%"></el-input>
        </el-form-item>
        <el-form-item label="发布日期：">
          <el-date-picker
            v-model="jobInfo.createDate"
            size="mini"
            type="date"
            value-format="yyyy-MM-dd HH:mm:ss"
            style="width: 100%"
            placeholder="发布日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="部门：">
          <el-select v-model="jobInfo.did" style="width: 100%" size="mini" placeholder="请选择部门" :loading="selectLoading" @focus="getDepartmentList">
            <el-option
              v-for="item in departments"
              :key="item.id"
              :label="item.name"
              :value="item.id">
            </el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button size="mini" @click="cancelEidt">取 消</el-button>
        <el-button size="mini" type="primary" @click="confirmFoo">确 定</el-button>
      </span>
    </el-dialog>
    <el-dialog
      title="详情"
      :visible.sync="dialogVisible1"
      width="35%">
      <p style="padding: 0 5%;padding-bottom: 50px;">{{ nowInfo }}</p>
    </el-dialog>
  </div>
</template>
<script>
  export default {
    data() {
      return {
        jobInfos: [],
        dialogVisible1: false,
        departments:[],
        department: {
          id:'',
          name: ''
        },
        jobInfo:{
          did:'',
          info:'',
          createDate:''
        },
        keywords: '',
        tableLoading: false,
        totalCount: -1,
        currentPage: 1,
        dialogVisible: false,
        selectLoading: false,
        dialogTitle: '',
        nowInfo: ''
      }
    },
    mounted: function () {
      this.loadInfoDep();
    },
    methods: {
      handleLook (row) {
        this.nowInfo = row.info
        this.dialogVisible1 = true
      },
      confirmFoo () {
        this.addInfo()
      },
      getDepartmentList () {
        this.selectLoading = true
        this.getRequest('/jobInfo/info/AllDeps').then(res => {
          this.selectLoading = false
          this.departments = res.data
        })
      },
      showEditInfoView(row){
        this.dialogTitle = "编辑招聘信息";
        this.jobInfo=row;
        this.dialogVisible = true;
      },
      addInfo(){
        this.tableLoading = true;
        this.postRequest(`/jobInfo/info/add`, this.jobInfo).then(resp=> {
          this.tableLoading = false
          this.dialogVisible = false
          this.loadInfoDep();
        })
      },
      updateInfo () {
        this.tableLoading = true
        this.postRequest("/jobInfo/info/update", this.jobInfo).then(resp=> {
          this.tableLoading = false
          this.dialogVisible = false
          this.loadInfoDep()
        })
      },
      cancelEidt(){
        this.dialogVisible = false;
      },
      showAddInfoView(){
        this.dialogTitle = "添加招聘信息";
        this.dialogVisible = true;
      },
      handleDelete(index, row) {
        this.$confirm('删除[' + row.info + ']招聘信息, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning',
          distinguishCancelAndClos: true
        }).then(() => {
          this.doDelete(row.id);
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          });
        });
      },
      doDelete(id){
        this.tableLoading = true;
        this.deleteRequest("/jobInfo/info/delete/" + id).then(()=>{
          this.tableLoading = false;
          this.loadBasicDep()
        });
      },
      currentChange(currentPage) {
        this.currentPage = currentPage;
        this.loadInfoDep();
      },
      loadInfoDep() {
        this.tableLoading = true;
        this.getRequest("/jobInfo/info/jobInfoPage?page=" + this.currentPage + "&size=10").then(resp => {
          this.tableLoading = false
          this.jobInfos = resp.data.jobInfoByPageShort
          this.totalCount = resp.data.count
        })
      }
    }
  }
</script>
