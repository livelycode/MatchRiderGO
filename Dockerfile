FROM node:argon

# create app directory
RUN mkdir -p /usr/src/app
WORKDIR /usr/src/app


# install app dependencies
RUN npm install express body-parser

# add source and assets
COPY server.js /usr/src/app
COPY testData.json /usr/src/app
COPY ./assets /usr/src/app/assets

EXPOSE 3000
CMD ["node", "server.js"]
