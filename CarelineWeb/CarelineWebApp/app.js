var carelineApp = angular.module("CarelineApp", ['ui.bootstrap', 'ngRoute', 'uiGmapgoogle-maps']);

carelineApp.config(function ($routeProvider, $locationProvider, uiGmapGoogleMapApiProvider) {
    // set routes
    $routeProvider
        .when('/', {
            controller: 'DashboardController',
            templateUrl: 'src/dashboard/dashboard-template.html'
        })
        .when('/receivers', {
            controller: 'CareReceiversController',
            templateUrl: 'src/care-receivers/care-receivers-template.html'
        })
        .when('/receiver/:id', {
            controller: 'CareReceiversController',
            templateUrl: 'src/care-receivers/care-receiver-template.html'
        })
        .when('/medications', {
            controller: 'MedicationsController',
            templateUrl: 'src/medications/medications-template.html'
        })
        .when('/settings', {
            controller: 'SettingsController',
            templateUrl: 'src/settings/settings-template.html'
        })
        .otherwise({ redirectTo: '/' });

    $locationProvider.html5Mode(true);

    // configure google maps provider
    uiGmapGoogleMapApiProvider.configure({
        key: 'AIzaSyDc8TPBzBS2ANsV5vdlI5MuiWhk-5ozDag',
        v: '3',
        libraries: 'weather,geometry,visualization, places'
    });
});