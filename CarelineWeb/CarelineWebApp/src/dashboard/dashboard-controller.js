carelineApp.controller('DashboardController', function ($scope, uiGmapGoogleMapApi, dashboard) {
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
        $scope.markers = dashboard.getMarkers();
        dashboard.registerEvents($scope.markers);
        $scope.onMarkerClicked = dashboard.onMarkerClicked;
    })

})