(function() {
    'use strict';

    angular
        .module('monolithApp')
        .controller('FbUTokenPPostDetailController', FbUTokenPPostDetailController);

    FbUTokenPPostDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'FbUToken'];

    function FbUTokenPPostDetailController($scope, $rootScope, $stateParams, previousState, entity, FbUToken) {
        var vm = this;

        vm.fbUToken = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('monolithApp:fbUTokenUpdate', function(event, result) {
            vm.fbUToken = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
