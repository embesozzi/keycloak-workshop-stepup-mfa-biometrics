import oidcClient from '@/oidc/oidc-client'
import store from '@/store';

export const AuthService = {
    signin() {
        return oidcClient.signinRedirect({ acr_values: "loa1_bio"});
    },
    signinSilent() {
        return oidcClient.signinSilent();
    },
    signinRedirectCallback() {
        return oidcClient.signinRedirectCallback();
    },
    signOutRedirect() {
       return oidcClient.signoutRedirect();
    },
    getUser() {
        return oidcClient.getUser();
    }
}

oidcClient.events.addUserLoaded(function (user) {  
  console.log('OIDC event addUserLoaded:', arguments);
  console.log('OIDC access token: ', user.access_token)
});

oidcClient.events.addAccessTokenExpiring(function () {
  console.log('OIDC event addAccessTokenExpiring:', arguments);
});

oidcClient.events.addAccessTokenExpired(function () {
  store.dispatch('auth/signOut');
});

oidcClient.events.addUserSignedOut(function () {
  store.dispatch('auth/signOut');
});