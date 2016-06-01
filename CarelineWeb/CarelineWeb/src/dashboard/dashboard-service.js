carelineApp.factory('dashboard', function ($uibModal, communication) {
    dashboard = {};
    dashboard.getAllReceivers = function () {
        return communication.ajaxGet('CareReceiver/GetList');
    };
    //dashboard.getTherapies = function () {
    //    return communication.ajaxGet('CareReceiver/TherapyCard');
    //};
    return dashboard;
});