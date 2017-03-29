(function() {
    'use strict';

    angular
        .module('btcarsApp')
        .controller('CartController', CartController);

    CartController.$inject = ['$localStorage', '$uibModalInstance', 'BuyService', 'CartService'];

    function CartController ($localStorage, $uibModalInstance, BuyService, CartService) {
        var vm = this;

        vm.clear = clear;
        vm.buy = BuyService.buy;
        vm.deleteItem = CartService.deleteItem;

        vm.total = $localStorage.totalFix;
        vm.cart = $localStorage.customer.cars;

        function clear() {
            $uibModalInstance.dismiss('cancel');
        }
    }
})();
