


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
                case 'btnSingUp' :
                    fSingUp();
                    break;
                case 'btnModify' :
                    fModify();
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

        callAjax("/getEmail", "get", "json", true, param, resultcallback);

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

        callAjax("/getNickname", "get", "json", true, param, resultcallback);

    }

   /* const email = $('#email');
    const password = $('#password');
    const passwordConfirm = $('#passwordConfirm');
    const name = $('#name');
    const nickname = $('#nickname');
    /!*const men = $('#men').val();
    const female = $('#female').val();*!/
    const cellNo = $('#cellNo');*/


    //이메일 유효성 검사
    function emailValidation(){

        const email = $('#email');

        //(알파벳,숫자)@(알파벳,숫자).(알파벳,숫자)
        let emailReg = /^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/;
        if(!emailReg.test(email.val())){
            console.log(email.val());
            alert("이메일 형식에 맞게 입력해주세요.")
            email.focus();
            return false;
        }

        //이메일 중복 검사 하지 않은 경우
        if(emailCheck ==0){
            alert('이메일 중복 확인을 해주세요.');
            email.focus();
            return false; //아래 코드부터 아무것도 진행하지 말아라.
        }

    }

    //비밀번호 유효성 검사
    function passValidation(){

        const password = $('#password');
        const passwordConfirm = $('#passwordConfirm');

        //jquery는 요소.val()로 값 가져오기
        //순수 js는 document.getElementById.value로 값 가져오기
        if(password.val() ==""){
            alert("비밀번호를 입력해주세요. ");
            password.focus();
            return false;
        }

        let pwReg = /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,20}$/;
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

    }

    function nameValidation(){

        const name = $('#name');


        if(name.val() =="" ){
            alert("이름을 입력해주세요. ");
            name.focus();
            return false;
        }

        //let nameReg = /^[가-힣a-zA-Z]{2,6}$/;//한글과 영문을 사용하는 최소 2글자 이상.
        let nameReg = /^(?=.*[a-z0-9가-힣])[a-z0-9가-힣]{2,6}$/;
        if(!nameReg.test(name.val())){
            console.log(name.val());
            alert("이름은 최소 2글자 이상 6글자 이하의 한영문자만 가능합니다. ");
            name.focus();
            return false;
        }

    }

    function nicknameValidation(){

        const nickname = $('#nickname');

        //닉네임 중복 검사 하지 않은 경우
        if(nicknameCheck ==0){
            alert('닉네임 중복 확인을 해주세요.');
            nickname.focus();
            return false; //아래 코드부터 아무것도 진행하지 말아라.
        }

        let nicknameReg =  /^(?=.*[a-z0-9가-힣])[a-z0-9가-힣]{2,15}$/;//한글과 영문을 사용하는 최소 2글자 이상.
        if(!nicknameReg.test(nickname.val())){
            console.log(nickname.val());
            alert("닉네임은 최소 2글자 이상 15글자 이하의 한영문자만 가능합니다. ");
            name.focus();
            return false;
        }

    }

    function cellValidation() {
        const cellNo = $('#cellNo');
        if (cellNo.val() == "") {
            alert("전화번호를 입력해주세요. ");
            cellNo.focus();
            return false;
        }

        /*        let cellNoReg =  /^[0-9]+/g; //숫자만 입력하는 정규식
        if(!cellNoReg.test(cellNo.val())){
            console.log(cellNo.val());

            alert("전화번호는 숫자만 입력할 수 있습니다.")
            cellNo.focus();
            return false;
        }*/

        if (isNaN(cellNo.val())) {
            console.log(cellNo.val());

            alert("전화번호는 숫자만 입력할 수 있습니다.")
            cellNo.focus();
            return false;
        }

    }


    function fSingUp(){
        console.log("회원가입 유효성 검사 중");
        emailValidation();
        passValidation();
        nameValidation();
        nicknameValidation();
        cellValidation();
        document.join_form.submit();
    }

    function fModify() {
        console.log("회원수정 유효성 검사 중 ");

        passValidation();
        nicknameCheck = 1;
        nicknameValidation();
        cellValidation();
        document.modify_form.submit();
    }



/*

    //유효성검사 템플릿
    function emailValidation(){


        const email = $('#email');


        let emailReg = /^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/;
        if(!emailReg.test(email.val())){
            console.log(email.val());
            alert("이메일 형식에 맞게 입력해주세요.")
            email.focus();
            return false;
        }

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

        //let nameReg = /^[가-힣a-zA-Z]{2,6}$/;//한글과 영문을 사용하는 최소 2글자 이상.
        let nameReg = /^(?=.*[a-z0-9가-힣])[a-z0-9가-힣]{2,6}$/;
        if(!nameReg.test(name.val())){
            console.log(name.val());
            alert("이름은 최소 2글자 이상 6글자 이하의 한영문자만 가능합니다. ");
            name.focus();
            return false;
        }

        //닉네임 중복 검사 하지 않은 경우
        if(nicknameCheck ==0){
            alert('닉네임 중복 확인을 해주세요.');
            nickname.focus();
            return false; //아래 코드부터 아무것도 진행하지 말아라.
        }

        let nicknameReg =  /^(?=.*[a-z0-9가-힣])[a-z0-9가-힣]{2,15}$/;//한글과 영문을 사용하는 최소 2글자 이상.
        if(!nicknameReg.test(nickname.val())){
            console.log(nickname.val());
            alert("닉네임은 최소 2글자 이상 15글자 이하의 한영문자만 가능합니다. ");
            name.focus();
            return false;
        }



        if(cellNo.val()==""){
            alert("전화번호를 입력해주세요. ");
            cellNo.focus();
            return false;
        }

        /!*        let cellNoReg =  /^[0-9]+/g; //숫자만 입력하는 정규식
                if(!cellNoReg.test(cellNo.val())){
                    console.log(cellNo.val());

                    alert("전화번호는 숫자만 입력할 수 있습니다.")
                    cellNo.focus();
                    return false;
                }*!/

        if(isNaN(cellNo.val())){
            console.log(cellNo.val());

            alert("전화번호는 숫자만 입력할 수 있습니다.")
            cellNo.focus();
            return false;

        }


        /!**
         * 회원 가입이랑 회원 정보 수정에서 모두 이 유효성검사를 사용해야한다.
         * 그렇다면 documet.form의 name.submit()을 어떻게 활용해야될까?
         *!/

        /!*document.join_form.submit();*!/ //유효성 검사를 모두 통과했으면 회원가입.
        /!* document.form.submit();*!/
        /!*    let formName = $('form').attr('name');
            if(formName == 'join_form'){
                document.join_form.submit();
            }else if(formName =='modofy_form'){
                document.modofy_form.submit();

            }
    *!/

        /!**
         * 1. 이름 크기 제한, 6글자 입력했더니 안됨. "회원가입테스트" (actual: 21, maximum: 20)
         * 2. 전화번호 그냥 기호 입력했는데 됐음.
         * 3. 이메일 형식 가운데 @ 받기
         *!/




    }

*/
