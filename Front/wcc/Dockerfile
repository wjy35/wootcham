# build stage
FROM node:16.16.0 as build-stage
WORKDIR /app
COPY ./Front/wwc/package*.json ./
RUN npm install
COPY ./Front/wwc/ .
RUN npm run build
 
# production stage
FROM nginx:stable-alpine as production-stage
COPY --from=build-stage /app/dist /usr/share/nginx/html
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]
