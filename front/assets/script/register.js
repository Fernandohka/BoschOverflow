async function  register(){
    let name = document.getElementById("name").value;
    let email = document.getElementById("email").value;
    let edv = document.getElementById("edv").value;
    let password = document.getElementById("password").value;

    console.log(name,email,edv,password);

    let res = await fetch("http://localhost:8080/user",{
        method:"POST",
        headers:{"Content-Type":"application/json"},
        body:JSON.stringify({"name":name,"mail":email,"password":password,"edv":edv})
    }).then((res)=>{
        res.json().then(data=>{
            console.log(data)
            alert(data.messages)
        })
    });
}