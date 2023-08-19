const params = new URLSearchParams(window.location.search);
const id = params.get("id");

document.getElementById("student-img").src = "images/student/" + id + ".jpeg";
sendPost("/api/students", '{"action": "get", "id": ' + id + '}', (success, response) => {
    document.getElementById("name").innerText = response['result']["name"];
});

document.getElementById("file-selector").setAttribute("value", id);

function imageChange(input) {
    const [picture] = input.files
    if(picture)
        document.getElementById("student-img").src = URL.createObjectURL(picture);
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
    var upName =false;
    if(n.tagName == "INPUT")
        upName = true;

    var f = document.getElementById("file-selector");

    if(f.files[0]) {
        var formData = new FormData();
        formData.append(id, f.files[0], id + ".jpg")
        sendPostFormData("/api/upload/student", formData, (success, response) => {
            if(!upName) {
                window.location = "students.html";
            }
        });
    }

    if(upName) {
        var text = n.value;
        if(text != "")
        sendPost("/api/students", '{"action": "update", "id": ' + id + ', "name": "' + n.value + '"}', (success, response) => {
            window.location = "students.html";
        });
    }else
        window.location = "students.html";
}