<script setup>
import {ref, reactive, computed} from 'vue'
import {useUserStore} from "@/stores/user.js"
import {useRoute, useRouter} from 'vue-router'

const userStore = useUserStore()
const route = useRoute()
const router = useRouter()

const defaultActive = computed(() => route.path)

const logout = () => {
  userStore.logout()
  router.push('/')
}
</script>

<template>
  <el-container>
    <el-header>
      <el-menu
          :default-active="defaultActive"
          class="el-menu-demo"
          mode="horizontal"
          :ellipsis="false"
          router
      >
        <h3 class="title">码客在线</h3>
        <el-menu-item index="/">首页</el-menu-item>
        <el-menu-item index="/menu">菜单</el-menu-item>

        <div style="flex-grow: 1"/>

        <template v-if="userStore.userToken">
          <el-sub-menu index="2">
            <template #title>
              <el-avatar :size="32" :src="userStore.userInfo.avatar"/>
              <span style="padding-left: 10px;">{{ userStore.userInfo.username }}</span>
            </template>
            <el-menu-item index="/user/profile">个人中心</el-menu-item>
            <el-menu-item index="/admin">后台管理</el-menu-item>
            <!--fix-bub: 退出后跳转到首页-->
            <el-menu-item @click="logout">退出</el-menu-item>
          </el-sub-menu>
        </template>
        <template v-else>
          <el-menu-item index="/login">登录</el-menu-item>
          <el-menu-item index="/register">注册</el-menu-item>
        </template>
      </el-menu>
    </el-header>
    <el-main style="max-width: 996px; margin: 0 auto; width: 100%;">
      <router-view/>
    </el-main>
  </el-container>
</template>

<style lang="scss" scoped>
.el-container {
  height: 100%;
}

.el-header {
  padding: 0;
  background-color: #ffffff;
  border-bottom: 1px solid var(--el-menu-border-color);

  .el-menu {
    max-width: 1280px;
    margin: 0 auto;

    .title {
      line-height: 60px;
      color: #31445b;
      padding: 0 20px;
    }
  }
}


</style>