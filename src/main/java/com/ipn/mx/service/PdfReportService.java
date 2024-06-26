package com.ipn.mx.service;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.ipn.mx.domain.entity.Categoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.List;

@Service
public class PdfReportService {

    @Autowired
    private CategoriaService categoriaService;

    public ByteArrayOutputStream generarReportePDF(List<Categoria> categorias) {
        Document document = new Document();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        try {
            PdfWriter.getInstance(document, outputStream);
            document.open();

            Font fontTitle = FontFactory.getFont(FontFactory.COURIER, 14, BaseColor.BLUE);
            Paragraph title = new Paragraph("Lista de Categorías", fontTitle);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);
            document.add(Chunk.NEWLINE);

            Font fontHeader = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 9);
            PdfPTable table = new PdfPTable(3);

            addHeaderCell(table, "ID clave de la categoria", fontHeader);
            addHeaderCell(table, "Nombre de la categoria", fontHeader);
            addHeaderCell(table, "Descripción de la categoria", fontHeader);

            for (Categoria categoria : categorias) {
                addCell(table, String.valueOf(categoria.getIdcategoria()));
                addCell(table, categoria.getNombrecategoria());
                addCell(table, categoria.getDescripcioncategoria());
            }

            document.add(table);
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return outputStream;
    }

    private void addHeaderCell(PdfPTable table, String text, Font font) {
        PdfPCell header = new PdfPCell();
        header.setBackgroundColor(BaseColor.LIGHT_GRAY);
        header.setHorizontalAlignment(Element.ALIGN_CENTER);
        header.setBorderWidth(1);
        header.setPhrase(new Phrase(text, font));
        table.addCell(header);
    }

    private void addCell(PdfPTable table, String text) {
        PdfPCell cell = new PdfPCell(new Phrase(text));
        cell.setPadding(1);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);
    }
}
