(function () {
    'use strict';

    angular
        .module('btcarsApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider.state('detail', {
            parent: 'cars',
            url: '/car?id',
            data: {
                authorities: [],
                pageTitle: 'BT Cars - Best Choice!'
            },
            views: {
                'content@': {
                    templateUrl: 'app/cars/detail/detail.html',
                    controller: 'CarController',
                    controllerAs: 'vm'
                }
            }
        });
    }

})();
