carelineApp.factory('schedule', function ($uibModal, communication) {
    schedule = {};

    schedule.getSchedule = function () {
        return communication.ajaxGet('schedule');
    };

    schedule.getScheduleById = function () {
        return communication.ajaxGet('schedule/' + id);
    };

    return schedule;
});