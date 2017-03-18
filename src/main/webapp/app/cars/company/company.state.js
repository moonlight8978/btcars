(function () {
    'use strict';

    angular
        .module('btcarsApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider.state('company', {
            parent: 'cars',
            url: '/company={company}',
            data: {
                authorities: []
            },
            views: {
                'content@': {
                    templateUrl: 'app/cars/company/company.html',
                    controller: 'CompanyController',
                    controllerAs: 'vm'
                }
            },
            resolve: {}
        });
    }

})();
