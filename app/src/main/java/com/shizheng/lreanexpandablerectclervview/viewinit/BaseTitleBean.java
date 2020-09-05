package com.shizheng.lreanexpandablerectclervview.viewinit;

import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

public class BaseTitleBean extends ExpandableGroup<NameBean> {

    public BaseTitleBean(String title, List<NameBean> items) {
        super(title, items);
    }



}
