(function() {
    'use strict';

    angular
        .module('btcarsApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider.state('search', {
            parent: 'app',
            url: '/search?q',
            data: {
                authorities: [],
                pageTitle: 'Search results... - BT Cars!'
            },
            views: {
                'content@': {
                    templateUrl: 'app/search/search.html',
                    controller: 'SearchController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                searchQuery: function (Search) {
                    return Search.searchQuery;
                }
            }
        });
    }
    
})();
