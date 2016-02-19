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

import NavigationBar from 'react-native-navbar';

export class AccountScene extends Component {

  render() {
    const leftButtonConf =
      <TouchableHighlight
        onPress={() => this.props.navigator.pop()}>
        <Text style={{color: "#ffffff"}}>
          Zurück
        </Text>
      </TouchableHighlight>

    const rightButtonConf =
      <TouchableHighlight>
        <Text style={{color: "#ffffff"}}>
          Ändern
        </Text>
      </TouchableHighlight>

    return (
      <View style={{flex: 1, flexDirection: "column"}}>
        <NavigationBar
          title={{ title: "Account" }}
          tintColor="#3479B1"
          statusBar={{hidden: true}}
          rightButton={rightButtonConf}
          leftButton={leftButtonConf}
        />

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

