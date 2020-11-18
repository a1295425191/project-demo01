package cn.tedu.ad.controller;

import cn.tedu.ad.service.AdService;
import cn.tedu.ad.service.ad_Service;
import cn.tedu.util.AdDomain;
import cn.tedu.util.SysResult;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/Ads/manage")
public class AdPicController {
    @Autowired
    AdService adService;

    /**
     *
     */
    @CrossOrigin
    @RequestMapping("/getAds")
    public List<AdDomain> getAds(){
        List<AdDomain> ads = adService.getAds();
        return ads;
    }

    /**
     *
     */
    @CrossOrigin
    @RequestMapping("/changeAds")
    public SysResult changeAds(AdDomain adDomain){
        try{
            adService.changeAds(adDomain);
            return SysResult.ok();
        }catch (Exception e){
            e.printStackTrace();
            return SysResult.build(000000,"修改错误",null);
        }
    }
    /**
     *
     */
    @CrossOrigin
    @RequestMapping("/addAds")
    public SysResult addAds(AdDomain adDomain){
        try{

            adService.addAds(adDomain);
            return SysResult.ok();
        }catch (Exception e){
            e.printStackTrace();
            return SysResult.build(123123123,"插入失败",null);
        }
    }
    /**
     *
     */
    @CrossOrigin
    @RequestMapping("/delAds")
    public SysResult delAds(int id){
        try{
            adService.delAds(id);
            return SysResult.ok();
        }catch (Exception e){
            e.printStackTrace();
            return SysResult.build(12121,"删除失败",null);
        }
    }


}
