async function  login(){
    let login = document.getElementById("login").value;
    let pass = document.getElementById("pass").value;

    console.log(login);
    console.log(pass);

    await fetch("http://localhost:8080/auth",
    {
        method:"POST",
        headers:{"Content-Type":"application/json"},
        body:JSON.stringify({"login":login,"password":pass})
    }).then((res)=>{
        res.json().then(data =>  {
            console.log(data)
            alert(data.messages)

            if(data.token != null) {
                sessionStorage.setItem("token", data.token);
                window.location.href = "http://127.0.0.1:5500/home.html"
            }
        })
    });
}