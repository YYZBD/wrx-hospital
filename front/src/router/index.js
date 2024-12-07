import {createRouter, createWebHashHistory} from "vue-router"

const routes = [
	{
		path: '/adminLayout',
		name: "AdminLayout",
		component: () => import('~/AdminLayout.vue'),
		children: [
			{
				path: '/admin',
				name: "AdminIndex",
				component: () => import('../views/admin/index.vue')
			},

			{
				path: '/admin/user',
				name: "AdminUser",
				component: () => import('../views/admin/user/index.vue')
			},
			{
				path: '/admin/admin',
				name: "AdminAdmin",
				component: () => import('../views/admin/admin/index.vue')
			},
			{
				path: '/admin/updatePwd',
				name: "AdminUpdatePwd",
				component: () => import('../views/admin/admin/updatePwd.vue')
			},
		]
	},

	{
		path: '/admin/login',
		name: "AdminLogin",
		component: () => import('../views/admin/login.vue')
	},


	{
		path: '/appLayout',
		name: "AppLayout",
		component: () => import('~/AppLayout.vue'),
		children: [
			{
				path: '/login',
				name: "Login",
				component: () => import('../views/app/login.vue')
			},
			{
				path: '/register',
				name: "Register",
				component: () => import('../views/app/register.vue')
			},
			{
				path: '/',
				name: "Index",
				component: () => import('../views/app/index.vue')
			},
			{
				path: '/user/center',
				name: "UserCenter",
				component: () => import('../views/app/user/center.vue'),
				meta: { auth: true },
				children: [
					{
						path: '/user/profile',
						name: "UserProfile",
						component: () => import('../views/app/user/profile.vue')
					},
					{
						path: '/user/updatePwd',
						name: "UserUpdatePwd",
						component: () => import('../views/app/user/updatePwd.vue')
					},
				]
			},

		]
	},

]

const router = createRouter({
	history: createWebHashHistory(),
	routes
})
export default router