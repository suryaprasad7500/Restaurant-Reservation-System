(function(){
    'use strict';
    angular
        .module('rrs')
        .controller('ResSingleController', ResSingleController);

    ResSingleController.$inject = ['dataService', '$routeParams'];

    function ResSingleController(dataService, $routeParams){
        var resSingleModel = this;
        resSingleModel.info = null;
        dataService
            .getOneRes($routeParams.email)
            .then(function(data){
                resSingleModel.info = data;
            }, function(error){
                console.log("Couldn't get the single res info!");
                console.error(error);
            });
        /*console.log("ResSingleController invoked!");*/
    }
})();