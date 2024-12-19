<script setup>

import {ListDepartments, ListDoctor, GetDoctor} from "^/app/booking.js";
import {reactive, ref} from "vue";
import {dayjs, ElMessage} from "element-plus";


const state = reactive({
  departments: [],
  doctors: [],
  // 表单数据进行传参，将选择的科室和预约时间传到后端
  form: {
    scheduleDate: null,
    departmentId: null
  },
  bookingDialog: false,
  bookingForm: {
    medicalCardId: '',
    scheduleId: ''
  },
  bookingDoctor: {},
  medicalCards: [],
  schedules: [],
})

// 获取所有可预约医生
const loadDoctors = () => {
  new ListDoctor(state.form).then(res => {
    state.doctors = res.data
  })
}

// 获取所有科室
const init = () => {
  new ListDepartments().then(res => {
    state.departments = res.data
  })
  loadDoctors()
}
init()

// 日期选择框禁用过去时间
const disabledDate = (time) => {
  return dayjs().subtract(1, 'day').isAfter(time)
  // return time.getTime() < Date.now()
}

// 点击搜索，查询所有可预约医生信息
const onSubmit = () => {
  loadDoctors()
}

// 点击预约，展示该医生信息、就诊卡信息、可预约时间信息
const bookingFn = (doctor) => {
  state.bookingDialog = true
  state.bookingDoctor = doctor
  new GetDoctor(doctor.id).then(res => {
    state.schedules = res.data.schedules
    state.medicalCards = res.data.medicalCards
  })
}

// 表单验证规则
const rules = {
  medicalCardId: [
    {required: true, message: '请选择就诊卡', trigger: 'blur'}
  ],
  scheduleId: [
    {required: true, message: '请选择预约时间', trigger: 'blur'}
  ]
}

// 获取当前表单实例对象
const resetBookingForm = ref()

// 点击确定提交预约
const submitBookingForm = () => {
  resetBookingForm.value.validate((valid) => {
    if (valid) {
      // console.log('预约成功')
      console.log('预约成功')
      ElMessage.success('预约成功')
      state.bookingDialog = false
    }
  })
}


</script>

<template>

  <el-card shadow="never">
    <h3>请选择</h3>

    <el-form :inline="true" :model="state.form" class="demo-form-inline">

      <!--科室下拉选择框，样式：一级科室不能选择，一级科室所属的二级科室可选择-->
      <el-form-item label="科室" style="margin-top: 20px">
        <el-select
            v-model="state.form.departmentId"
            placeholder="请选择科室"
            clearable
            style="width: 200px;">
          <el-option-group
              v-for="group in state.departments"
              :key="group.id"
              :label="group.name">
            <el-option
                v-for="item in group.children"
                :key="item.id"
                :label="item.name"
                :value="item.id">
            </el-option>
          </el-option-group>
        </el-select>
      </el-form-item>

      <!--预约日期选择框-->
      <el-form-item label="预约时间" style="margin-top: 20px">
        <el-date-picker
            v-model="state.form.scheduleDate"
            type="date"
            placeholder="请选择预约日期"
            clearable
            :disabled-date="disabledDate"
            value-format="YYYY-MM-DD"
            style="width: 200px;">
        </el-date-picker>
      </el-form-item>

      <!--预约按钮，绑定查询所有可预约医生信息-->
      <el-form-item style="margin-top: 20px">
        <el-button type="primary" @click="onSubmit">搜索</el-button>
      </el-form-item>
    </el-form>

  </el-card>

  <!--展示可预约医生信息-->
  <el-row :gutter="10">
    <el-col :span="8" v-for="doctor in state.doctors" style="margin-top: 10px">
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

            <!--预约按钮-->
            <el-button @click="bookingFn(doctor)">预约挂号</el-button>
          </el-space>
        </div>
      </el-card>
    </el-col>
  </el-row>

  <!--没有检索到任何医生提示信息-->
  <el-empty v-if="state.doctors.length === 0" description="当前时间为休息时间，没有可预约医生"/>


  <el-dialog title="请确认挂号信息" v-model="state.bookingDialog" width="500">
    <el-form :model="state.bookingForm" label-width="auto" :rules="rules" ref="bookingFormRef">
      <el-form-item label="预约医生：">
        <strong>{{state.bookingDoctor.name}} - {{state.bookingDoctor.position}}</strong>
      </el-form-item>
      <el-form-item label="就诊卡：" prop="medicalCardId">
        <el-radio-group v-model="state.bookingForm.medicalCardId">
          <el-radio  border v-for="item in state.medicalCards" :value="item.id">{{item.realName}} {{item.sn}}</el-radio>
        </el-radio-group>
        <el-link v-if="state.medicalCards.length === 0">请先添加就诊卡</el-link>
      </el-form-item>
      <el-form-item label="预约时间：" prop="scheduleId">
        <el-radio-group v-model="state.bookingForm.scheduleId">
          <el-radio v-for="item in state.schedules" :value="item.id">{{item.scheduleDate}} {{item.moment}} {{'剩余'+ item.quantity +'个号'}}</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="挂号费：">
        <p style="color: red">￥{{state.bookingDoctor.bookingFee}}元</p>
      </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button @click="state.bookingDialog = false">取 消</el-button>
      <el-button type="primary" @click="submitBookingForm">确 定</el-button>
    </div>
  </el-dialog>

</template>

<style scoped lang="scss">

</style>