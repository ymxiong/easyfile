package cc.eamon.easyfile.common;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: eamon
 * Email: eamon@eamon.cc
 * Time: 2019-06-22 23:47:47
 */
public class PDFTools {

    public static List<BufferedImage> convertToImage(File file) throws IOException {
        PDDocument document = PDDocument.load(file);
        PDFRenderer pdfRenderer = new PDFRenderer(document);
        List<BufferedImage> bufferedImageList = new ArrayList<>();

        for (int page = 0;page<document.getNumberOfPages();page++){
            BufferedImage img = pdfRenderer.renderImageWithDPI(page, 300, ImageType.RGB);
            bufferedImageList.add(img);
        }
        document.close();

        return bufferedImageList;
    }

    public static BufferedImage concat(BufferedImage[] images) throws IOException {
        int heightTotal = 0;
        for(int j = 0; j < images.length; j++) {
            heightTotal += images[j].getHeight();
        }

        int heightCurr = 0;
        BufferedImage concatImage = new BufferedImage(images[0].getWidth(), heightTotal, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = concatImage.createGraphics();
        for(int j = 0; j < images.length; j++) {
            g2d.drawImage(images[j], 0, heightCurr, null);
            heightCurr += images[j].getHeight();
        }
        g2d.dispose();

        return concatImage;
    }


    public static boolean doPDFtoImage(File file, String path, String picName, String picType) {
        return false;
    }

}
