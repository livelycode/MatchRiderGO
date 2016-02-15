import * as types from "./actionTypes";

export function login ({email, password}) {
  return {
    type: types.LOGIN,
    email,
    password
  };
}

export function accountDetails ({userId}) {
  return {
    type: types.ACCOUNT_DETAILS,
    userId
  };
}

export function matchPoints ({long, lat, radius}) {
  return {
    type: types.MATCHPOINTS,
    long,
    lat,
    radius
  };
}

export function availableRides ({start, destination, date}) {
  return {
    type: types.AVAILABLE_RIDES,
    start,
    destination,
    date
  };
}

export function bookedRides ({userId}) {
  return {
    type: types.BOOKED_RIDES,
    userId
  };
}

export function bookRide ({rideId}) {
  return {
    type: types.BOOK_RIDE,
    rideId
  };
}

export function cancelRide ({rideId}) {
  return {
    type: types.CANCEL_RIDE,
    rideId
  }
}

export function updateUser({user}) {
  return {
    type: types.UPDATE_USER,
    user
  }
}
