(function () {
    'use strict';

    angular
        .module('btcarsApp')
        .controller('DetailController', DetailController);

    DetailController.$inject = ['$stateParams', 'getCarFactory', 'CartService', 'BuyService', 'Car'];

    function DetailController($stateParams, getCarFactory, CartService, BuyService, Car) {
        var vm = this;

        vm.hot = [];
        vm.car = {};
        vm.addItem = CartService.addItem;
        vm.buy = BuyService.buy;
        vm.loading = true;
        vm.found = false;

        Car.get({ id: $stateParams.id }, function (result) {
            vm.car = result;
            getCarFactory.priceWithCommas(vm.car, false);
            vm.loading = false;
            vm.found = true;
        }, function (error) {
            console.log('Error while getting car!');
            vm.loading = false;
            vm.found = false;
        });
        
        Car.query({ category: 'hot' }, function (result) {
            vm.hot = result;
            getCarFactory.priceWithCommas(vm.hot, true);
        }, function (error) {
            console.log('Error while getting hot cars!');
        });

        $(document).ready(function() {
            $('[data-toggle="tooltip"]').tooltip();
        });
    }
})();
