angular.module('demoApp', [])

.controller('DemoController', function($scope, $timeout, $http) {

	$scope.services = [ {
		path : '/demo/1',
		time : 1000,
		running : false
	}, {
		path : '/demo/2',
		time : 2000,
		running : false
	} ]

	$scope.start = function(service) {
		service.running = true;
		var callService = function() {
			service.timeout = $timeout(function() {
				$http.get(service.path).success(function(data) {
					service.lastResponse = data;
				})
				callService();
			}, service.time);
		};
		callService();
	}
	$scope.stop = function(service) {
		service.running = false;
		$timeout.cancel(service.timeout);
	}
	
	$scope.sendToGauge = function() {
		$http.get('/demo/gauge', {
			params: {
				value : $scope.value
			}
		}).success(function(response) {
			$scope.response = response;
		})
	}

});