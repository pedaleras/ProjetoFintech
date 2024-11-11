<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html lang="pt">
<%@ include file="head.jsp" %>
<body class="d-flex justify-content-center align-items-center vh-100 bg-julio-azul">
<div class="container">
    <div class="row justify-content-center">
        <div class="col-md-4">
            <div class="card">
                <div class="card-header text-center">
                    <img src="resources/img/logo.webp" alt="Logo" class="img-fluid mb-3" style="max-height: 80px;">
                    <h4>Login</h4>
                </div>
                <div class="card-body d-flex flex-column justify-content-center align-items-center"> <!-- Flex para centralizar -->
                    <form action="login" method="post" class="w-100">
                        <div class="form-group">
                            <label for="username" class="text-white">Usuário</label>
                            <input type="text" id="username" name="username" class="form-control"
                                   placeholder="Digite o usuário" required value="admin">
                        </div>
                        <div class="form-group">
                            <label for="password" class="text-white">Senha</label>
                            <input type="password" id="password" name="password" class="form-control"
                                   placeholder="Digite a senha" required value="123">
                        </div>
                        <div class="d-flex justify-content-center mt-3"> <!-- Centraliza o botão -->
                            <button type="submit" class="btn btn-primary">Entrar</button>
                        </div>
                    </form>
                    <div class="mt-3 text-danger">
                        ${mensagemErro != null ? mensagemErro : ""}
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- Bootstrap JS and dependencies (if needed) -->
<%--<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>--%>
<%--<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>--%>
<%--<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>--%>
</body>
</html>
