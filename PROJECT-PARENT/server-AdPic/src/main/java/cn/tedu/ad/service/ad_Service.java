package cn.tedu.ad.service;

import cn.tedu.util.AdDomain;
import cn.tedu.util.PicUploadResult;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ad_Service {

    List<AdDomain> getAds();

    void changeAds(AdDomain adDomain);

    void addAds(AdDomain adDomain);

    void delAds(int id);

    PicUploadResult picUpload(MultipartFile pic);
}
