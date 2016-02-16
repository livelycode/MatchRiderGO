"use strict";
import React, {
  StyleSheet,
  View,
  Text
} from "react-native";


class Badge extends View {
  constructor (props) {
    super(props);
  }

  static defaultProps = {
    style: {},
  };

  render () {
    return (
      <View style={[styles.Badge, {backgroundColor: this.props.style.backgroundColor}]}>
        <Text style={{fontSize: 10, textAlign: "center"}}>{this.props.children}</Text>
      </View>
    )
  }
}


export class RideLocations extends View {
  constructor (props) {
    super(props);
  }

  render () {
    return (
      <View>
        <View style={{flex: 60, flexDirection: "row", alignItems: "center"}}>
          <View style={{flex: 20}}>
            <Badge style={{backgroundColor: "#5DAE41"}}>Start</Badge>
          </View>

          <View style={{flex: 80, paddingLeft: 10}}>
            <Text>{this.props.start}</Text>
          </View>
        </View>

        <View style={{flex: 40, flexDirection: "row", marginTop: 5, alignItems: "center"}}>
          <View style={{flex: 20}}>
            <Badge style={{backgroundColor: "#FF6900"}}>Ziel</Badge>
          </View>

          <View style={{flex: 80, paddingLeft: 10}}>
            <Text>{this.props.destination}</Text>
          </View>
        </View>
      </View>
    )
  }
}


const styles = StyleSheet.create({
  Badge: {
    borderRadius: 4,
    paddingTop: 2,
    paddingBottom: 2,
    paddingLeft: 8,
    paddingRight: 8,
  },

  Badge__green: {
    backgroundColor: "green",
  },

  Badge__blue: {
    backgroundColor: "blue",
  },
});
