(function() {
    'use strict';

    angular
        .module('btcarsApp')
        .controller('CartDialogueController', CartDialogueController);

    CartDialogueController.$inject = ['$localStorage', '$uibModalInstance', 'CartService', 'getCarFactory', 'Customer', 'car'];

    function CartDialogueController ($localStorage, $uibModalInstance, CartService, getCarFactory, Customer, car) {
        var vm = this;

        vm.car = car;
        vm.addItem = addItem;
        vm.deleteItem = deleteItem;
        vm.clear = clear;
        vm.error = null;

        function clear() {
            $uibModalInstance.dismiss('cancel');
        }

        function deleteItem(item) {
            vm.isSaving = true;
            var index = $localStorage.customer.cars.indexOf(item);
            $localStorage.customer.cars.splice(index, 1);
            Customer.update($localStorage.customer, function (result) {
                $localStorage.total = getCarFactory.calTotalPrice($localStorage.customer.cars);
                $localStorage.totalFix = $localStorage.total.toLocaleString();
                vm.error = null;
                $uibModalInstance.close(result);
                vm.isSaving = false;
            }, function () {
                vm.error = 'ERROR';
                vm.isSaving = false;
            });
        }

        function addItem(item) {
            vm.isSaving = true;
            var length = $localStorage.customer.cars.length;
            var i=0;
            if (length > 0) {
                for (i=0; i<length; i++) {
                    if (item.id == $localStorage.customer.cars[i].id)
                        break;
                }
            }
            if (i == length) {
                $localStorage.customer.cars.push(item);
                Customer.update($localStorage.customer, function (result) {
                    $localStorage.total = getCarFactory.calTotalPrice($localStorage.customer.cars);
                    $localStorage.totalFix = $localStorage.total.toLocaleString();
                    vm.error = null;
                    $uibModalInstance.close(result);
                    vm.isSaving = false;
                });
            } else {
                vm.error = 'ERROR';
                vm.isSaving = false;
            }
        }
    }
})();
