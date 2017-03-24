(function() {
    'use strict';
    angular
        .module('btcarsApp')
        .factory('Orderlist', Orderlist);

    Orderlist.$inject = ['$resource'];

    function Orderlist ($resource) {
        var resourceUrl =  'api/orderlists/:id';

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
            'update': { method:'PUT' }
        });
    }
})();
