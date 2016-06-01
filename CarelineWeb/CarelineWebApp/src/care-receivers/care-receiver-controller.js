carelineApp.controller('CareReceiverController', function ($scope, $location, uiGmapGoogleMapApi, receivers, googleMaps) {

    var receiverId = $location.path().split('/receiver/')[1];
    $scope.receiver = receivers.getReceiverById(receiverId);
    $scope.schedule = receivers.getScheduleById(receiverId);
    $scope.history = receivers.getTherapyById(receiverId);

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
        $scope.markers = googleMaps.getMarkersById(receiverId);
        googleMaps.registerEvents($scope.markers);
        $scope.onMarkerClicked = googleMaps.onMarkerClicked;
    })

})