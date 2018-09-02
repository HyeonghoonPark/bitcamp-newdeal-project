'use strict'

$('#fEmail').focusout(function(){ // 이메일 형식, 이미 가입한 이메일인지 검사
	console.log('들어옴')
	if(alertMessage()){
	$.getJSON(serverAddr+`/app/Auth/identifyEmailAddr`,{
		email : $('#fEmail').val()
	},
	function(data){
		if(data.state == 'fail'){
			swal({
                title: "이미 존재하는 이메일입니다!",
                icon: "error",
            })
		}else if(data.state == 'success'){
            swal({
                title: "사용가능한 이메일입니다!",
                icon: "success",
            })
		}
	})

	}
	
})

let regEmail = /([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;


function alertMessage(){
	
	var warningMessage = '';
		
	if($('#fEmail').val()==''){
		warningMessage = '이메일을 입력해주세요.';
	}else if(!regEmail.test($('#fEmail').val())){
		warningMessage = '올바른 이메일 형식이 아닙니다.';
	}	
	
	if(warningMessage!=''){
		swal({
            title: warningMessage,
            icon: "error",
        })
		return false
	}
	
	return true;
	
}

$('#SignUp-form-btn').click(function(){
 
	var warningMessage = '';
	
	if($('#fPassword1').val()==''){
		warningMessage = '비밀번호를 입력해주세요.';
	}else if($('#fPassword2').val()==''){
		warningMessage = '비밀번호를 한번 더 입력해주세요.';
	}else if($('#fPassword1').val()!=$('#fPassword2').val()){
		warningMessage = '비밀번호가 서로 일치하지 않습니다.';
	}else if($('#fPassword1').val().length > 6 || $('#fPassword2').val().length > 6){
		warningMessage = '비밀번호는 여섯글자 이상만 가능합니다.';
	}

	if(warningMessage!=''){
		swal({
            title: warningMessage,
            icon: "error",
        })
		return false
	}
	

    	$.post(serverAddr+'/app/member/signUp',{
    		email : $('#fEmail').val(),
    		name : $('#fName').val(),
    		pwd : $('#fPassword1').val()
    	},function(result){
    		
    		var state = result.state;
    		
    		if(state == "success"){
    			swal({
                    title: "입력한 이메일에서 가입 확인을 눌러주세요",
                    icon: "success",
                }).then((value)=>{
                    location.href = 'signIn.html'
                })
    		}else{
    			swal({
                    title: "회원 가입 실패!",
                    icon: "fail",
                })
    		}
    		
    	}, 'json').fail(() => {
            swal({
                title: "회원 가입 중에 오류 발생",
                icon: "fail",
            })
        });
   
});


function validate (input) {
    if($(input).attr('type') == 'email' || $(input).attr('name') == 'email') {
        if($(input).val().trim().match(/^([a-zA-Z0-9_\-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([a-zA-Z0-9\-]+\.)+))([a-zA-Z]{1,5}|[0-9]{1,3})(\]?)$/) == null) {
            return false;
        }
    }
    else {
        if($(input).val().trim() == ''){
            return false;
        }
    }
}