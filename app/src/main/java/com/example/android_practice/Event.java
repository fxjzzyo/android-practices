package com.example.android_practice;

import java.util.List;

/**
 * Created by fanlulin on 2019-07-02.
 */
public class Event
{
    /** 列表加载事件 */
    public static class ItemListEvent
    {
        private List<Item> items;

        public ItemListEvent(List<Item> items)
        {
            this.items = items;
        }

        public List<Item> getItems()
        {
            return items;
        }
    }

}
