
export const isUserLoggedIn = () => !!(localStorage.getItem('user') && localStorage.getItem('token'))

