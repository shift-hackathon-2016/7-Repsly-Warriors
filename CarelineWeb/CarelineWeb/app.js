var carelineApp = angular.module("CarelineApp", ['ui.bootstrap', 'ngRoute', 'uiGmapgoogle-maps', 'LocalStorageModule']);

carelineApp.config(function ($routeProvider, $locationProvider, uiGmapGoogleMapApiProvider) {

    // set routes
    $routeProvider
        .when('/', {
            controller: 'DashboardController',
            templateUrl: 'src/dashboard/dashboard-template.html'
        })
        .when('/login', {
            controller: 'LoginController',
            templateUrl: 'src/login/login-template.html'
        })
        .when('/register', {
            controller: 'RegisterController',
            templateUrl: 'src/registration/register-template.html'
        })
        .when('/receivers', {
            controller: 'CareReceiversController',
            templateUrl: 'src/care-receivers/care-receivers-template.html'
        })
        .when('/receiver/:id', {
            controller: 'CareReceiverController',
            templateUrl: 'src/care-receivers/care-receiver-template.html'
            //receiver: function ($route, receiver) {
            //    return receiver.getReceiverById({ id: $route.current.params.id });
            //}
        })
        .when('/schedule', {
            controller: 'ScheduleController',
            templateUrl: 'src/schedule/schedule-template.html'
        })
        .when('/medications', {
            controller: 'MedicationsController',
            templateUrl: 'src/medications/medications-template.html'
        })
        .when('/settings', {
            controller: 'SettingsController',
            templateUrl: 'src/settings/settings-template.html'
        })
        .otherwise({ redirectTo: '/login' });

    $locationProvider.html5Mode(true);

    // configure google maps provider
    uiGmapGoogleMapApiProvider.configure({
        key: 'AIzaSyDc8TPBzBS2ANsV5vdlI5MuiWhk-5ozDag',
        v: '3',
        libraries: 'weather,geometry,visualization, places'
    });
});

carelineApp.run(['authService', function (authService) {
    authService.fillAuthData();

    //var baseUrl = 'http://localhost:52885/';
    //if (!authService.authentication.isAuth && window.location.pathname != '/login' && window.location.pathname != '/register') {
    //    window.location = baseUrl + 'login';
    //} else if (authService.authentication.isAuth && (window.location.pathname == '/login' || window.location.pathname == '/register')) {
    //    window.location = baseUrl + '/';
    //}
}]);