var appControllers = angular.module('arbanking-controllers');

appControllers.controller("adminCtrl", ['$scope','$http', '$constants','$routeParams','Upload','$trackableScene','$timeout',
    function($scope, $http , $constants,$routeParams,Upload,$trackableScene,$timeout){
        $scope.messages = [];

        var fetchChannels = function(){
            $http({
                url: $constants.getUrl('/channels'),
                method: 'GET'
            }).success(function(data){
                if(!(data instanceof Array)){
                    data = [data];
                }
                $scope.channels = data;
            });
        };

        $scope.deleteChannel = function(channel){
            $http({
                url : $constants.getUrl('/channel/'+channel.name    ),
                method: 'DELETE'
            })
                .success(function(data){
                    fetchChannels();
                    $scope.messages.push({
                        type : 'success',
                        message: 'Channel  '+ channel.name + ' removed'
                    });
                })
                .error(function(data){
                    $scope.messages.push({
                        type : 'error',
                        message: data
                    });
                })
                .finally(function(){
                    $timeout(function(){
                        $scope.messages.shift();
                        $scope.$apply();
                    },1500);
                });
        }
        fetchChannels();

    }]);