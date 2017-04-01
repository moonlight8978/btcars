(function() {
    'use strict';

    angular
        .module('btcarsApp')
        .controller('BuyController', BuyController);

    BuyController.$inject = ['Orderlist', '$uibModalInstance', 'BuyService', 'Principal', 'LoginService'];

    function BuyController (Orderlist, $uibModalInstance, BuyService, Principal, LoginService) {
        var vm = this;

        // Customer object (database)
        vm.order = {};
        vm.order.total = BuyService.total;
        vm.order.totalFix = vm.order.total.toLocaleString();
        vm.order.cars = BuyService.cars;

        //  check if user logged in or not
        vm.authenticate = Principal.isAuthenticated();

        //  form action method
        //  and validate form variable
        vm.openList = BuyService.openList;
        vm.save = save;
        vm.clear = clear;
        vm.error = null;

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
            if (vm.authenticate)
                Orderlist.save(vm.order, onSaveSuccess, onSaveError);
            else {
                LoginService.open();
                vm.isSaving = false;
            }
        }

        function onSaveSuccess (result) {
            vm.error = null;
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.error = 'ERROR';
            vm.isSaving = false;
        }
    }
})();
