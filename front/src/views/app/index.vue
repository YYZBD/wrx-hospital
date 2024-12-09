<script setup>
import {ref,reactive} from "vue";
import {GetHome} from "^/app/index.js";
import {ListDoctor} from "^/app/doctor.js";

// 定义顶部分隔栏内容
const options = [
  { label: '手术科室', value: 0},
  { label: '非手术科室', value: 1},
  { label: '医技科室', value: 2},
  { label: '中心及其他', value: 3},
]
// 定义分隔栏默认选项
const defaultSegmented = ref(0)
// 状态管理
const state = reactive({
  //科室类型
  departments: [{children:[0]}],
  //子科室信息
  department: {},
  //医生信息
  doctors: [],
  // 默认值
  defaultSegmented: 0,
  // 所属科室的子科室
  departmentChildren:[]
})
// 初始化数据，获取首页数据
const init = () => {
  GetHome().then(res => {
    // 获取科室信息
    state.departments = res.data.departments
    // 获取子科室信息
    state.department = res.data.department
    // 获取医生信息
    state.doctors = res.data.doctors
    // 获取默认科室的子科室信息
    state.departmentChildren = state.departments[0].children
  })
}
init()
// 随options定义的value来切换科室
const changeSegmented = (val) => {
  // 根据value获取所属科室的子科室信息
  state.departmentChildren = state.departments[val].children
  // 根据科室改变科室介绍内容
  state.department = state.departments[val]
  // 为点击事件绑定医生信息
  getDoctors(state.department.id)
}

const changeDepartment = (item) => {
  // 获取医生信息
  state.department = item
  // 为点击事件绑定医生信息
  getDoctors(state.department.id)
}
// 获取医生相关信息
const getDoctors = (departmentId) => {
  ListDoctor({departmentId: departmentId}).then(res => {
    state.doctors = res.data
  })
}
</script>
<!--项目首页展示内容-->
<template>
  <!--页面内容显示宽度为1200px，使用绝对定位解决图片限制宽度问题-->
  <div style="position: absolute; top: 60px; left: 0; width: 100%" >
    <img src="/banner/首页1.jpg"  alt="" style="height: 650px; width: 100%;">
  </div>
  <!--查看element官网资料，设置页面格式-->
  <el-card shadow="never" style="margin-top: 650px">
    <template #header>
      <h2>医院简介</h2>
    </template>
    <!--使用el-row和el-col进行布局-->
    <el-row :gutter="20">
      <el-col :span="10">
        <el-image src="/banner/首页医院图.png" style="width: 455px;height: 350px" ></el-image>
      </el-col>
      <el-col :span="14">
        <div style="line-height: 20px">
          <h3>西安市第四人民医院</h3>
          <p style="margin-top: 10px">1916年，医院更名为“西安基督教广仁医院”，并创办了陕西省第一所护士学校—广仁护校。1952年10月，“广仁医院”由西安市人民政府接管，更名为“西安市第四人民医院”。1960年，更名为“西安市第四医院”。
            1997年3月，被国家卫生部授予“三级甲等综合医院”称号。2019年11月，医院正式更名为“西安市人民医院（西安市第四医院）”，为西北大学附属人民医院。医院现有大差市和航天城两个院区，总占地面积204.7亩，
            编制床位1800张。医院历经百余年历史浮沉、沧桑跌宕，从一间诊所到享誉西北地区的三级甲等医院，虽院址数易，院名累更，然“广济苍生 仁爱天下”的院训精神不变，救死扶伤、以患者为先的初心不变，
            勇担责任，推动医学发展的使命不变。</p>
          <p style="margin-top: 10px">联系电话：029-61199088(咨询电话)</p>
          <p style="margin-top: 10px">029-87480721(门诊部)</p>
          <p style="margin-top: 10px">029-87480709(医务科)</p>
          <p style="margin-top: 10px">029-87420006(总值班)</p>
          <p style="margin-top: 10px">门诊时间：上午：8:00-12:00（挂号时间7:30-11:00） 下午：13:30-17:00（挂号时间13:00-16:00）</p>
          <p style="margin-top: 10px">地址：陕西省西安市雁塔区西兴路与西兴南大街交叉口南</p>
        </div>
      </el-col>
    </el-row>
  </el-card>

  <!--科室与医生信息展示-->
  <el-card shadow="never" style="margin-top: 20px;">
    <template #header><h2>科室与专家</h2></template>
    <!--科室显示内容及绑定点击时间-->
    <el-segmented v-model="state.defaultSegmented" :options="options" @change="changeSegmented"/>
    <!--科室与医生信息展示-->
    <el-row :gutter="10" >
      <!--显示所属科室的子科室-->
      <el-col :span="6" v-for="item in state.departmentChildren" style="margin-top: 20px">
        <!--鼠标放上去变色加下划线显示，并添加随子科室选择而变化的科室介绍-->
        <el-link @click="changeDepartment(item)">{{item.name}}</el-link>
      </el-col>
    </el-row>

    <h3>科室介绍</h3>
    <!--展示科室介绍内容-->
    <p style="line-height: 25px" >{{state.department.introduce}}</p>

    <h3>医生团队</h3>
    <el-row :gutter="10">
      <el-col :span="8" v-for="doctor in state.doctors" style="margin-bottom: 10px">
        <el-card shadow="never">
          <div style="display: flex; gap: 20px">
            <!--显示医生图片信息-->
            <img :src="doctor.avatar" style="width: 72px; height: 100%;" alt="">
            <el-space direction="vertical" alignment="normal">
              <el-space>
                <!--医生姓名和职称-->
                <strong>{{doctor.name}}</strong>
                <el-text>{{doctor.position}}</el-text>
              </el-space>
              <!--医生简介-->
              <p>{{doctor.introduce}}</p>
            </el-space>
          </div>
        </el-card>
      </el-col>
    </el-row>

  </el-card>



</template>

<style lang="scss" scoped>

.el-card h3{
  margin: 20px 0;
}

</style>