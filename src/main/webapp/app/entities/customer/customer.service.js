(function() {
    'use strict';
    angular
        .module('btcarsApp')
        .factory('Customer', Customer);

    Customer.$inject = ['$resource'];

    function Customer ($resource) {
        var resourceUrl =  'api/customers/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                    }
                    return data;
                }
            },
            'queryByUserId': { method: 'GET', isArray: false},
            'update': { method: 'PUT' }
        });
    }
})();
