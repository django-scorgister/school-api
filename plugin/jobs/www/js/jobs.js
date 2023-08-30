load("/api/jobs", "jobs", "../images/job");

function loadMainCardBody(id) {

    document.getElementById("main-custom").href = "customjob.html?id=" + id;

    var old = document.getElementById("capability");
    if(old.tagName != "strong") {
        var cap = document.createElement("strong");
        cap.setAttribute("value", old.getAttribute("value"));
        cap.id = old.id;

        old.parentNode.replaceChild(cap, old);

    }

    document.getElementById("capability").innerText = "--";
    document.getElementById("attributed").innerText = "--";


    document.getElementById("edit-capability").innerText = "Edit capability";
    document.getElementById("edit-capability").onclick = editCapability;

    document.getElementById("select-student").innerHTML = "";
    document.getElementById("select-student-att").innerHTML = "";
    document.getElementById("select-student").hidden = true;
    document.getElementById("select-student-att").hidden = true;
    document.getElementById("error").hidden = true;
    document.getElementById("assign").hidden = false;
    document.getElementById("remove").hidden = false;



    document.getElementById("assign").onclick = assign;
    document.getElementById("remove").onclick = remove;

    sendPost("/api/jobs", {"action": "get", "id": id}, (success, response) => {
        if(!success)
            return;
        document.getElementById("capability").innerText = response["result"]["capability"];
    });

    updateAssignment(id);
    updateAssignmentList();
    

}

function updateAssignmentList() {
    var sel = document.getElementById("select-student").innerHTML = "";

    sendPost("/api/attribution", {"action": "list_students"}, (success, response) => {
        sel = document.getElementById("select-student");
        if(!success) {
            var op = document.createElement("option");
            op.innerText = "No students available";
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
    });
}

function updateAssignment(id) {
    sendPost("/api/attribution", {"action": "get", "id": id}, (success, response) => {
        if(!success)
            return;

        var att = "";
        var arr = response["result"]["attributions"]
        if(arr.length == 0)
            document.getElementById("attributed").innerText = "--";

        var sel = document.getElementById("select-student-att");
        sel.innerHTML = "";
        for(var i = 0; i < arr.length; i++) {
            sendPost("/api/students", {"action": "get", "id": arr[i]}, (success, response) => {
                if(!success)
                    return;

                if(att != "")
                    att += ", ";

                att += response["result"]["name"];
                document.getElementById("attributed").innerText = att;

                var o = document.createElement("option");
                o.value = response["result"]["id"];;
                o.innerText = response["result"]["name"];
                sel.appendChild(o);
            });
        }
    });
}


function assign(e) {
    document.getElementById("remove").hidden = true;

    var sel = document.getElementById("select-student");
    if(sel.hidden) {
        sel.hidden = false;

    }else {
        var id = getZoomedElement().id;
        var uid = sel.value;

        uid = parseInt(uid);

        sendPost("/api/attribution", {"action": "add", "id": id, "user_id": uid}, (success, response) => {
            if(success) {
                sel.hidden = true;
                document.getElementById("error").hidden = true;
                document.getElementById("remove").hidden = false;

                updateAssignment(id);
                updateAssignmentList();
            }else {
                document.getElementById("error").hidden = false;
            }
        });
    }
}

function remove(e) {
    document.getElementById("assign").hidden = true;

    var sel = document.getElementById("select-student-att");
    if(sel.hidden) {

        updateAssignment(getZoomedElement().id);
        sel.hidden = false;

    }else {
        var id = getZoomedElement().id;
        var uid = sel.value;

        uid = parseInt(uid);

        sendPost("/api/attribution", {"action": "remove", "id": id, "user_id": uid}, (success, response) => {
            if(success) {
                sel.hidden = true;
                document.getElementById("error").hidden = true;
                document.getElementById("assign").hidden = false;

                updateAssignment(id);
            }else {
                document.getElementById("error").hidden = false;
            }
        });
    }
}


function editCapability(e) {
    var cap = document.getElementById("capability");

    if(cap.tagName != "INPUT") {

        var newInput = document.createElement("input");
        newInput.setAttribute("type", "number");
        newInput.setAttribute("value", cap.innerText);
        newInput.setAttribute("min", 0);
        newInput.setAttribute("placeholder", "Capability");
        newInput.required = true;
        newInput.setAttribute("id", "capability");

        cap.parentNode.replaceChild(newInput, cap);
        e.target.innerText = "Submit";

    }else {

        var id = getZoomedElement().id;

        var capability = cap.value;

        if(capability == "") {
            cap.value = 1;
            return;
        }

        capability = parseInt(capability);

        if(capability <= 0) {
            cap.value = 1;
            return;
        }

        if(capability == cap.getAttribute("value")) {
            var stro = document.createElement("strong");
            stro.innerText = cap.getAttribute("value");
            stro.id = cap.id;
    
            cap.parentNode.replaceChild(stro, cap);
            return;
        }

        var res = prompt("Warning: to change the capacity, we will remove all statistics on all jobs");

        if(res == null) {
            var stro = document.createElement("strong");
            stro.innerText = cap.getAttribute("value");
            stro.id = cap.id;
    
            cap.parentNode.replaceChild(stro, cap);
            return;
        }

        sendPost("/api/attribution", {"action": "update_capability", "id": id, "capability": capability}, (success, response) => {
            if(success) {
                var stro = document.createElement("strong");
                stro.innerText = cap.value;
                stro.id = cap.id;
        
                cap.parentNode.replaceChild(stro, cap);
                updateAssignment(id);
            }else {
                cap.value = 1;
            }
        });
    }
}