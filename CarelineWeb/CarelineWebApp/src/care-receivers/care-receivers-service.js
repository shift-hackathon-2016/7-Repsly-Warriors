carelineApp.factory('receivers', function ($uibModal, communication) {
    receivers = {};

    receivers.getAllReceivers = function () {
        return communication.ajaxGet('receivers');
    };


    return receivers;
});