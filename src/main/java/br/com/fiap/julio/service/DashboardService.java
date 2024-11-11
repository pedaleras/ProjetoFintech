package br.com.fiap.julio.service;

import br.com.fiap.julio.dao.RecebimentosDao;
import br.com.fiap.julio.dao.GastosDao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class DashboardService {
    public DashboardService() {

    }

    public Map<String, List<Double>> obterRecebimentosMensais() throws SQLException {
        RecebimentosDao recebimentosDao = new RecebimentosDao();
        // Método que retorna um mapa com os meses e valores dos recebimentos
        return recebimentosDao.obterRecebimentosPorMes();
    }

    public Map<String, List<Double>> obterGastosMensais() throws SQLException {
        GastosDao gastosDao = new GastosDao();
        // Método que retorna um mapa com os meses e valores dos gastos
        return gastosDao.obterGastosPorMes();
    }

    public Map<String, Double> obterDistribuicaoGastos() throws SQLException {
        GastosDao gastosDao = new GastosDao();
        // Método que retorna a distribuição de tipos de gastos
        return gastosDao.obterDistribuicaoPorTipo();
    }
}
