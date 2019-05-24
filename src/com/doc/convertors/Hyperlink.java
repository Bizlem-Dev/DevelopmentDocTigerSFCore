package com.doc.convertors;

import org.docx4j.XmlUtils;
import org.docx4j.jaxb.Context;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.exceptions.InvalidFormatException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
import org.docx4j.openpackaging.parts.relationships.Namespaces;

public class Hyperlink {

	public static void main(String[] args) throws Docx4JException {
		WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage.createPackage();
	    MainDocumentPart mdp = wordMLPackage.getMainDocumentPart();
	 
	    // Create hyperlink
	    Hyperlink link = createHyperlink(mdp, "https://docs.aspose.com");
	 
	    // Add it to a paragraph
	    org.docx4j.wml.P paragraph = Context.getWmlObjectFactory().createP();
	    paragraph.getContent().add( link );
	    mdp.addObject(paragraph);
	    String dataDir="D:\\DOCTIGER114IPProject\\testing docx\\abhishek\\";
	    // Now save it
	    wordMLPackage.save(new java.io.File(dataDir + "OUT_HyperlinkTest.docx") );
	 System.out.println("done");
		
	}
	public static Hyperlink createHyperlink(MainDocumentPart mdp, String url) {
		 
	    try {
	 
	        // We need to add a relationship to word/_rels/document.xml.rels
	        // but since its external, we don't use the
	        // usual wordMLPackage.getMainDocumentPart().addTargetPart
	        // mechanism
	        org.docx4j.relationships.ObjectFactory factory =
	            new org.docx4j.relationships.ObjectFactory();
	 
	        org.docx4j.relationships.Relationship rel = factory.createRelationship();
	        rel.setType( Namespaces.HYPERLINK  );
	        rel.setTarget(url);
	        rel.setTargetMode("External");
	 
	        mdp.getRelationshipsPart().addRelationship(rel);
	 
	        // addRelationship sets the rel's @Id
	 
	        String hpl = "<w:hyperlink r:id=\"" + rel.getId() + "\" xmlns:w=\"http://schemas.openxmlformats.org/wordprocessingml/2006/main\" " +
	        "xmlns:r=\"http://schemas.openxmlformats.org/officeDocument/2006/relationships\" >" +
	        "<w:r>" +
	        "<w:rPr>" +
	        "<w:rStyle w:val=\"Hyperlink\" />" +  // TODO: enable this style in the document!
	        "</w:rPr>" +
	        "<w:t>Link</w:t>" +
	        "</w:r>" +
	        "</w:hyperlink>";
	 
//	          return (Hyperlink)XmlUtils.unmarshalString(hpl, Context.jc, P.Hyperlink.class);
	        return (Hyperlink)XmlUtils.unmarshalString(hpl);
	 
	    } catch (Exception e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	        return null;
	    }
	}
}
