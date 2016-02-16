'use strict';
import React, {
  AppRegistry,
  Component,
  StyleSheet,
  Text,
  View,
  Image,
  TextInput,
  TouchableHighlight
} from 'react-native';

import {Button} from "./components/Button";
import {MatchRiderLogo} from "./components/Image";

class MRTextInput extends TextInput {
  constructor (props) {
    super(props);

    this.state = {
      text: "",
    };
  }

  render () {
    return (
      <TextInput
        style={styles.TextInput}
        underlineColorAndroid = "transparent"
        onChangeText={(text) => this.setState({text})}
        placeholder={this.props.placeholder}
      />
    )
  }
}

class EmailInput extends MRTextInput {
  static defaultProps = {
    placeholder: "E-Mail-Adresse",
  };

  constructor (props) {
    super(props);
  }
}

class PasswordInput extends MRTextInput {
  static defaultProps = {placeholder: "Passwort", secureTextEntry: true};

  constructor (props) {
    super(props);
  }
}


export class LoginScene extends Component {
  render() {
    return (
      <View style={[styles.Col__middle, {backgroundColor: "#ffffff", flex: 1}]}>
        <View style={{alignSelf: "center"}}>
          <MatchRiderLogo />
        </View>
        <View style={{marginTop: 20, paddingLeft: 30, paddingRight: 30}}>
          <View style={[styles.TextInputView]}>
            <EmailInput />
          </View>

          <View style={[styles.TextInputView, {marginTop: 10}]}>
            <PasswordInput />
          </View>

          <View style={[{alignSelf: "flex-end", marginTop: 10}]}>
            <Button text="Login" />
          </View>
        </View>
      </View>
    );
  }
}

const styles = StyleSheet.create({
  Col__middle: {
    flexDirection: "column",
    alignItems: "center",
    justifyContent: "center",
  },

  Container: {
    maxWidth: 360,
  },

  TextInput: {
    padding: 4,
  },

  TextInputView: {
    backgroundColor: "#fff",
    padding: 0,
    borderRadius: 4,
    borderWidth: 1,
    borderColor: "#e9e9e9",
  },
});

