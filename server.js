var express = require("express");
var app = express();
var bodyParser = require("body-parser");

// --- TEST DATA ---
var states = {
  FAILURE: "FAILURE",
  SUCCESS: "SUCCESS",
  DATABASE_ERROR: "DATABASE_ERROR",
  INVALID_USER: "INVALID_USER",
  INVALID_PASSWORD: "INVALID_PASSWORD",
  INVALID_SESSION: "INVALID_SESSION"
};

var genders = {
  FEMALE : "female",
  MALE: "male"
}

var passenger = {
  name: "Eve",
  email: "eve@matchrider.de",
  photo: "/assets/images/eve.jpg",
  description: "I search for lifeforms!",
  phone: "555-1234",
  facebookId: "4321",
  id: "42",
  gender: genders.FEMALE,
  password: "12345"
};

var session = {
  sessionId: "ab12",
  userId: "42"
};

var driver = {
  name: "Wall-E",
  photo: "/assets/images/wall-e.jpg",
  score: 3,
  phone: "555-5678",
  description: "I clean up the earth!"
}

var car = {
  photo: "/assets/images/car.jpg",
  model: "russian",
  color: "some"
}

var location = {
  lat: 49.406517,
  long: 8.672457,
  name: "Dezernat 16",
  photo: "/assets/images/dezernat.jpg"
}

var destination = {
  lat: 49.503738,
  long: 8.462728,
  name: "Happy Hour",
  photo: "/assets/images/happy-hour.jpg"
}


var ride = {
  start: location,
  destination: destination,
  driver: driver,
  date: new Date(2016, 15, 2),
  price: [2, 40],
  distance: 20.1,
  car: car
}

function warning (request, errorState) {
  return {
    request: request,
    error: errorState
  }
}


// --- ROUTES ---

app.use(bodyParser.json());
app.use("/assets", express.static(__dirname + "/assets"));
  
app.post("/login", function (req, res) {
  console.log(req.body.email);
  var errorResponse;
  var reqBody = req.body;
  if (reqBody.email === passenger.email) {
    if (reqBody.password === passenger.password) {
      res.send(session);
    } else {
      errorResponse = warning(reqBody, states.INVALID_PASSWORD);
      res.send(errorResponse);
    }
  } else {
    errorResponse = warning(reqBody, states.INVALID_USER);
    res.send(errorResponse);
  }
});

app.post("/account-details", function (req, res) {
  res.send(passenger);
});

app.post("/matchpoints", function (req, res) {
  res.send([location, destination]);
});

app.post("/available-rides", function (req, res) {
  res.send([ride]);
});

app.post("/booked-rides", function (req, res) {
  var booked_rides =[ride, ride, ride, ride, ride, ride];
  res.send(booked_rides);
});

app.post("/book-ride", function (req, res) {
  res.send(states.SUCCESS);
});

app.post("/cancel-ride", function (req, res) {
  res.send(states.SUCCESS);
});

app.post("/update-user", function (req, res) {
  res.send(states.SUCCESS);
});

app.listen(3000, function () {
  console.log("Server listening on port 3000!");
});
