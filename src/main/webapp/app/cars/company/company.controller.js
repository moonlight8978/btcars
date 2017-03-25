(function () {
    'use strict';

    angular
        .module('btcarsApp')
        .controller('CompanyController', CompanyController);

    CompanyController.$inject = ['$stateParams', 'getCarFactory', 'CartService', 'BuyService'];

    function CompanyController($stateParams, getCarFactory, CartService, BuyService) {
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

        getCarFactory.getHotCar().then(function (responseHot) {
            vm.hot = responseHot.data;
            getCarFactory.priceWithCommas(vm.hot, true);
        }, function (error) {
            console.log('Error while getting hot cars!');
        });
        getCarFactory.getCarByCompany($stateParams.company).then(function (responseCarFilterByCompany) {
            vm.cars = responseCarFilterByCompany.data;
            getCarFactory.priceWithCommas(vm.cars, true);
        }, function (error) {
            console.log('Error while getting cars!');
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
