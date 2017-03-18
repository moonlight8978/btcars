(function() {
    'use strict';

    angular
        .module('btcarsApp')
        .controller('RecommendDialogController', RecommendDialogController);

    RecommendDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', '$q', 'entity', 'Recommend', 'Car'];

    function RecommendDialogController ($timeout, $scope, $stateParams, $uibModalInstance, $q, entity, Recommend, Car) {
        var vm = this;

        vm.recommend = entity;
        vm.clear = clear;
        vm.save = save;
        vm.cars = Car.query({filter: 'recommend-is-null'});
        $q.all([vm.recommend.$promise, vm.cars.$promise]).then(function() {
            if (!vm.recommend.car || !vm.recommend.car.id) {
                return $q.reject();
            }
            return Car.get({id : vm.recommend.car.id}).$promise;
        }).then(function(car) {
            vm.cars.push(car);
        });

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.recommend.id !== null) {
                Recommend.update(vm.recommend, onSaveSuccess, onSaveError);
            } else {
                Recommend.save(vm.recommend, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('btcarsApp:recommendUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
