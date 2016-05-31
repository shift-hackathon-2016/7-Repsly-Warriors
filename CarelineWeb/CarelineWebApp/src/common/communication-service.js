
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

        // test
        if (url == 'receivers') {
            hardcodedValues = {
                "List": [
                  {
                      "IDUser": 1,
                      "UserRowid": "42343242424",
                      "Name": "Pero",
                      "Address": "Perina adresa",
                      "Email": "p@p",
                      "Avatar": "",
                      "Username": "pero",
                      "Password": "pero",
                      "Manager": "false",
                      "AccountID": 1,
                      "Note": ""
                  },
                  {
                      "IDUser": 2,
                      "UserRowid": "23424325235",
                      "Name": "Sero",
                      "Address": "Serina adresa",
                      "Email": "s@s",
                      "Avatar": "",
                      "Username": "sero",
                      "Password": "sero",
                      "Manager": "true",
                      "AccountID": 1,
                      "Note": "dfsfsdfs"
                  }
                ]
            };
            return hardcodedValues;
        } else if (url == 'locations') {
            hardcodedValues = {
                "Tracking": [
                  {
                      "IDUserTracking": 1,
                      "UserTrackingRowid": "42343242424",
                      "EventTypeID": 1,
                      "EventDateTime": "9/9/2015",
                      "Latitude": 45.79404830932617,
                      "Longitude": 15.94569206237793,
                      "AccountID": 1,
                      "UserID": 1
                  },
                  {
                      "IDUserTracking": 2,
                      "UserTrackingRowid": "4234231111",
                      "EventTypeID": 1,
                      "EventDateTime": "9/9/2015",
                      "Latitude": 45.794002532958984,
                      "Longitude": 15.94548511505127,
                      "AccountID": 1,
                      "UserID": 1
                  }
                ]
            };
            return hardcodedValues;
        }


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