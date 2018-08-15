package com.ucar.qtcassist.base.util;

import com.artofsolving.jodconverter.DocumentConverter;
import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.converter.StreamOpenOfficeDocumentConverter;

import java.io.File;
import java.net.ConnectException;

public class DocumentConverUtil extends Thread {

	// 需要转换的文件
	private File inputFile;

	// 输出的文件
	private File outputFile;

	public DocumentConverUtil(File inputFile, File outputFile) {

		this.inputFile = inputFile;

		this.outputFile = outputFile;

	}

	public void docToPdf() {


		OpenOfficeConnection connection = new SocketOpenOfficeConnection("10.104.102.225",7009);

		try {

			CustomerDocumentFormatRegistry formatReg = new CustomerDocumentFormatRegistry();

			connection.connect();

			DocumentConverter converter = new StreamOpenOfficeDocumentConverter(connection,formatReg);

			converter.convert(inputFile, outputFile);

		} catch (ConnectException cex) {

			cex.printStackTrace();

		} finally {

			// close the connection

			if (connection != null) {

				connection.disconnect();

				connection = null;

			}

		}

	}

	public static void main(String[] args) {

		File ppt = new File("F:\\开发环境搭建培训文稿v0.1--张钧.pptx");
		File pdf = new File("F:\\开发环境搭建培训文稿v0.1--张钧.pdf");

		DocumentConverUtil docToPdf = new DocumentConverUtil(ppt, pdf);
		docToPdf.docToPdf();
	}

	public void run() {

		this.docToPdf();

	}

	public File getInputFile() {
		return inputFile;
	}

	public void setInputFile(File inputFile) {
		this.inputFile = inputFile;
	}

	public File getOutputFile() {
		return outputFile;
	}

	public void setOutputFile(File outputFile) {
		this.outputFile = outputFile;
	}

}
