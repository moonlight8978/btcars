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
                    controller: 'companyController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                cars: function (getCarFactory, $stateParams) {
                    var response = getCarFactory.getData('/api/cars/' + $stateParams.company).then(function (response) {
                        return response;
                    });
                    return response;
                }
            }
        });
    }

})();
