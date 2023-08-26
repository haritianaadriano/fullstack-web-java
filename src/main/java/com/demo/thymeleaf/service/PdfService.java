package com.demo.thymeleaf.service;

import com.lowagie.text.DocumentException;
import lombok.AllArgsConstructor;
import org.aspectj.apache.bcel.util.ClassPath;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

@Service
@AllArgsConstructor
public class PdfService {
    private final String PDF_RESOURCE = "classpath:/templates/pdf/";
    private EmployeeService employeeService;

    public File generatedPdf(int idEmployee) throws IOException, DocumentException {
        Context context = getContext(idEmployee);
        String html = loadAndFillTemplate(context);
        return renderPdf(html);
    }

    private File renderPdf(String html) throws IOException, DocumentException {
        File templateFile = File.createTempFile("employee", "pdf");
        OutputStream outputStream = new FileOutputStream(templateFile);
        ITextRenderer renderer = new ITextRenderer(20f * 4f / 3f, 20);
        renderer.setDocumentFromString(html, new ClassPathResource(PDF_RESOURCE).getURL().toExternalForm());
        renderer.layout();
        renderer.createPDF(outputStream);
        outputStream.close();
        templateFile.deleteOnExit();
        return templateFile;
    }

    private String loadAndFillTemplate(Context context) {
        TemplateEngine templateEngine = new TemplateEngine();
        ClassLoaderTemplateResolver resolver = new ClassLoaderTemplateResolver();
        resolver.setSuffix(".html");
        resolver.setTemplateMode(TemplateMode.HTML);
        templateEngine.setTemplateResolver(resolver);
        return templateEngine.process("employee_pdf", context);
    }

    private Context getContext(int idEmployee) {
        Context context = new Context();
        context.setVariable("one_employee", employeeService.getOneEmployee(idEmployee));
        return context;
    }
}
