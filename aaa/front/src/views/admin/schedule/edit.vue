<script setup>
  import {ref, reactive} from 'vue'
  import {Info, Save} from '^/admin/schedule'
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
                          doctorId: [{required: true, message: '此项是必填项', trigger: 'blur'}],
                          departmentId: [{required: true, message: '此项是必填项', trigger: 'blur'}],
                          scheduleDate: [{required: true, message: '此项是必填项', trigger: 'blur'}],
                          moment: [{required: true, message: '此项是必填项', trigger: 'blur'}],
                          quantity: [{required: true, message: '此项是必填项', trigger: 'blur'}],
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
                                        <el-form-item label="医生ID" prop="doctorId">
              <el-input v-model="editForm.doctorId"/>
            </el-form-item>
                                        <el-form-item label="科室ID" prop="departmentId">
              <el-input v-model="editForm.departmentId"/>
            </el-form-item>
                                        <el-form-item label="排班日期" prop="scheduleDate">
              <el-input v-model="editForm.scheduleDate"/>
            </el-form-item>
                                        <el-form-item label="上午或下午" prop="moment">
              <el-input v-model="editForm.moment"/>
            </el-form-item>
                                        <el-form-item label="就诊数量" prop="quantity">
              <el-input v-model="editForm.quantity"/>
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