(function() {
    'use strict';

    angular
        .module('monolithApp')
        .controller('FbpostPPostDialogController', FbpostPPostDialogController);

    FbpostPPostDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Fbpost'];

    function FbpostPPostDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Fbpost) {
        var vm = this;

        vm.fbpost = entity;
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
            if (vm.fbpost.id !== null) {
                Fbpost.update(vm.fbpost, onSaveSuccess, onSaveError);
            } else {
                Fbpost.save(vm.fbpost, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('monolithApp:fbpostUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
