package br.com.fiap.julio.controller.Home;

import br.com.fiap.julio.service.DashboardService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private DashboardService dashboardService;

    public HomeServlet() {
        this.dashboardService = new DashboardService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Obter dados dos serviços
            Map<String, List<Double>> recebimentosMensais = dashboardService.obterRecebimentosMensais();
            Map<String, List<Double>> gastosMensais = dashboardService.obterGastosMensais();
            Map<String, Double> distribuicaoGastos = dashboardService.obterDistribuicaoGastos();

            Gson gson = new GsonBuilder().create();

            // Converte mapas em strings JSON
            String recebimentosJson = gson.toJson(recebimentosMensais);
            String gastosJson = gson.toJson(gastosMensais);
            String distribuicaoGastosJson = gson.toJson(distribuicaoGastos);

            // Define os atributos como JSON strings
            request.setAttribute("recebimentosJson", recebimentosJson);
            request.setAttribute("gastosJson", gastosJson);
            request.setAttribute("distribuicaoGastosJson", distribuicaoGastosJson);

            // Encaminhar para o JSP
            request.getRequestDispatcher("index.jsp").forward(request, response);
        } catch (Exception e) {
            throw new ServletException("Erro ao carregar os dados do dashboard", e);
        }
    }
}
