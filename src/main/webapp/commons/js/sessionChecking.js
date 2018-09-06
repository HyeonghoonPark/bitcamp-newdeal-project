$.getJSON(serverAddr+'/app/auth/sessionChecking',function(result){
  
    if(result.state=='fail'){
        swal({
            title: "세션이 만료 됐습니다!",
            icon: "error",
        }).then((value)=>{
            location.href = serverAddr+'/signIn.html'
        })
    }
    
})