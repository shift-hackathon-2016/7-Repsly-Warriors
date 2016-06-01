carelineApp.controller('NavigationController', function ($scope, localStorageService) {
    var authData = localStorageService.get('authorizationData');
    if (authData != null) {
        $scope.isAuth = true;
    }
})