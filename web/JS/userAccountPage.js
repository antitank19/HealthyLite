/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//show a caretain page

const subNav = document.querySelector("#subNav");
const subInfoTab = document.querySelector("#accountInfoTab");
const subStatTab = document.querySelector("#accountStatTab");

const productPage = document.querySelector("#productPage");
const accountInfoPage = document.querySelector("#accountInfoPage");
const accountStatPage = document.querySelector("#accountStatPage");
const cartPage = document.querySelector("#cartPage");

function hideAllPage() {
    accountInfoPage.classList.add("displayNone");
    accountStatPage.classList.add("displayNone");
}
function hideSelectedElement(page) {
    page.classList.add("displayNone");
}
function showSelectedElement(page) {
    page.classList.remove("displayNone");
}
function deActiviateAllTab() {
    subInfoTab.classList.remove("active");
    subStatTab.classList.remove("active");
}
function deActiviateSelectedTab(element) {
    element.classList.remove("active");
}
function activiateSelectedTab(element) {
    element.classList.add("active");
}
function showAcountInfoPage() {
    hideAllPage();
    deActiviateAllTab();
    hideSelectedElement(subNav);

    showSelectedElement(accountInfoPage);
    showSelectedElement(subNav);
    activiateSelectedTab(subInfoTab);
    resizeAside();
}
function showAcountStatPage() {
    hideAllPage();
    deActiviateAllTab();
    hideSelectedElement(subNav);

    showSelectedElement(accountStatPage);
    showSelectedElement(subNav);
    activiateSelectedTab(subStatTab);
    resizeAside();
}
subInfoTab.addEventListener("click", showAcountInfoPage);
subStatTab.addEventListener("click", showAcountStatPage);

//code for pop up
let backdrop = document.querySelector("#backdrop");
backdrop.addEventListener("click", hideAllPopUp);

let updatAccountForm = document.querySelector(".popUp.updateAccountForm");
function popUpAccountForm() {
    backdrop.classList.remove("displayNone");
    updatAccountForm.classList.remove("displayNone");
}
function popUpProductDetail(proId) {
    backdrop.classList.remove("displayNone");
    console.log(proId);
    document.querySelector(`#productDetail-${proId}`).classList.remove("displayNone");

}
function hideAllPopUp() {
    backdrop.classList.add("displayNone");
    let popUps = document.querySelectorAll(`.popUp:not(.displayNone)`);
    for (let i = 0; i < popUps.length; i++) {
        popUps[i].classList.add("displayNone");
    }
}
//document.body.addEventListener("click", hideAllPopUp);


//rating
let ratingForm=document.querySelector('#ratingForm');
function showRatingForm(productId, productName, numOfStar, comment, formAction){
    hideAllPopUp();
    numOfStar=parseInt(numOfStar);
    ratingForm.classList.remove('displayNone');
    backdrop.classList.remove("displayNone");
    ratingForm.querySelector('input#ratingProductId').value=productId;
    ratingForm.querySelector('span.ratingProductName').innerText=productName;
    ratingForm.querySelector(`#star${numOfStar}`).checked=true;
    ratingForm.querySelector('textarea').innerText=comment;
    ratingForm.action=formAction;
}
function resizeAside() {
    $("aside").height($("main").height());
}