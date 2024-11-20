// Fechar o modal de permissões ao clicar em "Salvar Alterações"
document.getElementById('saveChangesButton').addEventListener('click', function () {
    const modal = document.getElementById('userPermissionModal');
    const bootstrapModal = bootstrap.Modal.getInstance(modal);
    bootstrapModal.hide();
});