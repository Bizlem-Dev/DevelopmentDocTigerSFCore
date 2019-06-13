package com.doc.services;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
//import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.xml.bind.JAXBElement;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.docx4j.Docx4jProperties;
import org.docx4j.dml.wordprocessingDrawing.Inline;
import org.docx4j.jaxb.Context;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.BinaryPartAbstractImage;
import org.docx4j.utils.Log4jConfigurator;
import org.docx4j.utils.SingleTraversalUtilVisitorCallback;
import org.docx4j.wml.Body;
import org.docx4j.wml.ContentAccessor;
import org.docx4j.wml.Drawing;
import org.docx4j.wml.ObjectFactory;
import org.docx4j.wml.P;
import org.docx4j.wml.R;
import org.docx4j.wml.Tbl;
import org.docx4j.wml.Tc;
import org.docx4j.wml.Text;
import org.docx4j.wml.Tr;

import com.doc.convertors.DocxToPdfConvertor;

public class ReplaceTextWithImage {

	
	final static Logger log = Logger.getLogger(ReplaceTextWithImage.class);
	public static void main(String[] args) throws Exception {
//OperationtestTemplate.docx"; 
		String outputDocxPath = "D:\\DOCTIGER114IPProject\\testing docx\\imagetest\\Doc1.docx"	;//DocImgLink.docx";
		String igp = "D:\\DOCTIGER114IPProject\\testing docx\\down1.png";
		WordprocessingMLPackage wordMLPackage = null;
		wordMLPackage = getTemplate(outputDocxPath);
		org.docx4j.wml.ObjectFactory factory = new org.docx4j.wml.ObjectFactory();
		factory = Context.getWmlObjectFactory();
		String saveimgpath="D:\\DOCTIGER114IPProject\\testing docx\\";
//		URL url = new URL("https://www.google.com/url?sa=i&source=images&cd=&ved=2ahUKEwiYrPzaouHiAhUGuI8KHbnADTsQjRx6BAgBEAU&url=https%3A%2F%2Funsplash.com%2Fsearch%2Fphotos%2Fnew&psig=AOvVaw22J3pHlvOzE8CzG0lHT0iy&ust=1560337074066894");
		try {
			BufferedImage image = null;
			// https://upload.wikimedia.org/wikipedia/commons/5/58/Sunset_2007-1.jpg
			//https://upload.wikimedia.org/wikipedia/commons/5/58/Sunset_2007-1.jpg
//			https://en.wikipedia.org/wiki/Sunset#/media/File:Sunset_2007-1.jpg
		
//			String imageUrl="https://en.wikipedia.org/wiki/Sunset#/media/File:Sunset_2007-1.jpg";
			String imageUrl="http://34.74.13.213:8082/centerimage.png";
			//https://unsplash.com/photos/kEgJVDkQkbU
			int o= imageUrl.lastIndexOf("/");
			String imgname = imageUrl.substring(o+1,imageUrl.length());
			System.out.println("imgname=== "+imgname);
//			saveImage(imageUrl, saveimgpath);
			
			
			// read the url
//           image = ImageIO.read(url);
//// 
//////            for png
//            ImageIO.write(image, "png",new File("D:\\DOCTIGER114IPProject\\testing docx\\imgtion.png"));
//// 
////            // for jpg
//            ImageIO.write(image, "jpg",new File("D:\\DOCTIGER114IPProject\\testing docx\\imgtion.jpg"));
// 
//			URL url = new URL("https://cdn.pixabay.com/photo/2017/08/24/03/41/milky-way-2675322_960_720.jpg");
		
		} catch (Exception e) {
			e.printStackTrace();
		}

		Map<String, Object> replimg = new HashMap<String, Object>();
//		if() {}
//		"<<Imglink>>":"http://34.74.13.213:8082/centerimage
		replimg.put("<<Imglink>>", "http://34.74.13.213:8082/centerimage.png");
		replimg.put("fds", "http://34.74.13.213:8082/centerimage.png");
//		replimg.put("Hello", igp);
		System.out.println("maplist = " + replimg);
		for (Map.Entry<String, Object> entry : replimg.entrySet()) {
//			System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
		}
		ReplaceLinkWithImage(replimg, saveimgpath, wordMLPackage);
	/*	List elemetns = getAllElementFromObject(wordMLPackage.getMainDocumentPart(), Tbl.class);

		for (Object obj : elemetns) {
			if (obj instanceof Tbl) {
				Tbl table = (Tbl) obj;
				List rows = getAllElementFromObject(table, Tr.class);
				for (Object trObj : rows) {
					Tr tr = (Tr) trObj;
					List cols = getAllElementFromObject(tr, Tc.class);
					for (Object tcObj : cols) {
						Tc tc = (Tc) tcObj;
						List texts = getAllElementFromObject(tc, Text.class);
						for (Object textObj : texts) {
							Text text = (Text) textObj;
							for (Map.Entry<String, Object> entry : replimg.entrySet()) {
								if (text.getValue().equalsIgnoreCase(entry.getKey().trim())) { // "${MY_PLACE_HOLDER}"
									File file = new File(entry.getValue().toString().trim());
									P paragraphWithImage = addInlineImageToParagraph(createInlineImage(file));
									tc.getContent().remove(0);

									tc.getContent().add(paragraphWithImage);

								}
							}
						}
						System.out.println("here");
					}
				}
				System.out.println("here");
			}
		}
		*/

		wordMLPackage.save(new java.io.File("D:\\DOCTIGER114IPProject\\testing docx\\resultrplc.docx"));

	}

