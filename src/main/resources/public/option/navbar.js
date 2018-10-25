app.directive("navigationList",function(){
    var controller = ['$scope', function($scope){
        console.log($scope);

        $scope.navbarInteraction = function(interaction){
            $scope.$emit('navbarInteraction', interaction);
        }

    }];

    return {
        restrict: 'EA',
        templateUrl: "/option/navbar.html",
        controller: controller
    }
})
