const DIV_CONTAINER = document.getElementById("card-container");

function load(apiPath, arrayName, imgPath, idContainer = "card-container", customCardFunc = customCard0, postData = null) {
    var resFunc = function(success, obj) {
        if(!success)
            return;

        var sts = obj['result'][arrayName];
        for(var i = 0; i < sts.length; i++) {
            addCard(sts[i], imgPath, idContainer, customCardFunc);
        }

        if(sts.length == 0) {
            addAddCard(idContainer, customCardFunc);
        }
    };

    if(postData == null)
        sendGet(apiPath, resFunc);
    else
        sendPost(apiPath, postData, resFunc);

}

function addCard(cardObj, imgPath, idContainer, customCardFunc) {
    var div = document.createElement("div");
    div.setAttribute("id", cardObj['id']);
    div.setAttribute("class", "card basic");
    div.setAttribute("onclick", "onClickCard(" + cardObj['id'] + ")")

    var img = document.createElement("img");
    img.setAttribute("class", "card-img");

    img.setAttribute("src", imgPath + "/" + cardObj['id'] + ".jpeg");
    img.setAttribute("onerror", "this.src='" + imgPath + "/default.png'")
    img.setAttribute("class", "img-card")

    var h3 = document.createElement("h3");
    h3.innerText = cardObj['name'];

    div.appendChild(img);
    div.appendChild(h3);

    customCardFunc(div);
    
    document.getElementById(idContainer).appendChild(div);
}

function addAddCard(idContainer, customCardFunc) {
    var div = document.createElement("div");

    div.setAttribute("class", "card basic");
    div.setAttribute("onclick", "addNew()")
    var img = document.createElement("img");
    img.setAttribute("class", "card-img");

    img.setAttribute("src", imgAdd);
    img.setAttribute("class", "img-card");

    var h3 = document.createElement("h3");
    h3.innerText = "Add new";

    div.appendChild(img);
    div.appendChild(h3);

    customCardFunc(div);
    
    document.getElementById(idContainer).appendChild(div);
}

function customCard0(div) {

}

function addNew() {
    window.location = addRedirect;
}