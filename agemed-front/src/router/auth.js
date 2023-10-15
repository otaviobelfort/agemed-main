export default [
  {
    path: "/login",
    name: "login",
    component: () =>
      import(/* webpackChunkName: "emissao" */ "@/views/Login.vue"),
    meta: {
      layout: "blank",
      isRedirection: true,
    },
  },
];
