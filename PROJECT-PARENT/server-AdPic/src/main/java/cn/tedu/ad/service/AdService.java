package cn.tedu.ad.service;

import cn.tedu.ad.mapper.ad_mapper;
import cn.tedu.util.AdDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdService implements ad_Service {
    @Autowired(required = false)
    private ad_mapper ad_mapper;
    @Override
    public List<AdDomain> getAds() {
        return ad_mapper.getAds();
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
}
