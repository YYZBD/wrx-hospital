import {createApp} from 'vue'
import './style.css'
import App from './App.vue'
import router from "./router/index.js"
import {createPinia} from 'pinia'
import piniaPluginPersistedstate from 'pinia-plugin-persistedstate'

import ElementPlus from 'element-plus'
import zhCn from 'element-plus/es/locale/lang/zh-cn'
import 'element-plus/dist/index.css'
import 'dayjs/locale/zh-cn'
import {dayjs} from "element-plus";

// 用于mock假数据
// import initMock from './mock/index';
// initMock()

import './permission'

const pinia = createPinia()
pinia.use(piniaPluginPersistedstate)

const app = createApp(App)

app.config.globalProperties.$fD = function (dateStr) {
	return dayjs(dateStr).format('YYYY-MM-DD HH:mm:ss')
}

app
		.use(router)
		.use(pinia)
		.use(ElementPlus, {locale: zhCn}) // fixbug: 国际化选择中文
		.mount('#app')
