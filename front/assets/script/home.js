async function loadSpaces() {
    let query = document.getElementById("querySpace").value;    
    await fetch(`http://localhost:8080/space/get?query=${query}&page=0&limit=65`, {
        method: "GET",
        headers: { "Content-Type": "application/json", "Authorization": "Bearer " + window.sessionStorage.getItem("token") }
    }).then((res) => {
        if (res.status==403){
            window.location.replace("http://127.0.0.1:5500/login.html");
        }
        res.json().then(data => {
            let boxSpace = document.getElementById("divSpaces")
            boxSpace.innerHTML="";
            data.forEach(space => {
                let a = document.createElement("a");
                a.className = "btn btn-dark btn-space";
                a.onclick = function(){
                    toSpace(space.id)
                };
                a.text = space.name;
                boxSpace.appendChild(a);
            });
        })
    });
}

async function toSpace(id) {
    window.localStorage.setItem("spaceId", id);
    window.location.href = "http://127.0.0.1:5500/spaces.html"
}
