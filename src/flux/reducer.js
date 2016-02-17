import * as types from "./actionTypes";
import {AlertIOS} from "react-native";
import {fromJS} from "immutable";
import {GENDERS} from "../enums";

const initialState = fromJS({
  sessionId: "",
  user: {
    gender: GENDERS.FEMALE,
  }
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
