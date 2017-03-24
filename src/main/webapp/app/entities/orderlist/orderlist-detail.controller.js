(function() {
    'use strict';

    angular
        .module('btcarsApp')
        .controller('OrderlistDetailController', OrderlistDetailController);

    OrderlistDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Orderlist', 'Car'];

    function OrderlistDetailController($scope, $rootScope, $stateParams, previousState, entity, Orderlist, Car) {
        var vm = this;

        vm.orderlist = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('btcarsApp:orderlistUpdate', function(event, result) {
            vm.orderlist = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
