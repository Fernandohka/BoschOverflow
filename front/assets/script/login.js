async function  login(){
    let login = document.getElementById("login").value;
    let pass = document.getElementById("pass").value;
    console.log(login);
    console.log(pass);
    let res = await fetch("http://localhost:8080/auth",{
        method:"POST",
        body:JSON.stringify({"login":login,"password":pass})
    });
    console.log(res);
}