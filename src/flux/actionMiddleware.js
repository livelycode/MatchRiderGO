import {AlertIOS} from "react-native";
import * as types from "./actionTypes";

const serverAddress = "http://192.168.0.20:3000";

function send (store, data, meta) {
  fetch(serverAddress + meta.endpoint, {
    method: meta.method,
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(data)
  }).then((response) =>
          response.text())
    .then((responseData) => {
      store.dispatch(meta.next(JSON.parse(responseData)))})
    .done();
}

export default store => next => action => {
  if (action.meta && action.meta.method) {
    send(store, action.data, action.meta);
  }
  return next(action);
}
