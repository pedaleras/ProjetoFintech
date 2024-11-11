<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<header id="header" class="header d-flex align-items-center fixed-top">
    <div class="container-fluid container-xl position-relative d-flex align-items-center">

        <a href="home" class="logo d-flex align-items-center me-auto">
            <img src="resources/img/logo.webp" alt="">
            <h1 class="sitename">J.U.L.I.O.</h1>
        </a>

        <nav id="navmenu" class="navmenu">
            <ul>
                <li><a href="home">Home</a></li>
                <!-- Adicionar futuramente -->
                <%--          <li><a href="#servicos">Serviços</a></li>--%>
                <%--          <li><a href="#services">Services</a></li>--%>
                <%--          <li><a href="#portfolio">Portfolio</a></li>--%>
                <%--          <li><a href="#team">Team</a></li>--%>
                <%--          <li><a href="#pricing">Pricing</a></li>--%>
                <li class="dropdown">
                    <a href="#">
                        <span>Cadastros</span>
                        <i class="bi bi-chevron-down toggle-dropdown"></i>
                    </a>
                    <ul>
                        <li><a href="listarGastos">Gastos</a></li>
                        <li><a href="listarRecebimentos">Recebimentos</a></li>
                        <li><a href="listarTipoGasto">Tipo de Gasto</a></li>
                        <li><a href="listarTipoRenda">Tipo de Renda</a></li>
                    </ul>
                </li>
            </ul>
            <i class="mobile-nav-toggle d-xl-none bi bi-list"></i>
        </nav>
        <!-- <a class="btn-getstarted" href="#about">Get Started</a> -->
    </div>
</header>