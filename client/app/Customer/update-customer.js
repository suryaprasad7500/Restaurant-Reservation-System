(function(){
    angular
        .module('rrs')
        .controller('CustProUpdateController', CustProUpdateController);
    CustProUpdateController.$inject = ['dataService'];

    function CustProUpdateController(dataService){
        var custUpdate = this;
        custUpdate.updateCust = function(custObj, email){
            dataService
                .updateCustomer(custObj, email);
        }
    }
})();