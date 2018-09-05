(function ($) {
    "use strict";

    
    /*==================================================================
    [ Validate ]*/
    var input = $('.validate-input .input100');

    $('.validate-form').on('submit',function(){
        var check = true;

        for(var i=0; i<input.length; i++) {
            if(validate(input[i]) == false){
                showValidate(input[i]);
                check=false;
            }
        }

        return check;
    });


    $('.validate-form .input100').each(function(){
        $(this).focus(function(){
           hideValidate(this);
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

    function showValidate(input) {
        var thisAlert = $(input).parent();

        $(thisAlert).addClass('alert-validate');
    }

    function hideValidate(input) {
        var thisAlert = $(input).parent();

        $(thisAlert).removeClass('alert-validate');
    }
    
    

})(jQuery);



$('#btn').click(function(){
    
    console.log($('#fEmail').val())
    console.log($('#fPassword').val())

    if($('#fEmail').val().length==0){
        swal({
            title: "이메일을 입력해주세요",
            icon: "error",
        })
        return;
    }else if($('#fPassword').val().length==0){
        swal({
            title: "비밀번호를 입력해주세요",
            icon: "error",
        })
        return;
    }
    
    $.post(serverAddr+'/app/auth/signIn',{
        email : $('#fEmail').val(),
        pwd : $('#fPassword').val()
    },function(result){
        console.log(result.state);
        if(result.state=='success'){
            location.href = "./Management/list.html";
        }else if(result.state=='notAuthEmail'){
            swal({
                title: "이메일 인증을 하셔야 이용이 가능합니다.",
                icon: "error",
            })
        }else if(result.state=='notFind'){
            swal({
                title: "비밀번호 혹은 이메일이 일치하지 않습니다.",
                icon: "error",
            })
        };
    }) 
    
    
})
