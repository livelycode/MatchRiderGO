"use strict";
import React, {
  AppRegistry,
  Component,
  StyleSheet,
  Text,
  View,
  ScrollView,
  Image,
  TouchableHighlight,
  PixelRatio,
  TouchableOpacity,
  Navigator
} from "react-native";

import NavigationBar from 'react-native-navbar';

import {AccountScene} from "./Account";
import {UserAvatar} from "./components/UserInfo";
import {MenuBar, MenuBarStyles} from "./components/Menu";
import {RideLocations} from "./components/RideInfo";

import * as actionCreators from "./flux/actionCreators";
import { connect } from "react-redux";

import {GENDERS} from "./enums";

class RideRow extends View {
  render() {
    const {photo, name, date, destination, start} = this.props;
    const niceDate = new Date(date);
    return (
      <View style={{paddingLeft: 10, paddingRight: 5, height: 100, flexDirection: "row", borderBottomWidth: 1, borderColor: "#efefef"}}>
        <View style={{flex: 2, alignItems: "center", justifyContent: "center"}}>
          <UserAvatar name={name} photo={photo} />
        </View>

        <View style={{flex: 7, paddingLeft: 20, justifyContent: "center"}}>
          <Text style={{marginBottom: 5}}>{niceDate.getHours() + ":" + niceDate.getMinutes()}</Text>
          <RideLocations start={start} destination={destination} />
        </View>

        <View style={{flex: 1, alignItems: "center", justifyContent: "center"}}>
          <TouchableHighlight style={{alignSelf: "flex-end", flex: 1, justifyContent: "center"}} onPress={null}>
            <Image
            style={null}
            source={require("../images/BookedRides-RideDetails.png")}
            />
          </TouchableHighlight>
        </View>
      </View>
    );
  }
}


class BookedRidesView extends Component {
  componentDidMount () {
    this.props.bookedRides({userId: this.props.userId}, this.props.session);
  }
  renderRides (rides) {
    if(rides.length > 0) {
      const rideRows = rides.map((ride) => {
        return <RideRow name={ride.driver.name}
                        photo={ride.driver.photo}
                        date={ride.date}
                        start={ride.start.name}
                        destination={ride.destination.name} />;
      });
      return rideRows;
    } else {
      return null;
    }
  }

  render () {
    const leftButtonConf =
      <TouchableHighlight style={{marginTop: -12}}>
        <Image
        style={{width: 44, height: 44}}
        source={this.props.userPhoto
                ? this.props.userPhoto
                : (this.props.userGender == GENDERS.FEMALE
                   ? require("../images/UserAccount-PhotoFemale.png")
                   : require("../images/1.png"))}
        />
      </TouchableHighlight>

    const rightButtonConf =
      <TouchableHighlight style={{marginTop: -12}}>
        <Image
          style={{width: 44, height: 44, alignSelf: "center"}}
          source={require("../images/BookedRides-AddRide@3x.png")}
        />
      </TouchableHighlight>

    const {rides, userId} = this.props;

    return (
      <View style={{flex: 1, flexDirection: "column"}}>
        <NavigationBar
          showAnimation={"none"}
          hideAnimation={"none"}
          title={
            <Text style={{color: "#ffffff"}}>
              Gebuchte Fahrten
            </Text>
          }
          tintColor="#2062A3"
          statusBar={{hidden: true}}
          rightButton={rightButtonConf}
          leftButton={
            <TouchableHighlight style={{marginTop: -12}}
              onPress={() => this.props.navigator.push({
                component: AccountScene,
              })}>

              <Image
                style={{width: 44, height: 44}}
                source={this.props.userPhoto
                        ? this.props.userPhoto
                        : (this.props.userGender == GENDERS.FEMALE
                           ? require("../images/UserAccount-PhotoFemale.png")
                           : require("../images/1.png"))}/>
            </TouchableHighlight>
          }
        />

        <ScrollView
          automaticallyAdjustContentInsets={false}
            style={{flex: 90, borderWidth: 1, borderColor: "red", flexDirection: "column"}}>
          {
            this.renderRides(rides)
          }
        </ScrollView>
      </View>
     );
  }
}

export const BookedRidesScene = connect(
  (state) => {
    return {
      userId: state.getIn(["user", "id"]),
      session: state.getIn(["session"]).toJS(),
      rides: state.getIn(["rides", "booked"]).toJS(),
      userGender: state.getIn(["user", "gender"]),
      userPhoto: state.getIn(["user", "photo"]),
    }
  },
  actionCreators
)(BookedRidesView);
