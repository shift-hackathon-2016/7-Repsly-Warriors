carelineApp.factory('receivers', function ($uibModal, communication) {
    receivers = {};

    receivers.getAllReceivers = function () {
        return communication.ajaxGet('CareReceiver/GetList');
    };

    receivers.getReceiverById = function (id) {
        return communication.ajaxGet('receiver/' + id);
    };

    receivers.getScheduleById = function (id) {
        return communication.ajaxGet('schedule/' + id);
    };

    receivers.getTherapyById = function (id) {
        return communication.ajaxGet('CareReceiver/TherapyCard' + id);
    };

    receivers.getScheduleById = function () {
        return communication.ajaxGet('schedule/' + id);
    };

    receivers.addCareReceiver = function (scope) {
        var params = {};
        params.Name = scope.Name;
        params.Email = scope.Email;
        params.Address = scope.Address;
        params.Username = scope.Username;
        params.Password = scope.Password;
        params.Avatar = scope.Avatar;
        params.Note = scope.Note;

        communication.ajaxPost('CareReceiver/SaveCareReceiver', params);
    }

    return receivers;
});