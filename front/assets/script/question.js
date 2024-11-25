async function loadAnswers() {
    console.log("Bearer " + window.sessionStorage.getItem("token"))
    await fetch(`http://localhost:8080/answer/questionId`, {
        method: "GET",
        headers: { "Content-Type": "application/json", "Authorization": "Bearer " + window.sessionStorage.getItem("token") }
    }).then((res) => {
        console.log(res)
        res.json().then(data => {
            let boxAnswer = document.getElementById("divAnswers");
            boxAnswer.innerHTML="";
            data.forEach(answer => {
                let li = document.createElement("li");
                li.className = "list-group-item";
                li.text = answer.description;
                boxQuestion.appendChild(li);
            });
        })
    });
}

async function newAnswers() {
    let userAnwser = document.getElementById("userAnswer").ariaValueMax;
    console.log(userAnwser)

    await fetch(`http://localhost:8080/answer`, {
        method: "POST",
        headers: { "Content-Type": "application/json", "Authorization": "Bearer " + window.sessionStorage.getItem("token") },
        body:JSON.stringify({
            "questionId":window.localStorage.getItem("questionId"),
            "description":userAnwser
        })
    }).then((res) => {
        console.log(res)
        if(res.status>=200 && res.status<299){
            alert("Criado com sucesso!!")
            return
        }
        alert("Erro ao criar resposta!")
        res.JSON().then(data=>{
            console.log(data)
        })
    });
}

loadAnswers()
