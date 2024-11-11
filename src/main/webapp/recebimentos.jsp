<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<html lang="pt">
<%@include file="head.jsp" %>
<body class="starter-page-page">
<%@include file="header.jsp" %>
<main class="main">
  <div class="container">
  <h2 class="mb-4">Lista de Recebimentos</h2>

  <!-- Filtro de data -->
  <form action="listarRecebimentos" method="get" class="mb-4">
    <div class="row">
      <div class="col-md-4">
        <label for="dataInicio" class="form-label">Data Início</label>
        <input type="date" class="form-control" id="dataInicio" name="dataInicio" value="${param.dataInicio}">
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
  <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#cadastroRecebimentoModal" id="novoCadastroBtn">
    Novo
  </button>

  <table class="table table-striped mt-4">
    <thead>
    <tr>
      <th>ID</th>
      <th>Valor</th>
      <th>Data</th>
      <th>Tipo de Renda</th>
      <th>Ações</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="recebimento" items="${listaRecebimentos}">
      <tr>
        <td>${recebimento.id}</td>
        <td>${recebimento.valor}</td>
        <td>${recebimento.data}</td>
        <td>${recebimento.tipoRenda.descricao}</td>
        <td>
          <!-- Botão de exclusão com link para um servlet de exclusão -->
          <a href="deletarRecebimento?id=${recebimento.id}" class="btn btn-danger btn-sm">Excluir</a>

          <!-- Botão para abrir modal de edição -->
          <button type="button" class="btn btn-warning btn-sm"
                  data-bs-toggle="modal" data-bs-target="#cadastroRecebimentoModal"
                  data-id="${recebimento.id}" data-valor="${recebimento.valor}"
                  data-data="${recebimento.data}" data-tipoRendaId="${recebimento.tipoRenda.id}">
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
<div class="modal fade" id="cadastroRecebimentoModal" tabindex="-1" aria-labelledby="cadastroRecebimentoLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="cadastroRecebimentoLabel">Cadastrar Recebimento</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <form id="recebimentoForm" action="cadastrarRecebimento" method="post">
          <input type="hidden" id="recebimentoId" name="id">
          <div class="mb-3">
            <label for="valor" class="form-label">Valor</label>
            <input type="number" step="0.01" class="form-control" id="valor" name="valor" required>
          </div>
          <div class="mb-3">
            <label for="data" class="form-label">Data</label>
            <input type="date" class="form-control" id="data" name="data" required>
          </div>
          <div class="mb-3">
            <label for="tipoRenda" class="form-label">Tipo de Renda</label>
            <select id="tipoRenda" name="tipoRendaId" class="form-select" required>
              <c:forEach var="tipoRenda" items="${listaTipoRenda}">
                <option value="${tipoRenda.id}">${tipoRenda.descricao}</option>
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
  const cadastroModal = document.getElementById('cadastroRecebimentoModal');
  cadastroModal.addEventListener('show.bs.modal', function (event) {
    const button = event.relatedTarget;
    const modalTitle = cadastroModal.querySelector('.modal-title');
    const form = document.getElementById('recebimentoForm');
    const idInput = document.getElementById('recebimentoId');
    const valorInput = document.getElementById('valor');
    const dataInput = document.getElementById('data');
    const tipoRendaSelect = document.getElementById('tipoRenda');

    if (button.id === 'novoCadastroBtn') {
      // Configura modal para novo cadastro
      modalTitle.textContent = 'Cadastrar Recebimento';
      form.action = 'cadastrarRecebimento';
      idInput.value = '';
      valorInput.value = '';
      dataInput.value = '';
      tipoRendaSelect.value = ''; // Resetar seleção
    } else {
      // Configura modal para edição
      modalTitle.textContent = 'Editar Recebimento';
      form.action = 'cadastrarRecebimento';
      idInput.value = button.getAttribute('data-id');
      valorInput.value = button.getAttribute('data-valor');
      dataInput.value = button.getAttribute('data-data');
      tipoRendaSelect.value = button.getAttribute('data-tipoRendaId'); // Define a seleção correta
    }
  });
</script>
</body>
</html>
