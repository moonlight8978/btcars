(function () {
    'use strict';

    angular
        .module('btcarsApp')
        .controller('AllSidebarController', AllSidebarController);

    AllSidebarController.$inject = ['getCarFactory', 'hot', 'CarsService'];

    function AllSidebarController(getCarFactory, hot, CarsService) {
        var vm = this;

        vm.hot = hot;
        getCarFactory.priceWithCommas(vm.hot, true);

        vm.setPriceFilter = setPriceFilter;
        vm.setHpFilter = setHpFilter;
        vm.setSort = setSort;

        function setSort(value) {
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
