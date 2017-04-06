(function () {
    'use strict';

    angular
        .module('btcarsApp')
        .controller('DetailsContentController', DetailsContentController);

    DetailsContentController.$inject = ['$stateParams', 'car', 'getCarFactory', 'CartService', 'BuyService'];

    function DetailsContentController($stateParams, car, getCarFactory, CartService, BuyService) {
        var vm = this;

        vm.addItem = CartService.addItem;
        vm.buy = BuyService.buy;

        vm.car = car;
        getCarFactory.priceWithCommas(vm.car, false);

        $(document).ready(function() {
            $('[data-toggle="tooltip"]').tooltip();
        });

    }
})();
