var appControllers = angular.module('arbanking-controllers');

appControllers.controller("newSceneCtrl", ['$scope','$http', '$constants','$routeParams','Upload','$trackableScene','$timeout',
    function($scope, $http , $constants,$routeParams,Upload,$trackableScene,$timeout){
        var channel = $routeParams.name;
        $scope.messages = [];

        $scope.scene={};
        $scope.loading = {};
        $scope.loading.visible = false;
        $scope.loading.progress = 0;
        $scope.scene.trackables = [{}];


        $scope.createNewSelect= function(){
            if($scope.scene.trackables.length < $scope.trackables.length ) $scope.scene.trackables.push({});
        };

        $scope.removeNewSelect= function(){
            if($scope.scene.trackables.length > 1)  $scope.scene.trackables.pop();
        };

        $http({
            url: $constants.getUrl('/channel/'+$routeParams.name+'/trackables')
        })
        .success(function(data){
            if(!(data instanceof Array)){
                data = [data];
            }
            $scope.trackables = data;

        });
        var i = 0;
        var link = function(){
            if(i >= $scope.scene.trackables.length || $scope.scene.trackables[0] == {}) return;
            console.log($scope.scene);
            new $trackableScene.linkSceneToTrackable($scope.scene,$scope.scene.trackables[i],channel)
                .success(function(){
                    $scope.loading.progress += Math.round(i*100 /$scope.scene.trackables.length  / 10) * 10;
                    $scope.messages.push({
                        type : 'success',
                        message: 'Scene  '+ $scope.scene.name + ' linked to ' + i + ' trackable'
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
                url : $constants.getUrl('/channel/'+channel+'/scene'),
                fields:{name:$scope.scene.name,description:$scope.scene.description},
                method: 'POST',
                file: $scope.files[0]
            }).success(function(data) {
                $scope.messages.push({
                    type : 'success',
                    message: 'Scene  '+ $scope.scene.name + ' created'
                });
                $scope.scene.id = data.scene[0].id;
                $scope.loading.visible = false;
                $scope.loading.progress= 0;
                i = 0;
                link();
            }).progress(function(evt){
                $scope.loading.progress = parseInt(100.0 * evt.loaded / (evt.total));
            }).error(function(data) {
                $scope.messages.push({
                    type: 'error',
                    message: data
                });
                $scope.loading.visible = false;
                $scope.loading.progress= 0;

            }).finally(function(){
                $timeout(function(){
                    $scope.messages.shift();
                    $scope.$apply();
                },1500);
            });
        };




}]);