<script setup>
import {ref, reactive} from 'vue'
import {useUserStore} from "@/stores/user.js";
import router from "@/router/index.js"
import {Register, GetCaptcha} from "^/app/login.js"
import {ElMessage} from 'element-plus'

const userStore = useUserStore()

const form = reactive({
  username: '',
  password: '',
  code: '',
  uuid: ''
})
const rules = reactive({
  username: [{required: true, message: '此项是必填项', trigger: 'blur'}],
  password: [{required: true, message: '此项是必填项', trigger: 'blur'}],
  code: [{required: true, message: '此项是必填项', trigger: 'blur'}]
})

const formRef = ref()
const handleRegister = () => {
  formRef.value.validate(async (valid) => {
    if (valid) {
      Register(form).then(res => {
        ElMessage.success('注册成功，请登录')
        router.push("/login")
      }, err => {
        getCaptcha()
        form.code = ''
      })
    }
  })
}

const captchaImg = ref('')
const getCaptcha = () => {
  GetCaptcha().then(res => {
    form.uuid = res.data.uuid
    captchaImg.value = res.data.imgBase64
  })
}
getCaptcha()

</script>

<template>

  <el-card shadow="never" style="max-width: 500px; margin: auto">
    <template #header>用户注册</template>

    <el-form
        ref="formRef"
        style="max-width: 500px"
        :model="form"
        :rules="rules"
        label-width="auto"
    >
      <el-form-item label="用户名" prop="username">
        <el-input v-model="form.username"/>
      </el-form-item>
      <el-form-item label="密码" prop="password">
        <el-input type="password" v-model="form.password" show-password="true"/>
      </el-form-item>
      <el-form-item label="验证码" prop="code">
        <el-input v-model="form.code" style="width: 60%; margin-right: 10px;"/>
        <el-image :src="captchaImg" @click="getCaptcha"></el-image>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="handleRegister" style="margin: 0 auto;">注册</el-button>
      </el-form-item>
    </el-form>

  </el-card>

</template>

<style lang="scss" scoped>


</style>