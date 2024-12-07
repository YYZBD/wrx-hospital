<script setup>
  import {ref, reactive} from 'vue'
  import {Info, Save} from '^/admin/medicalRecord'
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
                          sn: [{required: true, message: '此项是必填项', trigger: 'blur'}],
                          userId: [{required: true, message: '此项是必填项', trigger: 'blur'}],
                          doctorId: [{required: true, message: '此项是必填项', trigger: 'blur'}],
                          bookingId: [{required: true, message: '此项是必填项', trigger: 'blur'}],
                          bookingSn: [{required: true, message: '此项是必填项', trigger: 'blur'}],
                          medicalCardId: [{required: true, message: '此项是必填项', trigger: 'blur'}],
                          medicalCardSn: [{required: true, message: '此项是必填项', trigger: 'blur'}],
                          medicalCardName: [{required: true, message: '此项是必填项', trigger: 'blur'}],
                          diagnosis: [{required: true, message: '此项是必填项', trigger: 'blur'}],
                          treatment: [{required: true, message: '此项是必填项', trigger: 'blur'}],
                          recordTime: [{required: true, message: '此项是必填项', trigger: 'blur'}],
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
                                        <el-form-item label="就诊编号" prop="sn">
              <el-input v-model="editForm.sn"/>
            </el-form-item>
                                        <el-form-item label="用户ID" prop="userId">
              <el-input v-model="editForm.userId"/>
            </el-form-item>
                                        <el-form-item label="医生ID" prop="doctorId">
              <el-input v-model="editForm.doctorId"/>
            </el-form-item>
                                        <el-form-item label="预约ID" prop="bookingId">
              <el-input v-model="editForm.bookingId"/>
            </el-form-item>
                                        <el-form-item label="" prop="bookingSn">
              <el-input v-model="editForm.bookingSn"/>
            </el-form-item>
                                        <el-form-item label="就诊卡ID" prop="medicalCardId">
              <el-input v-model="editForm.medicalCardId"/>
            </el-form-item>
                                        <el-form-item label="就诊卡号" prop="medicalCardSn">
              <el-input v-model="editForm.medicalCardSn"/>
            </el-form-item>
                                        <el-form-item label="就诊人名称" prop="medicalCardName">
              <el-input v-model="editForm.medicalCardName"/>
            </el-form-item>
                                        <el-form-item label="诊断结果" prop="diagnosis">
              <el-input v-model="editForm.diagnosis"/>
            </el-form-item>
                                        <el-form-item label="治疗方案" prop="treatment">
              <el-input v-model="editForm.treatment"/>
            </el-form-item>
                                        <el-form-item label="就诊日期" prop="recordTime">
              <el-input v-model="editForm.recordTime"/>
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