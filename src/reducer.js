import * as types from "./actionTypes";
import {AlertIOS} from "react-native";

const initialState = {
  sessionId: ""
}

export function login (state, email, password) {
  fetch("http://192.168.0.20:3000/login", {
    method: 'POST',
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    },
    body: JSON.stringify({
      email,
      password
    })
  }).then((response) => response.text())
    .then((responseData) => AlertIOS.alert(
      "LOGIN RESPONSE:",
      JSON.parse(responseData).sessionId))
    .done()

  return state;
}


export default function reducer (state = initialState, action = {}) {
  switch (action.type) {
  case types.LOGIN:
    return login(state, action.email, action.password);
  default:
    return state;
  }
}
