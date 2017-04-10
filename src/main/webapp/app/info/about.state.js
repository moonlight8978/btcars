(function() {
    'use strict';

    angular
        .module('btcarsApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider.state('about', {
            parent: 'app',
            url: '/about',
            data: {
                authorities: [],
                pageTitle: 'About us ・ BT Cars'
            },
            views: {
                'content@': {
                    templateUrl: 'app/info/about.html'
                }
            }
        });
    }
})();
