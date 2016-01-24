(function(){
    'use strict';
    angular
        .module('rrs')
        .controller('CustSingleController', CustSingleController);
    CustSingleController.$inject = ['dataService', '$routeParams'];

    function CustSingleController(dataService, $routeParams){
        var custSingleModel = this;
        custSingleModel.info = null;
        dataService
            .getOneCust($routeParams.email)
            .then(function(data){
                custSingleModel.info = data;
            }, function(error){
               console.error(error);
            });
    }
})();