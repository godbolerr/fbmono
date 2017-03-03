(function() {
    'use strict';

    angular
        .module('monolithApp')
        .controller('FbUTokenPPostDialogController', FbUTokenPPostDialogController);

    FbUTokenPPostDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'FbUToken'];

    function FbUTokenPPostDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, FbUToken) {
        var vm = this;

        vm.fbUToken = entity;
        vm.clear = clear;
        vm.save = save;

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.fbUToken.id !== null) {
                FbUToken.update(vm.fbUToken, onSaveSuccess, onSaveError);
            } else {
                FbUToken.save(vm.fbUToken, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('monolithApp:fbUTokenUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
