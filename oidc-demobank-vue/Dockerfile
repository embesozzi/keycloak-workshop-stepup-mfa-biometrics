# FROM node:12.14.1-alpine as build-stage
FROM --platform=linux/amd64 node:16.0.0 as build-stage

WORKDIR /app
COPY package*.json ./
RUN npm install
COPY . .
RUN npm run build

FROM nginx:stable-alpine as release-stage
LABEL maintainer="Martin Besozzi <embesozzi@gmail.com>"

COPY --from=build-stage /app/dist /usr/share/nginx/html/dist
COPY nginx/default.conf /etc/nginx/conf.d/default.conf

EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]
