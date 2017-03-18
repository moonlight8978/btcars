(function() {
    'use strict';

    angular
        .module('btcarsApp')
        .controller('RecommendDeleteController',RecommendDeleteController);

    RecommendDeleteController.$inject = ['$uibModalInstance', 'entity', 'Recommend'];

    function RecommendDeleteController($uibModalInstance, entity, Recommend) {
        var vm = this;

        vm.recommend = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Recommend.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
