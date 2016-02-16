"use strict";
import React, {
  StyleSheet,
  View,
} from "react-native";

export class MenuBar extends View {
  constructor (props) {
    super(props);
  }

  render () {
    return (
      <View style={[MenuBarStyles.MenuBar, this.props.style]}>
        {this.props.children}
      </View>
    )
  }
}


export const MenuBarStyles = StyleSheet.create({
  MenuBar: {
    flexDirection: "row",
    alignItems: "center",
    justifyContent: "center",
    backgroundColor: "#eee",
    borderColor: "#d8d8d8",
    flex: 10,
  },

  MenuBar__bottom: {
    borderTopWidth: 1,
  },

  MenuBar__top: {
    borderBottomWidth: 1,
  },
});
