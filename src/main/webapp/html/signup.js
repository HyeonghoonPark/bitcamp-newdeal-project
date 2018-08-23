'use strict'

$('#SignUp-form-btn').click(() => {
    console.log($('#mEmail').val());
    console.log($('#mName').val());
    console.log($('#mPassword').val());
    $.post(`${serverApiAddr}/json/member/signup`, {
        'email': $('#mEmail').val(),
        'name': $('#mName').val(),
        'password': $('#mPassword').val()
    }, (result) => {
        if (result.status === 'success') {
            swal({
                title: "회원 가입을 축하드립니다!",
                icon: "success",
            }).then((value)=>{
                location.href = 'index.html'
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