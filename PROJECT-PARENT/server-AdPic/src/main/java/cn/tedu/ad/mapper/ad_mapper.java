package cn.tedu.ad.mapper;

import cn.tedu.util.AdDomain;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ad_mapper {
    List<AdDomain> getAds();
    void changeAdsByid(AdDomain adDomain);
    void insertAds(@Param("str1")String str1 ,@Param("str2")String str2);

    void deleteById(@Param("id") int id);
}
