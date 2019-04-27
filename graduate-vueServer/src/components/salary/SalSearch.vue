<template>
  <div>
    <h1>
      工资表查询
    </h1>
    <el-container>
      <el-header style="padding: 0px;display:flex;justify-content:space-between;align-items: center">
        <div style="display: inline">
          <el-input
            placeholder="通过员工工号搜索员工,记得回车哦..."
            clearable
            style="width: 300px;margin: 0px;padding: 0px;"
            @keyup.enter.native="searchEmpSalary"
            size="mini"
            prefix-icon="el-icon-search"
            v-model="keywords">
          </el-input>
          <el-button type="primary" size="mini" style="margin-left: 5px" icon="el-icon-search" @click="searchPerSal">搜索
          </el-button>
        </div>
        <div style="margin-left: 5px;margin-right: 20px;display: inline">
          <el-button size="mini" type="success" @click="refresh" icon="el-icon-refresh"></el-button>
          <el-button type="primary" size="mini" icon="el-icon-plus" @click="showAddSalaryView">添加个人工资</el-button>
        </div>
      </el-header>
      <el-main style="padding-left: 0px;padding-top: 0px">
        <div style="margin-top: 10px">
          <el-table
            :data="emps"
            size="mini"
            border
            stripe
            v-loading="tableLoading"
            @selection-change="handleSelectionChange"
            style="width: 100%">
            <el-table-column
              type="selection"
              align="left"
              width="55">
            </el-table-column>
            <el-table-column
              prop="name"
              align="left"
              fixed
              label="姓名"
              width="120">
            </el-table-column>
            <el-table-column
              prop="workID"
              width="120"
              align="left"
              label="工号">
            </el-table-column>
            <el-table-column
              width="70"
              prop="salary.basicSalary"
              label="基本工资">
            </el-table-column>
            <el-table-column
              width="70"
              prop="salary.trafficSalary"
              label="交通补助">
            </el-table-column>
            <el-table-column
              width="70"
              prop="salary.lunchSalary"
              label="午餐补助">
            </el-table-column>
            <el-table-column
              prop="salary.bonus"
              width="70"
              label="奖金">
            </el-table-column>
            <el-table-column
              width="100"
              label="启用时间">
              <template slot-scope="scope" >
                <span v-if="scope.row.salary && scope.row.salary.createDate">{{ scope.row.salary.createDate | formatDate }}</span>
                <span v-else>无</span>
              </template>
            </el-table-column>
            <el-table-column label="养老金" align="center">
              <el-table-column
                prop="salary.pensionPer"
                width="70"
                label="比率">
              </el-table-column>
              <el-table-column
                width="70"
                prop="salary.pensionBase"
                label="基数">
              </el-table-column>
            </el-table-column>
            <el-table-column label="医疗保险" align="center">
              <el-table-column
                width="70"
                prop="salary.medicalPer"
                label="比率">
              </el-table-column>
              <el-table-column
                prop="salary.medicalBase"
                width="70"
                label="基数">
              </el-table-column>
            </el-table-column>
            <el-table-column label="公积金" align="center">
              <el-table-column
                width="70"
                prop="salary.accumulationFundPer"
                label="比率">
              </el-table-column>
              <el-table-column
                prop="salary.accumulationFundBase"
                width="70"
                label="基数">
              </el-table-column>
            </el-table-column>
            <el-table-column label="操作" align="center">
              <el-table-column label="删除" align="center">
                <template slot-scope="scope">
                  <el-button
                    size="mini"
                    type="danger"
                    @click="doDelete(scope.row.id)">删除
                  </el-button>
                </template>
              </el-table-column>
            </el-table-column>
          </el-table>
        </div>
        <div style="text-align: left;margin-top: 10px">
          <el-button type="danger" round size="mini" :disabled="multipleSelection.length==0" @click="deleteAll">批量删除
          </el-button>
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
      title="添加个人工资"
      :visible.sync="showAddSalaryViewDialog"
      width="30%">
      <el-form label-position="left" label-width="20%">
        <el-form-item label="工号：">
          <el-input v-model="showAddSalaryData.workID" size="mini" placeholder="请输入工号" style="width: 80%;"></el-input>
          <el-button size="mini" type="primary" @click="toSearchName">确定</el-button>
        </el-form-item>
        <el-form-item label="姓名：">
          <el-input v-model="currentName.name" size="mini" placeholder="请先输入工号" style="width: 100%" disabled></el-input>
        </el-form-item>
        <el-form-item label="账套名称：">
          <el-select
            v-model="showAddSalaryData.eid"
            size="mini"
            placeholder="请选择账套名称"
            style="width: 100%"
            :loading="selectIsloading"
            @focus="toGetList"
          >
            <el-option v-for="(item, index) of salaryNameList"
              :key="index"
              :label="item.name"
              :value="item.id"
            ></el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="showAddSalaryViewDialog = false">取 消</el-button>
        <el-button type="primary" @click="confirmAddSalary">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>
