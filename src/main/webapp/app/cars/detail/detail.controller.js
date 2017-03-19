(function () {
    'use strict';

    angular
        .module('btcarsApp')
        .controller('CarController', CarController);

    CarController.$inject = ['$stateParams', 'getCarFactory', 'CartService'];

    function CarController($stateParams, getCarFactory, CartService) {
        var vm = this;

        vm.addItem = CartService.addItem;
        vm.hot = [];
        vm.car = {};

        getCarFactory.getCarById($stateParams.id).then(function (responseCar) {
            vm.car = responseCar.data;
            getCarFactory.priceWithCommas(vm.car, false);
        }, function (error) {
            console.log('Error while getting hot cars!');
        });
        getCarFactory.getHotCar().then(function (responseHot) {
            vm.hot = responseHot.data;
            getCarFactory.priceWithCommas(vm.hot, true);
        }, function (error) {
            console.log('Error while getting hot cars!');
        });

        $(document).ready(function(){
            $('[data-toggle="tooltip"]').tooltip();
        });
    }
})();
