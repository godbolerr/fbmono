(function() {
    'use strict';

    angular
        .module('monolithApp')
        .controller('FbMessagePPostDetailController', FbMessagePPostDetailController);

    FbMessagePPostDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'FbMessage'];

    function FbMessagePPostDetailController($scope, $rootScope, $stateParams, previousState, entity, FbMessage) {
        var vm = this;

        vm.fbMessage = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('monolithApp:fbMessageUpdate', function(event, result) {
            vm.fbMessage = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
