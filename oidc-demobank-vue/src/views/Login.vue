<template>
   <v-app
    id="inspire">
      <v-app-bar
          app
          color="yellow"
        >
        <v-toolbar-title>
          <span class="title ml-3 mr-5">Global&nbsp;<span class="font-weight-light">Bank Loans</span></span>
        </v-toolbar-title>
        <v-spacer></v-spacer>
        <v-btn text v-on:click="signIn"> 
            Sign In
            <v-icon>mdi-fingerprint</v-icon>
        </v-btn>
      </v-app-bar>
      <v-container
        class="fill-height bg"
        fluid>
      </v-container>
      <v-footer style="background-color:#e9e9eb">
        <v-row justify="center" no-gutters>
        <v-col class="text-center mb-5" cols="12">
          Sample provided by <a style="text-decoration: none;" href="https://twogenidentity.com"> ©2023 TwoGenIdentity.</a> All rights reserved 
        </v-col>
        </v-row>
      </v-footer>
    </v-app>
</template>

<script>
export default {
  name: 'Home',
  data: () => ({
      icons: [
        'mdi-web',
        'mdi-github',
        'mdi-linkedin'
      ]}
  ),
  methods: {
    signIn: function () {
        this.$store.dispatch("auth/signin");
    }
  }
}
</script>

<style scoped>
  .bg {
    background: url("/bankloan/bg9.png") no-repeat center center;
    height: 100%;
    background-size: cover;
}
</style>