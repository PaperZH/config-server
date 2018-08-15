package com.ucar.qtcassist.service.Impl;

import com.ucar.qtcassist.service.RemoteFileService;
import com.zuche.framework.enums.BusinessLineEnum;
import com.zuche.framework.udfs.client.UDFSClient;
import com.zuche.framework.udfs.client.upload.UDFSUploadResultVO;
import com.zuche.framework.udfs.client.upload.UDFSUploadVO;
import com.zuche.framework.upload.ResourceUploadVO;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 请填写类注释
 *
 * @author shijie.xu
 * @since 2018年08月15日
 */
@Service
public class RemoteFileServiceImpl implements RemoteFileService {
    @Override
    public String uploadFile(InputStream inputStream,String Name) throws IOException{
        byte[] fileByte =getByteArrayInputStreamResource(inputStream);
        UDFSUploadVO vo = new UDFSUploadVO();
        vo.setName(Name);
        vo.setData(fileByte);
        vo.setBusinessLine(BusinessLineEnum.EVP);
        UDFSUploadResultVO resultVO=  UDFSClient.upload(vo);
        if(resultVO!=null){
            return resultVO.getOriginalName();
        }
        return null;
    }

    private static byte[] getByteArrayInputStreamResource(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int length =1024;
        byte[] temp=new byte[length];
        int i;
        while((i=inputStream.read(temp,0,length))>0){
            byteArrayOutputStream.write(temp,0,i);
        }
        byte [] data = byteArrayOutputStream.toByteArray();
        return data;
    }
}
