package cn.tedu.ad.service;

import cn.tedu.util.AdDomain;

import java.util.List;

public interface ad_Service {

    List<AdDomain> getAds();

    void changeAds(AdDomain adDomain);

    void addAds(AdDomain adDomain);

    void delAds(int id);
}
