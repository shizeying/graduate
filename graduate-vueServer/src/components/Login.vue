<template>
  <div class="background">
    <!-- <el-form v-if="isLoginPage" :rules="rules" class="login-container" label-position="left" label-width="0px" v-loading="loading"> -->
    <el-form :rules="rules" class="login-container" label-position="left" label-width="0px" v-loading="loading">
      <h3 class="login_title">系统登录</h3>
      <el-form-item prop="account" label-width="60px" label="帐号:"  >
        <el-input type="text" v-model="loginForm.username"
                  auto-complete="off" placeholder="账号"></el-input>
      </el-form-item>
      <el-form-item prop="checkPass" label-width="60px" label="密码:">
        <el-input type="password" v-model="loginForm.password"
                  auto-complete="off" placeholder="密码"></el-input>
      </el-form-item>
      <el-checkbox class="login_remember" v-model="checked"
                   label-position="left">记住密码
      </el-checkbox>
      <el-form-item style="width: 100%">
        <el-button type="primary" style="width: 100%" @click="submitClick">登录</el-button>
      </el-form-item>
      <!-- <el-form-item style="width: 100%">
        <el-button style="width: 100%" @click="toRegister">注册</el-button>
      </el-form-item> -->
    </el-form>
     <!-- <el-form v-else class="login-container" label-position="left"
             label-width="0px" v-loading="loading">
      <h3 class="login_title">系统注册</h3>
      <el-form-item label-width="60px" label="姓名:"  >
        <el-input type="text" v-model="register.name" :disabled="isPassed"
                  auto-complete="off" placeholder="姓名"></el-input>
      </el-form-item>
      <el-form-item label-width="60px" label="工号:"  >
        <el-input type="text" v-model="register.workID" :disabled="isPassed"
                  auto-complete="off" placeholder="工号"></el-input>
      </el-form-item>
      <el-form-item v-if="isPassed"  label-width="60px" label="帐号:"  >
        <el-input type="text" v-model="register.username"
                  auto-complete="off" placeholder="账号"></el-input>
      </el-form-item>
      <el-form-item v-if="isPassed" label-width="60px" label="密码:">
        <el-input type="password" v-model="register.password"
                  auto-complete="off" placeholder="密码"></el-input>
      </el-form-item>
      <el-checkbox class="login_remember" v-model="checked"
                   label-position="left">记住密码
      </el-checkbox>
      <el-form-item style="width: 100%">
        <el-button type="primary" style="width: 100%" @click="toNextSetp">{{ btnText }}</el-button>
      </el-form-item>
      <el-form-item style="width: 100%">
        <el-button style="width: 100%" @click="toLoginPage">登录</el-button>
      </el-form-item>
    </el-form> -->
  </div>
</template>

<script>
  export default {
    data() {
      return {
        rules: {
          account: [{required: true, message: '请输入用户名', trigger: 'blur'}],
          checkPass: [{required: true, message: '请输入密码', trigger: 'blur'}]
        },
        checked: true,
        loginForm: {
          username: 'admin',
          password: '123'
        },
        register: {
          name: '',
          workID: '',
          username: '',
          password: ''
        },
        isPassed: false,
        loading: false,
        isLoginPage: true
      }
    },
    computed: {
      btnText () {
        return this.isPassed ? '注册' : '下一步'
      }
    },
    methods: {
      toLoginPage () {
        this.isLoginPage = true
      },
      toNextSetp () {
        if (isPassed) {
          this.postRequest('/system/hr/hr/reg', {
            name: this.register.name,
            username: this.register.username,
            password: this.register.password
          }).then(() => {
            this.isLoginPage = false
          })
        } else {
          this.getRequest(`/system/hr/Verb?name=${this.register.name}?workID=${this.register.workID}`).then(() => {
            this.isPassed = true
          })
        }
      },
      toRegister () {
        this.isLoginPage = false
      },
      submitClick: function () {
        var _this = this;
        this.loading = true;
        this.postRequest('/login', {
          username: this.loginForm.username,
          password: this.loginForm.password
        }).then(resp => {
          _this.loading = false;
          if (resp && resp.status == 200) {
            var data = resp.data;
            _this.$store.commit('login', data.obj);
            var path = _this.$route.query.redirect;
            _this.$router
              .replace({path: path == '/' || path == undefined ? '/home' : path});
          }
        });
      }
    }
  }
</script>
<style>
  .background {
    background-image: url("../imge/img101.png");
    background-size: cover;
    width: 93%;
    height: 400%;
    margin-top: -180px;
    padding: 60px;
    background-repeat: no-repeat;
    background-position: center center;
  }

  .login-container {
    border-radius: 15px;
    background-clip: padding-box;
    margin: 180px auto ;
    width: 350px;
    padding: 35px 35px 15px 35px;
    background: #fff;
    text-align: right;
    border: 1px solid #eaeaea;
    box-shadow: 0 0 25px #cac6c6;
  }

  .login_title {
    margin: 0px auto 40px auto;
    text-align: center;
    color: #505458;
  }

  .login_remember {
    margin: 0px 0px 35px 0px;
    text-align: left;
  }
</style>
