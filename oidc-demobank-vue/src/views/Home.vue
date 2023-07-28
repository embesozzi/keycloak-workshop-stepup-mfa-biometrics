<template>
		<v-app id="keep">
		<!-- Bar -->
		<v-app-bar
			app
			clipped-right
      		flat
        	color="yellow"
		>
			<v-spacer></v-spacer>
				<span v-if="response && response.profile">
				<v-icon
					color="grey lighten-3"
					large
				>
					mdi-account-circle
				</v-icon> 
				{{response.profile.name}}
			</span>
		</v-app-bar>
		<!-- Home menu -->
		<v-navigation-drawer
			v-model="drawer"
			app
            :rail="rail"
			permanent
			color="grey lighten-4"
			@click="rail = false"
		>
				<v-list
					density="compact"
					class="grey lighten-4"
				>
				<v-list-item
				 nav>
					<span class="title ml-3 mr-5">Global&nbsp;<span class="font-weight-light">Bank Loans</span></span>
					<template v-slot:append>
						<v-btn
						variant="text"
						icon="mdi-chevron-left"
						@click.stop="rail = !rail"
						></v-btn>
					</template>
				</v-list-item>
				<v-divider></v-divider>
				<v-list-item link>
					<v-list-item-icon>
						<v-icon>mdi-cash</v-icon>
					</v-list-item-icon>
					<v-list-item-content @click="content='products'">
						<v-list-item-title>Personal Loans</v-list-item-title>
					</v-list-item-content>
				</v-list-item>
				<v-list-item link>
					<v-list-item-icon>
						<v-icon>mdi-image</v-icon>
					</v-list-item-icon>
					<v-list-item-content @click="content='tokens'">
						<v-list-item-title>Identity profile</v-list-item-title>
					</v-list-item-content>
				</v-list-item>
				<v-list-item link>
					<v-list-item-icon>
						<v-icon>mdi-logout</v-icon>
					</v-list-item-icon>
					<v-list-item-content @click="logout()">
						<v-list-item-title>Logout</v-list-item-title>
						</v-list-item-content>
				</v-list-item>
				</v-list>
		</v-navigation-drawer>
		<v-main class="grey lighten-4">
			<v-container
				fluid
				class="grey lighten-4"
			>
				<!-- Products page content -->
				<v-row v-if="content=='products'"
					ustify="center"
					align="center"
				>
					<v-row
						class="mb-8"
						align="center" 
						justify="center"
					>
						<v-col class="text-center">
							<span class="text-body-1">Here are the pre-approved loans listed, due to being part of Global Digital Bank.</span>
						</v-col>
					</v-row>
					<v-col
						v-for="result in results"
						:key="result.title"
						cols="12"
					>
						<v-card
							class="mx-auto"
							width="800"
							max-width="800"
							outlined
						>
							<div class="d-flex flex-no-wrap justify-space-between">
								<div>
									<v-card-title class="text-h5">
										{{result.name}} | {{result.estimatedAmount}}
									</v-card-title>
									<v-card-subtitle>{{result.description}}</v-card-subtitle>
									<v-card-text>
										Interest Rate:	24 |
										Loan Term:	12 Months |
										Fee:	3%
									</v-card-text>
									<v-card-actions>
										<v-btn text>
										Review
										</v-btn>
										<v-btn text>
										Compare
										</v-btn>
										<v-btn
											text
											color="teal accent-4"
											elevation="1"
											@click="apply"
										>
										Request
										</v-btn>	
									</v-card-actions>
								</div>
								<v-avatar
									rounded="0"
									class="ma-3"
               						size="150"
								>
									<v-img  height="100px" :src="result.logo"></v-img>
								</v-avatar>
							</div>
						</v-card>
					</v-col>
				</v-row>
				<!-- Token page content -->
				<v-row v-if="content=='tokens'">
					<v-expansion-panels popout>
						<v-expansion-panel>
							<v-expansion-panel-header>ID Token JWT</v-expansion-panel-header>
							<v-expansion-panel-content>
									{{response.id_token}}
							</v-expansion-panel-content>
						</v-expansion-panel>
						<v-expansion-panel>
							<v-expansion-panel-header>ID Token Payload</v-expansion-panel-header>
							<v-expansion-panel-content>
									<pre>{{idTokenPayload}}</pre>
							</v-expansion-panel-content>
						</v-expansion-panel>
						<v-expansion-panel>
							<v-expansion-panel-header>Access Token JWT</v-expansion-panel-header>
							<v-expansion-panel-content>
								{{accessToken}}	
							</v-expansion-panel-content>
						</v-expansion-panel>
						<v-expansion-panel>
							<v-expansion-panel-header>Access Token Payload</v-expansion-panel-header>
							<v-expansion-panel-content>
									<pre>{{accessTokenPayload}}</pre>
							</v-expansion-panel-content>
						</v-expansion-panel>
						<v-expansion-panel>
							<v-expansion-panel-header>Scopes</v-expansion-panel-header>
							<v-expansion-panel-content>
									{{response.scope}}
							</v-expansion-panel-content>
						</v-expansion-panel>
					</v-expansion-panels>
				</v-row>
			</v-container>
			<!--ToDo: Add step-up use cases  -->
			<v-dialog
				v-model="dialog"
				transition="dialog-top-transition"
				width="800"
			>
				<v-card>
					<v-card-title class="text-h5">
						Modal
					</v-card-title>
					<v-card-text>
					</v-card-text>
					<v-card-actions>
						<v-spacer></v-spacer>
						<v-btn
							color="green-darken-1"
							variant="text"
							@click="dialog = false"
						>
							Close
						</v-btn>
						<v-btn
							color="green-darken-1"
							variant="text"
							@click="signIn"
						>
							Login MFA
						</v-btn>
					</v-card-actions>
				</v-card>
			</v-dialog>
		</v-main>
		<v-footer class="bg-grey-lighten-1">
			<v-row justify="center" no-gutters>
			<v-col class="text-center mb-5" cols="12">
				Sample provided by <a style="text-decoration: none;" href="https://twogenidentity.com"> ©2023 TwoGenIdentity.</a>All rights reserved 
			</v-col>
			</v-row>
  		</v-footer>
	 </v-app>
