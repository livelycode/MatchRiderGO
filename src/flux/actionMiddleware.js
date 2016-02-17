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
  }).then((response) => {
    if (response.ok) {
      return response.text();
    } else {
      // TODO: handle erros better
      alert("Network error");
      return null;
    }
  }).then((responseData) => {
    const parsed = JSON.parse(responseData);
    if (parsed.error) {
      alert(parsed.error);
    } else {
      meta.callback(parsed);
      store.dispatch(meta.next(parsed));
    }
  }).catch(err => alert(err))
    .done();
}

export default store => next => action => {
  if (action.meta && action.meta.method) {
    send(store, action.data, action.meta);
  }
  return next(action);
}
