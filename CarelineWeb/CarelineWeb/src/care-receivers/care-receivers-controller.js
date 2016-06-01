carelineApp.controller('CareReceiversController', function ($scope, uiGmapGoogleMapApi, receivers, googleMaps) {

    $scope.receivers = receivers.getAllReceivers();

    $scope.addCareReceiver = function () {
        receivers.addCareReceiver($scope);
    }

    //prepare map
    $scope.map = {
        center:
            {
                latitude: 45.79404830932617,
                longitude: 15.94569206237793
            },
        zoom: 15
    };

    uiGmapGoogleMapApi.then(function (maps) {
        $scope.options = { scrollwheel: false };
        //$scope.markers = googleMaps.getMarkers();
        //googleMaps.registerEvents($scope.markers);
        //$scope.onMarkerClicked = googleMaps.onMarkerClicked;
    })

})