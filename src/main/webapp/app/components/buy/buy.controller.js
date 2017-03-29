(function() {
    'use strict';

    angular
        .module('btcarsApp')
        .controller('BuyController', BuyController);

    BuyController.$inject = ['Orderlist', '$uibModalInstance', 'BuyService', 'Principal'];

    function BuyController (Orderlist, $uibModalInstance, BuyService, Principal) {
        var vm = this;

        // Customer object (database)
        vm.order = {};
        vm.order.total = BuyService.total;
        vm.order.cars = BuyService.cars;

        vm.save = save;
        vm.clear = clear;

        // Get current user info
        // Then bind to view
        Principal.identity().then(function(account) {
            vm.order.lastname = account.lastName;
            vm.order.firstname = account.firstName;
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            Orderlist.save(vm.order, onSaveSuccess, onSaveError);
        }

        function onSaveSuccess (result) {
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }
    }
})();
