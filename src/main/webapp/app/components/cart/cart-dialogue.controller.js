(function() {
    'use strict';

    angular
        .module('btcarsApp')
        .controller('CartDialogueController', CartDialogueController);

    CartDialogueController.$inject = ['$localStorage', '$uibModalInstance', 'CartService', 'getCarFactory', 'Customer', 'car', 'Principal', 'LoginService'];

    function CartDialogueController ($localStorage, $uibModalInstance, CartService, getCarFactory, Customer, car, Principal, LoginService) {
        var vm = this;

        vm.car = car;   // get car info from prev state
        vm.addItem = addItem;
        vm.deleteItem = deleteItem;
        vm.clear = clear;
        vm.error = null;
        vm.authenticate = Principal.isAuthenticated();

        function clear() {
            $uibModalInstance.dismiss('cancel');
        }

        /*  To delete an item from cart
            Find item's index
            Delete it, if fail show ERROR
            Then update total price */
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

        /*  Add item to cart
            Check if item was exist or not (by id)
            Add item to cart/ Or show ERROR
            Update total price      */
        function addItem(item) {
            vm.isSaving = true;
            if (vm.authenticate) {
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
            } else {
                vm.error = null;
                LoginService.open();
                vm.isSaving = false;
            }
        }
    }
})();
