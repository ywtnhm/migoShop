package com.migo.controller;

import com.migo.pojo.EasyUIDataGridResult;
import com.migo.pojo.MigoResult;
import com.migo.pojo.TbItem;
import com.migo.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Author  知秋
 * Created by kauw on 2016/9/23.
 */
@Controller
public class ItemController {
    @Autowired
    private ItemService itemService;

    @RequestMapping("/item/{itemId}")
    @ResponseBody
    private TbItem getItemById(@PathVariable long itemId){
        TbItem item=itemService.getItemById(itemId);
        return item;
    }
    @RequestMapping({"/item/list"})
    @ResponseBody
    public EasyUIDataGridResult getItemList(Integer page,Integer rows){
        EasyUIDataGridResult result=itemService.getItemList(page,rows);
        return result;
    }

    @RequestMapping(value = "/item/save",method = RequestMethod.POST)
    @ResponseBody
    public MigoResult createItem(TbItem item,String desc,String itemParams){
        MigoResult result=itemService.CreateItem(item,desc,itemParams);
        return  result;
    }

    @RequestMapping("/page/item/{itemId}")
    public String showItemParam(@PathVariable long itemId, Model model){
        String html=itemService.getItemParamHtml(itemId);
        model.addAttribute("migohtml",html);
        return "migo";
    }
}
