<template>
  <div>
    <h1>
      基本信息
    </h1>
    <el-container>
      <el-header style="padding: 0px;display:flex;justify-content:space-between;align-items: center">
        <div style="margin-left: 5px;margin-right: 20px;display: inline">
          <el-button type="primary" size="mini" icon="el-icon-plus"
                     @click="showAddSalaryView">
            添加面试信息
          </el-button>
        </div>
      </el-header>
      <el-main style="padding-left: 0px;padding-top: 0px">
        <div style="margin-top: 10px">
          <el-table
            :data="jobBasics"
            size="mini"
            border
            stripe
            v-loading="tableLoading"
            style="width: 100%">
            <el-table-column
              prop="id"
              align="left"
              label="编号"
              width="70">
            </el-table-column>
            <el-table-column
              prop="name"
              align="left"
              label="姓名"
              width="115">
            </el-table-column>
            <el-table-column
              prop="age"
              align="left"
              label="年龄"
              width="115">
            </el-table-column>
            <el-table-column
              prop="gender"
              width="110"
              align="left"
              label="性别">
            </el-table-column>
            <el-table-column
              width="180"
              align="center"
              prop="phone"
              label="联系方式">
            </el-table-column>
            <el-table-column
              width="180"
              align="center"
              prop="sparePhone"
              label="紧急联系方式">
            </el-table-column>
            <el-table-column
              width="150"
              align="center"
              prop="departmentBean.name"
              label="面试部门">
            </el-table-column>
            <el-table-column
              width="200"
              align="center"
              label="面试时间">
              <template slot-scope="scope">{{ scope.row.interviewDate | formatDate}}</template>
            </el-table-column>
            <el-table-column label="操作" align="center">
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
        page-size="10"
        layout="prev, pager, next"
        :total="totalCount">
      </el-pagination>
    </div>
    <el-dialog
      :title="dialogTitle"
      :visible.sync="dialogVisible"
      width="50%">
      <el-form inline label-position="left" size="mini" label-width="100px">
        <el-form-item label="姓名" style="width: 300px; ">
          <el-input size="mini" v-model="params.name" style="width: 100%"></el-input>
        </el-form-item>
        <el-form-item label="年龄" style="width: 300px;  ">
          <el-input size="mini" v-model="params.age"></el-input>
        </el-form-item>
        <el-form-item label="性别" style="width: 300px;  ">
          <template>
            <el-radio v-model="params.gender" label="男">男</el-radio>
            <el-radio v-model="params.gender" label="女">女</el-radio>
          </template>
        </el-form-item>
        <el-form-item label="联系方式" style="width: 300px; ">
          <el-input size="mini" v-model="params.phone"></el-input>
        </el-form-item>
        <el-form-item label="紧急联系方式" style="width: 300px; ">
          <el-input size="mini" v-model="params.sparePhone"></el-input>
        </el-form-item>
        <el-form-item label="面试部门" style="width: 300px; " label-width="90px">
          <el-select v-model="params.did" size="mini" placeholder="请选择部门" :loading="selectLoading" @focus="getDepartmentList" style="width: 90%">
            <el-option
              v-for="item in departments"
              :key="item.id"
              :label="item.name"
              :value="item.id">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="面试时间" style="width: 100%;" label-width="90px">
          <el-date-picker
            v-model="params.interviewDate"
            size="mini"
            type="date"
            value-format="yyyy-MM-dd HH:mm:ss"
            placeholder="面试时间"
            style="width: 100%;"
            ></el-date-picker>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="confirmAdd">确 定</el-button>
      </span>
    </el-dialog>

  </div>
</template>
<script>
  export default {
    data() {
      return {
        jobBasics:[],
        departments: [],
        jobBasic:{
          id: '',
          name:'',
          age:'',
          gender:'',
          phone:'',
          sparePhone:'',
          interviewDate:''
        },
        params: {
          name: '',
          did: '',
          age: '',
          gender: '',
          phone: '',
          sparePhone: '',
          interviewDate: ''
        },
        department:[],
        keywords: '',
        tableLoading: false,
        totalCount: -1,
        currentPage: 1,
        dialogVisible: false,
        tableLoading: false,
        advanceSearchViewVisible: false,
        showOrHidePop: false,
        showOrHidePop2: false,
        dialogTitle: '',
        selectLoading: false
      }
    },
    mounted: function () {
      this.loadBasicDep();
    },
    methods:{
      confirmAdd () {
        this.postRequest('/jobInfo/basic/insert', this.params).then(res => {
          this.dialogVisible = false
          this.loadBasicDep()
        })
      },
       getDepartmentList () {
        this.selectLoading = true
         console.log(this.departments)
         if (this.departments>0){
           return
         }
        this.getRequest('/jobInfo/info/AllDeps').then(res => {
          this.selectLoading = false
          this.departments = res.data
          console.log(this.departments)
        })
      },
      showAddSalaryView () {
        this.dialogTitle = '添加面试信息'
        this.dialogVisible = true
      },
      handleDelete(index, row) {
        this.$confirm('删除[' + row.name + ']应聘信息, 是否继续?', '提示', {
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
        this.deleteRequest("/jobInfo/basic/delete/" + id).then(()=>{
          this.tableLoading = false;
          this.loadBasicDep()
        });
      },
      currentChange(currentPage) {
        this.currentPage = currentPage;
        this.loadBasicDep();
      },
      loadBasicDep() {
        this.tableLoading = true;
        var _this = this;
        this.getRequest("/jobInfo/basic/jobBasicPage?page=" + this.currentPage + "&size=10").then(resp => {
          _this.tableLoading = false;
          this.jobBasics = resp.data.employeeByPageShort
          this.totalCount = resp.data.count
        })
      }
    }
  }
</script>
