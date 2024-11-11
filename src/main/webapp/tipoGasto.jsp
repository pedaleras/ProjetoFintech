<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<html lang="pt">
<%@include file="head.jsp" %>
<body class="starter-page-page">
<%@include file="header.jsp" %>
<main class="main">
    <div class="container">
        <h2 class="mb-4">Lista de Tipos de Gasto</h2>
        <!-- Botão para abrir modal de cadastro -->
        <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#cadastroTipoGastoModal"
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
            <c:forEach var="tipoGasto" items="${listaTipoGasto}">
                <tr>
                    <td>${tipoGasto.id}</td>
                    <td>${tipoGasto.descricao}</td>
                    <td>
                        <!-- Botão de exclusão com link para um servlet de exclusão -->
                        <a href="deletarTipoGasto?id=${tipoGasto.id}" class="btn btn-danger btn-sm">Excluir</a>

                        <!-- Botão para abrir modal de edição -->
                        <button type="button" class="btn btn-warning btn-sm"
                                data-bs-toggle="modal" data-bs-target="#cadastroTipoGastoModal"
                                data-id="${tipoGasto.id}" data-descricao="${tipoGasto.descricao}">
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
<div class="modal fade" id="cadastroTipoGastoModal" tabindex="-1" aria-labelledby="cadastroTipoGastoLabel"
     aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="cadastroTipoGastoLabel">Cadastrar Tipo de Gasto</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form id="tipoGastoForm" action="cadastrarTipoGasto" method="post">
                    <!-- Campo oculto para o ID, usado em edições -->
                    <input type="hidden" id="tipoGastoId" name="id">
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
    const cadastroModal = document.getElementById('cadastroTipoGastoModal');
    cadastroModal.addEventListener('show.bs.modal', function (event) {
        const button = event.relatedTarget; // Botão que acionou o modal
        const modalTitle = cadastroModal.querySelector('.modal-title');
        const form = document.getElementById('tipoGastoForm');
        const idInput = document.getElementById('tipoGastoId');
        const descricaoInput = document.getElementById('descricao');

        if (button.id === 'novoCadastroBtn') {
            // Se for o botão de novo cadastro, limpa os campos
            modalTitle.textContent = 'Cadastrar Tipo de Gasto';
            form.action = 'cadastrarTipoGasto'; // Define a ação do formulário
            idInput.value = '';
            descricaoInput.value = '';
        } else {
            // Caso contrário, é uma edição
            modalTitle.textContent = 'Editar Tipo de Gasto';
            form.action = 'cadastrarTipoGasto'; // Define a ação do formulário
            idInput.value = button.getAttribute('data-id');
            descricaoInput.value = button.getAttribute('data-descricao');
        }
    });
</script>
</body>
</html>
