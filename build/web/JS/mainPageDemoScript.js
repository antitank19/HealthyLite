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

const subNav = document.querySelector("#subNav");
const subInfoTab = document.querySelector("#accountInfoTab");
const subStatTab = document.querySelector("#accountStatTab");

const productPage = document.querySelector("#productPage");
const accountInfoPage = document.querySelector("#accountInfoPage");
const accountStatPage = document.querySelector("#accountStatPage");
const cartPage = document.querySelector("#cartPage");

function hideAllPage() {
    productPage.classList.add("displayNone");
    accountInfoPage.classList.add("displayNone");
    accountStatPage.classList.add("displayNone");
    cartPage.classList.add("displayNone");
}
function showAllPage() {
    productPage.classList.remove("displayNone");
    accountInfoPage.classList.remove("displayNone");
    accountStatPage.classList.remove("displayNone");
    cartPage.classList.remove("displayNone");
}
function hideSelectedElement(page) {
    page.classList.add("displayNone");
}
function showSelectedElement(page) {
    page.classList.remove("displayNone");
}
function deActiviateAllTab() {
    mainAccountTab.classList.remove("active");
    mainProductTab.classList.remove("active");
    mainCartTab.classList.remove("active");
    subInfoTab.classList.remove("active");
    subStatTab.classList.remove("active");
}
function deActiviateSelectedTab(element) {
    element.classList.remove("active");
}
function activiateSelectedTab(element) {
    element.classList.add("active");
}
function showProductPage() {
    hideAllPage();
    deActiviateAllTab();
    hideSelectedElement(subNav);

    showSelectedElement(productPage);
    activiateSelectedTab(mainProductTab);
}
function showCartPage() {
    hideAllPage();
    deActiviateAllTab();
    hideSelectedElement(subNav);

    showSelectedElement(cartPage);
    activiateSelectedTab(mainCartTab);
}
function showAcountInfoPage() {
    hideAllPage();
    deActiviateAllTab();
    hideSelectedElement(subNav);

    showSelectedElement(accountInfoPage);
    showSelectedElement(subNav);
    activiateSelectedTab(mainAccountTab);
    activiateSelectedTab(subInfoTab);
}
function showAcountStatPage() {
    hideAllPage();
    deActiviateAllTab();
    hideSelectedElement(subNav);

    showSelectedElement(accountStatPage);
    showSelectedElement(subNav);
    activiateSelectedTab(mainAccountTab);
    activiateSelectedTab(subStatTab);
}
mainProductTab.addEventListener("click", showProductPage);
mainCartTab.addEventListener("click", showCartPage);
mainAccountTab.addEventListener("click", showAcountInfoPage);
subInfoTab.addEventListener("click", showAcountInfoPage);
subStatTab.addEventListener("click", showAcountStatPage);
function a() {
    alert("ok");
}

//code for pop up
let backdrop = document.querySelector("#backdrop");
backdrop.addEventListener("click", hideAllPopUp);

