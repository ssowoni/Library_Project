$(document).ready(function (){
    //이메일 중복 확인 체크 여부 0이면 체크 안함, 1이면 체크
    let emailCheck = 0;
    let nicknameCheck =0;

    //이메일 중복 체크
    function checkEmail(){
        console.log("checkEmail");

        if($('#email').val() !='') {
            $.ajax({
                type: 'GET',
                url: '/duplicationCheck',
                data: 'email=' + $('#email').val(),
                dataType: 'json',
                success: function (result) {
                    if (result == '0') {
                        $('#id-check').text('사용 가능한 이메일입니다.');
                        emailCheck = 1;
                    } else {
                        $('#id-check').text('이미 사용중인 이메일입니다.');
                    }
                },
                error: function (xhr, status, er) {
                    console.log(xhr, status, er);
                }

            })
        }else{
            alert('이메일을 입력해주세요.');
            $('#email').focus();
        }
    }

    //닉네임 중복 체크
    function checkNickname(){
        console.log("checkNickname");

        if($('#nickname').val() !='') {
            $.ajax({
                type: 'GET',
                url: '/duplicationCheck',
                data: 'nickname=' + $('#nickname').val(),
                dataType: 'json',
                success: function (result) {
                    if (result == '0') {
                        $('#nickname-check').text('사용 가능한 닉네임입니다..');
                        nicknameCheck = 1;
                    } else {
                        $('#nickname-check').text('이미 닉네임 이메일입니다.');
                    }
                },
                error: function (xhr, status, er) {
                    console.log(xhr, status, er);
                }

            })
        }else{
            alert('닉네임을 입력해주세요.');
            $('#email').focus();
        }
    }



    //유효성 검사
    function joinform_check(){



    }


})