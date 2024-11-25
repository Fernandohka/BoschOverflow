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

loadAnswers()