	private static P addInlineImageToParagraph(Inline inline) {
		// Now add the in-line image to a paragraph
		P paragraph=null;
		try {
		log.info("start addInlineImageToParagraph ");
		ObjectFactory factory = new ObjectFactory();
		 paragraph = factory.createP();
		R run = factory.createR();
		log.info(" 1");
		paragraph.getContent().add(run);
		Drawing drawing = factory.createDrawing();
		run.getContent().add(drawing);
		drawing.getAnchorOrInline().add(inline);
		log.info(" done3 ");
		}catch (Exception e) {
			log.info(" exc in addInlineImageToParagraph "+e);
			// TODO: handle exception
		}
		return paragraph;
	}

	private static Inline createInlineImage(File file,WordprocessingMLPackage wordMLPackage) throws Exception {
		int docPrId = 1;
		int cNvPrId = 2;
		BinaryPartAbstractImage imagePart=null;
		try {
			
			log.info("start createInlineImage ");
		byte[] bytes = convertImageToByteArray(file);

		 imagePart = BinaryPartAbstractImage.createImagePart(wordMLPackage, bytes);

		
		}catch (Exception e) {
			// TODO: handle exception
			log.info("exc in  createInlineImage "+e);
			
		}
		return imagePart.createImageInline("Filename hint", "Alternative text", docPrId, cNvPrId, false);
		
	}