<script>
  export default {
    data() {
      return {
        emps: [],
        salaries: [],
        tableLoading: false,
        totalCount: -1,
        sid: '',
        osid: '',
        currentPage: 1,
        salary: {
          createDate: '',
          basicSalary: '',
          trafficSalary: '',
          lunchSalary: '',
          bonus: '',
          pensionBase: '',
          pensionPer: '',
          medicalBase: '',
          medicalPer: '',
          accumulationFundBase: '',
          accumulationFundPer: ''
        },
        showAddSalaryViewDialog: false,
        currentName: {},
        showAddSalaryData: {
          workID: '',
        },
        salaryNameList: [],
        keywords: '' ,
        selectIsloading: false,
        salaries: [],
        multipleSelection: [],
        tableLoading: false
      }
    },
    mounted: function () {
      this.loadEmpSal();
    },
    methods: {
       deleteAll(){
        this.$confirm('删除[' + this.multipleSelection.length + ']条记录, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          var ids = '';
          this.multipleSelection.forEach(row=> {
            ids = ids + row.id + ",";
          })
          this.doDelete(ids);
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          });
        });
      },
      handleSelectionChange(val) {
        this.multipleSelection = val;
      },
      doDelete(id){
        this.tableLoading = true;
        this.deleteRequest("/salary/search/delete/" + id).then(resp=>{
          this.tableLoading = false;
          this.loadEmpSal();
        })
      },
      searchPerSal () {
        this.tableLoading = true
        this.getRequest(`/salary/search/getSal?workID=${this.keywords}`).then(res => {
          this.emps = res.data
        }).catch(err => {
          console.log(err)
        }).then(() => {
          this.tableLoading = false
        })
      },
      toSearchName () {
        this.getRequest(`/salary/search/select?workID=${this.showAddSalaryData.workID}`).then(res => {
          this.currentName = res.data[0],
          console.log( this.currentName.id)
        })
      },
      currentChange(currentPage) {
        this.currentPage = currentPage;
        this.loadEmpSal();
      },
      showAddSalaryView () {
        this.showAddSalaryViewDialog = true
      },
      loadEmpSal() {
        this.tableLoading = true;
        this.getRequest("/salary/search/empSalPage?page=" + this.currentPage + "&size=10").then(resp => {
          this.tableLoading = false;
          this.emps = resp.data.emps
          this.totalCount = resp.data.count
        })
      },
      refresh () {
        this.keywords = ''
        this.currentPage = 1
        this.loadEmpSal();
      },
      confirmAddSalary () {
        this.putRequest(`/salary/search/insertEmpSalary?sid=${this.showAddSalaryData.eid}&eid=${this.currentName.id}`).then(() => {}).catch(() => {}).then(() => {
          this.showAddSalaryViewDialog = false
        })
      },
      toGetList () {
        this.selectIsloading = true
        this.getRequest('/salary/search/salary').then(res => {
          this.salaryNameList = res.data
        }).catch(err => {
          console.log(err)
        }).then(() => {
          this.selectIsloading = false
        })
      }
    }
  }
</script>
<style>
  .el-dialog__body {
    padding-top: 0px;
    padding-bottom: 0px;
  }

  .slide-fade-enter-active {
    transition: all .8s ease;
  }

  .slide-fade-leave-active {
    transition: all .8s cubic-bezier(1.0, 0.5, 0.8, 1.0);
  }

  .slide-fade-enter, .slide-fade-leave-to {
    transform: translateX(10px);
    opacity: 0;
  }
</style>

