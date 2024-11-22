async function loadSpaces(){
    console.log("Bearer "+window.sessionStorage.getItem("token"))
    await fetch("http://localhost:8080/space/get?query=&page=1&limit=2",{
        method:"GET",
        headers:{"Content-Type":  "application/json","Authorization":"Bearer "+window.sessionStorage.getItem("token")}
    }).then((res)=>{
        res.json().then(data=>{
            console.log(data)
        })
    });
}

async function toSpace(id){
    window.localStorage.setItem("spaceId",id);
    window.location.href = "http://127.0.0.1:5500/spaces.html"
}