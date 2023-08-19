load("/api/students", "students", "images/student");

function onClickCard(id) {
    var card = document.getElementById(id);
    card.setAttribute("class", "card zoom-card");
    document.getElementById("veil").style = "";
    document.getElementById("main").style = "";
    loadMainCard(id);
    centerElement(id);

}

function restore(id) {
    var card = document.getElementById(id);
    card.setAttribute("class", "card basic");
    card.style.transform = "";

    document.getElementById("veil").style = "display: none;";
    document.getElementById("main").style = "display: none;";
}

function centerElement(id) {

    const centeredElement = document.getElementById(id);

    const windowWidth = window.visualViewport.width;
    const windowHeight = window.visualViewport.height;
    const elementWidth = centeredElement.offsetWidth;
    const elementHeight = centeredElement.offsetHeight;
    const centerX = (windowWidth - elementWidth) / 2 - centeredElement.offsetLeft;
    const centerY = (windowHeight - elementHeight) / 2 - centeredElement.offsetTop;

    const scaleX = (windowWidth - 250) / elementWidth;
    const scaleY = (windowHeight - 200) / elementHeight;
    const scale = Math.min(scaleX, scaleY);
    
    centeredElement.style.transform = `translate(${centerX}px, ${centerY}px) scale(${scale})`;
}

window.addEventListener('resize', () => {
    requestAnimationFrame(() => {
        var cards = document.getElementsByClassName("zoom-card");
        if(cards.length > 0)
            centerElement(cards[0].id);
    });
});

function customCard(div) {
    var d = document.createElement("div");
    d.setAttribute("class", "info");
    d.style = "display: none;";

    var select = document.createElement("select");
    select.setAttribute("class", "select-material");
  
    var quantity = document.createElement("p");
    quantity.setAttribute("class", "quantity")


    d.appendChild(select);
    d.appendChild(quantity);

    div.appendChild(d);

    sendGet("/api/materials", (xhr, response) => {
        var materials = response['result']['materials'];

        for(var i = 0; i < materials.length; i++) {
            var op = document.createElement("option");
            op.setAttribute("value", materials[i]['id']);
            op.innerText = materials[i]['name'];
            select.appendChild(op)
        }

        quantity.innerText = materials[0]['quantity'];
    });
}

function selectChange(event) {
    var id = event.target.value;
    var uid = getZoomedElement().id;

    displayQuantity(id, uid);
}

function displayQuantity(id, uid) {
    document.getElementById("main-material-quantity").innerText = "--";

    sendPost("/api/quantity", `{"action": "get", "id": ${id}, "user_id": ${uid}}`, (xhr, response) => {
        document.getElementById("main-material-quantity").innerText = response['result']['quantity'];
    });
}

function addOne(event) {
    var id = document.getElementById("main-select-materials").value;
    var uid = getZoomedElement().id;
    
    sendPost("/api/quantity", `{"action": "add", "id": ${id}, "user_id": ${uid}, "quantity": 1}`, (xhr, response) => {
        var o = document.getElementById("main-material-quantity");
        o.innerText = parseInt(o.innerText) + 1;
    });
}

function getZoomedElement() {
    var docs = document.getElementsByClassName("zoom-card");
    if(docs.length > 0)
        return docs[0];
}

function loadMainCard(id) {
    var card = document.getElementById(id);
    document.getElementById("main-img").src = card.children[0].src;

    document.getElementById("main-name").innerText = card.children[1].innerText;

    var sel =  document.getElementById("main-select-materials");

    sel.innerHTML = "";
    sel.onchange = selectChange;

    document.getElementById("add-one").onclick = addOne;
    document.getElementById("main-material-quantity").innerText = "--";

    document.getElementById("main-custom").href = "custom.html?id=" + id;
     

    sendGet("/api/materials", (xhr, response) => {
        var mats = response["result"]["materials"];
        for(var i = 0; i < mats.length; i++) {
            var op = document.createElement("option");
            op.setAttribute("value", mats[i]["id"]);
            op.innerText = mats[i]["name"];
            sel.appendChild(op);
        }

        displayQuantity(sel.options[sel.selectedIndex].value, id);
    });

    


}