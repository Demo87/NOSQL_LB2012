var cradle = require("cradle")
, util = require("util")
, fs = require("fs");




function isdef(ob) {
if(typeof(ob) == "undefined") return false;
return true;
}


var csv2json = function(csvdata, args) {
args = args || {};
var delim = isdef(args.delim) ? args.delim : ",";
var csvheaders = ['Location', 'Date'];

var csvlines = csvdata.split("\n");
for( var r in csvlines){
if (r<2){}else{
var tmp = csvlines[r].split(":");
csvheaders[r] = tmp[0];
csvlines[r] = csvlines[r].replace(tmp[0]+": ",'');
}

}

for (var i in csvlines) {

}
var lol ='{"'+"meteo"+'"'+":[{";
for (var i in csvlines) {

if(i<csvlines.length-1){
lol+='"'+csvheaders[i]+'":"'+csvlines[i]+'"';
}
if(i<csvlines.length-2){
lol+=",";
}
}
lol+='}]}';


return lol;
};


var connection = new(cradle.Connection)("localhost", 5984);
var db = connection.database('meteo');

var couchimport = function(filename, c) {
  var data = fs.readFileSync(filename, "utf-8");

  var dane = csv2json(data);
var output = JSON.parse(dane);

      db.save(c+'', output.meteo, function(er, ok) {
        if (er) {
          util.puts("Error: " + er);
          return;
        }
      });
    
  return c;
};
var filenames = [];

var walk = require('walk');

var walker = walk.walk('./infochimps/metar', { followLinks: false });

walker.on('file', function(root, stat, next) {
    filenames.push('infochimps/metar/' + stat.name);
    next();
});

walker.on('end', function() {
var i =0;
   filenames.forEach(function (name) {
i++;
  var n = couchimport(name, i);
  console.log("imported data from:", name, "(#" + n + ")");

});
});
