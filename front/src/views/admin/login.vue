<script setup>
import {ref, reactive} from 'vue'
import {useAdminStore} from "@/stores/admin.js";
import router from "@/router/index.js";

const adminStore = useAdminStore()

const form = reactive({
  username: 'admin',
  password: '111111'
})
const rules = reactive({
  username: [{required: true, message: '此项是必填项', trigger: 'blur'}],
  password: [{required: true, message: '此项是必填项', trigger: 'blur'}]
})

const formRef = ref()
const handleLogin = () => {
  formRef.value.validate(async (valid) => {
    if (valid) {
      adminStore.login(form).then(res => {
        router.push("/admin")
      })
    }
  })
}

</script>

<template>

  <div class="login-container">

    <el-form
        ref="formRef"
        style="max-width: 500px"
        :model="form"
        :rules="rules"
        label-width="auto"
        class="login-form"
    >
      <h3 class="title">码客在线后台管理系统</h3>

      <el-form-item prop="username">
        <el-input v-model="form.username" placeholder="用户名"/>
      </el-form-item>
      <el-form-item prop="password">
        <el-input type="password" v-model="form.password" placeholder="密码"/>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="handleLogin" style="margin: 0 auto; width: 100%">登录</el-button>
      </el-form-item>

      <div class="tips">
        <span>原创公众号：MarkerHub，网站：https://www.markerhub.com</span>
      </div>

    </el-form>
  </div>

</template>

<style lang="scss" scoped>

.login-container {
  background-color: #2d3a4b;
  height: 100vh;

  .login-form {
    width: 520px;
    margin: 0 auto;
    padding-top: 200px;

    .title {
      color: #fff;
      text-align: center;
      font-size: 26px;
      margin-bottom: 40px;
    }

    .tips {
      color: #fff;
    }
  }


}

</style>