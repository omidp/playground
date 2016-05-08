var http = require('http');
var cnt = 0;

http.createServer(function(req, res){
	
	cnt++;
	res.write('I have been accessed ' + cnt + " times");
	res.end();
	
	}).listen(3000, function(){console.log('server started');});
