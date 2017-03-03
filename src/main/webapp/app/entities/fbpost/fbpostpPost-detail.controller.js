(function() {
    'use strict';

    angular
        .module('monolithApp')
        .controller('FbpostPPostDetailController', FbpostPPostDetailController);

    FbpostPPostDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Fbpost'];

    function FbpostPPostDetailController($scope, $rootScope, $stateParams, previousState, entity, Fbpost) {
        var vm = this;

        vm.fbpost = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('monolithApp:fbpostUpdate', function(event, result) {
            vm.fbpost = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
