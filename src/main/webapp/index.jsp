<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<%@include file="head.jsp" %>
<body class="starter-page-page">
<%@include file="header.jsp" %>
<main class="main">
    <!-- Dashboard Section -->
    <section id="dashboard-section" class="dashboard-section section">
        <div class="container" data-aos="fade-up">
            <h2>Dashboard Financeiro</h2>
            <div class="row">
                <div class="col-md-6">
                    <h4>Recebimentos vs Gastos</h4>
                    <canvas id="recebimentosGastosChart"></canvas>
                </div>
                <div class="col-md-6">
                    <h4>Distribuição de Gastos</h4>
                    <canvas id="distribuicaoGastosChart"></canvas>
                </div>
            </div>
        </div>
    </section><!-- /Dashboard Section -->

</main>

<!-- Scroll Top -->
<a href="#" id="scroll-top" class="scroll-top d-flex align-items-center justify-content-center"><i class="bi bi-arrow-up-short"></i></a>

<!-- Vendor JS Files -->
<script src="resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
<script src="resources/vendor/php-email-form/validate.js"></script>
<script src="resources/vendor/aos/aos.js"></script>
<script src="resources/vendor/glightbox/js/glightbox.min.js"></script>
<script src="resources/vendor/swiper/swiper-bundle.min.js"></script>
<script src="resources/vendor/waypoints/noframework.waypoints.js"></script>
<script src="resources/vendor/imagesloaded/imagesloaded.pkgd.min.js"></script>
<script src="resources/vendor/isotope-layout/isotope.pkgd.min.js"></script>

<!-- Chart.js -->
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>

<!-- Main JS File -->
<script src="resources/js/main.js"></script>

<!-- Chart Configuration -->
<script>
    // Dados dinâmicos passados do servlet para o JSP (convertendo de JSON string para objetos JS)
    const recebimentosMensais = JSON.parse('${recebimentosJson}');
    const gastosMensais = JSON.parse('${gastosJson}');
    const distribuicaoGastos = JSON.parse('${distribuicaoGastosJson}');

    // Extrair os dados necessários para os gráficos a partir dos objetos
    const meses = Object.keys(recebimentosMensais); // Extrai as chaves (meses)
    const recebimentosData = Object.values(recebimentosMensais).flat(); // Extrai e "achata" os valores
    const gastosData = Object.values(gastosMensais).flat(); // Extrai e "achata" os valores

    // Recebimentos vs Gastos Chart
    const ctx1 = document.getElementById('recebimentosGastosChart').getContext('2d');
    new Chart(ctx1, {
        type: 'bar',
        data: {
            labels: meses,
            datasets: [
                {
                    label: 'Recebimentos',
                    data: recebimentosData,
                    backgroundColor: 'rgba(75, 192, 192, 0.5)',
                    borderColor: 'rgba(75, 192, 192, 1)',
                    borderWidth: 1
                },
                {
                    label: 'Gastos',
                    data: gastosData,
                    backgroundColor: 'rgba(255, 99, 132, 0.5)',
                    borderColor: 'rgba(255, 99, 132, 1)',
                    borderWidth: 1
                }
            ]
        },
        options: {
            responsive: true,
            scales: {
                y: {
                    beginAtZero: true
                }
            }
        }
    });

    // Preparar dados para o gráfico de Distribuição de Gastos
    const tiposDeGastos = Object.keys(distribuicaoGastos); // Extrai os tipos (chaves)
    const distribuicaoGastosData = Object.values(distribuicaoGastos); // Extrai os valores

    // Distribuição de Gastos Chart
    const ctx2 = document.getElementById('distribuicaoGastosChart').getContext('2d');
    new Chart(ctx2, {
        type: 'pie',
        data: {
            labels: tiposDeGastos,
            datasets: [{
                label: 'Distribuição de Gastos',
                data: distribuicaoGastosData,
                backgroundColor: [
                    'rgba(255, 99, 132, 0.5)',
                    'rgba(54, 162, 235, 0.5)',
                    'rgba(255, 206, 86, 0.5)',
                    'rgba(75, 192, 192, 0.5)',
                    'rgba(153, 102, 255, 0.5)'
                ]
            }]
        },
        options: {
            responsive: true
        }
    });
</script>

</body>
</html>
