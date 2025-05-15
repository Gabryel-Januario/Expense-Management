package com.ExpenseManagement.Expense.Management.services;

import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import com.ExpenseManagement.Expense.Management.models.Expense;
import com.ExpenseManagement.Expense.Management.utils.GetExpenses;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

@Service
public class StatisticsAndDashboardService {
    
    @Autowired
    private GetExpenses getExpenses;

    public BigDecimal totalExpenseCurrentMonth(Authentication authentication) {
    
        List<Expense> expensesForThisMonth = this.getExpenses.getCurrentMonthExpense(authentication);

        BigDecimal totalExpense = BigDecimal.ZERO;

        for (Expense expense : expensesForThisMonth) {
           totalExpense = totalExpense.add(expense.getAmount());
        }

        return totalExpense;
    }

    
    public byte[] dashboard(Authentication authentication) {
        List<Expense> expensesForThisMonth = this.getExpenses.getCurrentMonthExpense(authentication);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Document document = new Document();
        PdfWriter.getInstance(document, out);
        document.open();

        Font fontTitle = new Font(Font.HELVETICA, 18, Font.BOLD);
        Paragraph title = new Paragraph("Despesa Atual", fontTitle);
        title.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(title);

        document.add(Chunk.NEWLINE);

        PdfPTable table = new PdfPTable(4);
        table.setWidths(new float[] {4,2,2,3});

        Font fontHeader = new Font(Font.ITALIC, 14, Font.BOLD);

        table.addCell(createStyledHeaderCell("Descrição", fontHeader, Color.LIGHT_GRAY));
        table.addCell(createStyledHeaderCell("Valor", fontHeader, Color.LIGHT_GRAY));
        table.addCell(createStyledHeaderCell("Data", fontHeader, Color.LIGHT_GRAY));
        table.addCell(createStyledHeaderCell("Categoria", fontHeader, Color.LIGHT_GRAY));

        for( Expense expense : expensesForThisMonth) {
            table.addCell(createBodyCell(expense.getDescription()));
            table.addCell(createBodyCell("R$" + expense.getAmount().toString()));
            table.addCell(createBodyCell(expense.getDate().toString()));
            table.addCell(createBodyCell(expense.getCategory().toString()));
        }


        PdfPCell totalLabelCell = new PdfPCell(new Phrase("Total", fontHeader));
        totalLabelCell.setColspan(1); 
        totalLabelCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        totalLabelCell.setBackgroundColor(Color.LIGHT_GRAY);
        totalLabelCell.setPadding(5);
        table.addCell(totalLabelCell);
        
        
        PdfPCell totalValueCell = new PdfPCell(new Phrase("R$00000,00", fontHeader));
        totalValueCell.setColspan(3); 
        totalValueCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        totalLabelCell.setBackgroundColor(Color.LIGHT_GRAY);
        totalValueCell.setPadding(5);
        table.addCell(totalValueCell);
        
        document.add(table);
        document.close();

        return out.toByteArray();
    }

    private PdfPCell createStyledHeaderCell(String text, Font font, Color background ) {
        PdfPCell cell = new PdfPCell(new Phrase(text, font));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setBackgroundColor(background);
        cell.setPadding(5);
        return cell;
    }

    private PdfPCell createBodyCell(String text) {
        PdfPCell cell = new PdfPCell(new Phrase(text));
        cell.setPadding(5);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        return cell;
    }

}
