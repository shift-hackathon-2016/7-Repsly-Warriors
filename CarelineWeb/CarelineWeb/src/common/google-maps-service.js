carelineApp.factory('googleMaps', function ($uibModal, communication) {
    googleMaps = {};


    function getLocations() {
        return communication.ajaxGet('locations');
    }

    function getLocationsById(id) {
        return communication.ajaxGet('locations/' + id);
    }

    googleMaps.getMarkers = function () {
        var locations = getLocations();
        var markers = [];
        var markersCount = locations.Tracking.length;
        for (var i = 0; i < markersCount; i++) {
            markers.push(
                {
                    id: i,
                    coords: {
                        latitude: locations.Tracking[i].Latitude,
                        longitude: locations.Tracking[i].Longitude
                    }
                });

        }
        return markers;
    }
    googleMaps.getMarkersById = function (id) {
        var locations = getLocationsById(id);
        var markers = [];
        var markersCount = locations.Tracking.length;
        for (var i = 0; i < markersCount; i++) {
            markers.push(
                {
                    id: i,
                    coords: {
                        latitude: locations.Tracking[i].Latitude,
                        longitude: locations.Tracking[i].Longitude
                    }
                });

        }
        return markers;
    }
    googleMaps.onMarkerClicked = function (marker) {
        //marker.showWindow = true;
        //$scope.$apply();
        window.alert("Marker: lat: " + marker.model.coords.latitude + ", lon: " + marker.model.coords.longitude + " clicked!!")
    };

    googleMaps.registerEvents = function (markers) {

        for (var i = 0; i < markers.length; i++) {
            markers[i].onClicked = function () {
                onMarkerClicked(markers[i]);
            };
        }
    }

    return googleMaps;
});