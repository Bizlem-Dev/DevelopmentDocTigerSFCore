//package com.doc.services;
//
//import org.apache.poi.xwpf.usermodel.XWPFDocument;
//import org.apache.xmlbeans.XmlException;
//import org.apache.xmlbeans.XmlToken;
//import org.docx4j.dml.wordprocessingDrawing.Inline;
//import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
//import org.docx4j.openpackaging.parts.WordprocessingML.BinaryPartAbstractImage;
//import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
//import org.docx4j.wml.R;
//import org.openxmlformats.schemas.drawingml.x2006.main.CTNonVisualDrawingProps;
//import org.openxmlformats.schemas.drawingml.x2006.main.CTPositiveSize2D;
//import org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTInline;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.List;
//
//import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
//import org.apache.poi.xwpf.usermodel.Document;
//import org.apache.poi.xwpf.usermodel.XWPFDocument;
//
//
//
//public class CustomXWPFDocument extends XWPFDocument
//{
//    public CustomXWPFDocument(FileInputStream fileInputStream) throws IOException
//    {
//        super(fileInputStream);
//    }
//	static String outputDocxPath="D:\\DOCTIGER114IPProject\\testing docx\\imgdoc.docx";
//	//Documentimg1
//	static String igp="D:\\DOCTIGER114IPProject\\testing docx\\img1.jpg";
//    public void createPicture(String blipId,int id, int width, int height) throws Exception
//    {
//    	GenerationTest gt=new GenerationTest();
//        final int EMU = 9525;
//        width *= EMU;
//        height *= EMU;
//        //String blipId = getAllPictures().get(id).getPackageRelationship().getId();
//// new draw code
//    	File fileLogo = new File("D:\\DOCTIGER114IPProject\\testing docx\\img1.jpg");
//		WordprocessingMLPackage wordMLPackage =gt.readDocxFile(outputDocxPath);
//		MainDocumentPart mp = wordMLPackage.getMainDocumentPart();
//        List<Object> list = gt.getAllElementFromObject(mp, R.class);
//        org.docx4j.wml.ObjectFactory factory = new org.docx4j.wml.ObjectFactory();
//    	org.docx4j.wml.P para = factory.createP();
//		org.docx4j.wml.Drawing draw = factory.createDrawing();
//		((R)list.get(1)).getContent().clear();
//		((R)list.get(1)).getContent().add(draw);
//		System.out.println("((P)list.get(1)).getContent() = "+((R)list.get(1)).getContent());
//		para.getContent().add(((R)list.get(1)));
////		list.get(i).getContent().clear();
////		list.get(i).getContent().add(draw);
//		draw.getAnchorOrInline().add(GenerationTest.createInline(fileLogo, outputDocxPath,igp));
//        
////		CTInline inline1=para.getContent().add(((R)list.get(1)));
//        //end
//
//		  File file = new File("src/main/resources/PictureNew.png");
//          byte[] bytes = gt.convertImageToByteArray(file);   
//          BinaryPartAbstractImage imagePart = BinaryPartAbstractImage
//                  .createImagePart(wordMLPackage, bytes);
//          int docPrId = 1;
//          int cNvPrId = 2;
//
//          Inline inline1 = imagePart.createImageInline("Filename hint",
//                  "Alternative text", docPrId, cNvPrId, false);   
//          para.getContent().add(((R)list.get(1)));
////          CTInline inline =  para.getParagraphContent().;
//		CTInline inline =  createParagraph().createRun().getCTR().addNewDrawing().addNewInline();
//        String picXml = "" +
//                "<a:graphic xmlns:a=\"http://schemas.openxmlformats.org/drawingml/2006/main\">" +
//                "   <a:graphicData uri=\"http://schemas.openxmlformats.org/drawingml/2006/picture\">" +
//                "      <pic:pic xmlns:pic=\"http://schemas.openxmlformats.org/drawingml/2006/picture\">" +
//                "         <pic:nvPicPr>" +
//                "            <pic:cNvPr id=\"" + id + "\" name=\"Generated\"/>" +
//                "            <pic:cNvPicPr/>" +
//                "         </pic:nvPicPr>" +
//                "         <pic:blipFill>" +
//                "            <a:blip r:embed=\"" + blipId + "\" xmlns:r=\"http://schemas.openxmlformats.org/officeDocument/2006/relationships\"/>" +
//                "            <a:stretch>" +
//                "               <a:fillRect/>" +
//                "            </a:stretch>" +
//                "         </pic:blipFill>" +
//                "         <pic:spPr>" +
//                "            <a:xfrm>" +
//                "               <a:off x=\"0\" y=\"0\"/>" +
//                "               <a:ext cx=\"" + width + "\" cy=\"" + height + "\"/>" +
//                "            </a:xfrm>" +
//                "            <a:prstGeom prst=\"rect\">" +
//                "               <a:avLst/>" +
//                "            </a:prstGeom>" +
//                "         </pic:spPr>" +
//                "      </pic:pic>" +
//                "   </a:graphicData>" +
//                "</a:graphic>";
//
//        //CTGraphicalObjectData graphicData = inline.addNewGraphic().addNewGraphicData();
//        XmlToken xmlToken = null;
//        try
//        {
//            xmlToken = XmlToken.Factory.parse(picXml);
//        }
//        catch(XmlException xe)
//        {
//            xe.printStackTrace();
//        }
//       
//        inline.set(xmlToken);
//        //graphicData.set(xmlToken);
//
//        inline.setDistT(2);
//        inline.setDistB(0);
//        inline.setDistL(0);
//        inline.setDistR(0);
//
//        CTPositiveSize2D extent = inline.addNewExtent();
//        extent.setCx(width);
//        extent.setCy(height);
//
//        CTNonVisualDrawingProps docPr = inline.addNewDocPr();
//        docPr.setId(id);
//        docPr.setName("Picture " + id);
//        docPr.setDescr("Generated");
//    }
//    public static void main(String args[]) throws Exception {
//    
//    	CustomXWPFDocument document = new CustomXWPFDocument(new FileInputStream(new File(outputDocxPath)));
//        FileOutputStream fos = new FileOutputStream(new File("D:\\DOCTIGER114IPProject\\testing docx\\Dcdoc2.docx"));
//        String id = document.addPictureData(new FileInputStream(new File(igp)),Document.PICTURE_TYPE_JPEG);
////        document.createPicture(id,document.getNextPicNameNumber(Document.PICTURE_TYPE_JPEG), 100, 64);
//        document.createPicture(id,document.getNextPicNameNumber(Document.PICTURE_TYPE_JPEG), 100, 64);
//        document.write(fos);
//        fos.flush();
//        fos.close();
//        System.out.println("done");
//    	
//    }
//}
