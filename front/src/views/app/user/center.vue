<script setup>
import {ref, reactive} from 'vue'
import {useUserStore} from "@/stores/user.js"

const userStore = useUserStore()
</script>

<template>
  <el-row :gutter="16">
    <el-col :span="6">
      <el-card shadow="never" class="user-info">
        <el-avatar :size="80" :src="userStore.userInfo.avatar"/>
        <div class="username">{{ userStore.userInfo.username }}</div>
      </el-card>
      <el-card shadow="never" style="margin-top: 16px;">
        <template #header>个人中心</template>

        <div class="side-menu">
          <a @click="$router.push('/user/profile')">个人信息</a>
          <a @click="$router.push('/user/updatePwd')">修改密码</a>
          <a href="">个人钱包</a>
        </div>
      </el-card>
    </el-col>
    <el-col :span="18">
      <router-view/>

    </el-col>
  </el-row>
</template>

<style lang="scss" scoped>
.user-info {
  text-align: center;

  .username {
    font-weight: bold;
    font-size: 16px;
    margin-top: 12px;
  }
}

.side-menu {
  display: grid;
  grid-gap: 16px;
}

</style>