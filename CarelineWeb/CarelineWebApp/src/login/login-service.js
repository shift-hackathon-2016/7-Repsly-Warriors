carelineApp.factory('login', function (communication, localStorageService) {
    login = {};

    login.signIn = function (scope)  {
            var params = {};
            params.Username = scope.Username;
            params.Password = scope.Password;

            var loginSuccess = function () {
                var encodedAuth = btoa(params.Username + ':' + params.Password);
                localStorageService.set('authorizationData', encodedAuth);
                window.location = communication.baseUrl + '/';
            }

            var promise = communication.ajaxPost('Account/Login', params, loginSuccess);

    }

    return login;
});