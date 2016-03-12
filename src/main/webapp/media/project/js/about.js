app.controller('crtlabout', function($scope, $http, $routeParams) {
	$scope.name = "crtlabout";
	$scope.params = $routeParams;

	$scope.newball = {}
	$http.post('ballService.json', {}).success(function(data) {
		$scope.newball.date_no = data.result.date_no;
		$scope.newball.sale_count = data.result.sale_count;
		$scope.newball.sale_amt = data.result.red_ball1;
		$scope.newball.red_ball1 = data.result.red_ball1;
		$scope.newball.red_ball2 = data.result.red_ball2;
		$scope.newball.red_ball3 = data.result.red_ball3;
		$scope.newball.red_ball4 = data.result.red_ball4;
		$scope.newball.red_ball5 = data.result.red_ball5;
		$scope.newball.red_ball6 = data.result.red_ball6;
		$scope.newball.blue_ball1 = data.result.blue_ball1;
	});
})