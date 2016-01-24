(function(){
    'use strict';
    angular
        .module('rrs')
        .controller('OwnerDetails', OwnerDetails);

    OwnerDetails.$inject = ['dataService'];

    function OwnerDetails(dataService){
        var ownerDetailModel = this;
        ownerDetailModel.detail = null;
        dataService
            .getOwnerDetails()
            .then(function(data){
                ownerDetailModel.detail = data;
            }, function(error){
                console.log("Can't get the owner details!");
                console.log(error);
            });
    }
    /*console.log("Owner Details Controller invoked!");*/
})();