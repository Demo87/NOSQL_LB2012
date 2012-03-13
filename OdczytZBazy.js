var http = require('http'),
    jquery = require('jquery'),
    url = require('url');

var options = {
   host: 'localhost',
   port: 5984,
   path: '/meteo/_all_docs',
   method: 'GET'
};

var req = http.request(options, function(res) {
  //console.log('STATUS: ' + res.statusCode);
  //console.log('HEADERS: ' + JSON.stringify(res.headers));
  res.setEncoding('utf8');
  res.on('data', function (chunk) {
		chunk = chunk.replace(",",'');
		chunk = chunk.replace("\n",'');
		chunk = chunk.replace("{\"id\":\"",'').replace("{[",'').replace("]}",'');
		var tmp = chunk.split('"');
    console.log(tmp[0]);
  });
});

req.on('error', function(e) {
//\console.log('problem with request: ' + e.message);
});

// write data to request body
//req.write('data\n');
//req.write('data\n');
req.end();

