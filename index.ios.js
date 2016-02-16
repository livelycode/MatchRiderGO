'use strict';
import React, {
  AppRegistry,
  Component,
  StyleSheet,
  Text,
  View,
  Image,
  TextInput,
  TouchableHighlight,
  Navigator
} from 'react-native';

import Login from "./src/Login";
import { AvailableRidesScene } from "./src/AvailableRides";
import { AccountScene } from "./src/Account";
import { Provider } from 'react-redux/native';
import { createStore, applyMiddleware } from "redux";
import reducer from "./src/flux/reducer";
import * as actionCreators from "./src/flux/actionCreators";
import actionMiddleware from "./src/flux/actionMiddleware";
import { connect } from 'react-redux';

const createStoreWithMiddleware = applyMiddleware(actionMiddleware)(createStore);
const store = createStoreWithMiddleware(reducer);

class MatchRiderGO extends Component {
  render() {
    return (
      <Provider store={store}>
        {() => <Navigator
              initialRoute={{name: "Login", index: 0}}
              renderScene={(route, navigator) => <Login/>}
          />
        }
      </Provider>
    );
  }
}

const styles = StyleSheet.create({});

AppRegistry.registerComponent('MatchRiderGO', () => MatchRiderGO);
