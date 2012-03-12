var cradle = require("cradle")
, util = require("util")
, fs = require("fs");


function isdefined(ob) {
if(typeof(ob) == "undefined") return false;
return true;
}

function splitCSV(str, sep) {
        for (var foo = str.split(sep = sep || ","), x = foo.length - 1, tl; x >= 0; x--) {
            if (foo[x].replace(/"\s+$/, '"').charAt(foo[x].length - 1) == '"') {
                if ((tl = foo[x].replace(/^\s+"/, '"')).length > 1 && tl.charAt(0) == '"') {
                    foo[x] = foo[x].replace(/^\s*"|"\s*$/g, '').replace(/""/g, '"');
                } else if (x) {
                    foo.splice(x - 1, 2, [foo[x - 1], foo[x]].join(sep));
                } else foo = foo.shift().split(sep).concat(foo);
            } else foo[x].replace(/""/g, '"');
        } return foo;
    };


var csvTojson = function(csvdata, args) {
	args = args || {};
	var delim = isdefined	(args.delim) ? args.delim : ",";

	var csvlines = csvdata.split("\n");
	var csvheaders = splitCSV(csvlines[0], delim);
	var csvrows = csvlines.slice(1, csvlines.length);

	var ret = {};
	ret.headers = csvheaders;
	ret.rows = [];

	for(var r in csvrows) {
		if (csvrows.hasOwnProperty(r)) {
			var row = csvrows[r];
			var rowitems = splitCSV(row, delim);
			if(row.length == 0) break;
			var rowob = {};
			for(var i in rowitems) {
				if (rowitems.hasOwnProperty(i)) {
				var item = rowitems[i];	
				rowob[csvheaders[i]] = item;
				}
			}

			ret.rows.push(rowob);
		}
	}

return ret;
};

var connection = new(cradle.Connection)("localhost", 5984);
var db = connection.database('pojufo');

var couchimport = function(plik) {
  var data = fs.readFileSync(plik, "utf-8");
  var dane = csvTojson(data);
  var counter = 0;
  for (var p in dane.rows) {
	wpis = dane.rows[p];
      	counter++;
      	db.save(counter+'', wpis, function(er, ok) {
        if (er) {
         // util.puts("Error: " + er);
          return;
        }
      });
    }
  return counter;
};

var lista = ["infochimps/ufo.us.csv"];
lista.forEach(function (name) {
  var n = couchimport(name);
 // console.log("imported data from:", name, "(#" + n + ")");
});
