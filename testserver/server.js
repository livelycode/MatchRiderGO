var express = require("express");
var app = express();
var bodyParser = require("body-parser");
var serverState = require("./testData.json");

// --- TEST DATA ---
var states = {
  FAILURE: "FAILURE",
  SUCCESS: "SUCCESS",
  DATABASE_ERROR: "DATABASE_ERROR",
  INVALID_USER: "INVALID_USER",
  INVALID_PASSWORD: "INVALID_PASSWORD",
  INVALID_SESSION: "INVALID_SESSION",
  RESET_SUCCES: "RESET_SUCCESS"
};

function warning (errorState) {
  return {
    error: errorState
  }
}

function getUserBookedRides (userId) {
  var userRides = serverState.users[userId].bookedRides;
  userRides.map(function (ride) {
    return serverState.rides[ride];
  })
  
}

function resolveBookedRides () {
  var ride1 = serverState.rides["51"];
  var ride2 = serverState.rides["52"];
  var ride3 = serverState.rides["53"];

  ride1.driver = serverState.drivers[ride1.driver];
  ride2.driver = serverState.drivers[ride2.driver];
  ride3.driver = serverState.drivers[ride3.driver];
  
  ride1.car = serverState.cars[ride1.car];
  ride2.car = serverState.cars[ride2.car];
  ride3.car = serverState.cars[ride3.car];

  ride1.start = serverState.matchpoints[ride1.start];
  ride2.start = serverState.matchpoints[ride2.start];
  ride3.start = serverState.matchpoints[ride3.start];

  
  ride1.destination = serverState.matchpoints[ride1.destination];
  ride2.destination = serverState.matchpoints[ride2.destination];
  ride3.destination = serverState.matchpoints[ride3.destination];

  return [ride1, ride2, ride3];
}                         

function checkSession (userId, sessionId) {
  return sessionId === serverState.users[userId].session.id;
}

// --- ROUTES ---

app.use(bodyParser.json());
app.use("/assets", express.static(__dirname + "/assets"));
  
app.post("/login", function (req, res) {
  console.log(req);
  var testUser = serverState.users["11"];
  if (req.body.email === testUser.email) {
    if (req.body.password === testUser.password) {
      res.send({type: "SESSION", data: testUser.session});
    } else {
      res.send(warning(states.INVALID_PASSWORD));
    }
  } else {
    res.send(warning(states.INVALID_USER));
  }
});

app.post("/booked-rides", function (req, res) {
  var testUser = serverState.users["11"];
  var rides = serverState.rides;

  if (checkSession(req.body.userId, req.headers.session)) {
    if (req.body.userId === testUser.id) {
      res.send({rides: [rides["51"], rides["52"], rides["53"]]});
    } else {
      res.send(warning(states.INVALID_USER));
    }  
  } else {
    res.send(warning(states.INVALID_SESSION));
  }
});

app.post("/update-user", function (req, res) {
  res.send(states.SUCCESS);
});

app.post("/account-details", function (req, res) {
  res.send(user);
});

app.post("/matchpoints", function (req, res) {
  res.send([location, destination]);
});

app.post("/available-rides", function (req, res) {
  res.send([ride]);
});

app.post("/book-ride", function (req, res) {
  res.send(states.SUCCESS);
});

app.post("/cancel-ride", function (req, res) {
  res.send(states.SUCCESS);
});

app.get("/reset", function (req, res) {
  serverState = require("./testData.json");
  resolveBookedRides();
  res.send(states.RESET_SUCCESS);
})

app.listen(3000, function () {
  resolveBookedRides();
  console.log("Server listening on port 3000!");
});
