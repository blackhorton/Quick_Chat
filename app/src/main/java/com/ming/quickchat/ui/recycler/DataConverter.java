package com.ming.quickchat.ui.recycler;

/**
 * @author Hortons
 * on 8/15/19
 */

import java.util.ArrayList;

/**
 * RecyclerView的数据转换类
 */
public abstract class DataConverter {

    protected final ArrayList<MultipleItemEntity> ENTITIES = new ArrayList<>();
    private String mJsonData = null;

    public abstract ArrayList<MultipleItemEntity> convert();

    public DataConverter setJsonData(String json) {
        this.mJsonData = json;
        return this;
    }

    protected String getJsonData() {
        if (mJsonData == null || mJsonData.isEmpty()) {
            throw new NullPointerException("DATA IS NULL");
        }
        return mJsonData;
    }

}
