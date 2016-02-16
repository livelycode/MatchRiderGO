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
  View
} from 'react-native';

import { Provider } from 'react-redux/native';
import { createStore, applyMiddleware } from "redux";
import reducer from "./src/flux/reducer";
import * as actionCreators from "./src/flux/actionCreators";
import actionMiddleware from "./src/flux/actionMiddleware";
import { connect } from 'react-redux';

const createStoreWithMiddleware = applyMiddleware(actionMiddleware)(createStore);
const store = createStoreWithMiddleware(reducer);

class DefaultText extends Component {
  componentDidMount () {
    this.props.login({email: "foo", password: "bar"})
  }
  render () {
    return  (
      <Text style={styles.instructions}>
        {this.props.text}
      </Text>
    );
  }
}

const DefaultTextContainer = connect(
  (state) => {
    return {
      text: state.getIn(["userId"])
    }
  },
  actionCreators
)(DefaultText);

class MatchRiderGO extends Component {
  render() {
    return (
        <Provider store={store}>
        {() =>
          <View style={styles.container}>
            <Text style={styles.welcome}>
              MatchRider GO
            </Text>
            <DefaultTextContainer></DefaultTextContainer>
          </View>
        }
        </Provider>
    );
  }
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: '#F5FCFF'
  },
  welcome: {
    fontSize: 20,
    textAlign: 'center',
    margin: 10
  },
  instructions: {
    textAlign: 'center',
    color: '#333333',
    marginBottom: 5
  }
});


AppRegistry.registerComponent('MatchRiderGO', () => MatchRiderGO);
