(function() {
    'use strict';

    angular
        .module('btcarsApp')
        .factory('BuyService', BuyService);

    BuyService.$inject = ['$uibModal'];

    function BuyService ($uibModal) {
        var service = {
            open: open,
            buy: buy,
            cars: [],
            total: 0
        };

        var modalInstance = null;
        var resetModal = function () {
            service.cars = [];
            service.total = 0;
            modalInstance = null;
        };

        return service;

        function open () {
            if (modalInstance !== null) return;
            modalInstance = $uibModal.open({
                animation: true,
                templateUrl: 'app/components/buy/buy.html',
                controller: 'BuyController',
                controllerAs: 'vm'
            });
            modalInstance.result.then(
                resetModal,
                resetModal
            );
        }

        function buy(cars, isArray) {
            if (!isArray) {
                var car = cars;
                service.cars.push(car);
                service.total = car.price;
            } else {
                var length = cars.length,
                    i;
                for (i=0; i<length; i++) {
                    service.cars.push(cars[i]);
                    service.total += cars[i].price;
                }
            }
            service.open();
        }
    }
})();
