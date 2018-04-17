package com.nasaApod.controllers;

import java.io.IOException;
import java.io.OutputStream;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
//import com.nasaApod.enums.QuoteEnum;

import lombok.Cleanup;

@ManagedBean
public class DownloadController {

	public DownloadController() {}

	/**
	 * Create the pdf document containing all quotes
	 * @param document
	 * @throws DocumentException
	 */
	public void createAllQuotesPDF(Document document) throws DocumentException {
        Font headerFont = FontFactory.getFont(FontFactory.COURIER_BOLD, 25);
        Font cellFont = FontFactory.getFont(FontFactory.COURIER, 15);
        
		PdfPTable quotesTable = new PdfPTable(new float[] { 15, 60, 25 });
		quotesTable.setWidthPercentage(90);
		quotesTable.setHeaderRows(1);

        quotesTable.addCell(new PdfPCell(new Phrase("Who", headerFont)));
        quotesTable.addCell(new PdfPCell(new Phrase("What", headerFont)));
        quotesTable.addCell(new PdfPCell(new Phrase("When", headerFont)));

//        for(QuoteEnum qe : QuoteEnum.values()) {
//	        quotesTable.addCell(new PdfPCell(new Phrase(qe.getUser(), cellFont)));
//	        quotesTable.addCell(new PdfPCell(new Phrase(qe.getText(), cellFont)));
//	        quotesTable.addCell(new PdfPCell(new Phrase(qe.getDate(), cellFont)));
//        }
		
        document.add(quotesTable);
	}
	
	/**
	 * Download a pdf which contains all quotes
	 * @throws IOException
	 */
	public void downloadAllQuotesPDF() throws IOException {
		String fileName = "allEDQuotes.pdf";
		String contentType = "application/pdf";
		
		FacesContext fc = FacesContext.getCurrentInstance();
	    HttpServletResponse response = (HttpServletResponse) fc.getExternalContext().getResponse();

	    response.reset(); 
	    response.setContentType(contentType);
	    response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");

        try {
        	@Cleanup OutputStream output = response.getOutputStream();
    	    @Cleanup Document document = new Document(PageSize.A4.rotate());
            PdfWriter.getInstance(document, output);
            
            document.open();
        	
			createAllQuotesPDF(document);
		} catch (DocumentException | IOException e) {
			e.printStackTrace();
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
		}

        fc.responseComplete();
	}
}
