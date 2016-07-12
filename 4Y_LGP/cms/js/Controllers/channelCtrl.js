'use strict';

var appControllers = angular.module('arbanking-controllers');

appControllers.controller("channelCtrl", ['$scope','$http', '$routeParams','$constants','$timeout','$trackableScene',
	function($scope, $http, $routeParams, $constants,$timeout,$trackableScene){
        var channel = $routeParams.name;
        var fetchTrackables = function(){
            $http({
                url: $constants.getUrl('/channel/'+channel+'/trackables')
            }).success(function(data){
                if(!(data instanceof Array)){
                    data = [data];
                }
                for(var i = 0 ; i < data.length ;i++){
                    data[i].url = $constants.getUrl('/channel/'+channel+'/trackable?trackable='+data[i].id);
                }
                $scope.trackables = data;

            });
        };

        var fetchScenes = function(){

            $http({
                url: $constants.getUrl('/channel/'+channel+'/scenes')
            }).success(function(data){
                if(!(data instanceof Array)){
                    data = [data];
                }
                for(var i = 0 ; i < data.length;i++){
                    data[i].url = $constants.getUrl('/channel/'+channel+'/scene?scene='+data[i].id)
                }
                $scope.scenes = data;
            });
        };


        $scope.messages = [];
        $scope.deleteTrackable= function(trackable){
                $http({
                    url : $constants.getUrl('/channel/'+channel+'/trackable?trackable='+trackable.id),
                    method: 'DELETE'
                })
                .success(function(data){
                    fetchTrackables();
                    $scope.messages.push({
                        type : 'success',
                        message: 'Trackable  '+ trackable.name + ' removed'
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
        };

        $scope.setCurrentScene = function(scene){
            if(scene.current) return;
            $http({
                url : $constants.getUrl('/channel/'+channel+'/current'),
                data: {scene: scene.id },
                method: 'POST'
            })
            .success(function(data){
                fetchScenes();
                $scope.messages.push({
                    type : 'success',
                    message: 'Scene  '+ scene.name + ' is now the default scene'
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
        };

        $scope.deleteScene= function(scene){
            if(scene.current){
                $scope.messages.push({
                    type: 'error',
                    message: 'Deleting the current version is not allowed'
                });
                $timeout(function(){
                    $scope.messages.shift();
                    $scope.$apply();
                },1500);
                return;
            }
            $http({
                url : $constants.getUrl('/channel/'+channel+'/scene?scene='+scene.id),
                method: 'DELETE'
            })
                .success(function(data){
                    fetchScenes();
                    $scope.messages.push({
                        type : 'success',
                        message: 'Scene  '+ scene.name + ' removed'
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
        };

        $scope.sceneModal = function(scene){
            $('#sceneModal').modal('show');
            $scope.scene = scene;
            scene.trackURLBase = $constants.getUrl('/channel/'+channel+'/trackable?trackable=');
            console.log(scene);
            console.log("deu");
        };

        $scope.addTrackable = function(scene,trackable){
            trackable = { id : trackable};
            $trackableScene.linkSceneToTrackable(scene,trackable,channel)
                .success(function(){

                })
                .error(function(){

                });
        };


        fetchScenes();
        fetchTrackables();

	}]);