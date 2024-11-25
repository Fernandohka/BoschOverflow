// Fechar o modal de permissões ao clicar em "Salvar Alterações"
document.getElementById('saveChangesButton').addEventListener('click', function () {
    const modal = document.getElementById('userPermissionModal');
    const bootstrapModal = bootstrap.Modal.getInstance(modal);
    bootstrapModal.hide();
});

async function newQuest() {
    spaceid = window.localStorage.getItem("spaceId").value
    questionInput = document.getElementById("questionInput").value

    console.log(spaceid, questionInput)


    await fetch("http://localhost:8080/answer",
        {
            method: "POST",
            headers: { "Content-Type": "application/json", "Authorization": "Bearer " + window.sessionStorage.getItem("token") },
            body: JSON.stringify({ "description": questionInput, "idUserSpace": spaceid })
        }).then((res) => {
            res.json().then(data => {
                console.log(data)
            })
        });
}

async function loadUser() {
    let modal = document.getElementById("listUser");
    modal.innerHTML = "";
    let li = document.createElement("li");
    li.className = "list-group-item d-flex justify-content-between align-items-center";
    li.innerHTML = `
        Impossivel carregar!!
        <button class="btn btn-sm btn-dark" data-bs-toggle="modal" data-bs-target="#userPermissionModal">Alterar Permissão</button>
    `
    modal.appendChild(li)
}

async function newPermission() {
    let idUser = document.getElementById("userName").value;
    let permission = document.getElementById("userPermission").value;

    await fetch("http://localhost:8080/permission/post",
        {
            method: "POST",
            headers: { "Content-Type": "application/json", "Authorization": "Bearer " + window.sessionStorage.getItem("token") },
            body: JSON.stringify({ 
                "userId": idUser, 
                "spaceId":  window.localStorage.getItem("spaceId"),
                "permission": permission
            })
        }).then((res) => {
            if(res.status == 403 ){
                alert("Sem Autorização!!");
                return;
            }
            res.json().then(data => {
                alert("Criado com sucesso!!");
                console.log(data)
            })
        });
}

async function loadQuestions() {
    console.log("Bearer " + window.sessionStorage.getItem("token"))
    await fetch(`http://localhost:8080/question/by-space/6?page=0&size=5`, {
        method: "GET",
        headers: { "Content-Type": "application/json", "Authorization": "Bearer " + window.sessionStorage.getItem("token") }
    }).then((res) => {
        console.log(res)
        res.json().then(data => {
            let boxQuestion = document.getElementById("divQuestions");
            boxQuestion.innerHTML="";
            data.forEach(question => {
                let li = document.createElement("li");
                li.className = "list-group-item d-flex justify-content-between align-items-center";
                li.innerHTML = `
                    ${question.description}
                    <div>
                        <a href="question.html" class="btn btn-sm btn-dark">Ver pergunta</a>
                        <a href="spaces.html" class="btn btn-sm btn-dark" onclick=${function() {toQuestion(question.id)}}>Deletar</a>
                    </div>
                `
                boxQuestion.appendChild(li);
            });
        })
    });
}

async function toQuestion(id) {
    window.localStorage.setItem("questionId", id);
    window.location.href = "http://127.0.0.1:5500/question.html";
}

async function deleteQuestion(id) {
    
}

loadQuestions();