var cradle = require("cradle")
, util = require("util")
, fs = require("fs");


var connection = new(cradle.Connection)("localhost", 5984);
var db = connection.database('katastrofy');
var couchimport = function(filename) {
  var data = fs.readFileSync(filename, "utf-8");
  var dane = JSON.parse(data);
console.log(dane);
  var counter = 0;
      db.save("_mr", dane, function(er, ok) {

        if (er) {
          util.puts("Error: " + er);
          return;
        }
      });

  return counter;
};

var filenames = ["mapreduce.txt"];

filenames.forEach(function (name) {
  var n = couchimport(name);
});





