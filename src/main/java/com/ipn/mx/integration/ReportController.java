package com.ipn.mx.integration;

import com.ipn.mx.domain.entity.Categoria;
import com.ipn.mx.service.CategoriaService;
import com.ipn.mx.service.PdfReportService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.ByteArrayOutputStream;
import java.util.List;

@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/api")
public class ReportController {
	
	@Autowired
	CategoriaService categoriaService;
	
	@Autowired
    PdfReportService PdfReportService;

    public ReportController(PdfReportService PdfReportService) {
        this.PdfReportService = PdfReportService;
    }

    @GetMapping("/categorias/reporte")
    public ResponseEntity<byte[]> generarReportePDF() {
        List<Categoria> categorias = categoriaService.findAll();
        ByteArrayOutputStream byteArrayOutputStream = new PdfReportService().generarReportePDF(categorias);
        
        // Convertir ByteArrayOutputStream a array de bytes
        byte[] pdfBytes = byteArrayOutputStream.toByteArray();

        // Configurar la respuesta con el PDF generado
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("filename", "reporte.pdf");
        headers.setContentLength(pdfBytes.length);

        return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
    }

}
