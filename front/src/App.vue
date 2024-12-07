<script setup>
import {ref} from 'vue'

// 注意这里，我是为了线上演示的弹窗提示，可以自行去掉
const show = ref(import.meta.env.MODE === 'production')
</script>

<template>
  <router-view/>

  <!--可以去掉这个弹窗-->
  <el-dialog
      v-model="show"
      :show-close="false"
      :close-on-click-modal="false"
      :close-on-press-escape="false"
      width="500"
  >
    <h4 style="font-size: 15px;">请注意：因为部署压力，线上演示的数据都是mock假数据，并不经过后台处理，但不影响真实项目源码功能！</h4>
    <br>
    <h4 style="font-size: 15px; color: red;">获取项目视频、文档、源码：
      <a target="_blank" href="https://www.markerhub.com/course/intro/23" style="text-decoration: underline;">https://www.markerhub.com/course/intro/23</a>
    </h4>
    <template #footer>
      <div class="dialog-footer">
        <el-button @click="show = false">关闭</el-button>
      </div>
    </template>
  </el-dialog>
</template>

<style scoped>

</style>
