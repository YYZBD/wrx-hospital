import router from "@/router/index.js"
import {useAdminStore} from "@/stores/admin.js";
import {useUserStore} from "@/stores/user.js";

const whiteList = ['AdminLogin']

router.beforeEach(async (to, from, next) => {

	// 白名单
	if (whiteList.includes(to.name)) {
		next()
		return // fix-bug，加上return
	}

	// auth表示登录才能访问
	if (to.matched.some((record) => record.meta.auth)) {
		const userStore = useUserStore()
		if (!userStore.userToken) {
			next("/login")
			return
		}
	}

	if (!to.path.startsWith('/admin')) {
		next()
		return
	}

	const adminStore = useAdminStore()

	if (!adminStore.adminToken) {
		next('/admin/login')
		return
	}

	next()
})

// fix-bug: 解决新页面不置顶问题
router.afterEach((to, from, next) => {
	window.scrollTo(0, 0)
})