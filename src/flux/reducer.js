import * as types from "./actionTypes";
import {AlertIOS} from "react-native";
import {Map} from "immutable";

const initialState = Map({
  sessionId: "",
  userId: ""
});

function setSession (state, {sessionId, userId}) {
  const transient = state.updateIn(["sessionId"], () => sessionId);
  return transient.updateIn(["userId"], () => userId);
}

export default function reducer (state = initialState, action = {}) {
  switch (action.type) {
  case types.LOGIN:
    return state;
  case types.SET_SESSION:
    return setSession(state, action.data);
  default:
    return state;
  }
}
