(function() {
    'use strict';
    angular
        .module('monolithApp')
        .factory('FbUToken', FbUToken);

    FbUToken.$inject = ['$resource'];

    function FbUToken ($resource) {
        var resourceUrl =  'api/fb-u-tokens/:id';

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
