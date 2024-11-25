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

loadAnswers()
