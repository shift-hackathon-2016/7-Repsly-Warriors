
/**
 * Ajax calls wrapper.
 */
carelineApp.service('communication', function ($http, $q) {
    var baseUrl = 'http://localhost:60319/api/';
    var config = {
        headers: {
            'Authorization': 'Basic ' + ''
        }
    }

    //POST
    this.ajaxPost = function (url, param) {
        var deferred = $q.defer();
        $http.post(url, param, config)
            .success(function (data, status, headers, config) {
                deferred.resolve(data);
            })
            .error(function (data, status) {
                alert(status);
            });
        return deferred.promise;
    }

    //GET
    this.ajaxGet = function (url, param) {
        var deferred = $q.defer();
        $http.get(url, param, config)
            .success(function (data, status, headers, config) {
                deferred.resolve(data);
            })
            .error(function (data, status) {
                deferred.reject;
            });
        return deferred.promise;
    }


});