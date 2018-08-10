app.directive("navigationList",function(){
    var controller = ['$scope', function($scope){
        //console.log($scope);
    }];

    return {
        restrict: 'EA',
        templateUrl: "/option/navbar.html",
        controller: controller
    }
})
