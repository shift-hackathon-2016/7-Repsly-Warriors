carelineApp.controller('ScheduleController', function ($scope, schedule) {

    $scope.schedule = schedule.getSchedule();

})