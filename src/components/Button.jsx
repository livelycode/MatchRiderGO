"use strict";
import React, {
  Component,
  StyleSheet,
  Text,
  View,
  TouchableHighlight
} from "react-native";


export class Button extends Component {
  constructor (props) {
    super(props);
  }

  static defaultProps = {
    underlayColor: "#fff",
    style: {}
  };

  render () {
    return (
      <TouchableHighlight
        onPress={this.props.onPress}
        underlayColor={this.props.underlayColor}>
        <View
          style={[styles.Button,
                    this.props.style.backgroundColor
                    ? {backgroundColor: this.props.style.backgroundColor}
                    : null]}>
          <Text
            style={[styles.ButtonText,
                      this.props.style.color
                      ? {color : this.props.style.color}
                      : null]}>
            {this.props.text}
          </Text>
        </View>
      </TouchableHighlight>
    )
  }
}


const styles = StyleSheet.create({
  Button: {
    backgroundColor: "#008fca",
    paddingTop: 8,
    paddingBottom: 8,
    paddingLeft: 16,
    paddingRight: 16,
    borderRadius: 4,
  },
  ButtonText: {
    color: "#fff",
  }
});
