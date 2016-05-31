carelineApp.controller('CareReceiversController', function ($scope, receivers) {

    $scope.receivers = receivers.getAllReceivers();

    $scope.addCareReceiver = function () {
        receivers.addCareReceiver($scope);
    }

})