(function() {
    'use strict';

    angular
        .module('btcarsApp')
        .controller('CarDetailController', CarDetailController);

    CarDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Car', 'Customer', 'Orderlist'];

    function CarDetailController($scope, $rootScope, $stateParams, previousState, entity, Car, Customer, Orderlist) {
        var vm = this;

        vm.car = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('btcarsApp:carUpdate', function(event, result) {
            vm.car = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
