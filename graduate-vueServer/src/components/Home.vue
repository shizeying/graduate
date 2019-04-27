<template>
  <div>
    <el-container class="home-container">
      <el-header class="home-header">
        <span class="home_title">四川大学锦城学院毕业设计</span>
        <div style="display: flex;align-items: center;margin-right: 7px">
          <el-dropdown @command="handleCommand">
  <span class="el-dropdown-link home_userinfo" style="display: flex;align-items: center">
    {{user.name}}
    <i><img v-if="user.userface!=''" :src="user.userface"
            style="width: 40px;height: 40px;margin-right: 5px;margin-left: 5px;border-radius: 40px"/></i>
  </span>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item command="logout" divided>注销</el-dropdown-item>
              <el-dropdown-item command="reset" divided>修改密码</el-dropdown-item>
              <el-dropdown-item command="update" divided>修改资料</el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
        </div>
      </el-header>
      <el-container>
        <el-aside width="180px" class="home-aside">
          <div style="display: flex;justify-content: flex-start;width: 180px;text-align: left;">
            <el-menu style="background: #ececec;width: 180px;" unique-opened router>
              <template v-for="(item,index) in this.routes" v-if="!item.hidden">
                <el-submenu :key="index" :index="index+''">
                  <template slot="title">
                    <i :class="item.iconCls" style="color: #20a0ff;width: 14px;"></i>
                    <span slot="title">{{item.name}}</span>
                  </template>
                  <el-menu-item width="180px"
                                style="padding-left: 30px;padding-right:0px;margin-left: 0px;width: 170px;text-align: left"
                                v-for="child in item.children"
                                :index="child.path"
                                :key="child.path">{{child.name}}
                  </el-menu-item>
                </el-submenu>
              </template>
            </el-menu>
          </div>
        </el-aside>
          <el-main>
            <el-breadcrumb separator-class="el-icon-arrow-right">
              <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
              <el-breadcrumb-item v-text="this.$router.currentRoute.name"></el-breadcrumb-item>
            </el-breadcrumb>
            <keep-alive>
              <router-view v-if="this.$route.meta.keepAlive"></router-view>
            </keep-alive>
            <router-view v-if="!this.$route.meta.keepAlive"></router-view>
          </el-main>
      </el-container>
    </el-container>
    <el-dialog
      title="修改资料"
      :visible.sync="updateDialogVisible"
      width="600px">
      <el-form label-width="80px" inline>
        <el-form-item label="姓名">
          <el-input size="mini" v-model="userInfo.name"></el-input>
        </el-form-item>
        <el-form-item label="手机号">
          <el-input type="number" size="mini" v-model="userInfo.phone"></el-input>
        </el-form-item>
        <el-form-item label="电话号码">
          <el-input type="number" size="mini" v-model="userInfo.telephone"></el-input>
        </el-form-item>
        <el-form-item label="地址">
          <el-input size="mini" v-model="userInfo.address"></el-input>
        </el-form-item>
        <el-form-item label="头像URL">
          <el-input size="mini" style="width: 450px" v-model="userInfo.userface"></el-input>
        </el-form-item>
        <el-form-item label="配注">
          <el-input style="width: 460px" type="textarea" :rows="3" size="mini" v-model="userInfo.remark"></el-input>
        </el-form-item>
      </el-form>

      <span slot="footer" class="dialog-footer">
        <el-button @click="updateDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="comfirmUpdate">确 定</el-button>
      </span>
    </el-dialog>
    <el-dialog
      title="修改密码"
      :visible.sync="resetPasswordDialogVisible"
      width="30%">
      <el-form label-width="80px">
        <el-form-item label="新密码">
          <el-input type="password" size="mini" v-model="newPassword"></el-input>
        </el-form-item>
        <el-form-item label="确认密码">
          <el-input type="password" size="mini" v-model="comfirmPassword"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="resetPasswordDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="comfirmReset">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>
<script>
  export default{
    mounted: function () {
      // this.loadNF();
    },
    methods: {
      comfirmUpdate () {
        let str = ''
        let tempArr = Object.entries(this.userInfo)
        tempArr.forEach((i) => {
          str += `${i[0]}=${i[1]}&`
        })
        let params = str.substring(0, str.length - 1)
        this.putRequest('/system/hr/update?' + params).then(() => {
          this.getRequest("/logout");
          this.$store.commit('logout');
          this.$router.replace({path: '/'});
        })
      },
      comfirmReset () {
        if (this.newPassword === this.comfirmPassword) {
          console.log(this.newPassword)
          this.putRequest(`/system/hr/update?id=${JSON.parse(window.localStorage.getItem('user')).id}&password=${this.newPassword}`
          ).then(() => {
            this.getRequest("/logout");
            this.$store.commit('logout');
            this.$router.replace({path: '/'});
          })
        } else {
          this.$message.error('两次密码不一致！')
        }
      },
      handleCommand(cmd){
        var _this = this;
        if (cmd == 'logout') {
          this.$confirm('注销登录, 是否继续?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {
            _this.getRequest("/logout");
            _this.$store.commit('logout');
            _this.$router.replace({path: '/'});
          }).catch(() => {
            _this.$message({
              type: 'info',
              message: '取消'
            });
          });
        }
        if (cmd == 'reset') {
          this.resetPasswordDialogVisible = true
        }
        if (cmd == 'update') {
          this.updateDialogVisible = true
        }
      }
    },
    data(){
      return {
        isDot: false,
        resetPasswordDialogVisible: false,
        newPassword: '',
        comfirmPassword: '',
        updateDialogVisible: false,
        userInfo: {
          phone: '',
          telephone: '',
          address: '',
          username: '',
          userface: '',
          remark: ''
        }
      }
    },
    computed: {
      user(){
        return this.$store.state.user;
      },
      routes(){
        return this.$store.state.routes
      }
    }
  }
</script>
<style>
  .home-header {
    background-color: #20a0ff;
    color: #333;
    text-align: center;
    display: flex;
    align-items: center;
    justify-content: space-between;
    box-sizing: content-box;
    padding: 0px;
  }

  .home-aside {
    background-color: #ECECEC;
  }

  .home-main {
    background-color: #fff;
    color: #000;
    text-align: center;
    margin: 0px;
    padding: 0px;;
  }

  .home_title {
    color: #fff;
    font-size: 22px;
    display: inline;
    margin-left: 8px;
  }

  .home_userinfo {
    color: #fff;
    cursor: pointer;
  }

  .home_userinfoContainer {
    display: inline;
    margin-right: 20px;
  }

  .el-submenu .el-menu-item {
    width: 180px;
    min-width: 175px;
  }
</style>
