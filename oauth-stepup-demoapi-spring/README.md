# oauth-stepup-demoapi-spring

Spring OAuth demo api acting as [OAuth2 Resource Server](https://docs.spring.io/spring-security/site/docs/current/reference/html5/#oauth2resourceserver).

We also developed a custom Converter for parsing the ACR claim and Access Denied Handler for implementing the following IETF: https://datatracker.ietf.org/doc/draft-ietf-oauth-step-up-authn-challenge (Approved).


For more detail see: [aplication.yml](/src/main/resources/application.yml).

## Run locally

* Clone this repository
```
git clone https://github.com/embesozzi/oauth-stepup-demoapi-spring.git
```
- Adjust the [aplication.yml](/src/main/resources/application.yml)
- Run the app
```
mvn spring-boot:run
```

- You can access to the API on http://hostname:8001/api/v1/accounts
