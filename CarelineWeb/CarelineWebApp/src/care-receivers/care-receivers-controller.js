carelineApp.controller('CareReceiversController', function ($scope, receivers) {

    $scope.receivers = receivers.getAllReceivers();

})