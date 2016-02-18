"use strict";
import React, {
  StyleSheet,
  Image,
  View,
  Text,
} from "react-native";
import {SERVER_ADDRESS} from "../flux/connectionConfig";

export const UserAvatar = ({name, photo}) => (
  <View style={{alignItems: "center"}}>
    <Text style={{textAlign: "center"}}>{name}</Text>
    <Image
    style={{width: 64, height: 64}}
        source={photo ? {uri: SERVER_ADDRESS + photo} : require("../../images/ProfileTest.png")}
    />
  </View>
);


const styles = StyleSheet.create({
});
