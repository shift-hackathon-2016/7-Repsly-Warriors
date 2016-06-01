
/**
 * Ajax calls wrapper.
 */
carelineApp.service('communication', function ($http, $q, authService, localStorageService) {
    var baseUrl = 'http://api.carelineapp.me/api/';
    
    //POST
    this.ajaxPost = function (url, params, successHandler) {
        var deferred = $q.defer();

        var authData = localStorageService.get('authorizationData');

        var config = {
            headers: {
                'Authorization': 'Basic ' + authData,
                'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
            }
        }

        $http.post(baseUrl + url, JSON.stringify(params), config)
            .success(function (data, status, headers, config) {
                deferred.resolve(data);
                if (successHandler) {
                    successHandler();
                }
            })
            .error(function (data, status) {
                if (successHandler) {
                    successHandler();
                }
                //alert(data);
            });
        return deferred.promise;
    }

    //GET
    this.ajaxGet = function (url, param) {

        var authData = localStorageService.get('authorizationData');

        var config = {
            'Authorization': 'Basic ' + authData,
            'Content-Type': 'application/json',
            'Access-Control-Allow-Origin': '*'
        }
        var deferred = $q.defer();
        $http.get(baseUrl + url, param, config)
            .success(function (data, status, headers, config) {
                if (data.indexOf && data.indexOf('<unauthorized></unauthorized>') > 0) {
                    window.location = baseUrl + 'login';
                    return;
                }
                deferred.resolve(data);
            })
            .error(function (data, status) {
                deferred.reject;
            });
        return deferred.promise;
    }


});