<script setup>
  import {ref, reactive} from 'vue'
  import {Info, Save} from '^/admin/booking'
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
                          departmentId: [{required: true, message: '此项是必填项', trigger: 'blur'}],
                          medicalCardId: [{required: true, message: '此项是必填项', trigger: 'blur'}],
                                          medicalCardName: [{required: true, message: '此项是必填项', trigger: 'blur'}],
                          scheduleId: [{required: true, message: '此项是必填项', trigger: 'blur'}],
                          scheduleDate: [{required: true, message: '此项是必填项', trigger: 'blur'}],
                          scheduleMoment: [{required: true, message: '此项是必填项', trigger: 'blur'}],
                          bookingFee: [{required: true, message: '此项是必填项', trigger: 'blur'}],
                          status: [{required: true, message: '此项是必填项', trigger: 'blur'}],
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
                                        <el-form-item label="预约编号" prop="sn">
              <el-input v-model="editForm.sn"/>
            </el-form-item>
                                        <el-form-item label="用户ID" prop="userId">
              <el-input v-model="editForm.userId"/>
            </el-form-item>
                                        <el-form-item label="医生ID" prop="doctorId">
              <el-input v-model="editForm.doctorId"/>
            </el-form-item>
                                        <el-form-item label="科室ID" prop="departmentId">
              <el-input v-model="editForm.departmentId"/>
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
                                        <el-form-item label="排班ID" prop="scheduleId">
              <el-input v-model="editForm.scheduleId"/>
            </el-form-item>
                                        <el-form-item label="预约时间" prop="scheduleDate">
              <el-input v-model="editForm.scheduleDate"/>
            </el-form-item>
                                        <el-form-item label="上午或下午" prop="scheduleMoment">
              <el-input v-model="editForm.scheduleMoment"/>
            </el-form-item>
                                        <el-form-item label="挂号费" prop="bookingFee">
              <el-input v-model="editForm.bookingFee"/>
            </el-form-item>
                                        <el-form-item label="预约状态" prop="status">
              <el-input v-model="editForm.status"/>
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