let updatAccountForm = document.querySelector(".popUp.updateAccountForm");
function popUpAccountForm() {
    alert("ok");
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
//order
function confirmOrder() {
    if(numOfDetail()===0){
        alert("Cart is still empty\nBuy some product");
        return false;
    }
    let confirm=prompt("Confirm order (Y|N)?","N");
    if(confirm===null)return false;
    console.log(confirm.trim().charAt(0)==="Y"||confirm.trim().charAt(0)==="y");
    return confirm.trim().charAt(0)==="Y"||confirm.trim().charAt(0)==="y";
}
function isDetailNull(proId){
    return document.querySelector(`#newDetailCard-${proId}`)===null;
}
function numOfDetail(){
    return document.querySelectorAll("#newCartCard tr.detailCard").length;
}
function addToCart(proId, proName, proPrice) {
    if(isDetailNull(proId))
        addDetailCart(proId, proName, proPrice);
    else increaseProductNum(proId);
    event.stopImmediatePropagation();
}
function increaseProductNum(proId){
    document.querySelector(`#detailProductNum-${proId}`).value++;
    calcDetailTotoal(proId);
}
function removeDetail(proId){
    if (!isDetailNull(proId)){
        renamingDetail(proId);
        document.querySelector(`#newDetailCard-${proId}`).remove();
    }
    calcCartTotal();
}
function calcDetailTotoal(proId){
    let total=document.querySelector(`#detailTotal-${proId}`);
    let numOfProduct=document.querySelector(`#detailProductNum-${proId}`);
    let productPrice=document.querySelector(`#detailProductPrice-${proId}`);
    total.innerText=numOfProduct.value*productPrice.value;
    calcCartTotal();
}
function calcCartTotal(){
    l=document.querySelectorAll(`#newCartCard .detailTotal`);
    let total=0;
    for(i=0;i<l.length;i++){
        total=parseInt(total)+parseInt(l[i].innerText);
    }
    document.querySelector(`#newCartCard .cartTotal`).innerText=total;
}
function addDetailCart(proId, proName, proPrice){
    let numOfThisDetail=numOfDetail()+1;
    document.querySelector(`#newCartCard tbody#cartDetailList`).innerHTML+=
`                        <tr class="detailCard" id="newDetailCard-${proId}">
                            <td><span class="detailProductname">${proName}</span></td>
                            <td><input type="number" name="detailProductPrice-${numOfThisDetail}" id="detailProductPrice-${proId}" value="${proPrice}" readonly></td><td>X</td>
                            <td><input type="number" name="detailProductNum-${numOfThisDetail}" id="detailProductNum-${proId}" value="1" min="1" onchange="calcDetailTotoal('${proId}')" required></td>
                            <td style="text-align: center">=</td>
                            <td><span class="detailTotal" id="detailTotal-${proId}">${proPrice}</span></td>
                            <td style="text-align: center">vnd</td>
                            <td onclick="removeDetail('${proId}')"><i class="far fa-minus-square"></i></td>
                            <td><input type="hidden" name="detailProductId-${numOfThisDetail}" id="detailProductId-${proId}" value="${proId}"></td>
                        </tr>\n`;
    calcDetailTotoal(proId);
}
function renamingDetail(proId){
    let currentDetail=document.querySelector(`#newDetailCard-${proId}`);
    while(currentDetail.nextElementSibling!==null){
        currentDetail=currentDetail.nextElementSibling;
        console.log(currentDetail);
        currentDetailProductId=currentDetail.id.split('-')[1];
        console.log(currentDetailProductId);
        
        currentDetail.querySelector(`#detailProductPrice-${currentDetailProductId}`).name=
            `${currentDetail.querySelector(`#detailProductPrice-${currentDetailProductId}`).name.split('-')[0]}-`+
            `${parseInt(currentDetail.querySelector(`#detailProductPrice-${currentDetailProductId}`).name.split('-')[1])-1}`;
        console.log(currentDetail.querySelector(`#detailProductPrice-${currentDetailProductId}`).name);

        currentDetail.querySelector(`#detailProductNum-${currentDetailProductId}`).name=
            `${currentDetail.querySelector(`#detailProductNum-${currentDetailProductId}`).name.split('-')[0]}-`+
            `${parseInt(currentDetail.querySelector(`#detailProductNum-${currentDetailProductId}`).name.split('-')[1])-1}`;
        console.log(currentDetail.querySelector(`#detailProductNum-${currentDetailProductId}`).name);

        currentDetail.querySelector(`#detailProductId-${currentDetailProductId}`).name=
            `${currentDetail.querySelector(`#detailProductId-${currentDetailProductId}`).name.split('-')[0]}-`+
            `${parseInt(currentDetail.querySelector(`#detailProductId-${currentDetailProductId}`).name.split('-')[1])-1}`;
        console.log(currentDetail.querySelector(`#detailProductId-${currentDetailProductId}`).name);
    }
}

//rating
let ratingForm=document.querySelector('#ratingForm');
function showRatingForm(productId, productName, numOfStar, comment, formAction){
    hideAllPopUp();
    ratingForm.classList.remove('displayNone');
    backdrop.classList.remove("displayNone");
    ratingForm.querySelector('input#ratingProductId').value=productId;
    ratingForm.querySelector('span.ratingProductName').innerText=productName;
    ratingForm.querySelector(`#star${numOfStar}`).checked=true;
    ratingForm.querySelector('textarea').innerText=comment;
    ratingForm.action=formAction;
}