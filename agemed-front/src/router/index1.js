// Composables
import { createRouter, createWebHistory } from 'vue-router'
import TokenService from "@/service/TokenService";
// import { isUserLoggedIn, canNavigate } from '@/utils/loggeding'

const routes = [
  {
    path: '/',
    children: [
      {
        path: '',
        name: '/login',
        component: () => import('@/views/Login.vue'),

        meta: {
          layout: 'default',
          action: 'read',
          subject: 'Auth',
          authorize: ["USUARIOS"]
        }
      }
    ]
    },

  {
    path: '/agemed',
    component: () => import('@/layouts/default/Default.vue'),
    children: [
      {
        path: '/',
        name: 'home',
        component: () => import(/* webpackChunkName: "solicitacoes" */ '@/views/Home.vue'),

        meta: {
          layout: 'default',
          action: 'read',
          subject: 'Auth',
          authorize: ["USUARIOS"]
        }
      },
      {
        path: '/agemed/solicitacoes',
        name: 'solicitacoes',
        component: () => import(/* webpackChunkName: "solicitacoes" */ '@/views/Solicitacoes.vue'),
        meta: { authorize: ["USUARIOS"] },
      },
      {
        path: '/agemed/medico',
        name: 'medico',
        component: () => import(/* webpackChunkName: "solicitacoes" */ '@/views/Cadastro.vue'),
        meta: { authorize: ["USUARIOS"] },
      },
      {
        path: '/agemed/emissao',
        name: 'emissao',
        component: () => import(/* webpackChunkName: "emissao" */ '@/views/Emissao.vue'),
        meta: { authorize: ["USUARIOS"] },
      },
      {
        path: 'rg/nacional',
        name: 'rg-nacional',
        component: () => import(/* webpackChunkName: "emissao" */ '@/views/nacional/IdentidadeNacional.vue'),
        meta: { authorize: ["USUARIOS"] },
      },
      {
        path: 'rg/cadastro',
        name: 'rg-cadastro',
        component: () => import(/* webpackChunkName: "emissao" */ '@/views/estadual/IdentidadeEstadual.vue'),
        meta: { authorize: ["USUARIOS"] },
      },
      {
        path: '/agemed/home',
        name: 'home',
        component: () => import(/* webpackChunkName: "emissao" */ '@/views/Home.vue'),
      },
      {
        path: "/agemed/posto",
        name: "posto",
        component: () => import("@/views/posto/PostoView.vue"),
      }
    ],
  },
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
})


router.afterEach(() => {
  const appLoading = document.getElementById('loading-bg')
  if (appLoading) {
    appLoading.style.display = 'none'
  }
})

export default router
