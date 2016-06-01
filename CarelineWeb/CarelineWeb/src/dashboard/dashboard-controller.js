carelineApp.controller('DashboardController', function ($scope, uiGmapGoogleMapApi, dashboard, googleMaps) {
    //prepare map
    $scope.map = {
        center:
            {
                latitude: 45.79404830932617,
                longitude: 15.94569206237793
            },
        zoom: 15
    };

    $scope.receivers = dashboard.getAllReceivers();
    $scope.therapies = {};// dashboard.getTherapies();

    //uiGmapGoogleMapApi.then(function (maps) {
    //    $scope.options = { scrollwheel: false };
    //    $scope.markers = googleMaps.getMarkers();
    //    googleMaps.registerEvents($scope.markers);
    //    $scope.onMarkerClicked = googleMaps.onMarkerClicked;
    //})

})