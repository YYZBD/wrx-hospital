<script setup>
import {ref, reactive, computed} from 'vue'
import {useAdminStore} from "@/stores/admin.js";
import {HomeFilled, Menu} from "@element-plus/icons-vue"
import {useRoute} from 'vue-router'

const adminStore = useAdminStore()
const route = useRoute()

const defaultActive = computed(() => route.path)

</script>

<template>
  <el-container>
    <el-aside width="200px">
      <el-menu
          active-text-color="#ffd04b"
          background-color="#545c64"
          :default-active="defaultActive"
          text-color="#fff"
          router
      >
        <el-menu-item index="/admin">
          <el-icon>
            <home-filled/>
          </el-icon>
          <span>首页</span>
        </el-menu-item>
        <el-sub-menu index="service">
          <template #title>
            <el-icon>
              <Menu/>
            </el-icon>
            <span>业务管理</span>
          </template>
          <el-menu-item-group>

          </el-menu-item-group>
        </el-sub-menu>
        <el-sub-menu index="system">
          <template #title>
            <el-icon>
              <Menu/>
            </el-icon>
            <span>系统管理</span>
          </template>
          <el-menu-item-group>
            <el-menu-item index="/admin/user">用户管理</el-menu-item>
            <el-menu-item index="/admin/admin">管理员管理</el-menu-item>
          </el-menu-item-group>
        </el-sub-menu>
      </el-menu>
    </el-aside>
    <el-container>
      <el-header>

        <h3>码客在线后台管理系统</h3>

        <el-dropdown>
          <el-avatar :size="40" :src="adminStore.adminInfo.avatar"></el-avatar>
          <template #dropdown>
            <el-dropdown-menu>
              <el-dropdown-item @click="$router.push('/admin/updatePwd')">修改密码</el-dropdown-item>
              <el-dropdown-item @click="adminStore.logout()">退出</el-dropdown-item>
            </el-dropdown-menu>
          </template>
        </el-dropdown>

      </el-header>
      <el-main>
        <router-view/>
      </el-main>
    </el-container>
  </el-container>

</template>

<style lang="scss" scoped>

.el-container {
  background-color: #ffffff;

  .el-header {
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding: 0 16px;
    border-bottom: 1px solid #eee;
  }

  .el-aside {
    height: 100vh;
  }
}

.el-menu {
  height: 100%;
}

</style>