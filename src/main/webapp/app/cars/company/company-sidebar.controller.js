(function () {
    'use strict';

    angular
        .module('btcarsApp')
        .controller('CompanySidebarController', CompanySidebarController);

    CompanySidebarController.$inject = ['getCarFactory', 'hot', 'CarsService'];

    function CompanySidebarController(getCarFactory, hot, CarsService) {
        var vm = this;

        vm.hot = hot;
        getCarFactory.priceWithCommas(vm.hot, true);

        vm.setPriceFilter = setPriceFilter;
        vm.setHpFilter = setHpFilter;
        vm.setOrderBy = setOrderBy;

        function setOrderBy(value) {
            CarsService.setSort(value);
        }

        function setHpFilter(min, max) {
            CarsService.setHpFilter(min, max);
        }

        function setPriceFilter(min, max) {
            CarsService.setPriceFilter(min, max);
        }


    }

})();
