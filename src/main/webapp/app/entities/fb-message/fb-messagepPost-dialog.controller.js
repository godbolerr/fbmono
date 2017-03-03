(function() {
    'use strict';

    angular
        .module('monolithApp')
        .controller('FbMessagePPostDialogController', FbMessagePPostDialogController);

    FbMessagePPostDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'FbMessage'];

    function FbMessagePPostDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, FbMessage) {
        var vm = this;

        vm.fbMessage = entity;
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
            if (vm.fbMessage.id !== null) {
                FbMessage.update(vm.fbMessage, onSaveSuccess, onSaveError);
            } else {
                FbMessage.save(vm.fbMessage, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('monolithApp:fbMessageUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
