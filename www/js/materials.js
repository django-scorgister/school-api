load("/api/materials", "materials", "images/material");

function loadMainCardBody(id) {

    document.getElementById("stock-input").hidden = true;
    document.getElementById("add-stock").innerText = "Add stock";

    document.getElementById("add-stock").onclick = addStock;

    document.getElementById("main-custom").href = "custommat.html?id=" + id;

    updateQuantity(id);

    var sel = document.getElementById("main-select-students");
    sel.innerHTML = "";
    sel.onchange = selectChange;

    sendGet("/api/students", (success, response) => {
        if(!success) {
            var op = document.createElement("option");
            op.innerText = "No students registered";
            sel.appendChild(op);
            return;
        }

        var stus = response["result"]["students"];
        for(var i = 0; i < stus.length; i++) {
            var op = document.createElement("option");
            op.setAttribute("value", stus[i]["id"]);
            op.innerText = stus[i]["name"];
            sel.appendChild(op);
        }

        displayQuantity(id, sel.options[sel.selectedIndex].value);
    });
    
}

function updateQuantity(id) {
    sendPost("/api/quantity", {"action": "get_quantity", "id": parseInt(id)}, (success, response) => {
        if(!success) {
            document.getElementById("remaining-quantity").innerText = "--";
            document.getElementById("used-quantity").innerText = "--";
            return;
        }

        document.getElementById("remaining-quantity").innerText = response['result']['quantity'];

        sendPost("/api/quantity", {"action": "get_base", "id": parseInt(id)}, (suc, res) => {
            document.getElementById("used-quantity").innerText = res['result']['quantity'] - response['result']['quantity'];
        });
    });
}

function displayQuantity(id, uid) {
    document.getElementById("main-material-quantity").innerText = "--";
    
    sendPost("/api/quantity", {"action": "get", "id": parseInt(id), "user_id": parseInt(uid)}, (success, response) => {
        if(success)
            document.getElementById("main-material-quantity").innerText = response['result']['quantity'];
        else
            document.getElementById("main-material-quantity").innerText = 0;
    });
}

function selectChange(event) {
    var uid = event.target.value;
    var id = getZoomedElement().id;

    displayQuantity(id, uid);
}

function addStock(e) {
    var inStock = document.getElementById("stock-input");
    if(inStock.hidden) {
        inStock.value = 0;
        inStock.hidden = false;
        e.target.innerText = "Submit";

    }else {
        var id = getZoomedElement().id;
        var quantity = inStock.value;

        if(quantity == "") {
            inStock.value = 0;
            return;
        }

        quantity = parseInt(quantity);

        if(quantity == 0) {
            inStock.hidden = true;
            e.target.innerText = "Add stock";

        }
        sendPost("/api/quantity", {"action": "add_quantity", "id": parseInt(id), "quantity": parseInt(quantity)}, (success, response) => {
            if(success) {
                inStock.hidden = true;
                e.target.innerText = "Add stock";
                updateQuantity(id);
            }else {
                inStock.value = 0;
            }
        });
    }
}