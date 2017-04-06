(function () {
    'use strict';

    angular
        .module('btcarsApp')
        .controller('AllController', AllController);

    AllController.$inject = ['getCarFactory', 'cars', 'CartService', 'BuyService'];

    function AllController(getCarFactory, cars, CartService, BuyService) {
        var vm = this;

         vm.all = cars;
         getCarFactory.priceWithCommas(vm.all, true);

        vm.addItem = CartService.addItem;
        vm.buy = BuyService.buy;
        vm.sort = 'id';
        vm.range = {
            min: 0,
            max: 999999999
        };
        vm.setPriceFilter = setPriceFilter;
        vm.hpRange = {
            min: 0,
            max: 99999
        };
        vm.setHpFilter = setHpFilter;

        function setPriceFilter(min, max) {
            vm.range.min = min;
            vm.range.max = max;
        }
        function setHpFilter(min, max) {
            vm.hpRange.min = min;
            vm.hpRange.max = max;
        }

        $(document).ready(function() {
            $('[data-toggle="tooltip"]').tooltip();
        });
    }
})();
