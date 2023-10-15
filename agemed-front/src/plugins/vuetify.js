import '@mdi/font/css/materialdesignicons.css'
import 'vuetify/styles'
import { createVuetify } from 'vuetify'
import { customThemes } from '@themeConfig';

const customLight = customThemes.light;
const customDark = customThemes.dark;

let themeLocal = JSON.parse(localStorage.getItem("theme"));

export default createVuetify({
  theme: {
    defaultTheme: themeLocal ? themeLocal : "customLight",
    themes: {
      customLight,
      customDark
    }
  }
})
