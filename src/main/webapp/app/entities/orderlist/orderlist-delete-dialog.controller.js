(function() {
    'use strict';

    angular
        .module('btcarsApp')
        .controller('OrderlistDeleteController',OrderlistDeleteController);

    OrderlistDeleteController.$inject = ['$uibModalInstance', 'entity', 'Orderlist'];

    function OrderlistDeleteController($uibModalInstance, entity, Orderlist) {
        var vm = this;

        vm.orderlist = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Orderlist.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
