(function() {
    'use strict';

    angular
        .module('monolithApp')
        .controller('FbUTokenPPostController', FbUTokenPPostController);

    FbUTokenPPostController.$inject = ['FbUToken'];

    function FbUTokenPPostController(FbUToken) {

        var vm = this;

        vm.fbUTokens = [];

        loadAll();

        function loadAll() {
            FbUToken.query(function(result) {
                vm.fbUTokens = result;
                vm.searchQuery = null;
            });
        }
    }
})();
