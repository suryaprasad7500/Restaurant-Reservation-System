(function(){
    'use strict';
    angular
        .module('rrs')
        .controller('ViewCustomers', ViewCustomers);
    ViewCustomers.$inject = ['dataService'];

    function ViewCustomers(dataService){
        var custList = this;
        custList.data = null;
        dataService
            .getAllCustomers()
            .then(function (data) {
                custList.data = data;
            }, function (error){
                console.error(error);
            });
    }
})();