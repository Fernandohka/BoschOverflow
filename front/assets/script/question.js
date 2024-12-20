async function loadAnswers() {
    console.log("Bearer " + window.sessionStorage.getItem("token"))
    await fetch(`http://localhost:8080/answer/${window.localStorage.getItem("questionId")}`, {
        method: "GET",
        headers: { "Content-Type": "application/json", "Authorization": "Bearer " + window.sessionStorage.getItem("token") }
    }).then((res) => {
        console.log(res)
        if(res.status == 409) {
            let boxAnswer = document.getElementById("divAnswers");
            boxAnswer.innerHTML="";
            boxAnswer.innerHTML = `
                <li class="list-group-item">
                    Ainda não há respostas.
                </li>
            `
            return;
        }
        res.json().then(data => {
            let boxAnswer = document.getElementById("divAnswers");
            boxAnswer.innerHTML="";
            data.answersList.forEach(answer => {
                let li = document.createElement("li");
                li.className = "list-group-item";
                li.textContent = answer.description;
                boxAnswer.appendChild(li);
            });
        })
    });
}

async function newAnswers() {
    let userAnswer = document.getElementById("userAnswer").value;
    console.log(userAnswer)

    await fetch(`http://localhost:8080/answer`, {
        method: "POST",
        headers: { "Content-Type": "application/json", "Authorization": "Bearer " + window.sessionStorage.getItem("token") },
        body:JSON.stringify({
            "questionId":window.localStorage.getItem("questionId"),
            "description":userAnswer
        })
    }).then((res) => {
        console.log(res)
        if(res.status>=200 && res.status<299){
            alert("Criado com sucesso!!")
            loadAnswers();
            return
        }
        alert("Erro ao criar resposta!")
        res.json().then(data=>{
            console.log(data);
            alert(data);
        })
    });
}

loadAnswers()
