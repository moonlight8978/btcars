(function () {
    'use strict';

    angular
        .module('btcarsApp')
        .controller('CompanyController', CompanyController);

    CompanyController.$inject = ['$stateParams', 'getCarFactory', 'CartService', 'BuyService', 'Car'];

    function CompanyController($stateParams, getCarFactory, CartService, BuyService, Car) {
        var vm = this;

        vm.addItem = CartService.addItem;
        vm.buy = BuyService.buy;
        vm.cars = [];
        vm.hots = [];
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

        vm.loading = true;
        vm.found = false;

        Car.query({ category: 'hot' }, function (result) {
            vm.hot = result;
            getCarFactory.priceWithCommas(vm.hot, true);
        }, function (error) {
            console.log('Error while getting hot cars!');
        });

        Car.query({ company: $stateParams.company }, function (result) {
            vm.cars = result;
            vm.loading = false;
            vm.found = true;
            getCarFactory.priceWithCommas(vm.cars, true);
        }, function (error) {
            console.log('Error while getting cars!');
            vm.loading = false;
            vm.found = false;
        });

        function setPriceFilter(min, max) {
            vm.range.min = min;
            vm.range.max = max;
        }
        function setHpFilter(min, max) {
            vm.hpRange.min = min;
            vm.hpRange.max = max;
        }

        $(document).ready(function(){
            $('[data-toggle="tooltip"]').tooltip();
        });
    }

})();
