(function() {
    'use strict';

    angular
        .module('btcarsApp')
        .controller('CartController', CartController);

    CartController.$inject = ['$localStorage', '$uibModalInstance', 'BuyService'];

    function CartController ($localStorage, $uibModalInstance, BuyService) {
        var vm = this;

        vm.clear = clear;
        vm.buy = BuyService.buy;
        vm.cart = $localStorage.customer.carts;

        function clear() {
            $uibModalInstance.dismiss('cancel');
        }
    }
})();
