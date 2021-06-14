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

const backdrop = document.querySelector("#backdrop");


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
//popUp form
let productForm = document.querySelector('form#productForm');
function showProductForm(productId, productName, productPrice, description, formAction) {
    hideAllPopUp();
    let action = (formAction.includes("adminAddProduct") ? "Add product" : "Update product");
    productForm.classList.remove('displayNone');
    backdrop.classList.remove("displayNone");
    productForm.querySelector('input#productId').value = productId;
    productForm.querySelector('input#productName').value = productName;
    productForm.querySelector('input#productPrice').value = productPrice;
    productForm.querySelector('textarea#productDescription').innerText = description;
    productForm.action = formAction;
    productForm.querySelector('input#submit').value = action;
}
function resizeProductCard() {
    var maxHeight = 0;
    var maxWidth = 0;
    $(".productCard").each(function () {
        if ($(this).width() > maxWidth) {
            maxWidth = $(this).width();
        }
    });
    $(".productCard").width(maxWidth);
    $(".productCard").each(function () {
        if ($(this).height() > maxHeight) {
            maxHeight = $(this).height();
        }
    });
    $(".productCard").height(maxHeight);
}