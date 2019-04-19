import Vue from 'vue'
import Router from 'vue-router'
import Login from './views/Login.vue'
import Home from './views/Home.vue'
import familyHome from './views/familyHome.vue'
import main from './views/main.vue'
import familyMain from './views/familyMain.vue'
// import roles from './views/roles.vue'
import ratepre from './views/ratepre.vue'
import rateprefamily from './views/rateprefamily.vue'
// import rights from './views/rights.vue'
import annex from './views/annex.vue'
import text from './views/text.vue'
import textTwo from './views/textTwo.vue'
import textMain from './views/textMain.vue'
import step from './views/step.vue'
import stepfamily from './views/stepfamily.vue'
import warning from './views/warning.vue'
import position from './views/position.vue'
import positionfamily from './views/positionfamily.vue'
import life from './views/life.vue'
import watch from './views/watch.vue'
import person from './views/person.vue'
import customer from './views/customer.vue'
import textOpt from './views/textOpt.vue'
import family from './views/family.vue'
import me from './views/me.vue'
import saidOperation from './views/saidOperation.vue'
Vue.use(Router)

const router = new Router({
  routes: [
	  {path: '/',
	    redirect: '/login'
	  },
    {path: '/login',
      component: Login
    },
	{path: '/familyHome',
	  component: familyHome,
	  redirect: '/familyMain',
	  children: [{
				  path: '/familyMain',
				  name: '家属首页',
				  component: familyMain
				},
				{
					path: '/rateprefamily',
					name: '心率血压',
					component: rateprefamily
				},
				{
					path: '/stepfamily',
					name: '睡眠步数',
					component: stepfamily
				},
				{
					path: '/positionfamily',
					name: '位置信息',
					component: positionfamily
				},
				{
					path: '/me',
					name: '个人中心',
					component: me
				}]
	},
    { path: '/home',
      component: Home,
      redirect: '/main',
      children: [
				{
				  path: '/main',
				  name: '首页',
				  component: main
				},
				{
					path: '/ratepre',
					name: '心率血压',
					component: ratepre
				},
				{
					path: '/step',
					name: '睡眠步数',
					component: step
				},
				{
					path: '/warning',
					name: '摔倒报警',
					component: warning
				},
				{
					path: '/position',
					name: '位置信息',
					component: position
				},
				{
					path: '/life',
					name: '生活信息',
					component: life
				},
				{
					path: '/watch',
					name: '手表信息',
					component: watch
				},
				{
					path: '/person',
					name: '人员信息',
					component: person
				},
				{
					path: '/customer',
					name: '监护人信息',
					component: customer
				},
				{
					path: '/text',
					name: '公告动态编辑',
					component: text
				},
				{
					path: '/textTwo',
					name: '公告动态',
					component: textTwo
				},
				{
					path: '/textMain',
					name: '公告内容',
					component: textMain
				},
				{
					path: '/textOpt',
					name: '公告管理',
					component: textOpt
				},
				{
				  path: '/annex',
				  name: '附件管理',
				  component: annex
				},
				{
				  path: '/family',
				  name: '家属管理',
				  component: family
				},
				{
				  path: '/saidOperation',
				  name: '评论管理',
				  component: saidOperation
				}
      ]
    }
  ]
})

router.beforeEach((to, from, next) => {
	console.log('进入')
  if (to.path === '/login') {
    // 是登录页面，直接进入到 要访问的登录页面
    return next()
  }

  // 不是登录页面
  const token = localStorage.getItem('token')
  console.log("token",token)
  if (token) {
	  console.log('登陆过')
    // 登录过：
    next()
  } else {
	  console.log('未登录过')
    // 没有登录过
    next('/login')
  }
})

export default router
