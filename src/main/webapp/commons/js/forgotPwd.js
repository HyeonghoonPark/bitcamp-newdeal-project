'use strict'

$('#forgotPwd-form-btn').click(() => {
    $.post(`${serverApiAddr}/json/member/signup`, {
        'email': $('#mEmail').val(),
    }, (result) => {
        if (result.status === 'success') {
            swal({
                title: "회원 가입을 축하드립니다!",
                icon: "success",
            }).then((value)=>{
                location.href = 'signIn.html'
            })
        } else {
            swal({
                title: "회원 가입 실패!",
                icon: "error",
            })
            console.log(result.message)
        }
    }, 'json')
    .fail(() => {
        swal({
            title: "회원 가입 중에 오류 발생",
            icon: "error",
        })
    });
});

