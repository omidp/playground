jQuery(document).ready(
		function() {
			
			request.onOpen = function(response) {
				console.log('opned');
			};

			request.onClientTimeout = function(r) {
				console.log('timeout');
			}

			request.onReopen = function(response) {
				console.log('reopen');
			}

			request.onTransportFailure = function(errorMsg, request) {
				console.log('transport failed');
			}

			request.onMessagePublished = function(response) {
				console.log('onMessagePublished');
			}

			request.onMessage = function(response) {
				console.log(response.responseBody);
				console.log(contextPath + '/login.seam');
				if (response.responseBody == 'logout') {
					window.location = contextPath + '/login.seam';
				}
			}

			request.onClose = function(response) {
				console.log('onclose');
			}

			request.onError = function(response) {
				console.log('onerror');
			}

			request.onReconnect = function(request, response) {
				console.log('onreconnect');
			}
			subSocket = socket.subscribe(request);

		});