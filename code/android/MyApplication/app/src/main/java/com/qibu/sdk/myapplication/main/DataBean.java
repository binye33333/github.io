package com.qibu.sdk.myapplication.main;

import java.util.ArrayList;
import java.util.List;

class DataBean {
    public String imageRes;


    public static List<DataBean> getTestData() {

        ArrayList<DataBean> list = new ArrayList<>();

        DataBean bean = new DataBean();
        bean.imageRes = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec" +
                "=1585717471152&di=02595dd146dff17e037c0b7bd7c0fd34&imgtype=0&src=http%3A%2F" +
                "%2Fimg2.imgtn.bdimg.com%2Fit%2Fu%3D2863055012%2C3532495256%26fm%3D214%26gp%3D0" + ".jpg";
        list.add(bean);

        DataBean bean1 = new DataBean();
        bean1.imageRes = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec" +
                "=1585717470688&di=ae62b844efefade3d48f89af3db527aa&imgtype=0&src=http%3A%2F" +
                "%2Fimg.zcool.cn%2Fcommunity%2F01fae258a2b6dfa801219c773884cc" + ".png" +
                "%401280w_1l_2o_100sh.png";
        list.add(bean1);

        DataBean bean2 = new DataBean();
        bean2.imageRes = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec" +
                "=1585717470689&di=e3135a2dd1272b99f11490248501a1a6&imgtype=0&src=http%3A%2F" +
                "%2Fbpic.588ku.com%2Fback_pic%2F04%2F64%2F41%2F185874ad75f3234.jpg";
        list.add(bean2);

        DataBean bean3 = new DataBean();
        bean3.imageRes = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec" +
                "=1585717513572&di=89c9a73c76932324ba7d770ae6829a0d&imgtype=0&src=http%3A%2F" +
                "%2Fimg4.imgtn.bdimg.com%2Fit%2Fu%3D2424569854%2C325583504%26fm%3D214%26gp%3D0.jpg";
        list.add(bean3);

        return list;

    }
}
