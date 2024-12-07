<script setup>
import {ref, reactive} from 'vue'
import {UpdatePass} from "^/app/user.js"
import {ElMessage} from "element-plus"

const form = reactive({})
const rules = reactive({
  oldPass: [{required: true, message: '此项是必填项', trigger: 'blur'}],
  newPass: [{required: true, message: '此项是必填项', trigger: 'blur'}],
})

const formRef = ref()
const handleSubmit = () => {
  formRef.value.validate(async (valid) => {
    if (valid) {
      UpdatePass(form).then(res => {
        ElMessage.success("更新成功")
        formRef.value.resetFields()
      })
    }
  })
}

</script>

<template>
  <el-card shadow="never">

    <template #header>修改个人密码</template>

    <el-form
        ref="formRef"
        style="max-width: 500px"
        :model="form"
        :rules="rules"
        label-width="auto"
    >
      <el-form-item label="旧密码" prop="oldPass">
        <!--fix-bug：show-password="true"去掉="true"-->
        <el-input v-model="form.oldPass" show-password/>
      </el-form-item>
      <el-form-item label="新密码" prop="newPass">
        <el-input v-model="form.newPass" show-password/>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="handleSubmit" style="margin: 0 auto;">提交</el-button>
      </el-form-item>
    </el-form>

  </el-card>

</template>

<style lang="scss" scoped>


</style>











