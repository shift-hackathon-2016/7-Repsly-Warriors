carelineApp.controller('LoginController', function ($scope, login) {

    $scope.signIn = function () {
        login.signIn($scope);
    }
})