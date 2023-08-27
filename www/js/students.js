load("/api/students", "students", "images/student");

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

    sendPost("/api/quantity", `{"action": "get", "id": ${id}, "user_id": ${uid}}`, (success, response) => {
        if(success)
            document.getElementById("main-material-quantity").innerText = response['result']['quantity'];
    });
}

function addOne(event) {
    var id = document.getElementById("main-select-materials").value;
    var uid = getZoomedElement().id;
    
    sendPost("/api/quantity", `{"action": "add", "id": ${id}, "user_id": ${uid}, "quantity": 1}`, (success, response) => {
        if(success) {
            var o = document.getElementById("main-material-quantity");
            o.innerText = parseInt(o.innerText) + 1;
            document.getElementById("error-add").hidden = true;
        }else {
            document.getElementById("error-add").hidden = false;
        }
    });
}

function addCustom(e) {
    var custom = document.getElementById("custom-input");
    if(custom.hidden) {
        custom.value = 0;
        custom.hidden = false;
        e.target.innerText = "Submit";
    }else {
        var id = document.getElementById("main-select-materials").value;
        var uid = getZoomedElement().id;
        var quantity = custom.value;
        if(quantity == "") {
            custom.value = 0;
            return;
        }

        quantity = parseInt(quantity);

        if(quantity == 0) {
            custom.hidden = true;
            e.target.innerText = "Other quantity";
        }
        sendPost("/api/quantity", '{"action": "add", "id": ' + id + ', "user_id": ' + uid + ', "quantity", ' + quantity + '}', (success, response) => {
            if(success) {
                custom.hidden = true;
                var o = document.getElementById("main-material-quantity");
                o.innerText = parseInt(o.innerText) + parseInt(quantity);
                document.getElementById("error-add").hidden = true;
                e.target.innerText = "Other quantity";

            }else {
                document.getElementById("error-add").hidden = false;
            }
        });
    }
}

function loadMainCardBody(id) {
    var sel = document.getElementById("main-select-materials");

    sel.innerHTML = "";
    sel.onchange = selectChange;

    document.getElementById("add-one").onclick = addOne;
    document.getElementById("add-custom").onclick = addCustom;
    document.getElementById("add-custom").innerText = "Other quantity";


    document.getElementById("main-material-quantity").innerText = "--";

    document.getElementById("error-add").hidden = true;

    document.getElementById("main-custom").href = "custom.html?id=" + id;
     

    sendGet("/api/materials", (success, response) => {
        if(!success) {
            var op = document.createElement("option");
            op.innerText = "No materials registered";
            sel.appendChild(op);
            return;
        }
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