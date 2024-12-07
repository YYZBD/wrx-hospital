<script setup>
  import {ref, reactive} from 'vue'
  import {Info, Save} from '^/admin/medicalCard'
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
                    id: [{required: true, message: '此项是必填项', trigger: 'blur'}],
                          userId: [{required: true, message: '此项是必填项', trigger: 'blur'}],
                          sn: [{required: true, message: '此项是必填项', trigger: 'blur'}],
                          realName: [{required: true, message: '此项是必填项', trigger: 'blur'}],
                          cardNumber: [{required: true, message: '此项是必填项', trigger: 'blur'}],
                          phone: [{required: true, message: '此项是必填项', trigger: 'blur'}],
                          gender: [{required: true, message: '此项是必填项', trigger: 'blur'}],
                          createTime: [{required: true, message: '此项是必填项', trigger: 'blur'}],
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

                              <el-form-item label="" prop="id">
              <el-input v-model="editForm.id"/>
            </el-form-item>
                                        <el-form-item label="用户ID" prop="userId">
              <el-input v-model="editForm.userId"/>
            </el-form-item>
                                        <el-form-item label="卡号" prop="sn">
              <el-input v-model="editForm.sn"/>
            </el-form-item>
                                        <el-form-item label="真实名字" prop="realName">
              <el-input v-model="editForm.realName"/>
            </el-form-item>
                                        <el-form-item label="身份证" prop="cardNumber">
              <el-input v-model="editForm.cardNumber"/>
            </el-form-item>
                                        <el-form-item label="" prop="phone">
              <el-input v-model="editForm.phone"/>
            </el-form-item>
                                        <el-form-item label="性别" prop="gender">
              <el-input v-model="editForm.gender"/>
            </el-form-item>
                                        <el-form-item label="创建时间" prop="createTime">
              <el-input v-model="editForm.createTime"/>
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