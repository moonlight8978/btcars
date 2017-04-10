(function() {
    'use strict';

    angular
        .module('btcarsApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider.state('contacts', {
            parent: 'app',
            url: '/contacts',
            data: {
                authorities: [],
                pageTitle: 'Contacts ・ BT Cars'
            },
            views: {
                'content@': {
                    templateUrl: 'app/info/contacts.html'
                }
            }
        });
    }
})();
