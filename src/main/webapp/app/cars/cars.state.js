(function () {
    'use strict';

    angular
        .module('btcarsApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider.state('cars', {
            parent: 'app',
            url: '/cars',
            data: {
                authorities: []
            },
            views: {
                'content@': {
                    templateUrl: 'app/cars/cars.html',
                    controller: 'carsController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                all: function (getCarFactory) {
                    var response = getCarFactory.getData('api/cars/').then(function (response) {
                        return response;
                    });
                    return response;
                }
            }
        });
    }

})();
