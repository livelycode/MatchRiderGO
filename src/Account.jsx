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

export class AccountScene extends Component {
  render() {
    return (
      <View style={{flex: 1, flexDirection: "column"}}>
        <MenuBar style={MenuBarStyles.MenuBar__top}>
          <View style={{flex: 1}}>
            <Text style={{textAlign: "center"}}>Zurück</Text>
          </View>

          <View style={{flex: 3}}>
            <Text style={{fontSize: 20, textAlign: "center"}}>Account</Text>
          </View>

          <View style={{flex: 1}}>
            <Text style={{textAlign: "center"}}>Ändern</Text>
          </View>
        </MenuBar>

        <ScrollView
          automaticallyAdjustContentInsets={false}
          style={{flex: 80}}>
            <View style={[styles.ContentRow, {flexDirection: "row", borderBottomWidth: 2}]}>
              <View style={{flex: 20, alignItems: "center", justifyContent: "center"}}>
                <UserAvatar />
              </View>

              <View style={{flex: 80, paddingLeft: 20}}>
                <Text style={{textAlign: "center"}}>Mobilfunknummer</Text>
                <Text>Beschreibung beschreibung</Text>
              </View>
            </View>

            <View style={[styles.ContentRow, {flexDirection: "row"}]}>
              <View style={{flex: 80}}>
                <Text>Gratisfahrt verschicken</Text>
              </View>
              <View style={{flex: 20}}>
                <Text>Pfeil</Text>
              </View>
            </View>

            <View style={[styles.ContentRow, {flexDirection: "row"}]}>
              <View style={{flex: 80}}>
                <Text>Bezahlen</Text>
              </View>
              <View style={{flex: 20}}>
                <Text>Pfeil</Text>
              </View>
            </View>
        </ScrollView>

        <MenuBar style={[MenuBarStyles.MenuBar__bottom, {backgroundColor: "#FF5900"}]}>
          <Text>Logout</Text>
        </MenuBar>
      </View>
     );
  }
}


const styles = StyleSheet.create({
  ContentRow: {
    borderBottomColor: "#ededed",
    borderBottomWidth: 1,
    padding: 10
  }
});

