package com.cosmoplat.jtx.erp.purchase;

import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.data.CellRenderData;
import com.deepoove.poi.data.Cells;
import com.deepoove.poi.data.MergeCellRule;
import com.deepoove.poi.data.RowRenderData;
import com.deepoove.poi.data.Rows;
import com.deepoove.poi.data.Tables;
import com.deepoove.poi.data.Texts;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 文本测试
 * </p>
 *
 * @Author: Liziba
 * @Date: 2021/6/24 21:49
 */
public class DocTest {

    public static void main(String[] args) throws IOException {
        String filePath = "D:\\file\\text.docx";
        String targetPath = "D:\\file\\textTestTarget.docx";
        // 表头
        RowRenderData tableHead1 = Rows.of("安徽焦甜香生物科技公司香精香料配制单", "性别", "地址", "微信公众号").center().textFontSize(20).bgColor("4472c4").create();

        RowRenderData tableHead = Rows.of("姓名", "性别", "地址", "微信公众号").center().bgColor("4472c4").create();
        // 第一行
        RowRenderData row1 = Rows.create("张三", "男", "广东深圳", "liziba_98");
        // 第二行
        RowRenderData row2 = Rows.create("李四", "男", "广东2深圳", "lizi5sba_98");
        RowRenderData row3 = Rows.create("李四1", "女", "广东3深圳", "liz5iba_98");
        RowRenderData row7 = Rows.create("李四2", "男", "广东4深圳", "lizib5a_98");
        RowRenderData row4 = Rows.create("李四3", "男", "广东5深圳", "liziba_98");
        RowRenderData row5 = Rows.create("李四", "男", "广东深圳", "liziba_98");
        RowRenderData row6 = Rows.create("李四", "男", "广东深圳", "liziba_98");

        RowRenderData[] list= new RowRenderData[5];
        list[0]=(tableHead1);
        list[1]=(tableHead);
        list[2]=(row1);
        list[3]=(row3);
        list[4]=(row4);

        // 合并第一行和第二行的第二列与第三列
//        MergeCellRule rule = MergeCellRule.builder().map(MergeCellRule.Grid.of(1, 1), MergeCellRule.Grid.of(2, 1))
//                .map(MergeCellRule.Grid.of(1, 2), MergeCellRule.Grid.of(2, 2)).build();
        MergeCellRule rule = MergeCellRule.builder()
                .map(MergeCellRule.Grid.of(0, 0), MergeCellRule.Grid.of(0, 3)).build();
        XWPFTemplate template = XWPFTemplate.compile(filePath).render(
                new HashMap<String, Object>() {
                    {
                        put("title", "安徽焦甜香生物科技公司香精香料配制单");
                        put("version","2023第一版");
                        put("author", Texts.of("Liziba").color("000000").create());
                        put("link", Texts.of("百度").link("https://baidu.com").create());
                        put("anchor", Texts.of("anchortxt").anchor("appendix1").create());
                        put("table", Tables.of(list).mergeRule(rule).center().create());
                    }
                });


        template.writeAndClose(new FileOutputStream(targetPath));
    }

}
