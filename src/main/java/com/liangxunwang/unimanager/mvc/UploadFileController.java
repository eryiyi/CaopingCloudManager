package com.liangxunwang.unimanager.mvc;

import com.liangxunwang.unimanager.model.tip.DataTip;
import com.liangxunwang.unimanager.model.tip.ErrorTip;
import com.liangxunwang.unimanager.util.ControllerConstants;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

/**
 * Created by zhl on 2015/2/4.
 */
public class UploadFileController extends ControllerConstants {
    @RequestMapping("/uploadFile")
    @ResponseBody
    public String uploadImage(@RequestParam(value = "file") MultipartFile file, HttpServletRequest request){
        String path = request.getSession().getServletContext().getRealPath("upload");
        String fileName = file.getOriginalFilename();
        String newFileName = null;
        if(fileName != null && !"".equals(fileName)) {
            String type = fileName.substring(fileName.lastIndexOf("."), fileName.length()); //上传文件类型
            //判断上传文件类型
            if(!".mp3".equals(type)) {
                return toJSONString(new ErrorTip(1, "获取数据失败，请稍后重试！")
                );
            }
            newFileName = String.valueOf(System.currentTimeMillis()) + fileName.subSequence(fileName.lastIndexOf("."), fileName.length());
            File targetFile = new File(path, newFileName);
            if(!targetFile.exists()){
                targetFile.mkdirs();
            }
            //保存
            try {
                file.transferTo(targetFile);
            } catch (Exception e) {
                return toJSONString(new ErrorTip(1, "获取数据失败，请稍后重试！")
                );//上传文件失败，
            }
        } else {
            return toJSONString(new ErrorTip(1, "获取数据失败，请稍后重试！")
            );//上传图片为空
        }
        DataTip dataTip = new DataTip();
        dataTip.setData("upload/" + newFileName);
        return toJSONString(dataTip);
    }
}
