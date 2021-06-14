/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//show a caretain page
const mainNav = document.querySelector("#mainNav");
const mainProductTab = document.querySelector(".productTab");
const mainCartTab = document.querySelector(".cartTab");
const mainAccountTab = document.querySelector(".accountTab");

const backdrop=document.querySelector("#backdrop");


function hideSelectedElement(page) {
    page.classList.add("displayNone");
}
function showSelectedElement(page) {
    page.classList.remove("displayNone");
}
function deActiviateSelectedTab(element) {
    element.classList.remove("active");
}
function activiateSelectedTab(element) {
    element.classList.add("active");
}


//code for pop up
function popUpAccountDetail(accId) {
    backdrop.classList.remove("displayNone");
    console.log(accId);
    document.querySelector(`#accountDetail-${accId}`).classList.remove("displayNone");

}
function hideAllPopUp() {
    backdrop.classList.add("displayNone");
    let popUps = document.querySelectorAll(`.popUp:not(.displayNone)`);
    for (let i = 0; i < popUps.length; i++) {
        popUps[i].classList.add("displayNone");
    }
}


function resizeUserCard() {
    var maxHeight = 0;
    var maxWidth = 0;
    $(".userCard").each(function () {
        if ($(this).height() > maxHeight) {
            maxHeight = $(this).height();
        }
        if ($(this).width() > maxWidth) {
            maxWidth = $(this).width();
        }
    });
    $(".userCard").height(maxHeight);
    $(".userCard").width(maxWidth);
}
