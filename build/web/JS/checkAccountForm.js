/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function checkUserName(inputId) {
    if (!/^\S{6,}$/.test(document.querySelector('#' + inputId).value))
        alert('Username is invalid');
    return /^\S{6,}$/.test(document.querySelector('#' + inputId).value);
}
;
function checkPassword(inputId) {
    if (!/^\S{6,}$/.test(document.querySelector('#' + inputId).value))
        alert('Password is invalid');
    return /^\S{6,}$/.test(document.querySelector('#' + inputId).value);
}
;
function checkConfirmPassword(passId, confirmId) {
    if (document.querySelector('#' + passId).value !== document.querySelector('#' + confirmId).value)
        alert('Fail to confirm password');
    return document.querySelector('#' + passId).value === document.querySelector('#' + confirmId).value;
}
;
function finalCheck(action, usernameId, passId, confirmId) {
    console.log(action);
    alert("wait");
    if (action === 'createAccount'){
        let a= checkUserName(usernameId) && checkPassword(passId) && confirmPassword(passId, confirmId);
        return a;
    }else {
        if ((document.querySelector(`#changePasswordYes`).checked)){
            let a=checkUserName(usernameId) && checkPassword(passId) && confirmPassword(passId, confirmId);
            return a;
        }
        else {
            let a=checkUserName(usernameId);
            return a;
        }
    }
}
;
function changePassword() {
    if (document.querySelector(`#changePasswordYes`).checked) {
        document.querySelector(`#passwordRow`).classList.remove(`displayNone`);
        document.querySelector(`#passwordRow`).required = true;
        document.querySelector(`#confirmRow`).classList.remove(`displayNone`);
        document.querySelector(`#confirmRow`).required = true;
    } else {
        document.querySelector(`#passwordRow`).classList.add(`displayNone`);
        document.querySelector(`#passwordRow`).required = false;
        document.querySelector(`#confirmRow`).classList.add(`displayNone`);
        document.querySelector(`#confirmRow`).required = false;
    }
}