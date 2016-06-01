/**
* Theme: Minton Admin Template
* Author: Coderthemes
* Component: Ion Slider
* 
*/
$(document).ready(function () {
    $("#location_range").ionRangeSlider({
        min: 50,
        max: 500,
        from: 100,
        postfix: ' meters'
    });

    $("#movement_time").ionRangeSlider({
        min: 3,
        max: 15,
        from: 8,
        postfix: ' hours'
    });

    $("#medication_quantity").ionRangeSlider({
        min: 3,
        max: 20,
        from: 3
    });
});