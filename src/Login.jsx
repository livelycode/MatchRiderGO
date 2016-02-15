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
      <View style={[styles.Col, styles.Row, styles.Row_column, styles.Row_middle]}>
        <View style={styles.Row_middle}>
          <MatchRiderLogo />
        </View>
        <View style={styles.rhythm}>
          <View style={[styles.TextInputWrapper, styles.ElementWrapper]}>
            <EmailInput />
          </View>

          <View style={[styles.TextInputView, styles.ElementWrapper, styles.half_rhythm]}>
            <PasswordInput />
          </View>

          <View style={[styles.half_rhythm, styles.ElementWrapper, {alignSelf: "flex-end"}]}>
            <Button text="Login" />
          </View>
        </View>
      </View>
    );
  }
}

const styles = StyleSheet.create({
  rhythm: {
    marginTop: 20,
  },

  half_rhythm: {
    marginTop: 10,
  },

  Row: {
    flexDirection: "row",
  },

  Row_column: {
    flexDirection: "column",
  },

  Col: {
    flex: 1,
  },

  Row_middle: {
    alignItems: "center",
    justifyContent: "center",
  },

  Container: {
    maxWidth: 360,
  },

  TextInput: {
    padding: 4,
  },

  ElementWrapper: {
    marginLeft: 30,
    marginRight: 30,
  },

  TextInputWrapper: {
    padding: 0,
    borderRadius: 4,
    borderWidth: 1,
    borderColor: "#e9e9e9",
  },

  TextInputView: {
    backgroundColor: "#fff",
    padding: 0,
    borderRadius: 4,
    borderWidth: 1,
    borderColor: "#e9e9e9",
  },
});

