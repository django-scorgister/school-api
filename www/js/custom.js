const params = new URLSearchParams(window.location.search);
const id = params.get("id");

document.getElementById("custom-img").src = defualtImgPath + id + ".jpeg";
sendPost(apiNamePath, {"action": "get", "id": parseInt(id)}, (success, response) => {
    if(success)
        document.getElementById("name").innerText = response['result']["name"];
    else {
        alert("Error: no id selected");
        window.location = redirectLocation;
    }
});

document.getElementById("file-selector").setAttribute("value", id);

function imageChange(input) {
    const [picture] = input.files
    if(picture)
        document.getElementById("custom-img").src = URL.createObjectURL(picture);
}

function editName(but) {
    var n = document.getElementById("name");

    var newInput = document.createElement("input");
    newInput.setAttribute("type", "text");
    newInput.setAttribute("value", n.innerText);
    newInput.setAttribute("placeholder", "Student name");
    newInput.required = true;
    newInput.setAttribute("id", "name");

    but.hidden = true;

    n.parentNode.replaceChild(newInput, n);
}

function valid() {
    var n = document.getElementById("name");
    var upName = false;
    if(n.tagName == "INPUT")
        upName = true;

    var f = document.getElementById("file-selector");

    var imgDone = false;
    var nameDone = false;
    if(f.files[0]) {
        var formData = new FormData();
        formData.append(id, f.files[0], id + ".jpg")

        sendPostFormData(apiUploadPath, formData, (success, response) => {
            imgDone = true
            if(nameDone)
                window.location = redirectLocation;
        });
    }else 
        imgDone = true;

    if(upName) {
        var text = n.value;
        if(text != "")
            sendPost(apiNamePath, {"action": "update", "id": parseInt(id), "name": n.value}, (success, response) => {
                nameDone = true;
                if(imgDone)
                    window.location = redirectLocation;
            });
    }else {
        nameDone = true;
        if(imgDone == true)
           window.location = redirectLocation;
    }
}

function remove() {
    var p = prompt('To confirm, type "' + id +'" in the box below');
    if(p == id)
        sendPost(apiNamePath, {"action": "remove", "id": id}, (success, response) => {
            window.location = redirectLocation;
        });
    else if(p != ""  && p != null)
        alert("Incorrect confirmation");
}