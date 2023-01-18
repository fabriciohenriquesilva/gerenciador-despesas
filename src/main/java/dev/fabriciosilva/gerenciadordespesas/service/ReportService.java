package dev.fabriciosilva.gerenciadordespesas.service;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.SQLException;

@Service
public class ReportService {

    @Autowired
    private DataSource dataSource;

    public byte[] gerarRelatorioEmPdf(String query, String nomeArquivo) throws JRException, SQLException {
//        JasperReport compiledReport = JasperCompileManager
//                .compileReport("src/main/resources/reports/pessoa/pessoas.jrxml");
//
//        JasperPrint printedReport = JasperFillManager.fillReport(compiledReport, null, dataSource.getConnection());
//
//        byte[] relatorio = JasperExportManager.exportReportToPdf(printedReport);
//
//        return relatorio;

        JRDesignQuery designQuery = new JRDesignQuery();
//        designQuery.setText("SELECT p.id, p.nome, p.tipo_pessoa, p.tipo_documento, p.codigo FROM pessoa p");
        designQuery.setText(query);

//        JasperDesign jasperDesign = JRXmlLoader.load("src/main/resources/reports/pessoa/pessoas.jrxml");
        JasperDesign jasperDesign = JRXmlLoader.load(nomeArquivo);
        jasperDesign.setQuery(designQuery);

        JasperReport compiledReport = JasperCompileManager.compileReport(jasperDesign);
        JasperPrint printedReport = JasperFillManager.fillReport(compiledReport, null, dataSource.getConnection());

        byte[] relatorio = JasperExportManager.exportReportToPdf(printedReport);

        return relatorio;
    }
}
