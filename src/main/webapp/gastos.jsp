<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<html lang="pt">
<%@include file="head.jsp" %>
<body class="starter-page-page">
<%@include file="header.jsp" %>
<main class="main">
    <div class="container">
        <h2 class="mb-4">Lista de Gastos</h2>

        <!-- Filtro de data -->
        <form action="listarGastos" method="get" class="mb-4">
            <div class="row">
                <div class="col-md-4">
                    <label for="dataInicio" class="form-label">Data Início</label>
                    <input type="date" class="form-control" id="dataInicio" name="dataInicio"
                           value="${param.dataInicio}">
                </div>
                <div class="col-md-4">
                    <label for="dataFim" class="form-label">Data Fim</label>
                    <input type="date" class="form-control" id="dataFim" name="dataFim" value="${param.dataFim}">
                </div>
                <div class="col-md-4">
                    <button type="submit" class="btn btn-primary mt-4">Filtrar</button>
                </div>
            </div>
        </form>

        <!-- Botão para abrir modal de cadastro -->
        <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#cadastroGastoModal"
                id="novoCadastroBtn">
            Novo Gasto
        </button>

        <table class="table table-striped mt-4">
            <thead>
            <tr>
                <th>ID</th>
                <th>Valor</th>
                <th>Data</th>
                <th>Tipo de Gasto</th> <!-- Alterado para Tipo de Gasto -->
                <th>Ações</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="gasto" items="${listaGastos}">
                <tr>
                    <td>${gasto.id}</td>
                    <td>${gasto.valor}</td>
                    <td>${gasto.data}</td>
                    <td>${gasto.tipoGasto.descricao}</td> <!-- Certifique-se de que este campo existe em Gastos -->
                    <td>
                        <!-- Botão de exclusão com link para um servlet de exclusão -->
                        <a href="deletarGastos?id=${gasto.id}" class="btn btn-danger btn-sm">Excluir</a>

                        <!-- Botão para abrir modal de edição -->
                        <button type="button" class="btn btn-warning btn-sm"
                                data-bs-toggle="modal" data-bs-target="#cadastroGastoModal"
                                data-id="${gasto.id}" data-valor="${gasto.valor}"
                                data-data="${gasto.data}" data-tipoGastoId="${gasto.tipoGasto.id}">
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
<div class="modal fade" id="cadastroGastoModal" tabindex="-1" aria-labelledby="cadastroGastoLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="cadastroGastoLabel">Cadastrar Gasto</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form id="gastoForm" action="cadastrarGastos" method="post">
                    <input type="hidden" id="gastoId" name="id">
                    <div class="mb-3">
                        <label for="valor" class="form-label">Valor</label>
                        <input type="number" step="0.01" class="form-control" id="valor" name="valor" required>
                    </div>
                    <div class="mb-3">
                        <label for="data" class="form-label">Data</label>
                        <input type="date" class="form-control" id="data" name="data" required>
                    </div>
                    <div class="mb-3">
                        <label for="tipoGasto" class="form-label">Tipo de Gasto</label>
                        <select id="tipoGasto" name="tipoGastoId" class="form-select" required>
                            <c:forEach var="tipoGasto" items="${listaTipoGastos}">
                                <option value="${tipoGasto.id}">${tipoGasto.descricao}</option>
                            </c:forEach>
                        </select>
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

<script src="resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
<script>
    const cadastroModal = document.getElementById('cadastroGastoModal');
    cadastroModal.addEventListener('show.bs.modal', function (event) {
        const button = event.relatedTarget;
        const modalTitle = cadastroModal.querySelector('.modal-title');
        const form = document.getElementById('gastoForm');
        const idInput = document.getElementById('gastoId');
        const valorInput = document.getElementById('valor');
        const dataInput = document.getElementById('data');
        const tipoGastoSelect = document.getElementById('tipoGasto');

        if (button.id === 'novoCadastroBtn') {
            modalTitle.textContent = 'Cadastrar Gasto';
            form.action = 'cadastrarGastos';
            idInput.value = '';
            valorInput.value = '';
            dataInput.value = '';
            tipoGastoSelect.value = '';
        } else {
            modalTitle.textContent = 'Editar Gasto';
            form.action = 'cadastrarGastos';
            idInput.value = button.getAttribute('data-id');
            valorInput.value = button.getAttribute('data-valor');
            dataInput.value = button.getAttribute('data-data');
            tipoGastoSelect.value = button.getAttribute('data-tipoGastoId');
        }
    });
</script>
</body>
</html>
