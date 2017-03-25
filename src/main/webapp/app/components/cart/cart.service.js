(function () {
    'use strict';

    angular
        .module('btcarsApp')
        .factory('CartService', CartService);

    CartService.$inject = ['$uibModal'];

    function CartService($uibModal) {
        var cart = {
            addItem: addItem,
            openAdd: openAdd,
            deleteItem: deleteItem,
            openDelete: openDelete,
            car: null
        };

        var modalInstance = null;
        var resetModal = function () {
            cart.car = null;
            modalInstance = null;
        };

        return cart;

        function openAdd() {
            if (modalInstance !== null) return;
            modalInstance = $uibModal.open({
                animation: true,
                templateUrl: 'app/components/cart/cart-add.html',
                controller: 'CartController',
                controllerAs: 'vm',
                resolve: {
                    car: function () {
                        return cart.car;
                    }
                }
            });
            modalInstance.result.then(
                resetModal,
                resetModal
            );
        }

        function addItem(car) {
            cart.car = car;
            openAdd();
        }

        function openDelete() {
            if (modalInstance !== null) return;
            modalInstance = $uibModal.open({
                animation: true,
                templateUrl: 'app/components/cart/cart-delete.html',
                controller: 'CartController',
                controllerAs: 'vm',
                resolve: {
                    car: function () {
                        return cart.car;
                    }
                }
            });
            modalInstance.result.then(
                resetModal,
                resetModal
            );
        }

        function deleteItem(car) {
            cart.car = car;
            openDelete();
        }
    }

})();
