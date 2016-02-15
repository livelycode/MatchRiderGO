/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 */
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

import {LoginScene} from "./src/Login";

class MatchRiderGO extends Component {
  render() {
    return (
     <Navigator
        initialRoute={{name: "LoginScene", index: 0}}
        renderScene={(route, navigator) =>
          <LoginScene />
        }
     />
    )
  }
}

const styles = StyleSheet.create({
});

AppRegistry.registerComponent('MatchRiderGO', () => MatchRiderGO);
