(function(){
    'use strict';

    angular.module('rrs').service('dataService', dataService);

    dataService.$inject = ['$http', '$q'];

    function dataService($http, $q){
        var self = this;
        self.getReservations = function () {
            var defer = $q.defer();
            $http
                .get('http://localhost:8080/RRSapi/api/reservation')
                .then(function(response){
                    defer.resolve(response.data);
                }, function (error) {
                    defer.reject(error.status);
                });
            return defer.promise;
        };

        self.getOneRes = function (email){
          var defer = $q.defer();
            $http
                .get('http://localhost:8080/RRSapi/api/reservation/' + email)
                .then(function(response){
                    defer.resolve(response.data);
                }, function(error){
                    defer.reject(error.status);
                });
            return defer.promise;
        };

        self.createReservation = function(resObj){
            var defer = $q.defer();
            $http({
                method: "POST", url: 'http://localhost:8080/RRSapi/api/reservation', data: resObj
            });
        };
        self.updateReservation = function(resObj, email){
            $http({
               method: "PUT", url: 'http://localhost:8080/RRSapi/api/reservation/' +email, data: resObj
            });
            console.log(resObj);
        };
        self.updateCustomer = function(custObj, email){
            $http({
                method: "PUT", url: 'http://localhost:8080/RRSapi/api/customers/' +email, data: custObj
            });
        };
        self.getOwnerDetails = function(){
            var defer = $q.defer();
            $http
                .get('http://localhost:8080/RRSapi/api/owner/')
                .then(function(response){
                    defer.resolve(response.data);
                }, function(error){
                    defer.reject(error.status);
                });
            return defer.promise;
        };
        self.getTableData = function(){
            var defer = $q.defer();
            $http
                .get('http://localhost:8080/RRSapi/api/table/')
                .then(function(response){
                    defer.resolve(response.data);
                }, function(error){
                    defer.reject(error.status);
                });
            return defer.promise;
        };
        self.getAllCustomers= function(){
            var defer = $q.defer();
            $http
                .get('http://localhost:8080/RRSapi/api/customers')
                .then(function(response){
                    defer.resolve(response.data);
                }, function(error){
                    defer.reject(error.status);
                });
            return defer.promise;
        }
        self.getOneCust = function(email){
            var defer = $q.defer();
            $http
                .get('http://localhost:8080/RRSapi/api/customers/' + email)
                .then(function(response){
                    defer.resolve(response.data);
                }, function(error){
                    defer.reject(error.status);
                });
            return defer.promise;
        };
    }
})();