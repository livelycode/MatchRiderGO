"use strict";
import React, {
  AppRegistry,
  Component,
  StyleSheet,
  Text,
  View,
  Image,
  TextInput,
  TouchableHighlight
} from "react-native";

import {Map} from "immutable";
import {Button} from "./components/Button";
import {MatchRiderLogo} from "./components/Image";
import * as actionCreators from "./flux/actionCreators";
import { connect } from "react-redux";

import { BookedRidesScene } from "./BookedRides";



class MRTextInput extends TextInput {

  render () {
    return (
      <TextInput
        style={styles.TextInput}
        underlineColorAndroid = "transparent"
        onChangeText={this.props.onChange}
        value={this.props.value}
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
  constructor (props) {
    super(props);

    this.veryData = this.verifyData.bind(this);
    this.sendData = this.sendData.bind(this);

    this.state = {
      password: "",
      email: "",
    };
  }

  verifyData (loginData) {
    // Check for a "valid" e-mail address. This means:
    // * The address contains an `@' and a `.' (after the `@')
    // * There is at least 1 or more characters before/between `@' and `.'
    const trimmed_email = this.state.email.trim();

    if(/\S+@\S+\.\S+/.test(trimmed_email)) {
      return true;
    }

    return false;
  }

  sendData () {
    this.props.navigator.push({component: BookedRidesScene});
    if(this.verifyData()) {
      this.props.login(
        {email: this.state.email.trim(),
         password: this.state.password.trim()})
    }
  }

  render () {
    return (
      <View style={[styles.Col__middle, {backgroundColor: "#ffffff", flex: 1}]}>
        <View style={{alignSelf: "center"}}>
          <MatchRiderLogo />
        </View>
        <View style={{marginTop: 20, paddingLeft: 30, paddingRight: 30}}>
          <View style={[styles.TextInputView]}>
            <EmailInput value={this.state.email} onChange={(email) =>
              this.setState({email})} />
          </View>

          <View style={[styles.TextInputView, {marginTop: 10}]}>
            <PasswordInput value={this.state.password} onChange={(password) => this.setState({password})} />
          </View>

          <View style={[{alignSelf: "flex-end", marginTop: 10}]}>
            <Button text="Login" onPress={this.sendData} />
          </View>
        </View>
      </View>
    );
  }
}

export default connect(
  (state) => {
    return {
      userId: state.getIn(["userId"]),
      sessionId: state.getIn(["sessionId"])
    }
  },
  actionCreators
)(LoginScene);



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
