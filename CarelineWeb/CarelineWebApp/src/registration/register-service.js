carelineApp.factory('register', function (communication, localStorageService) {
    register = {};
    register.signUp = function (scope) {
        var params = {};
        params.Username = scope.Username;
        params.Password = scope.Password;
        params.RepeatPassword = scope.RepeatPassword;

        var registerSuccess = function () {
            var encodedAuth = btoa(params.Username + ':' + params.Password);
            localStorageService.set('authorizationData', encodedAuth);
            window.location = communication.baseUrl + '/';
        }

        var promise = communication.ajaxPost('Account/Register', params, registerSuccess);

    }
    return register;
});