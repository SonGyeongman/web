const submit = window.document.getElementById('submit');

const checkData = {
    emailCheck : {
        input : window.document.querySelector('input[name="email"]'),
        tag : window.document.getElementById('email-check'),
        success : "사용 가능한 아이디(이메일)입니다.",
        fail : "이미 사용 중인 아이디(이메일)입니다.",
        flag : false
    },
    passwordCheck : {
        input : window.document.querySelector('input[name="password"]'),
        inputCheck : window.document.querySelector('input.password-check'),
        tag : window.document.getElementById('password-check'),
        success : "비밀번호가 일치합니다.",
        fail: "비밀번호가 맞지 않습니다.",
        flag : false
    },
    phoneCheck : {
        input : window.document.querySelector('input[name="phone"]'),
        tag : window.document.getElementById('phone-check'),
        success : "사용 가능한 번호입니다.",
        fail : "이미 사용 중인 번호입니다.",
        flag : false
    }
};

const checkRequest = (data, check) => {
    let xhr = new XMLHttpRequest();
    xhr.open('GET', `/user/${data}`);
    xhr.onreadystatechange = () => {
        if(xhr.readyState === XMLHttpRequest.DONE){
            if(xhr.status >= 200 && xhr.status < 300){
                switch (xhr.responseText){
                    case 'SUCCESS' :
                        check.tag.innerText = check.success;
                        check.tag.style.color = 'blue';
                        check.flag = true;
                        break;
                    case 'FAILURE' :
                        check.tag.innerText = check.fail;
                        check.tag.style.color = 'red';
                        check.flag = false;
                        break;
                    default :
                        break;
                }
            }
        } else {
            console.log('실패');
        }
    };
    xhr.send();
};

checkData.emailCheck.input.addEventListener('blur', e => {
    if(checkData.emailCheck.input.value !== ''){
        checkRequest(`email-check?email=${checkData.emailCheck.input.value}`, checkData.emailCheck);
        return;
    }
    checkData.emailCheck.tag.innerText='';
    checkData.emailCheck.flag = false;
});

checkData.phoneCheck.input.addEventListener('blur', e => {
    if(checkData.phoneCheck.input.value !== ''){
        checkRequest(`phone-check?phone=${checkData.phoneCheck.input.value}`, checkData.phoneCheck);
        return;
    }
    checkData.phoneCheck.tag.innerText='';
    checkData.passwordCheck.flag = false;
});

checkData.passwordCheck.inputCheck.addEventListener('blur', () =>  {
    if(checkData.passwordCheck.input.value === null){
        return;
    }
    if(checkData.passwordCheck.input.value === checkData.passwordCheck.inputCheck.value){
        checkData.passwordCheck.tag.innerText = checkData.passwordCheck.success;
        checkData.passwordCheck.tag.style.color = 'blue';
        checkData.passwordCheck.flag = true;
        return;
    }
    checkData.passwordCheck.tag.innerText = checkData.passwordCheck.fail;
    checkData.passwordCheck.tag.style.color = 'red';
    checkData.passwordCheck.flag = false;
});

submit.addEventListener('submit', e => {
    if(checkData.emailCheck.flag && checkData.passwordCheck.flag && checkData.phoneCheck.flag){
        return;
    }
    e.preventDefault();
});

submit.addEventListener('click', e => {
    if(checkData.emailCheck.flag && checkData.passwordCheck.flag && checkData.phoneCheck.flag){
        return;
    }
    e.preventDefault();
});