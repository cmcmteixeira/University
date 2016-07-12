var appControllers = angular.module('arbanking-controllers');

appControllers.controller("newTrackableCtrl", ['$scope','$http', '$routeParams','$constants','Upload','$timeout','$trackableScene',
    function($scope, $http, $routeParams,$constants,Upload,$timeout,$trackableScene){
        var channel = $routeParams.name;

        $scope.loading = {};
        $scope.loading.visible = false;
        $scope.loading.progress = 0;

        $scope.trackable={};

        $http({
            url: $constants.getUrl('/channel/'+$routeParams.name+'/scenes')
        })
        .success(function(data){
            if(!(data instanceof Array)){
                data = [data];
            }
            $scope.scenes = data;
            $scope.trackable.scenes = [];
            $scope.trackable.scenes.push({});
        });

        $scope.messages = [];
        $scope.createNewSelect= function(){
            $scope.trackable.scenes.push({});
        };
        $scope.removeNewSelect= function(){
            if($scope.trackable.scenes.length > 1)  $scope.trackable.scenes.pop();
        };
        var i = 0;
        $scope.loading = {};
        $scope.loading.visible = true;
        $scope.loading.progress= 0;
        var link = function(){
            console.log($scope.trackable);
            if(i >= $scope.trackable.scenes.length || $scope.trackable.scenes[0] == {}) return;
            new $trackableScene.linkSceneToTrackable($scope.trackable.scenes[i],$scope.trackable,channel)
                .success(function(){
                    //$scope.loading.progress += Math.round(i*100 /$scope.scene.trackables.length  / 10) * 10;
                    $scope.messages.push({
                        type : 'success',
                        message: 'Scene  '+ $scope.trackable.name + ' linked to ' + i + ' scene'
                    });
                    $timeout(function(){
                        $scope.messages.shift();
                        $scope.$apply();
                    },1500);
                    i++;
                    link();
                })
                .error(function(){
                    $scope.messages.push({
                        type : 'error',
                        message: 'Scene  '+ $scope.scene.name + ' was not linked to ' + scene.trackables[i].name
                    });
                    $timeout(function(){
                        $scope.messages.shift();
                        $scope.$apply();
                    },1500);
                });
        };
        $scope.submit = function(){
            $scope.loading.visible = true;
            $scope.loading.progress= 0;
            if($scope.files == undefined){
                $scope.messages.push({
                    'type'      : 'error',
                    'message'   : 'A file must be specified'
                });
                return;
            }
            Upload.upload({
                url : $constants.getUrl('/channel/'+channel+'/trackable'),
                fields:{name:$scope.trackable.name,description:$scope.trackable.description},
                method: 'POST',
                file: $scope.files[0]
            })
            .success(function(data){
                $scope.messages.push({
                    type : 'success',
                    message: 'Trackable  '+data.trackable.name + ' created'
                });
                $scope.trackable.id= data.trackable.id;
                i=0;
                link();
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
    }
]);