(function() {
    'use strict';

    angular
        .module('btcarsApp')
        .factory('OrderlistSearch', OrderlistSearch);

    OrderlistSearch.$inject = ['$resource'];

    function OrderlistSearch($resource) {
        var resourceUrl =  'api/_search/orderlists/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true}
        });
    }
})();
