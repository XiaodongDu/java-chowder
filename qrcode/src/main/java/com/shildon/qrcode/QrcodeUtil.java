package com.shildon.qrcode;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 二维码生成工具。
 * @author shildon<shildondu@gmail.com>
 * @date Dec 20, 2015 3:22:16 PM
 *
 */
public final class QrcodeUtil {
	
	private static final Logger log = LoggerFactory.getLogger(QrcodeUtil.class);
	
	/**
	 * 生成二维码并写进输出流。
	 * @param source 内容
	 * @param imageType 图片类型
	 * @param outputStream 输出流
	 */
	public void generateQrcode(String source, ImageType imageType, OutputStream outputStream) {
		ByteArrayOutputStream byteArrayOutputStream = QRCode.from(source).
				to(imageType).stream();
		try {
			outputStream.write(byteArrayOutputStream.toByteArray());
		} catch (IOException e) {
			log.error("Error in write qrcode image to outputstream.", e);
		}
	}
	
	/**
	 * 生成二维码并写进目标路径。
	 * @param source 内容
	 * @param imageType 图片类型
	 * @param targetPath 目标路径
	 */
	public void generateQrcode(String source, ImageType imageType, String targetPath) {
		Path target = Paths.get(targetPath);
		try {
			generateQrcode(source, imageType, Files.newOutputStream(target));
		} catch (IOException e) {
			log.error("Error in creating outputstream to target.", e);
		}
	}

}
