<script setup>
import {ref, reactive} from 'vue'
import {GetUserInfo, UpdateUserInfo} from "^/app/user.js"
import {ElMessage} from "element-plus"
import {useUserStore} from "@/stores/user.js"
import UploadImg from "~/UploadImg.vue";

const userStore = useUserStore()

const form = reactive({})
const rules = reactive({
  avatar: [{required: true, message: '此项是必填项', trigger: 'blur'}],
  username: [{required: true, message: '此项是必填项', trigger: 'blur'}],
})

const formRef = ref()
const handleSubmit = () => {
  formRef.value.validate(async (valid) => {
    if (valid) {
      UpdateUserInfo(form).then(res => {
        ElMessage.success("更新成功")
        userStore.updateUserInfo(res.data)
      })
    }
  })
}

const initData = () => {
  GetUserInfo().then(res => {
    Object.assign(form, res.data)
  })
}
initData()

const handleUpload = (images) => {
  form.avatar = images
}

</script>

<template>
  <el-card shadow="never">

    <template #header>修改个人信息</template>

    <el-form
        ref="formRef"
        style="max-width: 500px"
        :model="form"
        :rules="rules"
        label-width="auto"
    >
      <el-form-item label="头像" prop="avatar">
        <!--上传头像-->
        <UploadImg :images="form.avatar" :limit="1" @handleUpload="handleUpload"></UploadImg>
      </el-form-item>
      <el-form-item label="用户名" prop="username">
        <el-input v-model="form.username"/>
      </el-form-item>
      <el-form-item label="手机号码" prop="phone">
        <el-input v-model="form.phone"/>
      </el-form-item>
      <el-form-item label="个人签名" prop="signature">
        <el-input v-model="form.signature"/>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="handleSubmit" style="margin: 0 auto;">提交</el-button>
      </el-form-item>
    </el-form>

  </el-card>

</template>

<style lang="scss" scoped>


</style>











