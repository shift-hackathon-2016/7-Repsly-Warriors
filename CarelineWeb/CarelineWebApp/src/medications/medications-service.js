carelineApp.factory('medications', function ($uibModal, communication) {
    medications = {};

    medications.getAllMedications = function () {
        return communication.ajaxGet('medications');
    };

    medications.addMedication = function (scope) {
        var params = {};
        params.Name = scope.Name;
        params.Description = scope.Description;
        params.MedColor = scope.MedColor;
        params.MedType = scope.MedType;
        params.Quantity = scope.Quantity;
        params.MedImg = scope.MedImg;

        communication.ajaxPost('Medicine/SaveMedicine', params);
    }

    return medications;
});