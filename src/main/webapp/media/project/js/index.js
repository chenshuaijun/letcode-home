app.controller('crtlindex', [ '$scope', '$base64', '$http', '$uibModal','$log',
		function($scope, $base64, $http, $uibModal,$log) {

			$scope.items = [ 'item1', 'item2', 'item3' ];
			$scope.animationsEnabled = true;
			
			$scope.open = function(size,title) {
				var modalInstance = $uibModal.open({
					animation : $scope.animationsEnabled,
					templateUrl : 'views/common/modal/alert.html',
					controller:'ModalInstanceCtrl',
					size : size,
					backdrop : false,
					resolve : {
						items : function() {
							$scope.items = [ 'item6', 'item7', 'item8' ];
							return $scope.items;
						}
					} 
				}).result.then(function(selectedItem) {
					alert(selectedItem)
					$scope.selected = selectedItem;
				}, function() {
					$log.info('Modal dismissed at: ' + new Date());
				});
			}
		} ]);

app.controller('ModalInstanceCtrl', function ($scope, $uibModalInstance, items) {

  $scope.items = items;
  $scope.selected = {
    item: $scope.items[0]
  };

  $scope.ok = function () {
    $uibModalInstance.close();
  };

  $scope.cancel = function () {
    $uibModalInstance.dismiss('cancel');
  };
  $scope.toggleDropdown = function($event) {
    $event.preventDefault();
    $event.stopPropagation();
    $scope.status.isopen = !$scope.status.isopen;
  };
});