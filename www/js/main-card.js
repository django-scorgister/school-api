
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
    const centerY = (windowHeight - elementHeight) / 2 - centeredElement.offsetTop + window.pageYOffset;

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

function getZoomedElement() {
    var docs = document.getElementsByClassName("zoom-card");
    if(docs.length > 0)
        return docs[0];
}

function loadMainCard(id) {
    var card = document.getElementById(id);
    document.getElementById("main-img").src = card.children[0].src;

    document.getElementById("main-name").innerText = card.children[1].innerText;

    loadMainCardBody(id);

}