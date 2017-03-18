(function() {
    'use strict';
    angular
        .module('btcarsApp')
        .factory('Recommend', Recommend);

    Recommend.$inject = ['$resource'];

    function Recommend ($resource) {
        var resourceUrl =  'api/recommends/:id';

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
