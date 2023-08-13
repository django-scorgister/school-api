const DIV_CONTAINER = document.getElementById("card-container");

function load(apiPath, arrayName) {
    var resFunc = function(success, obj) {
        if(!success)
            return;

        var sts = obj['result'][arrayName];
        for(var i = 0; i < sts.length; i++) {
            addCard(sts[i]);
        }
    };

    sendGet(apiPath, resFunc);
}

function addCard(cardObj) {
    var div = document.createElement("div");
    div.setAttribute("id", cardObj['id']);
    div.setAttribute("class", "card basic");
    div.setAttribute("onclick", "onClickCard(" + cardObj['id'] + ")")

    var img = document.createElement("img");
    img.setAttribute("class", "card-img");

    img.setAttribute("src", "images/tomcat.gif");

    var h3 = document.createElement("h3");
    h3.innerText = cardObj['name'];

    div.appendChild(img);
    div.appendChild(h3);
    
    DIV_CONTAINER.appendChild(div);
}