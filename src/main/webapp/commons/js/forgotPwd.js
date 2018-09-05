'use strict'

$('#forgotPwd-form-btn').click(() => {
    console.log(serverAddr)
    $.getJSON(serverAddr+'/app/auth/identifyEmailAddr',{
        email: $('#fEmail').val(),
        checkPage : 1
        },function(data){
            if(data.state == 'fail'){
                swal({
                    title: "이메일에서 메일을 확인해주세요!",
                    icon: "success",
                }).then((value)=>{
                    location.href = `${nodeServerAddr}/issueTPWD?sendmail=`+$('#fEmail').val()
                })
            }else if(data.state == 'success'){
                swal({
                    title: "존재하지 않는 이메일입니다!",
                    icon: "error",
                })
            }else if(data.state == 'checkEmail'){
                swal({
                    title: "이메일에 메일이 간 계정입니다!",
                    icon: "success",
                }) 
            }
        })
    
});

