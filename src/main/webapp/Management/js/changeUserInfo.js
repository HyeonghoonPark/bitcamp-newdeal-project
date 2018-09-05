$('#userEmailForm').attr("value",userEmail);

$('#title').html('개인정보변경')
$('#title-detail').html('비밀번호변경')

$('#sbmBtn').click(function(e){
    
    console.log($('#userPassword').val());
    console.log($('#changePassword2').val());
    
    
    console.log($('#changePassword2').val().length)
    if($('#userPassword').val().length == 0){
        swal({
            title: '현재 비밀번호를 입력해주세요.',
            icon: 'error',
        })
        
        e.preventDefault();
    }else if($('#changePassword2').val().length < 6){
        swal({
            title: '비밀번호를 6글자 이상으로 설정해주세요.',
            icon: 'error',
        })
        e.preventDefault();
    }else if($('#changePassword2').val() != $('#changePassword1').val()){
        swal({
            title: '변경할 비밀번호가 서로 일치하지 않습니다.',
            icon: 'error',
        })
        e.preventDefault();
    }else if($('#changePassword2').val().length < 6){
        swal({
            title: '비밀번호를 6글자 이상으로 설정해주세요.',
            icon: 'error',
        })
        e.preventDefault();
    }else{
        
        $.post(serverAddr+'/app/member/changeUserPassword',{
            existingUserPassword : $('#userPassword').val(),
            newUserPassword : $('#changePassword2').val()
        },function(result){
            
            if(result.state == 'passwordMatchingFail'){
                swal({
                    title: '현재 비밀번호가 일치하지 않습니다.',
                    icon: 'error',
                })
            }else if(result.state == 'success'){
                swal({
                    title: '비밀번호 변경이 완료 됐습니다.',
                    icon: 'success',
                }).then((value)=>{
                    location.href = `http://localhost:8080/bitcamp-newdeal-project/Management/list.html`
                })
            }else{
                swal({
                    title: '잠시 후 다시 요청해주세요.',
                    icon: 'error',
                })
            }
        })  
    }   
})