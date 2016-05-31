
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
    this.ajaxPost = function (url, params) {
        var deferred = $q.defer();

        config.headers['Content-Type'] = 'application/x-www-form-urlencoded; charset=UTF-8';
        alert(JSON.stringify(params));

        $http.post(url, JSON.stringify(params), config)
            .success(function (data, status, headers, config) {
                deferred.resolve(data);
            })
            .error(function (data, status) {
                alert(data);
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
        } else if (url == 'medications') {
            hardcodedValues = {
                "List": [
                  {
                      "MedicineID": 1,
                      "Name": "Pill name",
                      "MedImg": 1,
                      "Description": "Description",
                      "MedColor": "#956091",
                      "MedType": "Med type",
                      "Quantity": 1,
                      "AccountID": 1
                  },
                  {
                      "MedicineID": 2,
                      "Name": "Pill name2",
                      "MedImg": 1,
                      "Description": "Description2",
                      "MedColor": "#6b2fac",
                      "MedType": "Med type2",
                      "Quantity": 1,
                      "AccountID": 1
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