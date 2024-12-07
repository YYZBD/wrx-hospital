<script setup>
import {ref, reactive} from 'vue'
import {Info, Save} from '^/admin/admin'
import UploadImg from "~/UploadImg.vue";
import {ElMessage} from "element-plus";

const showDrawer = ref(false)

const editForm = ref({})
const init = (id) => {
  showDrawer.value = true
  if (id) {
    Info(id).then(res => {
      editForm.value = res.data
    })
  } else {
    editForm.value = {}
  }
}
defineExpose({init})

const rules = reactive({
  avatar: [{required: true, message: '此项是必填项', trigger: 'blur'}],
  username: [{required: true, message: '此项是必填项', trigger: 'blur'}],
  email: [{required: true, message: '此项是必填项', trigger: 'blur'}],
})
const emits = defineEmits(['handleClose'])

const closeHandle = (hasChange) => {
  showDrawer.value = false
  emits('handleClose', hasChange)
}

const formRef = ref()
const submitHandle = () => {
  formRef.value.validate((valid, fields) => {
    if (valid) {
      Save(editForm.value).then(res => {
        ElMessage.success('操作成功')
        closeHandle(true)
      })
    }
  })

}
</script>

<template>

  <el-drawer
      v-model="showDrawer"
      title="新增或编辑"
  >

    <el-form
        ref="formRef"
        style="max-width: 600px"
        :model="editForm"
        :rules="rules"
        label-width="auto"
    >
      <el-form-item label="头像" prop="avatar">
        <!--@handleUpload="(images) => {editForm.value.avatar = images}"-->
        <UploadImg v-model:images="editForm.avatar" :limit="1"></UploadImg>
      </el-form-item>
      <el-form-item label="管理员名称" prop="username">
        <el-input v-model="editForm.username"/>
        <el-alert v-if="!editForm.id" title="默认密码：888888，请在创建后及时修改密码"></el-alert>
      </el-form-item>
      <el-form-item label="邮箱" prop="email">
        <el-input v-model="editForm.email"/>
      </el-form-item>
      <el-form-item label="手机号" prop="phone">
        <el-input v-model="editForm.phone"/>
      </el-form-item>

      <el-form-item>
        <el-button type="primary" @click="submitHandle">提交</el-button>
        <el-button @click="closeHandle">关闭</el-button>
      </el-form-item>

    </el-form>

  </el-drawer>

</template>

<style lang="scss" scoped>

</style>