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