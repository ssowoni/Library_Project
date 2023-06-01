


    $(document).ready(function (){

        registerBtnClickEvent()
    })


    //이메일 중복 확인 체크 여부 0이면 체크 안함, 1이면 체크
    //true, false로 생각하자 .
    let emailCheck = 0;
    let nicknameCheck =0;


    //이메일 중복 체크
    function registerBtnClickEvent(){

        $('button[name=btn]').click(function (e){
            e.preventDefault();
            const btnId = $(this).attr('id');

            switch (btnId){
                case 'btnEmailCheck' :
                    fEmailCheck();
                    break;
                case 'btnNicknameCheck' :
                    fNicknameCheck();
                    break;
                case 'btnJoin' :
                    fJoin();
                    break;
            }
        })
    }

    //닉네임 중복 체크
    function fEmailCheck(){
        const email = $("#email").val();
        console.log("checkEmail : " + email);

        const param = {
            email : email
        }

        const resultcallback = function(returndata){
            console.log("emailCheck returndata : " + returndata);
            if($('#email').val() !='') {

                if (returndata == '0') {
                    $('#id-check').text('사용 가능한 이메일입니다.');
                    emailCheck = 1;
                } else {
                    $('#id-check').text('이미 사용중인 이메일입니다.');
                    emailCheck = 0;

                }
            }else{
                alert('이메일을 입력해주세요.');
                $('#email').focus();
            }
        }

        callAjax("/duplicationCheck", "get", "json", true, param, resultcallback);

    }

    function fNicknameCheck(){
        const nickname = $("#nickname").val();
        console.log("checkNickname : " + nickname);

        const param = {
            nickname : nickname
        }

        const resultcallback = function(returndata){
            console.log("emailCheck returndata : " + returndata);
            if($('#nickname').val() !='') {
                if (returndata == '0') {
                    $('#nickname-check').text('사용 가능한 닉네임입니다..');
                    nicknameCheck = 1;
                } else {
                    $('#nickname-check').text('이미 사용중인 닉네임입니다.');
                    nicknameCheck = 0;

                }
            }else{
                alert('닉네임을 입력해주세요.');
                $('#nickname').focus();
            }
        }

        callAjax("/duplicationCheck", "get", "json", true, param, resultcallback);

    }


    //이메일 중복 체크
    /*function checkEmail(){
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
    }*/



    //유효성 검사
    function fJoin(){

        console.log("회원가입 유효성 검사 중");

        const email = $('#email');
        const password = $('#password');
        const passwordConfirm = $('#passwordConfirm');
        const name = $('#name');
        const nickname = $('#nickname');
        /*const men = $('#men').val();
        const female = $('#female').val();*/
        const cellNo = $('#cellNo');

        //이메일 중복 검사 하지 않은 경우
        if(emailCheck ==0){
            alert('이메일 중복 확인을 해주세요.');
            email.focus();
            return false; //아래 코드부터 아무것도 진행하지 말아라.
        }

        //jquery는 요소.val()로 값 가져오기
        //순수 js는 document.getElementById.value로 값 가져오기
        if(password.val() ==""){
            alert("비밀번호를 입력해주세요. ");
            password.focus();
            return false;
        }

        let pwReg = /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,15}$/;
        if(!pwReg.test(password.val())){
            console.log(password.val());
            alert("비밀번호는 영문 숫자 특수기호 조합 8자리 이상입니다.");
            password.focus();
            return false;
        }

        if(password.val() !== passwordConfirm.val()){
            alert("비밀번호가 일치하지 않습니다.")
            passwordConfirm.focus();
            return false;
        }

        if(name.val() =="" ){
            alert("이름을 입력해주세요. ");
            name.focus();
            return false;
        }

        //이메일 중복 검사 하지 않은 경우
        if(nicknameCheck ==0){
            alert('닉네임 중복 확인을 해주세요.');
            nickname.focus();
            return false; //아래 코드부터 아무것도 진행하지 말아라.
        }

        if(cellNo.val()==""){
            alert("전화번호를 입력해주세요. ");
            cellNo.focus();
            return false;
        }

        let cellNoReg = /^[0-9]+/g; //숫자만 입력하는 정규식
        if(!cellNoReg.test(cellNo.val())){
            alert("전화번호는 숫자만 입력할 수 있습니다.")
            cellNo.focus();
            return false;
        }

        document.join_form.submit(); //유효성 검사를 모두 통과했으면 회원가입.


        /**
         * 1. 이름 크기 제한, 6글자 입력했더니 안됨. "회원가입테스트" (actual: 21, maximum: 20)
         * 2. 전화번호 그냥 기호 입력했는데 됐음.
         * 3. 이메일 형식 가운데 @ 받기
         */




    }

