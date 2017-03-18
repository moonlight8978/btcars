(function() {
    'use strict';

    angular
        .module('btcarsApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider.state('home', {
            parent: 'app',
            url: '/',
            data: {
                authorities: []
            },
            views: {
                'content@': {
                    templateUrl: 'app/home/home.html',
                    controller: 'HomeController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                news: function (getCarFactory) {
                    var response = getCarFactory.getData('api/cars/new').then(function (data) {
                        return data;
                    });
                    return response;
                },
                random: function (getCarFactory) {
                    var response = getCarFactory.getData('api/cars/random').then(function (data) {
                        return data;
                    });
                    return response;
                }
            }
        });
    }
})();