</template>
<script>
import { mapGetters } from "vuex";

export default {
	name: 'User',
	data: () => ({
		results: null,
		content : "products", // Default page
		dialog : false,
		drawer: true,
        rail: true,
		mockLoans: [
			{ id : "1", type:"loan", name: "HSBC Loan", estimatedAmount: "$1,000 to $50,000", description: "APR range: 11.99% to 18.99% with autopay" , logo : "https://futureconsiderations.com/wp-content/uploads/2012/03/HSBC-Logo-300x200.png"},
			{ id : "2", type:"loan", name: "Barclays Loan", estimatedAmount: "$1,000 to $40,000", description: "APR range: 13.99% to 18.99% with autopay", logo : "https://logos-world.net/wp-content/uploads/2021/08/Barclays-Emblem.png"},
			{ id : "3", type:"loan", name: "Bank of America Loan", estimatedAmount: "$1,000 to $30,000", description: "APR range: 20.99% to 21.99% with autopay" , logo : "https://play-lh.googleusercontent.com/GdICLxnKd-e-lmV46N_SjihWHzAUX1nj8e--dF2KrQjvomN4zxOR-iEWwsTG_Tqr_oc"},

		]
	}),
	created() {
    	this.showContent("loans");
	},
	computed: {
		...mapGetters({
				accessToken : 'auth/accessToken',
				response : 'auth/tokenResponse'
			}),
			idTokenPayload() {
					return JSON.stringify(
							JSON.parse( Buffer.from(this.response.id_token.split(".")[1], 'base64').toString()), null, 2);
			},
			accessTokenPayload() {
					return JSON.stringify(
							JSON.parse(Buffer.from(this.accessToken.split(".")[1], 'base64').toString()), null, 2);
			}
	},
	methods: {
		showContent: function(){
			this.results = this.mockLoans;
		},
		signIn: function () {
        	// this.$store.dispatch("auth/signin");
    	},
		apply: function(){
			this.dialog = true;
		},
		logout : function () {
			this.$store.dispatch("auth/signOut");
		}
	}
}
</script>