	private static byte[] convertImageToByteArray(File file) throws FileNotFoundException, IOException {
		InputStream is = new FileInputStream(file);
		long length = file.length();
		// You cannot create an array using a long, it needs to be an int.
		if (length > Integer.MAX_VALUE) {
			System.out.println("File too large!!");
		}
		byte[] bytes = new byte[(int) length];
		int offset = 0;
		int numRead = 0;

		while (offset < bytes.length && (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
			offset += numRead;
		}
//     while (offset= 0) {
//            offset += numRead;
//     }
		// Ensure all the bytes have been read
		if (offset < bytes.length) {
			System.out.println("Could not completely read file " + file.getName());
		}
		is.close();
		return bytes;
	}

	private static WordprocessingMLPackage getTemplate(String name) throws Docx4JException, FileNotFoundException {
		WordprocessingMLPackage template = WordprocessingMLPackage.load(new FileInputStream(new File(name)));
		return template;
	}

	private static List getAllElementFromObject(Object obj, Class toSearch) {
		List result = new ArrayList();
		if (obj instanceof JAXBElement)
			obj = ((JAXBElement) obj).getValue();

		if (obj.getClass().equals(toSearch)) {
			result.add(obj);
		} else if (obj instanceof ContentAccessor) {
			List children = ((ContentAccessor) obj).getContent();
			for (Object child : children) {
				result.addAll(getAllElementFromObject(child, toSearch));
			}

		}
		return result;
	}

	public static String saveImage(String imageUrl, String saveimgpath) throws IOException {
		String imgname ="";
		try {
			int o= imageUrl.lastIndexOf("/");
			 imgname = imageUrl.substring(o+1,imageUrl.length()).replaceAll(":", "_");
			System.out.println("imgname= "+imgname);
			URL url = new URL(imageUrl);
			if(!imgname.contains(".")) {
				imgname="img.jpg";
			}else {
				
			}
			
//			url.getFile();
			
			System.out.println("url.getFile()= ");
			// read the url

			InputStream is = url.openStream();
			System.out.println("done");
			OutputStream os = new FileOutputStream(saveimgpath+imgname);

			byte[] b = new byte[2048];
			int length;

			while ((length = is.read(b)) != -1) {
				os.write(b, 0, length);
			}

			is.close();
			os.close();
		}catch (Exception e) {
			// TODO: handle exception
		}
		return saveimgpath+imgname;
	}
	
public static void ReplaceLinkWithImage(Map<String, Object> replimg,String saveimgpath,WordprocessingMLPackage wordMLPackage) throws Exception {
//	Map<String, Object> replimg = new HashMap<String, Object>();
//	if() {}
//	replimg.put("cds", igp);
//	replimg.put("fds", igp);
//	replimg.put("Hello", igp);
//	System.out.println("maplist = " + replimg);
//	for (Map.Entry<String, Object> entry : replimg.entrySet()) {
//		System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
//	}
	try {
		System.out.println("start= ");
		log.info("start ReplaceLinkWithImage");
	List elemetns = getAllElementFromObject(wordMLPackage.getMainDocumentPart(), Tbl.class);

	for (Object obj : elemetns) {
		if (obj instanceof Tbl) {
			Tbl table = (Tbl) obj;
			List rows = getAllElementFromObject(table, Tr.class);
			for (Object trObj : rows) {
				Tr tr = (Tr) trObj;
				List cols = getAllElementFromObject(tr, Tc.class);
				for (Object tcObj : cols) {
					Tc tc = (Tc) tcObj;
					List<Object> texts = getAllElementFromObject(wordMLPackage.getMainDocumentPart(), Text.class);
//					List texts = getAllElementFromObject(tc, Text.class);
					
					for (Object textObj : texts) {
						Text text = (Text) textObj;
						for (Map.Entry<String, Object> entry : replimg.entrySet()) {
							try{
								System.out.println("entry.getKey() ***===== "+entry.getKey().trim()+"  text.getValue()=== "+text.getValue());
								log.info("entry.getKey() = "+entry.getKey().trim());
							if (text.getValue().trim().equalsIgnoreCase(entry.getKey().trim())) { // "${MY_PLACE_HOLDER}"
								// save image to server
								 System.out.println("text.getValue() if--------= "+text.getValue());
								log.info("entry.getKey().trim() = "+entry.getKey().trim());
								log.info("entry.getKey().trim() = "+entry.getValue().toString().trim());
								String filepath="";
								try {
								 filepath=saveImage(entry.getValue().toString().trim(), saveimgpath);
								 System.out.println("filepath= "+filepath);
								 log.info(" filepath= " + filepath);
								}catch (Exception e) {
									// TODO: handle exception
									 log.info(" exc in =saveImage " + e);
								}
								try {
									//  /home/ubuntu/generationTomcat/Images/centerimage.png
//									String fp="/home/ubuntu/generationTomcat/Images/centerimage.png";
//									 log.info(" img file  filepath== " + fp);
								File myfile = new File(filepath);
								 log.info(" img file  filepathnnn111111== " + filepath);
								P paragraphWithImage = addInlineImageToParagraph(createInlineImage(myfile,wordMLPackage));
								
								tc.getContent().remove(0);
								log.info("22" );
								tc.getContent().add(paragraphWithImage);
								log.info(" 33");
								}catch (Exception e) {
									// TODO: handle exception
									 log.info(" ex in  file== " + e);
								}
							}
						}catch (Exception e) {
							// TODO: handle exception
							 log.info(" ex in  file== " + e);
						}
						}
					}
					System.out.println("Done");
				}
			}
			log.info("done1" );
			System.out.println("here");
		}
	}
	}catch (Exception e) {
		// TODO: handle exception
		log.info("exception = "+e );
	}
}
}
