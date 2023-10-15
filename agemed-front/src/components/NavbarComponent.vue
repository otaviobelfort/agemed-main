<script setup>
import { ref } from "vue";
import { useTheme } from "vuetify";
import navigations from "@/navigations/index";
import router from "@/router/index";
import TokenService from "@/service/TokenService";
let access_token = null;

if (localStorage.getItem("access_token")) {
  access_token = JSON.parse(localStorage.getItem("access_token"));
}
let tokenResolvido = null;
if (access_token) {
  tokenResolvido = ref(TokenService.parseJwt(access_token));
}

console.log("tokenResolvido:", tokenResolvido);

const drawer = ref(false);
const group = ref(null);
const theme = useTheme();
const toggleTheme = () => {
  theme.global.name.value = theme.global.current.value.dark
    ? "customLight"
    : "customDark";
  localStorage.setItem("theme", JSON.stringify(theme.global.name.value));
};

const value = ref(null);

const items = [{ title: "Perfil" }, { title: "Sair" }];

const nextPage = (path) => {
  router.replace(path);
};
</script>

<template>
  <v-layout class="rg-navbar">
    <v-app-bar prominent color="primary">
      <v-toolbar-title style="max-width: 110px">
        <v-avatar size="80" rounded>
          <v-img src="@/assets/image/brasao-ma.png"></v-img>
        </v-avatar>
      </v-toolbar-title>
      <v-spacer style="padding-left: 10%; height: 64px">
        <v-layout class="overflow-visible">
          <v-bottom-navigation
            v-model="value"
            color="blue-lighten-1"
            bg-color="transparent"
            style="height: 64px; top: 15px"
          >
            <v-btn
              v-for="(nav, index) in navigations"
              :key="index"
              @click="nextPage(nav.path)"
              :class="
                nav.path === $route.path
                  ? 'v-btn--active text-blue-lighten-1'
                  : ''
              "
            >
              <div class="nav-min-width">
                <v-icon class="mr-1" size="20">{{ nav.icon }}</v-icon>
                <h3>{{ nav.label }}</h3>
              </div>
              <span
                :class="nav.path === $route.path ? 'v-btn__underlay' : ''"
              ></span>
            </v-btn>
          </v-bottom-navigation>
        </v-layout>
      </v-spacer>
      <v-btn
        v-if="theme.global.name.value == 'customDark'"
        variant="text"
        icon="mdi-weather-night"
        @click="toggleTheme"
      ></v-btn>
      <v-btn
        v-else
        variant="text"
        icon="mdi-brightness-7"
        @click="toggleTheme"
      ></v-btn>
      <div class="d-flex flex-column px-3">
        <span>{{ tokenResolvido?.usuario.nome }}</span>
        <small> {{ tokenResolvido?.usuario.login }}</small>
      </div>
      <div class="text-center mr-2">
        <v-menu open-on-hover>
          <template #activator="{ props }">
            <v-avatar color="blue-lighten-4" size="45" v-bind="props">
              <v-icon icon="mdi-account"></v-icon>
            </v-avatar>
          </template>

          <v-list style="min-width: 7em">
            <v-list-item
              v-for="(item, index) in items"
              :key="index"
              class="cursor-pointer list-hover"
            >
              <v-list-item-title>{{ item.title }}</v-list-item-title>
            </v-list-item>
          </v-list>
        </v-menu>
      </div>
    </v-app-bar>
  </v-layout>
</template>
<style scoped>
.v-tab--selected {
  background-color: #3b4c6b;
}

.v-slide-group-item--active {
  border-bottom: 4px solid #42a5f5;
}

.tab-slider-size {
  height: 5px;
}

.overflow-visible {
  min-height: 64px;
  margin-top: -16px;
}

.nav-min-width {
  display: flex;
  justify-content: center;
  align-items: center;
  min-width: 150px;
}

.btn-active-navbar {
  border-bottom: 2px solid #42a5f5;
  width: 100%;
  position: relative;
  margin-bottom: -38px;
}

.v-btn__underlay {
  background-color: #42a5f5;
  height: 5px;
  width: 168px;
  top: 60px;
}
</style>
