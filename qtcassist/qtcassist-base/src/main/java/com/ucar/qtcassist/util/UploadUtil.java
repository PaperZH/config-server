package com.ucar.qtcassist.util;

import com.zuche.framework.remote.RemoteClient;
import com.zuche.framework.remote.RemoteClientFactory;
import com.zuche.framework.remote.RemoteType;
import com.zuche.framework.upload.ResourceUploadResultVO;
import com.zuche.framework.upload.ResourceUploadScaleVO;
import com.zuche.framework.upload.ResourceUploadVO;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Description: TODO
 *
 * @author wangyang
 * @since 2018/8/13
 */

public class UploadUtil {

    public static void main(String args[]) throws Exception {
        RemoteClient client = RemoteClientFactory.getInstance(RemoteType.HESSIAN);

        File file = new File("D:\\img2.jpg");
        FileInputStream inputStream = new FileInputStream(file);
        byte[] bytes = getByteArrayInputStreamResource(inputStream);

        ResourceUploadVO vo = new ResourceUploadVO();
        vo.setName("123/322/723.jpg");
        vo.setData(bytes);
        vo.setCompress(true);
        vo.setCompressSize(10);

        List<ResourceUploadScaleVO> list = new ArrayList<ResourceUploadScaleVO>();
        vo.setScaleInfos(list);
        ResourceUploadScaleVO scaleVO = new ResourceUploadScaleVO("small", 500, 500,null,true);
        list.add(scaleVO);

        ResourceUploadResultVO resultVO= (ResourceUploadResultVO)client.executeToObject("carresources.commonResourceInsert.service", vo);
        System.out.println(resultVO.getOriginalName());
    }

    private static byte[] getByteArrayInputStreamResource(InputStream inputStream) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int len = 1024;
        byte tmp [] = new byte[len];
        int i ;
        while((i=inputStream.read(tmp, 0, len))>0){
            baos.write(tmp, 0, i);
        }
        byte[] data = baos.toByteArray();
        return data;
    }



}
