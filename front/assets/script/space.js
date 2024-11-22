// Fechar o modal de permissões ao clicar em "Salvar Alterações"
document.getElementById('saveChangesButton').addEventListener('click', function () {
    const modal = document.getElementById('userPermissionModal');
    const bootstrapModal = bootstrap.Modal.getInstance(modal);
    bootstrapModal.hide();
});

async function newQuest(){
    spaceid = window.localStorage.getItem("spaceId").value
    questionInput = document.getElementById("questionInput").value

    console.log(spaceid,questionInput)


    await fetch("http://localhost:8080/answer",
        {
            method:"POST",
            headers:{"Content-Type":"application/json","Authorization":"Bearer "+window.sessionStorage.getItem("token")},
            body:JSON.stringify({"description":questionInput,"idUserSpace":spaceid})
        }).then((res)=>{
            res.json().then(data =>  {
                console.log(data)
            })
        });
}