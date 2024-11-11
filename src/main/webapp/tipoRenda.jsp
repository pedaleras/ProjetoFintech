<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<html lang="pt">
<%@include file="head.jsp" %>
<body class="starter-page-page">
<%@include file="header.jsp" %>
<main class="main">
    <div class="container">
        <h2 class="mb-4">Lista de Tipos de Renda</h2>
        <!-- Botão para abrir modal de cadastro -->
        <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#cadastroTipoRendaModal"
                id="novoCadastroBtn">
            Novo
        </button>
        <table class="table table-striped">
            <thead>
            <tr>
                <th>ID</th>
                <th>Descrição</th>
                <th>Ações</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="tipoRenda" items="${listaTipoRenda}">
                <tr>
                    <td>${tipoRenda.id}</td>
                    <td>${tipoRenda.descricao}</td>
                    <td>
                        <!-- Botão de exclusão com link para um servlet de exclusão -->
                        <a href="deletarTipoRenda?id=${tipoRenda.id}" class="btn btn-danger btn-sm">Excluir</a>

                        <!-- Botão para abrir modal de edição -->
                        <button type="button" class="btn btn-warning btn-sm"
                                data-bs-toggle="modal" data-bs-target="#cadastroTipoRendaModal"
                                data-id="${tipoRenda.id}" data-descricao="${tipoRenda.descricao}">
                            Editar
                        </button>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</main>

<!-- Modal de Cadastro/Edição -->
<div class="modal fade" id="cadastroTipoRendaModal" tabindex="-1" aria-labelledby="cadastroTipoRendaLabel"
     aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="cadastroTipoRendaLabel">Cadastrar Tipo de Renda</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form id="tipoRendaForm" action="cadastrarTipoRenda" method="post">
                    <!-- Campo oculto para o ID, usado em edições -->
                    <input type="hidden" id="tipoRendaId" name="id">
                    <div class="mb-3">
                        <label for="descricao" class="form-label">Descrição</label>
                        <input type="text" class="form-control" id="descricao" name="descricao" required>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
                        <button type="submit" class="btn btn-primary">Salvar</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<!-- Bootstrap JS -->
<script src="resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
<script>
    // Script para configurar o modal para edição ou cadastro
    const cadastroModal = document.getElementById('cadastroTipoRendaModal');
    cadastroModal.addEventListener('show.bs.modal', function (event) {
        const button = event.relatedTarget; // Botão que acionou o modal
        const modalTitle = cadastroModal.querySelector('.modal-title');
        const form = document.getElementById('tipoRendaForm');
        const idInput = document.getElementById('tipoRendaId');
        const descricaoInput = document.getElementById('descricao');

        if (button.id === 'novoCadastroBtn') {
            // Se for o botão de novo cadastro, limpa os campos
            modalTitle.textContent = 'Cadastrar Tipo de Renda';
            form.action = 'cadastrarTipoRenda'; // Define a ação do formulário
            idInput.value = '';
            descricaoInput.value = '';
        } else {
            // Caso contrário, é uma edição
            modalTitle.textContent = 'Editar Tipo de Renda';
            form.action = 'cadastrarTipoRenda'; // Define a ação do formulário
            idInput.value = button.getAttribute('data-id');
            descricaoInput.value = button.getAttribute('data-descricao');
        }
    });
</script>
</body>
</html>
