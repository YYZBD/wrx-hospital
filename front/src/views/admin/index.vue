<script setup>
import {ref, reactive} from 'vue'
import {useAdminStore} from "@/stores/admin.js";

const adminStore = useAdminStore()

</script>

<template>
  <div style="font-size: 18px; padding: 24px 0;">欢迎回来：{{ adminStore.adminInfo.username }}</div>

  <el-card shadow="never">
    <el-row>
      <el-col :span="6">
        <el-statistic title="注册用户" :value="268500"/>
      </el-col>
      <el-col :span="6">
        <el-statistic title="待审核申请" :value="78"/>
      </el-col>
      <el-col :span="6">
        <el-statistic title="待救助" :value="433"/>
      </el-col>
      <el-col :span="6">
        <el-statistic title="Daily active users" :value="268500"/>
      </el-col>
    </el-row>
  </el-card>
</template>

<style lang="scss" scoped>

</style>