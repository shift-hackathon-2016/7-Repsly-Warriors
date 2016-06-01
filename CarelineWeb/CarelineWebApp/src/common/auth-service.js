carelineApp.factory('authService', function ($http, $q, localStorageService) {
    var _authentication = {
        isAuth: false
    };
    var authServiceFactory = {};
    var fillAuthData = function () {

        var authData = localStorageService.get('authorizationData');
        if (authData != null) {
            _authentication.isAuth = true;
        }

    }
    authServiceFactory.fillAuthData = fillAuthData;
    authServiceFactory.authentication = _authentication;
    return authServiceFactory;
});