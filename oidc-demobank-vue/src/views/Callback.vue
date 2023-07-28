<template>
  <v-app id="keep">
    <v-app-bar
        app
        color="yellow"
        clipped-left
      >
        <span class="title ml-3 mr-5">Global&nbsp;<span class="font-weight-light">Bank Loans</span></span>
    </v-app-bar>
    <v-main class="grey lighten-4">
      <v-progress-linear :indeterminate="true" />
    </v-main>
  </v-app>
</template>

<script>

export default {
  name: 'Callback',
  mounted() {
    this.$store.dispatch("auth/signinRedirectCallback")
  }
}
</script>