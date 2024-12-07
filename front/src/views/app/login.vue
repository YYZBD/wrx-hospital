<script setup>
import {ref, reactive} from 'vue'
import {useUserStore} from "@/stores/user.js";
import router from "@/router/index.js";
import {useRoute} from "vue-router";

const route = useRoute()

const userStore = useUserStore()

const form = reactive({
  username: 'MarkerHub',
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
      userStore.login(form).then(res => {
        router.push(route.query.redirect? route.query.redirect : "/")
      })
    }
  })
}

</script>

<template>

  <el-card shadow="never" style="max-width: 500px; margin: auto">
    <template #header>用户登录</template>

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
        <el-input type="password" v-model="form.password"/>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="handleLogin" style="margin: 0 auto;">登录</el-button>
      </el-form-item>
    </el-form>

  </el-card>

</template>

<style lang="scss" scoped>


</style>