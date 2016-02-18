import * as types from "./actionTypes";
import {AlertIOS} from "react-native";
import {fromJS} from "immutable";
import {GENDERS} from "../enums.js";

const initialState = fromJS({
  session: {
    id: "ab12"
  },
  user: {
    gender: GENDERS.FEMALE,
    id: "42"
  },
  rides: {
    booked: []
  }
});

function setUser (state, {session, user}) {
  const transient = state.updateIn(["session", "id"], () => session.id);
  return transient.updateIn(["user"], () => fromJS(user));
}

function setBookedRides (state, {rides}) {
  return state.updateIn(["rides", "booked"], () => fromJS(rides));
}

export default function reducer (state = initialState, action = {}) {
  switch (action.type) {
  case types.SET_SESSION:
    return setUser(state, action.data);
  case types.SET_BOOKED_RIDES:
    return setBookedRides(state, action.data);
  default:
    return state;
  }
}
