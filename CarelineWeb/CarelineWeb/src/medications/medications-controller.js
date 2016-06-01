carelineApp.controller('MedicationsController', function ($scope, medications) {

    $scope.medications = medications.getAllMedications();

    $scope.addMedication = function () {
        medications.addMedication($scope);
    }
})