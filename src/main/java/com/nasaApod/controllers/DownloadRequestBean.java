package com.nasaApod.controllers;

import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.StringUtils;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.nasaApod.Constants;
import com.nasaApod.entities.Apod;

import lombok.Cleanup;

@ManagedBean
@RequestScoped
public class DownloadRequestBean {

	public DownloadRequestBean() {}

	/**
	 * Create the pdf document containing the selected date's apod
	 * @param document
	 * @param apod
	 * @throws DocumentException
	 * @throws MalformedURLException
	 * @throws IOException
	 */
	public void createAllQuotesPDF(Document document, Apod apod) throws DocumentException, MalformedURLException, IOException {
        Font headerFont = FontFactory.getFont(FontFactory.COURIER_BOLD, 15);
        Font cellFont = FontFactory.getFont(FontFactory.COURIER, 7);
        
		PdfPTable apodTable = new PdfPTable(new float[] { 30, 70 });
		apodTable.setWidthPercentage(90);

		//image cell
		Image img = Image.getInstance(new URL(apod.getUrl()));
		PdfPCell imgCell = new PdfPCell(img, true);
		imgCell.setColspan(2);
		apodTable.addCell(imgCell);
		//title cells
		apodTable.addCell(new PdfPCell(new Phrase(Constants.APOD_DOWNLOAD_CONTROLLER_PDF_CELL_TITLE, headerFont)));
		apodTable.addCell(new PdfPCell(new Phrase(apod.getTitle(), cellFont)));
		//copyright cells
		if(!StringUtils.isEmpty(apod.getCopyright())){
			apodTable.addCell(new PdfPCell(new Phrase(Constants.APOD_DOWNLOAD_CONTROLLER_PDF_CELL_COPYRIGHT, headerFont)));
			apodTable.addCell(new PdfPCell(new Phrase(apod.getCopyright(), cellFont)));
		}
		//explanation cells
		apodTable.addCell(new PdfPCell(new Phrase(Constants.APOD_DOWNLOAD_CONTROLLER_PDF_CELL_EXPLANATION, headerFont)));
		apodTable.addCell(new PdfPCell(new Phrase(apod.getExplanation(), cellFont)));

        document.add(apodTable);
	}
	
	/**
	 * Download a pdf which contains selected date's apod
	 * @param apod
	 * @throws IOException
	 */
	public void downloadApodPDF(Apod apod) throws IOException {
		String fileName = "Apod-"+apod.getDate()+".pdf";
		String contentType = "application/pdf";
		
		FacesContext fc = FacesContext.getCurrentInstance();
	    HttpServletResponse response = (HttpServletResponse) fc.getExternalContext().getResponse();

	    response.reset(); 
	    response.setContentType(contentType);
	    response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");

        try {
        	@Cleanup OutputStream output = response.getOutputStream();
    	    @Cleanup Document document = new Document(PageSize.A4);
            PdfWriter.getInstance(document, output);
            
            document.open();
        	
			createAllQuotesPDF(document, apod);
		} catch (DocumentException | IOException e) {
			e.printStackTrace();
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
		}

        fc.responseComplete();
	}
}
