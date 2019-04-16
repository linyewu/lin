package com.example.careold.controller;



import com.example.careold.common.DefineData;
import com.example.careold.common.PhotoUtil;
import com.example.careold.common.ReturnCodeUtil;
import org.apache.commons.collections.map.ListOrderedMap;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.OutputStream;
import java.util.Map;

/**
 * 功能：人员请求接口
 *
 * @Author: yanghd_bill
 * @create: 2018/11/19 11:03
 */
@RestController
@RequestMapping("image")
public class ImagesApi {

    private static final Logger LOG = LoggerFactory.getLogger(ImagesApi.class);
//    @Autowired
//    ConstantProperties properties;

    /**
     * 功能：文件保存接口
     *
     * @Author: yanghd_bill
     * @create: 2018/12/20 11:03
     */
    @PostMapping("saveImage")
    public ListOrderedMap saveImage(@RequestParam("file") MultipartFile file) {
        ListOrderedMap result=new ListOrderedMap();
        LOG.info("-------------------------------ImagesApi.java  start----------------------------------");
        Map<String,String> res = PhotoUtil.saveFile(file, "C:/me/files/school/oldIot/src/assets/img/");//D:/me/img/tempDir/
        LOG.info("-------------------------------ImagesApi.java  end----------------------------------");
        result.put(ReturnCodeUtil.returnCode,"1111");
        return result;
    }


        @RequestMapping(value="/download")
        public ResponseEntity<byte[]> download(HttpServletRequest request, HttpServletResponse response, @RequestParam("name") String filename)throws Exception {
            //下载显示的文件名，解决中文名称乱码问题
            filename=new String(filename.getBytes("iso-8859-1"),"UTF-8");
            //下载文件路径
//            String path = request.getServletContext().getRealPath("/upload/");
            String path = "C:/me/files/school/oldIot/src/assets/img/";
            File file = new File( path+ filename);
            HttpHeaders headers = new HttpHeaders();
            //下载显示的文件名，解决中文名称乱码问题
            String downloadFielName = new String(filename.getBytes("iso-8859-1"),"UTF-8");
            //通知浏览器以attachment（下载方式）打开图片
            headers.setContentDispositionFormData("Content-Disposition", downloadFielName);
            //application/octet-stream ： 二进制流数据（最常见的文件下载）。
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),
                    headers, HttpStatus.CREATED);
        }

    @RequestMapping(value="/downloadTwo")
    public static void loanPic(String picPath, HttpServletRequest request, HttpServletResponse response, @RequestParam("name") String filename){

        String path = request.getServletContext().getRealPath("/upload/");
        File file = new File(path + File.separator + filename);
        if(file==null || !file.exists()){
            return;
        }


        OutputStream os=null;
        try {
            response.reset();
            response.setContentType("application/octet-stream;charset=utf-8");
            response.setHeader("content-Disposition","attachment;filename="+file.getName() );
            os=response.getOutputStream();
            os.write(FileUtils.readFileToByteArray(file));
            os.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
