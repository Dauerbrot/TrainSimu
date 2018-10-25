app.directive("createRoute",function(){
    return {
        restrict: 'EA',
        templateUrl: "/directives/routeTemplate/createRoute.html",
        controller: ['$scope', 'routeService', function($scope, routeService){


            $scope.generateFirstRoute = function(){
                routeService.simulateRoute();
            };

            $scope.getRouteResponse = function(){
                routeService.getRouteResponse();
            }
        }]
    }
});