package com.sid.controller;



	
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
/*import com.sid.dao.ImageRepository;
import com.sid.entities.ImagePdf;
import com.sid.entities.service.pdfService;*/

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
	import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

	@RestController
	public class pdfController {

		/*
	    @GetMapping("/pdf/generate")
	    @CrossOrigin("*") 
	    public void generatePDF(HttpServletResponse response ) throws IOException {
	        response.setContentType("application/pdf");
	        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss");
	        String currentDateTime = dateFormatter.format(new Date());

	        String headerKey = "Content-Disposition";
	        String headerValue = "attachment; filename= "+this.pdfService.getPath();
	        response.setHeader(headerKey, headerValue);
	      
	                
	        this.pdfService.export(response);
	              
	    }*/
	    
	    @CrossOrigin("*") 
	    @PostMapping(path="/uploadPhoto")
	    public ResponseEntity<List<String>> uploadFiles(@RequestParam("files")List<MultipartFile> multipartFiles, 
	    		HttpServletResponse response ) throws IOException {
	        List<String> filenames = new ArrayList<>();
	        for(MultipartFile file : multipartFiles) {
	            String filename = StringUtils.cleanPath(file.getOriginalFilename());
	       Path p = Files.write(Paths.get(System.getProperty("user.home")+"/images/"+filename+".jpg"),
	            		file.getBytes());
	      
	            filenames.add(filename);
	            
		        String path = "simple.pdf";
		        
		       // String  path =  "C:\\Users\\21658\\Desktop\\pdf\\simple.pdf";
		     
	            com.itextpdf.kernel.pdf.PdfWriter pdfWriter=new com.itextpdf.kernel.pdf.PdfWriter(path);
		        PdfDocument pdfDocument=new PdfDocument(pdfWriter);
		         pdfDocument.addNewPage();
		         Document document=new Document(pdfDocument);
		         
		        // String imagePath = "C:\\Users\\21658\\Desktop\\pdf\\logo.jpg";
		         ImageData imageData=ImageDataFactory.create(Files.readAllBytes(p));
		         Image image=new Image(imageData);
		         image.setRelativePosition(50, 50, 50, 50);
		         //image.setFixedPosition(pdfDocument.getDefaultPageSize().getWidth()/4,
		         		  //             pdfDocument.getDefaultPageSize().getHeight()/4);
		         
		         document.add(image);
		      
		         document.close();
	            
	        }     
	        return ResponseEntity.ok().body(filenames);
	    }
	
}
