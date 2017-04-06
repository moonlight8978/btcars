(function () {
    'use strict';

    angular
        .module('btcarsApp')
        .controller('DetailsTitleController', DetailsTitleController);

    DetailsTitleController.$inject = ['car'];

    function DetailsTitleController(car) {
        var vm = this;

        vm.car = car;

    }

})();
