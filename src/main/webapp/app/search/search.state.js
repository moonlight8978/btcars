(function() {
    'use strict';

    angular
        .module('btcarsApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('searchParent', {
            parent: 'app',
            url: '/search',
            abstract: true,
            data: {
                authorities: [],
                pageTitle: 'Search results... - BT Cars!'
            },
            views: {
                'content@': {
                    templateUrl: 'app/search/search.html'
                },
                'sidebar@searchParent': {
                    templateUrl: 'app/search/search-sidebar.html',
                    controller: 'SearchSidebarController',
                    controllerAs: 'vm'
                }
            }
        })
        .state('search', {
            parent: 'searchParent',
            url: '/:q',
            views: {
                'content@searchParent': {
                    templateUrl: 'app/search/search-content.html',
                    controller: 'SearchContentController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                searchQuery: function ($stateParams) {
                    return $stateParams.q;
                }
            }
        })
        ;
    }

})();
