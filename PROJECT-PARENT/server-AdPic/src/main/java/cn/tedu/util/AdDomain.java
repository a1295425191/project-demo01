package cn.tedu.util;

public class AdDomain {
    private int id;
    private String AdPicUri;
    private String AdUri;

    public AdDomain() {
    }

    public AdDomain(int id, String adPicUri, String adUri) {
        this.id = id;
        AdPicUri = adPicUri;
        AdUri = adUri;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAdPicUri() {
        return AdPicUri;
    }

    public void setAdPicUri(String adPicUri) {
        AdPicUri = adPicUri;
    }

    public String getAdUri() {
        return AdUri;
    }

    public void setAdUri(String adUri) {
        AdUri = adUri;
    }

    @Override
    public String toString() {
        return "AdDomain{" +
                "id=" + id +
                ", AdPicUri='" + AdPicUri + '\'' +
                ", AdUri='" + AdUri + '\'' +
                '}';
    }
}
