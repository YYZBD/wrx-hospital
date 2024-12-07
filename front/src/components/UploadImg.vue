<script setup>
import {ref, reactive, watch, defineEmits} from 'vue'
import {Plus} from "@element-plus/icons-vue"

import {useUserStore} from "@/stores/user.js"
import {useAdminStore} from "@/stores/admin.js"
import {ElMessage} from "element-plus";

const userStore = useUserStore()
const adminStore = useAdminStore()

const props = defineProps({
  images: {type: String, default: ''},
  limit: {type: Number, default: 9}
})

const data = reactive({
  action: import.meta.env.VITE_APP_BASE_URL + '/common/uploadImg',
  headers: {
    // fixbug，为空字符串时候，设置为null
    userToken: userStore.userToken || null,
    adminToken: adminStore.adminToken || null,
  },
  pics: [],
  dialogImageUrl: '',
  dialogVisible: false
})

const flag = watch(() => props.images, (newImages, oldImages) => {
  if (newImages) {
    data.pics = newImages.split(";").map(e => ({url: e}))
  } else {
    // fix-bug
    data.pics = []
  }

  // 结束监听，只需要初始化时候监听一次即可
  // fix-bug，不能结束监听
  // flag()
})

const emit = defineEmits(['handleUpload', 'update:images'])

const uploadRef = ref()
const handleSuccess = (response, uploadFile, uploadFiles) => {
  if (response.code === 200) {
    uploadFile.url = response.data;
    callback()
  } else {
    ElMessage.error(response.msg)
    uploadRef.value.handleRemove(uploadFile)
  }
}
const handleRemove = (uploadFile, uploadFiles) => {
  callback()
}

const handlePreview = (uploadFile) => {
  data.dialogImageUrl = uploadFile.url
  data.dialogVisible = true
}
const handleExceed = () => {
  ElMessage.error("超出数量限制")
}
const callback = () => {
  const newImages = data.pics.map(e => e.url).join(';')
  emit('handleUpload', newImages)
  emit('update:images', newImages)
}


</script>

<template>

  <el-upload
      ref="uploadRef"
      v-model:file-list="data.pics"
      :action="data.action"
      :headers="data.headers"
      name="image"
      list-type="picture-card"
      :on-preview="handlePreview"
      :on-remove="handleRemove"
      :on-success="handleSuccess"
      :on-exceed="handleExceed"
      :limit="props.limit"
  >
    <el-icon>
      <Plus/>
    </el-icon>
  </el-upload>

  <el-dialog v-model="data.dialogVisible">
    <img w-full :src="data.dialogImageUrl" alt="Preview Image"/>
  </el-dialog>

</template>

<style lang="scss" scoped>

</style>