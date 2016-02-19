"use strict";
import React, {
  AppRegistry,
  Component,
  StyleSheet,
  Text,
  View,
  Image,
  TextInput,
  TouchableHighlight,
  Navigator,
  ScrollView,
  TouchableOpacity,
} from "react-native";

import Login from "./src/Login";
import {BookedRidesScene} from "./src/BookedRides";
import { AccountScene } from "./src/Account";




import { Provider } from "react-redux";
import { createStore, applyMiddleware } from "redux";
import reducer, {initialState} from "./src/flux/reducer";
import * as actionCreators from "./src/flux/actionCreators";
import actionMiddleware from "./src/flux/actionMiddleware";
import { connect } from "react-redux";

let store = createStore(reducer, initialState, applyMiddleware(actionMiddleware));

const SCENES = {
  BOOKED_RIDES: {scene: <BookedRidesScene />},
};



function renderScene(route, navigator) {
  return <route.component route={route} navigator={navigator} />;
}


class MatchRiderGO extends Component {
  render() {
    const initialRoute = {
      component: Login
    };

    return (
      <Provider store={store}>
        <Navigator
          configureScene={() => Navigator.SceneConfigs.HorizontalSwipeJump}
          initialRoute={initialRoute}
          renderScene={renderScene}
        />
      </Provider>
    );
  }
}

AppRegistry.registerComponent("MatchRiderGO", () => MatchRiderGO);
