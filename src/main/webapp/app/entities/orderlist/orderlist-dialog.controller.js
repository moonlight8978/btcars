(function() {
    'use strict';

    angular
        .module('btcarsApp')
        .controller('OrderlistDialogController', OrderlistDialogController);

    OrderlistDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Orderlist', 'Car'];

    function OrderlistDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Orderlist, Car) {
        var vm = this;

        vm.orderlist = entity;
        vm.clear = clear;
        vm.save = save;
        vm.cars = Car.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.orderlist.id !== null) {
                Orderlist.update(vm.orderlist, onSaveSuccess, onSaveError);
            } else {
                Orderlist.save(vm.orderlist, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('btcarsApp:orderlistUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
