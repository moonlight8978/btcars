(function() {
    'use strict';

    angular
        .module('btcarsApp')
        .controller('RecommendDetailController', RecommendDetailController);

    RecommendDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Recommend', 'Car'];

    function RecommendDetailController($scope, $rootScope, $stateParams, previousState, entity, Recommend, Car) {
        var vm = this;

        vm.recommend = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('btcarsApp:recommendUpdate', function(event, result) {
            vm.recommend = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
