function confirmOrder() {
    if (numOfDetail() === 0) {
        alert("Cart is still empty\nBuy some product");
        return false;
    }
    let confirm = prompt("Confirm order (Y|N)?", "N");
    if (confirm === null)
        return false;
    console.log(confirm.trim().charAt(0) === "Y" || confirm.trim().charAt(0) === "y");
    return confirm.trim().charAt(0) === "Y" || confirm.trim().charAt(0) === "y";
}
function calcDetailTotoal(proId) {
    let total = document.querySelector(`#detailTotal-${proId}`);
    let numOfProduct = document.querySelector(`#detailProductNum-${proId}`);
    let productPrice = document.querySelector(`#detailProductPrice-${proId}`);
    total.innerText = numOfProduct.value * productPrice.value;
    calcCartTotal();
}

function calcCartTotal() {
    l = document.querySelectorAll(`#newCartCard .detailTotal`);
    let total = 0;
    for (i = 0; i < l.length; i++) {
        total = parseInt(total) + parseInt(l[i].innerText);
    }
    document.querySelector(`#newCartCard .cartTotal`).innerText = total;
}
function hideAllPopUp() {
    backdrop.classList.add("displayNone");
    let popUps = document.querySelectorAll(`.popUp:not(.displayNone)`);
    for (let i = 0; i < popUps.length; i++) {
        popUps[i].classList.add("displayNone");
    }
}
function roundNumofStar(numOfStar) {
    let roundNumOfStar = 5 * (Math.round((product.getAvgNumOfStar() * 10) / 5)) / 10.0;
    return roundNumOfStar;
}
function resizeAside() {
    $("aside").height($("main").height());
}