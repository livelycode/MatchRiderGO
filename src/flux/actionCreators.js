import * as types from "./actionTypes";
import {POST, GET} from "./remoteMethods";
import * as endpoints from "./endpoints";

export function setSession({sessionId, userId}) {
  return {
    type: types.SET_SESSION,
    data: {
      sessionId,
      userId
    }
  }
}

export function login ({email, password}, callback) {
  return {
    type: types.LOGIN,
    data: {
      email,
      password
    },
    meta: {
      method: POST,
      endpoint: endpoints.LOGIN,
      next: setSession,
      callback: callback
    }
  };
}

export function accountDetails ({userId}) {
  return {
    type: types.ACCOUNT_DETAILS,
    data: {
      userId
    },
    meta: {
      method: POST,
      endpoint: endpoints.ACCOUNT_DETAILS
    }
  };
}

export function matchPoints ({long, lat, radius}) {
  return {
    type: types.MATCHPOINTS,
    data: {
      long,
      lat,
      radius
    },
    meta: {
      method: POST,
      endpoint: endpoints.MATCHPOINTS
    }
  };
}

export function availableRides ({start, destination, date}) {
  return {
    type: types.AVAILABLE_RIDES,
    data: {
      start,
      destination,
      date
    },
    meta: {
      method: POST,
      endpoint: endpoints.AVAILABLE_RIDES
    }
  };
}

export function bookedRides ({userId}) {
  return {
    type: types.BOOKED_RIDES,
    data: {
      userId
    },
    meta: {
      method: POST,
      endpoint: endpoints.BOOKED_RIDES
    }
  };
}

export function bookRide ({rideId}) {
  return {
    type: types.BOOK_RIDE,
    data: {
      rideId
    },
    meta: {
      method: POST,
      endpoint: endpoints.BOOK_RIDE
    }
  };
}

export function cancelRide ({rideId}) {
  return {
    type: types.CANCEL_RIDE,
    data: {
      rideId
    },
    meta: {
      method: POST,
      endpoint: endpoints.CANCEL_RIDE
    }
  }
}

export function updateUser({user}) {
  return {
    type: types.UPDATE_USER,
    data: {
      user
    },
    meta: {
      method: POST,
      endpoint: endpoints.UPDATE_USER
    }
  }
}

