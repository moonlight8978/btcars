(function () {
    'use strict';

    angular
        .module('btcarsApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('all', {
            parent: 'cars',
            url: '/all',
            data: {
                authorities: [],
                pageTitle: 'All Cars ・ BT Cars - Best Choice!'
            },
            views: {
                'content@': {
                    templateUrl: 'app/cars/all/all.html',
                    controller: 'AllController',
                    controllerAs: 'vm'
                },
                'sidebar@all': {
                    templateUrl: 'app/cars/all/all-sidebar.html',
                    controller: 'AllSidebarController',
                    controllerAs: 'vm'
                }
            },
            onEnter: ['CarsService', function (CarsService) {
                CarsService.reset();
            }],
            resolve: {
                cars: function (Car, $q) {
                    let cars = Car.query();
                    return $q.resolve(cars.$promise).then(function (result) {
                        return result;
                    });
                }
            }
        });
    }

})();
