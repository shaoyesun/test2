package com.controller.vue;

import com.utils.ExceptionUtil;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * Created by root on 16-11-17.
 */
@Controller
@RequestMapping(value = "/vue")
public class VueController {

    private static Logger log = Logger.getLogger(VueController.class);

    @RequestMapping(value = "/vueStudy/{pack}/{view}")
    public String vueStudy(@PathVariable String pack, @PathVariable String view) {
        if (view.equals("0")) return pack;
        return pack + "/" + view;
    }

    /**
     *上传文件
     */
    @RequestMapping(value = "/uperDate",method= RequestMethod.POST)
    public String uperDate(MultipartFile photo, MultipartFile data, HttpServletRequest request) throws IOException {
        if(!photo.isEmpty()) {
            ServletContext sc = request.getSession().getServletContext();
            String dir = sc.getRealPath("/update");

            String fileName = photo.getOriginalFilename();
            FileUtils.writeByteArrayToFile(new File("/home/bmk/uploads", fileName), photo.getBytes());
            System.out.println("icon url : /home/bmk/uploads/" + fileName);
        }
        if(!data.isEmpty()) {
            ServletContext sc = request.getSession().getServletContext();
            String dir = sc.getRealPath("/update");

            String fileName = data.getOriginalFilename();
            System.out.println("filename : " + fileName);
            System.out.println("icon url : /home/bmk/uploads/" + fileName);
            FileUtils.writeByteArrayToFile(new File("/home/bmk/uploads", fileName), data.getBytes());
        }
        return "index";
    }

    /**
     * 文件下载
     */
    @RequestMapping(value = "/downloadClient", method = RequestMethod.GET)
    public void downLoadClientFile(String fileName, HttpServletResponse response) throws IOException {
        fileName = "10896橙色活力PPT模板(www.52ppt.com).pptx";
        /*try {
            fileName = new String(fileName.getBytes("ISO-8859-1"), "UTF-8");
        } catch (Exception e) {
            log.warn("解码错误" + ExceptionUtil.print(e));
        }*/
        String realPath = "/home/bmk/uploads/" + fileName;
        downFile(fileName, realPath, response);
    }

    private void downFile(String filename, String realPath, HttpServletResponse response) throws IOException {
        try {
            filename = new String(filename.getBytes("ISO-8859-1"), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            log.warn("文件转码出现异常异常信息为：" + ExceptionUtil.print(e));
        }
        log.debug("下载的文件名：" + filename);
        log.debug("下载的文件路径：" + realPath);
        OutputStream os = null;
        InputStream inputStream = null;
        try {
            response.setCharacterEncoding("utf-8");
            response.setContentType("multipart/form-data");
            response.setContentType("application/x-download");
            response.setHeader("Content-Disposition", "attachment;fileName="
                    + filename);
            inputStream = new FileInputStream(realPath);
            os = response.getOutputStream();
            byte[] b = new byte[1024];
            int length;
            while ((length = inputStream.read(b)) != -1) {
                os.write(b, 0, length);
            }
            os.flush();
        } catch (IOException e) {
            log.warn("下载文件出现异常，异常信息为：" + ExceptionUtil.print(e));
        } finally {
            inputStream.close();
            os.close();
        }
    }

}
