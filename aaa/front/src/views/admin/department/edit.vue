<script setup>
  import {ref, reactive} from 'vue'
  import {Info, Save} from '^/admin/department'
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
                          parentId: [{required: true, message: '此项是必填项', trigger: 'blur'}],
                          name: [{required: true, message: '此项是必填项', trigger: 'blur'}],
                          introduce: [{required: true, message: '此项是必填项', trigger: 'blur'}],
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
                                        <el-form-item label="上级科室" prop="parentId">
              <el-input v-model="editForm.parentId"/>
            </el-form-item>
                                        <el-form-item label="科室名称" prop="name">
              <el-input v-model="editForm.name"/>
            </el-form-item>
                                        <el-form-item label="科室介绍" prop="introduce">
              <el-input v-model="editForm.introduce"/>
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