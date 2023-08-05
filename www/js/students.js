load("/api/students", "students", customCard);

function onClickCard(id) {
    var card = document.getElementById(id);
    card.setAttribute("class", "card zoom-card");
    document.getElementById("veil").style = "";
    centerElement(id);

}

function restore(id) {
    var card = document.getElementById(id);
    card.setAttribute("class", "card basic");
    card.style.transform = "";
    card.getElementsByClassName("info")[0].style = "display: none;";

    document.getElementById("veil").style = "display: none;";
}

function centerElement(id) {

    const centeredElement = document.getElementById(id);

    centeredElement.children[2].style = "display: block;";


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
    select.addEventListener("change", selectChange);
    
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
    sendPost("/api/material", `{"action": "get", "id": ${id}}`, (xhr, response) => {
        getZoomedElement().getElementsByClassName("quantity")[0].innerText = response['result']['quantity'];
    });
}

function getZoomedElement() {
    var docs = document.getElementsByClassName("zoom-card");
    if(docs.length > 0)
        return docs[0];
}