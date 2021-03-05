import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class fwb {

    public static void main(String[] args) {
        conversion();
    }

    public static void conversion() {

        String s = "[{\"source\":\"https://oss.cosmoplat.com:443/jsk-mall/upload/image/202103/6219f01b-1fb4-4f52-a07f-e0484150dade_source.png\",\"large\":\"https://oss.cosmoplat.com:443/jsk-mall/upload/image/202103/6219f01b-1fb4-4f52-a07f-e0484150dade_large.jpg\",\"medium\":\"https://oss.cosmoplat.com:443/jsk-mall/upload/image/202103/6219f01b-1fb4-4f52-a07f-e0484150dade_medium.jpg\",\"thumbnail\":\"https://oss.cosmoplat.com:443/jsk-mall/upload/image/202103/6219f01b-1fb4-4f52-a07f-e0484150dade_thumbnail.jpg\",\"order\":0},{\"source\":\"https://oss.cosmoplat.com:443/jsk-mall/upload/image/202103/3d038374-e170-4271-a109-acd0ec161473_source.jpg\",\"large\":\"https://oss.cosmoplat.com:443/jsk-mall/upload/image/202103/3d038374-e170-4271-a109-acd0ec161473_large.jpg\",\"medium\":\"https://oss.cosmoplat.com:443/jsk-mall/upload/image/202103/3d038374-e170-4271-a109-acd0ec161473_medium.jpg\",\"thumbnail\":\"https://oss.cosmoplat.com:443/jsk-mall/upload/image/202103/3d038374-e170-4271-a109-acd0ec161473_thumbnail.jpg\",\"order\":1},{\"source\":\"https://oss.cosmoplat.com:443/jsk-mall/upload/image/202103/afe3df4a-d585-42be-ad64-f10c34c0f96d_source.jpg\",\"large\":\"https://oss.cosmoplat.com:443/jsk-mall/upload/image/202103/afe3df4a-d585-42be-ad64-f10c34c0f96d_large.jpg\",\"medium\":\"https://oss.cosmoplat.com:443/jsk-mall/upload/image/202103/afe3df4a-d585-42be-ad64-f10c34c0f96d_medium.jpg\",\"thumbnail\":\"https://oss.cosmoplat.com:443/jsk-mall/upload/image/202103/afe3df4a-d585-42be-ad64-f10c34c0f96d_thumbnail.jpg\",\"order\":2},{\"source\":\"https://oss.cosmoplat.com:443/jsk-mall/upload/image/202103/ad37c259-3c34-4393-905e-18d311bf66b0_source.jpg\",\"large\":\"https://oss.cosmoplat.com:443/jsk-mall/upload/image/202103/ad37c259-3c34-4393-905e-18d311bf66b0_large.jpg\",\"medium\":\"https://oss.cosmoplat.com:443/jsk-mall/upload/image/202103/ad37c259-3c34-4393-905e-18d311bf66b0_medium.jpg\",\"thumbnail\":\"https://oss.cosmoplat.com:443/jsk-mall/upload/image/202103/ad37c259-3c34-4393-905e-18d311bf66b0_thumbnail.jpg\",\"order\":3},{\"source\":\"https://oss.cosmoplat.com:443/jsk-mall/upload/image/202103/f999e6ab-26d2-46d0-b126-b56ac4c10c76_source.jpg\",\"large\":\"https://oss.cosmoplat.com:443/jsk-mall/upload/image/202103/f999e6ab-26d2-46d0-b126-b56ac4c10c76_large.jpg\",\"medium\":\"https://oss.cosmoplat.com:443/jsk-mall/upload/image/202103/f999e6ab-26d2-46d0-b126-b56ac4c10c76_medium.jpg\",\"thumbnail\":\"https://oss.cosmoplat.com:443/jsk-mall/upload/image/202103/f999e6ab-26d2-46d0-b126-b56ac4c10c76_thumbnail.jpg\",\"order\":4},{\"source\":\"https://oss.cosmoplat.com:443/jsk-mall/upload/image/202103/b610047d-18f4-4227-a0d1-2f484c161c1e_source.jpg\",\"large\":\"https://oss.cosmoplat.com:443/jsk-mall/upload/image/202103/b610047d-18f4-4227-a0d1-2f484c161c1e_large.jpg\",\"medium\":\"https://oss.cosmoplat.com:443/jsk-mall/upload/image/202103/b610047d-18f4-4227-a0d1-2f484c161c1e_medium.jpg\",\"thumbnail\":\"https://oss.cosmoplat.com:443/jsk-mall/upload/image/202103/b610047d-18f4-4227-a0d1-2f484c161c1e_thumbnail.jpg\",\"order\":5},{\"source\":\"https://oss.cosmoplat.com:443/jsk-mall/upload/image/202103/74f7f559-6fb3-4398-bde0-b91362c506c1_source.jpg\",\"large\":\"https://oss.cosmoplat.com:443/jsk-mall/upload/image/202103/74f7f559-6fb3-4398-bde0-b91362c506c1_large.jpg\",\"medium\":\"https://oss.cosmoplat.com:443/jsk-mall/upload/image/202103/74f7f559-6fb3-4398-bde0-b91362c506c1_medium.jpg\",\"thumbnail\":\"https://oss.cosmoplat.com:443/jsk-mall/upload/image/202103/74f7f559-6fb3-4398-bde0-b91362c506c1_thumbnail.jpg\",\"order\":6}]";

        JSONArray jsonArray = JSONArray.parseArray(s);
        StringBuffer sb =new StringBuffer();
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jb = jsonArray.getJSONObject(i);
            String url = jb.getString("source");
            //System.out.println(url);
            String base = "<p><img src=\""+url+"\" title=\"\" alt=\"\" width=\"100%\" ></p>";
           // System.out.println(base);
            sb.append(base);
        }
        System.out.println(sb);


    }

}
