(function() {
    'use strict';
    angular
        .module('monolithApp')
        .factory('Fbpost', Fbpost);

    Fbpost.$inject = ['$resource'];

    function Fbpost ($resource) {
        var resourceUrl =  'api/fbposts/:id';

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
