(function () {
    'use strict';

    angular
        .module('btcarsApp')
        .controller('DetailController', DetailController);

    DetailController.$inject = ['$stateParams', 'getCarFactory', 'CartService', 'BuyService'];

    function DetailController($stateParams, getCarFactory, CartService, BuyService) {
        var vm = this;

        vm.hot = [];
        vm.car = {};
        vm.addItem = addItem;
        vm.buy = BuyService.buy;

        getCarFactory.getCarById($stateParams.id).then(function (responseCar) {
            vm.car = responseCar.data;
            getCarFactory.priceWithCommas(vm.car, false);
        }, function (error) {
            console.log('Error while getting car!');
        });
        getCarFactory.getHotCar().then(function (responseHot) {
            vm.hot = responseHot.data;
            getCarFactory.priceWithCommas(vm.hot, true);
        }, function (error) {
            console.log('Error while getting hot cars!');
        });

        function addItem(car) {
            CartService.car = car;
            CartService.openAdd();
        }

        $(document).ready(function() {
            $('[data-toggle="tooltip"]').tooltip();
        });
    }
})();
