"use strict";
import React, {
  StyleSheet,
  Image,
} from "react-native";

export const MatchRiderLogo = () => (
  <Image
    style={styles.Logo}
    source={require("../../images/matchrider-logo-rgb.jpg")}
  />
);


const styles = StyleSheet.create({
  Logo: {
    width: 202,
    height: 71
  },
});
