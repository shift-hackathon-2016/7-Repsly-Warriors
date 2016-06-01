carelineApp.factory('login', function (communication, localStorageService) {
    login = {};

    login.signIn = function (scope)  {
            var params = {};
            params.username = scope.Username;
            params.password = scope.Password;

            var loginSuccess = function () {
                var encodedAuth = btoa(params.username + ':' + params.password);
                localStorageService.set('authorizationData', encodedAuth);
                window.location.pathname = '/';
            }

            var promise = communication.ajaxPost('Account/SignIn', params, loginSuccess);

    }

    return login;
});