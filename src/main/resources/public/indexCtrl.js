app.controller("myCtrl", ['$scope', 'stationService', function($scope, stationService) {
    $scope.firstName = "John";
    $scope.lastName= "Doe";
    $scope.option = "";

    $scope.$on('navbarInteraction',function(event, interaction){
        $scope.option = interaction;
    });

    $scope.showOption = true;
    /**
    purpose is to activate or deactivate the graphic field, so we can edit the informations from the train network
    */
    $scope.toggleOptionField = function(){
        if($scope.showOption){
            $scope.showOption = false;
        }else{
            $scope.showOption = true;
        }
        $scope.option = "";
    }

    stationService.getStationResponse();

}]);