/**
 * Created by carlos on 23/05/2015.
 */
var appControllers = angular.module('arbanking-controllers');

appControllers.controller("newChannelCtrl", ['$scope','$http', '$constants','$routeParams','$timeout',
    function($scope,$http,$constants,$routeParams,$timeout){
        var channel = $routeParams.name;

        $scope.channel={};
        $scope.done = false;
        $scope.messages=[];
        $scope.submit = function(){
            $http({
                url: $constants.getUrl('/channel'),
                data: {description:$scope.channel.description , name: $scope.channel.name},
                method: 'POST'
            })
            .success(function(data){
                $scope.messages.push({
                    type : 'success',
                    message: 'Application '+$scope.channel.name + 'created'
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
                    $scope.messages.shift()
                    $scope.$apply();
                },1500);
            });


        };




    }]);