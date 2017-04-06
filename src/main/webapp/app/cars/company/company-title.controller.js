(function () {
    'use strict';

    angular
        .module('btcarsApp')
        .controller('CompanyTitleController', CompanyTitleController);

    CompanyTitleController.$inject = ['cars'];

    function CompanyTitleController(cars) {
        var vm = this;

        vm.cars = cars;

    }

})();
