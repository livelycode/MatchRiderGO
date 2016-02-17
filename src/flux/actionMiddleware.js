import {AlertIOS} from "react-native";
import * as types from "./actionTypes";
import {SERVER_ADDRESS} from "./connectionConfig";

function send (store, data, meta, session) {
  fetch(SERVER_ADDRESS + meta.endpoint, {
    method: meta.method,
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json',
      'Session': meta.session.id
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
      if(meta.callback) {
        meta.callback(parsed);
      }
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
