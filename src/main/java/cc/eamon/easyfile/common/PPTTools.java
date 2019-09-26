package cc.eamon.easyfile.common;

import org.apache.poi.xslf.usermodel.*;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: eamon
 * Email: eamon@eamon.cc
 * Time: 2019-06-22 23:47:21
 */
public class PPTTools {


    public static void main(String[] args) {
        doPPT2007toImage(new File("/Users/eamon/Documents/Doc/Homework/工程伦理.pptx"), "/Users/eamon/Documents/Doc/Homework/ggg", "a", "png");
    }


    /**
     * 设置PPTX字体
     *
     * @param slide
     */
    private static void setFont(XSLFSlide slide) {
        for (XSLFShape shape : slide.getShapes()) {
            if (shape instanceof XSLFTextShape) {
                XSLFTextShape txtshape = (XSLFTextShape) shape;
                for (XSLFTextParagraph paragraph : txtshape.getTextParagraphs()) {
                    List<XSLFTextRun> truns = paragraph.getTextRuns();
                    for (XSLFTextRun trun : truns) {
                        trun.setFontFamily("宋体");
                    }
                }
            }

        }
    }


    private static boolean checkFile(String name) {
        if (name == null) return false;
        int pos = name.lastIndexOf(".");
        String extName = "";
        if (pos >= 0) {
            extName = name.substring(pos);
        }
        if (".ppt".equalsIgnoreCase(extName) || ".pptx".equalsIgnoreCase(extName)) {
            return true;
        }
        return false;
    }

    /**
     * PPT转图片 （jpeg）(2007)
     *
     * @param file
     * @param path    存放路径
     * @param picName 图片前缀名称 如 a 生成后为a_1,a_2 ...
     * @param picType 转成图片的类型，无点 如 jpg bmp png ...
     * @return true/false
     */
    public static boolean doPPT2007toImage(File file, String path, String picName, String picType) {
        try {
            boolean isppt = checkFile(file.getName());
            if (!isppt) {
                return false;
            }
            FileInputStream is = new FileInputStream(file);
            XMLSlideShow xmlSlideShow = new XMLSlideShow(is);
            List<XSLFSlide> xslfSlides = xmlSlideShow.getSlides();
            Dimension pageSize = xmlSlideShow.getPageSize();

            is.close();
            for (int i = 0; i < xslfSlides.size(); i++) {
                System.out.print("第" + i + "页。");
                setFont(xslfSlides.get(i));
                BufferedImage img = new BufferedImage(pageSize.width,
                        pageSize.height, BufferedImage.TYPE_INT_RGB);

                Graphics2D graphics = img.createGraphics();
                graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);
                graphics.setRenderingHint(RenderingHints.KEY_RENDERING,
                        RenderingHints.VALUE_RENDER_QUALITY);
                graphics.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                        RenderingHints.VALUE_INTERPOLATION_BICUBIC);
                graphics.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS,
                        RenderingHints.VALUE_FRACTIONALMETRICS_ON);

                graphics.setPaint(Color.white);
                graphics.fill(new Rectangle2D.Float(0, 0, pageSize.width,
                        pageSize.height));
                xslfSlides.get(i).draw(graphics);
                FileOutputStream out = new FileOutputStream(path + "/" +
                        +(i + 1) + "." + picType);
                javax.imageio.ImageIO.write(img, "jpeg", out);
                out.close();
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


//    /**
//     * PPT转图片 （jpeg）(2007)
//     *
//     * @param file
//     * @param path    存放路径
//     * @param picName 图片前缀名称 如 a 生成后为a_1,a_2 ...
//     * @param picType 转成图片的类型，无点 如 jpg bmp png ...
//     * @return true/false
//     */
//    public static boolean doPPT2007toImageSteam(MultipartFile file,String basePath, String path, String picName, String picType, SubFileLocationCallback subFileLocationCallback) {
//        try {
//            boolean isppt = checkFile(file.getOriginalFilename());
//            if (!isppt) {
//                return false;
//            }
//            InputStream is = file.getInputStream();
//            XMLSlideShow xmlSlideShow = new XMLSlideShow(is);
//            List<XSLFSlide> xslfSlides = xmlSlideShow.getSlides();
//            Dimension pageSize = xmlSlideShow.getPageSize();
//
//            is.close();
//            List<String> locations = new ArrayList<>();
//            for (int i = 0; i < xslfSlides.size(); i++) {
//                System.out.print("第" + i + "页。");
//                setFont(xslfSlides.get(i));
//                BufferedImage img = new BufferedImage(pageSize.width,
//                        pageSize.height, BufferedImage.TYPE_INT_RGB);
//
//                Graphics2D graphics = img.createGraphics();
//                graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
//                        RenderingHints.VALUE_ANTIALIAS_ON);
//                graphics.setRenderingHint(RenderingHints.KEY_RENDERING,
//                        RenderingHints.VALUE_RENDER_QUALITY);
//                graphics.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
//                        RenderingHints.VALUE_INTERPOLATION_BICUBIC);
//                graphics.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS,
//                        RenderingHints.VALUE_FRACTIONALMETRICS_ON);
//
//                graphics.setPaint(Color.white);
//                graphics.fill(new Rectangle2D.Float(0, 0, pageSize.width,
//                        pageSize.height));
//                xslfSlides.get(i).draw(graphics);
//                File f = new File(basePath + path);
//                if (!f.exists()) {
//                    f.mkdirs();
//                }
//                String pathAll = path + "/" + picName + "-"
//                        + (i + 1) + "." + picType;
//                FileOutputStream out = new FileOutputStream(basePath + pathAll);
//                javax.imageio.ImageIO.write(img, "jpeg", out);
//                out.close();
//                locations.add(pathAll);
//            }
//            subFileLocationCallback.callbackLocation(locations);
//            return true;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return false;
//    }

}
