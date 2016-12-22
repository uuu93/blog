package org.lxb.framework.util;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;


/**
 * 图片处理类
 * 
 * @author 李治勇
 * 
 */
public class ImageUtils {

	/**
	 * 截取图片尺寸方法
	 * 
	 * @param path 文件路径
	 * @param x X坐标起点
	 * @param y Y坐标起点
	 * @param width 返回的图片宽
	 * @param height 返回的图片高
	 * @throws IOException
	 */
	public static void cutImage(String path, int x, int y, int width, int height)
			throws IOException {
		ImageInputStream imageStream = null;
		try {
			// 读取目标图片文件
			File file = new File(path);
			FileInputStream in = new FileInputStream(file);

			// 计算图片截取尺寸
			int imageX = x + width;
			int imageY = y + height;

			// 取出文件后缀名
			String fileName = file.getName();
			String fileFix = fileName.substring(fileName.lastIndexOf(".") + 1);

			Iterator<ImageReader> readers = ImageIO
					.getImageReadersByFormatName(fileFix);
			ImageReader reader = readers.next();
			imageStream = ImageIO.createImageInputStream(in);
			reader.setInput(imageStream, true);
			ImageReadParam param = reader.getDefaultReadParam();

			// 截取图片
			Rectangle rect = new Rectangle(x, y, imageX, imageY);
			param.setSourceRegion(rect);

			BufferedImage bi = reader.read(0, param);

			// 回写图片到上传目录中
			FileOutputStream out = new FileOutputStream(path);
			ImageIO.write(bi, fileFix, out);
			out.close();
		} finally {
			imageStream.close();
		}
	}

	/**
	 * 切换图片路径
	 * @param fileName 文件名
	 * @param path 保存路径
	 * @param tempPath 临时图片位置
	 * @return
	 * @throws IOException
	 */
	@SuppressWarnings("resource")
	public static String moveFile(String fileName,String path,String tempPath) throws IOException{
		FileInputStream fis = new FileInputStream(tempPath + "/" + fileName);
		FileOutputStream out = new FileOutputStream(path + "/" + fileName);
		
		int read = 0;
		byte[] bytes = new byte[1024];
		while ((read = fis.read(bytes)) != -1){
			out.write(bytes, 0, read);
		}
		out.flush();
		out.close();
		
		File deletefile = new File(tempPath + "/" + fileName);
		deletefile.delete();//完成操作以后删除原文件
		
		return fileName;
	}
	
	public static void main(String[] args) throws Exception {
		// 测试方法
//		ImageUtils.cutImage("E:\\Images\\upload\\test.jpg", 50, 50, 500, 500);
//		ImageUtils.moveFile("test.jpg","E:\\Images\\upload","E:\\Images\\upload\\temp");
	}
}