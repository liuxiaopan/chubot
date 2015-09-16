myApp.controller("LoginController", function ($scope, $http, $window) {
    $scope.login = {};
    $scope.isShowAlert = false;
    $scope.status = "alert-danger";
    $scope.loginUser=function(){
        var pwd = $scope.login.password;
        pwd = hex_sha1(pwd);
        pwd = hex_md5(pwd);
        var myData = {user: $scope.login.user, password: pwd, captcha: $scope.captchaVal };
        console.log(myData);
        $http.post('/user/login', myData)
            .success(function(data){
                if(data.error == undefined){
                    console.log("redirect home page");
                    $window.location.assign("/");
                }else {
                    $scope.reloadCaptcha();
                    $scope.alertMsg(data.error);
                }
            }).error(function(data){
            });
    };
    $scope.$watch("login.user + login.password", function(newValue){
        $scope.isShowAlert = false;
    });
    $scope.alertMsg = function(msg){
        $scope.isShowAlert = true;
        $scope.alertInfo =msg;
    };
});