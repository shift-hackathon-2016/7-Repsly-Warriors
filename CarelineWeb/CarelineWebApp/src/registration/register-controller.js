carelineApp.controller('RegisterController', function ($scope, register) {

    $scope.signUp = function () {
        register.signUp($scope);
    }
})