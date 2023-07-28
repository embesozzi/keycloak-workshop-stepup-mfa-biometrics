import Oidc from 'oidc-client';

Oidc.Log.logger = console;
Oidc.Log.level = (process.env.NODE_ENV === 'production') ? Oidc.Log.ERROR : Oidc.Log.DEBUG;

// OIDC configuration
let config = (window.config && window.config.VUE_APP_OIDC_PROVIDER_DOMAIN) ? window.config : process.env;
let oidcProviderDomain = config.VUE_APP_OIDC_PROVIDER_DOMAIN;
let clientId = config.VUE_APP_OIDC_CLIENT_ID;
let scopes = config.VUE_APP_OIDC_SCOPES;
let clientRedirectUri = config.VUE_APP_OIDC_CLIENT_REDIRECT_URI;
let clientPostLogoutRedirectUri = config.VUE_APP_OIDC_CLIENT_POST_LOGOUT_REDIRECT_URI;

let instance;

// OIDC Client
export const getOidcClient = () => {
  if (instance) {
      return instance;
  }

  instance = new Oidc.UserManager({
    userStore: new Oidc.WebStorageStateStore(),  
    authority: oidcProviderDomain,
    client_id: clientId,
    redirect_uri: clientRedirectUri || window.location.origin + '/callback',
    response_type: 'code',
    scope: scopes,
    post_logout_redirect_uri: clientPostLogoutRedirectUri || window.location.origin + '/home?action=logout',
    accessTokenExpiringNotificationTime: 10,
    automaticSilentRenew: false,
    filterProtocolClaims: false,
    loadUserInfo: true,
    monitorSession: false,
    includeIdTokenInSilentRenew : false
  });
  return instance;
}
