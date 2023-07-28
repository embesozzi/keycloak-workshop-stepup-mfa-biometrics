window.config = {
    VUE_APP_OIDC_PROVIDER_DOMAIN: "https://localhost/realms/bank",
    VUE_APP_OIDC_CLIENT_ID: "bank-loan",
    VUE_APP_OIDC_CLIENT_REDIRECT_URI: "https://localhost/bankloan/callback",
    VUE_APP_OIDC_SCOPES: "openid profile roles",
    VUE_APP_OIDC_CLIENT_POST_LOGOUT_REDIRECT_URI: "https://localhost/bankloan/home?action=logout"
}