const http = require('http');
const mime = require('mime');
const fs = require('fs');
const path = require('path');
var cache = {};

const hostname = '127.0.0.1';
const port = 3000;


function sendFile(response, filePath, fileContents) {
response.writeHead(200,{"content-type": mime.lookup(path.basename(filePath))});
response.end(fileContents);
}

function send404(response) {
response.writeHead(404, {'Content-Type': 'text/plain'});
response.write('Error 404: resource not found.');
response.end();
}

function serveStatic(response, cache, absPath) {
	fs.exists(absPath, function(exists) {
		if (exists) {
		fs.readFile(absPath, function(err, data) {
			if (err) {
				send404(response);
			} else {
				sendFile(response, absPath, data);
			}
		});
} else {
	send404(response);
		}
		});
	}


const server = http.createServer(function(request, response) {
var filePath = false;
if (request.url == '/') {
		filePath = 'index.json';
} else {
		filePath = request.url;
	}
var absPath = './' + filePath;
serveStatic(response, cache, absPath);
});

server.listen(port, hostname, function() {
console.log("Server listening on port 3000.");
});
