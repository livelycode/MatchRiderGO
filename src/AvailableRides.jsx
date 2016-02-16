"use strict";
import React, {
  AppRegistry,
  Component,
  StyleSheet,
  Text,
  View,
  ScrollView,
  Image,
  TouchableHighlight
} from "react-native";

import {UserAvatar} from "./components/UserInfo";
import {MenuBar, MenuBarStyles} from "./components/Menu";
import {RideLocations} from "./components/RideInfo";

class RideRow extends View {
  render() {
    return (
      <View style={{paddingLeft: 10, paddingRight: 5, height: 100, flexDirection: "row", borderBottomWidth: 1, borderColor: "#efefef"}}>
        <View style={{flex: 20, alignItems: "center", justifyContent: "center"}}>
          <UserAvatar />
        </View>

        <View style={{flex: 70, paddingLeft: 20, justifyContent: "center"}}>
          <Text style={{marginBottom: 5}}>Datum</Text>
          <RideLocations start="Heidelberg" destination="Mannheim" />
        </View>

        <View style={{flex: 10, alignItems: "center", justifyContent: "center"}}>
          <TouchableHighlight onPress={null}>
            <Image
            style={{width: 16, height: 32}}
            source={require("../images/RightArrow.png")}
            />
          </TouchableHighlight>
        </View>
      </View>
      );
  }
}

export class AvailableRidesScene extends Component {
  render() {
    return (
      <View style={{flex: 1, flexDirection: "column"}}>
        <MenuBar style={MenuBarStyles.MenuBar__top}>
          <View style={{flex: 1}}>
            <Image
              style={{width: 48, height: 48, alignSelf: "center"}}
              source={require("../images/1.png")}
            />
          </View>

          <View style={{flex: 3}}>
            <Text style={{fontSize: 20, textAlign: "center"}}>Gebuchte Farten</Text>
          </View>

          <View style={{flex: 1}}>
            <Image
              style={{width: 48, height: 48, alignSelf: "center"}}
              source={require("../images/23.png")}
            />
          </View>
        </MenuBar>

        <ScrollView
          automaticallyAdjustContentInsets={false}
          style={{flex: 90, borderWidth: 1, borderColor: "red", flexDirection: "column"}}>
          <RideRow />
          <RideRow />
          <RideRow />
          <RideRow />
          <RideRow />
          <RideRow />
        </ScrollView>
      </View>
     );
  }
}

class Thumb extends Component {
  render() {
    return (
      <View style={styles.button}>
        <Image style={styles.img} source={{uri:this.props.uri}} />
      </View>
    );
  }
}



const styles = StyleSheet.create({
  text_center: {
    textAlign: "center",
  },
});
