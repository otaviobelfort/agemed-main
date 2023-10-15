// Components
import App from './App.vue'
import router from '@/router';

import { VDataTable, VDataTableVirtual, VDataTableServer } from 'vuetify/labs/VDataTable';

// Composables
import { createApp } from 'vue'

// Plugins
import { registerPlugins } from '@/plugins'

//Vue The Mask
import VueTheMask from 'vue-the-mask';

//FlatPickr
import flatPickr from 'vue-flatpickr-component';
import 'flatpickr/dist/flatpickr.css';

const app = createApp(App)
//const app = createApp(App).use(router)


registerPlugins(app)
app.use(VueTheMask)
app.component('v-data-table', VDataTable)
app.component('v-data-table-virtual', VDataTableVirtual)
app.component('v-data-table-server', VDataTableServer)
app.component('flat-pickr', flatPickr)
app.mount('#app')
