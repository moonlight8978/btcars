(function () {
    'use strict';

    angular
        .module('btcarsApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('detailParent', {
            parent: 'cars',
            abstract: true,
            data: {
                authorities: [],
                pageTitle: 'BT Cars - Best Choice!'
            },
            views: {
                'content@': {
                    templateUrl: 'app/cars/detail/details.html'
                },
                'sidebar@detailParent': {
                    templateUrl: 'app/cars/detail/details-sidebar.html',
                    controller: 'DetailsSidebarController',
                    controllerAs: 'vm'
                }
            }
        })
        .state('detail', {
            parent: 'detailParent',
            url: '/id/:id',
            views: {
                'content@detailParent': {
                    templateUrl: 'app/cars/detail/details-content.html',
                    controller: 'DetailsContentController',
                    controllerAs: 'vm'
                },
                'title@detailParent': {
                    templateUrl: 'app/cars/detail/details-title.html',
                    controller: 'DetailsTitleController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                car: function (Car, $stateParams, $q) {
                    var car = Car.get({ id: $stateParams.id });
                    return $q.resolve(car.$promise).then(function (result) {
                        return result;
                    });
                }
            }
        });
    }

})();
