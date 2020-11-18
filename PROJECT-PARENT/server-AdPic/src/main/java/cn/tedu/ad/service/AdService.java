package cn.tedu.ad.service;

import cn.tedu.ad.mapper.ad_mapper;
import cn.tedu.util.AdDomain;
import cn.tedu.util.PicUploadResult;
import cn.tedu.util.UploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.UUID;

@Service
public class AdService implements ad_Service {
    @Autowired(required = false)
    private ad_mapper ad_mapper;
    @Override
    public List<AdDomain> getAds() {
        List<AdDomain> ads = ad_mapper.getAds();
        return ads;
    }

    @Override
    public void changeAds(AdDomain adDomain) {
        ad_mapper.changeAdsByid(adDomain);
    }

    @Override
    public void addAds(AdDomain adDomain) {
        ad_mapper.insertAds(adDomain.getAdPicUri(),adDomain.getAdUri());
    }

    @Override
    public void delAds(int id) {
        ad_mapper.deleteById(id);
    }
    /**
     * 1、校验图片是否正确（这里用后缀验证 png gif jpeg...）
     * 2、生成该图片的多级路径/upload/a/v/3/d/f/6/h/d/
     * 3、重命名图片文件，避免考虑覆盖的问题
     * 4、将流数据输出到磁盘文件，图片上传保存完成了
     * 利用之前生成的变量，拼接url地址，存储到PicLoadResult中返回给ajax使用
     */
    @Override
    public PicUploadResult picUpload(MultipartFile pic) {
        PicUploadResult result=new PicUploadResult();
        //拿到原名称 originalFilename
        String oName=pic.getOriginalFilename();
        //解析后缀 sky.png
        String extName=oName.substring(oName.lastIndexOf("."));
        //比对名称，图片后缀中的一员
        //利用正则表达式 match
        String regex=".(png|gif|jpg|jpeg)";
        boolean matches=extName.matches(regex);
        if(!matches){
            //进入if表示后缀不合法
            result.setError(1);
            return result;
        }
        /*
        生成路径 upload/a/b/c/d/e/f/g/h   upload：业务前缀
        uplaod/a/v/ 3/d/f/6/h/d/ upload: 业务前缀，
        上传的图片很多种，一个上传图片服务，提供多个系统调用
         user-head-img/ shop-Logo/ product-img/
        后面的是多级路径多级路径特点分开处理小量的图片
        */
        String path= UploadUtil.getUploadPath(UUID.randomUUID().toString(),"/easymall")+"/";
        //图片上传，有原名称，原名称可以重复的，每次都让他不重复
        String newFileName=System.currentTimeMillis()+extName;
        //多级路径创建
        File dir=new File("C:/Users/weal/Desktop/j/img"+path);
        if(!dir.exists()){
            //不存在，创建多级路径
            dir.mkdirs();
        }
        try{
            //使用pic数据，输出图片文件到磁盘中
            pic.transferTo(new File(
                    "C:/Users/weal/Desktop/j/img"
                            +path+newFileName));
        }catch (Exception e){
            e.printStackTrace();
            result.setError(1);
            return result;
        }
        String url="C:/Users/weal/Desktop/j/img"+path+newFileName;
        result.setUrl(url);
        return result;


    }
}
