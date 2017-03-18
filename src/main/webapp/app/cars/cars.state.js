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
                authorities: [],
                pageTitle: 'All Cars ・ BT Cars - Best Choice!'
            },
            views: {
                'content@': {
                    templateUrl: 'app/cars/cars.html',
                    controller: 'CarsController',
                    controllerAs: 'vm'
                }
            },
            resolve: {}
        });
    }

})();
