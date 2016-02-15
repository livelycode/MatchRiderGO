var express = require("express");
var app = express();

// --- TEST DATA ---
var states = {
  FAILURE: 0,
  SUCCESS: 1,
  DATABASE_ERROR: 2
}
var passenger = {
  name: "Eve",
  email: "eve@matchrider.de",
  photo: "/assets/images/eve.jpg",
  description: "I search for lifeforms!",
  phone: "555-1234",
  facebookId: "4321",
  id: "42"
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

// --- ROUTES ---

app.use("/assets", express.static(__dirname + "/assets"));
  
app.post("/login", function (req, res) {
  console.log(req);
  res.send(session);
});

app.post("/account-details", function (req, res) {
  console.log(req);
  res.send(passenger);
});

app.post("/match-points", function (req, res) {
  console.log(req);
  res.send([location, destination]);
});

app.post("/available-rides", function (req, res) {
  console.log(req);
  res.send([ride]);
});

app.post("/booked-rides", function (req, res) {
  console.log(req);
  res.send([ride]);
});

app.post("/book-ride", function (req, res) {
  console.log(req);
  res.send(states.SUCCESS);
});

app.post("/cancel-ride", function (req, res) {
  console.log(req);
  res.send(states.SUCCESS);
});

app.post("/update-user", function (req, res) {
  console.log(req);
  res.send(states.SUCCESS);
});

app.listen(3000, function () {
  console.log("Server listening on port 3000!");
});
