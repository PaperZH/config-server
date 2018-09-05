package com.ucar.qtcassist.courseware.service.Impl;

import com.artofsolving.jodconverter.DocumentConverter;
import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.converter.StreamOpenOfficeDocumentConverter;
import com.ucar.qtcassist.base.util.CustomerDocumentFormatRegistry;
import com.ucar.qtcassist.courseware.service.RemoteFileService;
import com.ucar.qtcassist.courseware.util.GetBytes;
import com.zuche.framework.enums.BusinessLineEnum;
import com.zuche.framework.udfs.client.UDFSClient;
import com.zuche.framework.udfs.client.upload.UDFSUploadResultVO;
import com.zuche.framework.udfs.client.upload.UDFSUploadVO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.ConnectException;
import java.sql.Date;
import java.util.UUID;

/**
 * 请填写类注释
 *
 * @author shijie.xu
 * @since 2018年08月15日
 */
@Service
public class RemoteFileServiceImpl implements RemoteFileService {
    @Value("${openOffice.host}")
    String openOfficeHost;

    @Value("${openOffice.port}")
    int openOfficePort;



    @Override
    public String uploadFile(InputStream inputStream, String Name) throws IOException {
        byte[] fileByte = GetBytes.getByteArrayInputStreamResource(inputStream);
        UDFSUploadVO vo = new UDFSUploadVO();
        vo.setName(Name);
        vo.setData(fileByte);
        vo.setBusinessLine(BusinessLineEnum.EVP);
        UDFSUploadResultVO resultVO = UDFSClient.upload(vo);
        if(resultVO != null) {
            return resultVO.getOriginalName();
        }
        return null;
    }

    @Override
    public void fileConvert(File inputFile, File outputFile) {
        OpenOfficeConnection connection = new SocketOpenOfficeConnection(openOfficeHost, openOfficePort);

        try {

            CustomerDocumentFormatRegistry formatReg = new CustomerDocumentFormatRegistry();

            connection.connect();

            DocumentConverter converter = new StreamOpenOfficeDocumentConverter(connection, formatReg);

            converter.convert(inputFile, outputFile);

        } catch (ConnectException cex) {

            cex.printStackTrace();

        } finally {

            // close the connection

            if(connection != null) {

                connection.disconnect();

                connection = null;

            }

        }
    }

    @Override
    public String saveTemFile(String name) {
        int point = name.lastIndexOf(".");
        String uuid = UUID.randomUUID().toString().replaceAll("-","");
        String suffix = name.substring(point + 1, name.length());
        String r=uuid+"."+suffix;
        return r;
    }
}
