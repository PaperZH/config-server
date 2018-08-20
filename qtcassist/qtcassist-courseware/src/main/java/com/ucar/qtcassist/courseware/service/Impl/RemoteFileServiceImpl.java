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
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.ConnectException;

/**
 * 请填写类注释
 *
 * @author shijie.xu
 * @since 2018年08月15日
 */
@Service
public class RemoteFileServiceImpl implements RemoteFileService {
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
        OpenOfficeConnection connection = new SocketOpenOfficeConnection("10.104.102.225", 7009);

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
}
