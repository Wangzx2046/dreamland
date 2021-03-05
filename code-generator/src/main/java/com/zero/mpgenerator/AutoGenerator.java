package com.zero.mpgenerator;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.FileOutConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.TemplateConfig;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: Main
 * @Description: 生成器主方法*
 * @Author: wzx
 * @Date: 2019-08-20 14:56
 * @Version 1.0.0
 **/
// 演示例子，执行 main 方法控制台输入模块表名回车自动生成对应项目目录中
public class AutoGenerator {


    public static void main(String[] args) {

        Map<String, String> tableNames = new HashMap<String, String>();
        tableNames.put("realtime_record_pv_details".toLowerCase(), "网关采集实时数据");


     //   tableNames.put("zkb_article".toLowerCase(), "ZK8-大家谈");

  //      tableNames.put("tool_email_config".toLowerCase(), "邮箱配置");
 //       tableNames.put("sys_quartz_job".toLowerCase(), "系统定时任务");
//        tableNames.put("sys_quartz_log".toLowerCase(), "系统定时任务日志表");
  //      tableNames.put("sys_user".toLowerCase(), "系统用户表");
//        tableNames.put("sys_menu".toLowerCase(), "系统菜单表");
//        tableNames.put("sys_dict".toLowerCase(), "系统字典表");
 //      tableNames.put("sys_role".toLowerCase(), "系统角色表");
//        tableNames.put("sys_roles_menus".toLowerCase(), "系统角色表");
//        tableNames.put("sys_role".toLowerCase(), "系统角色表");
       /* tableNames.put("sys_dept".toLowerCase(), "系统部门表");
        tableNames.put("sys_dict".toLowerCase(), "系统数据字典表");
        tableNames.put("sys_dict_detail".toLowerCase(), "系统数据字典详情表");
        tableNames.put("sys_job".toLowerCase(), "系统岗位表");
        tableNames.put("sys_roles_depts".toLowerCase(), "系统角色部门关联表");
        tableNames.put("sys_users_roles".toLowerCase(), "系统用户角色关系表");
        tableNames.put("sys_users_jobs".toLowerCase(), "系统用户岗位关系表");
        tableNames.put("sys_log".toLowerCase(), "系统日志表");
        tableNames.put("sys_role".toLowerCase(), "系统角色表");
        tableNames.put("sys_role".toLowerCase(), "系统角色表");*/


//        tableNames.put("system_file".toLowerCase(), "上传文件管理表");
        //
//        tableNames.put("system_permi".toLowerCase(), "系统权限表");
        // tableNames.put("system_role".toLowerCase(), "系统角色表");
//        tableNames.put("system_role_permi".toLowerCase(), "系统角色和权限的关系表");
//        tableNames.put("system_user_role".toLowerCase(), "系统用户角色关系表");
        // tableNames.put("system_log".toLowerCase(), "系统日志表");
        //    tableNames.put("system_dict_detail".toLowerCase(), "系统数据字典详情表");
        //   tableNames.put("system_dict".toLowerCase(), "系统数据字典表");
        for (String key : tableNames.keySet()) {
            crateFile("system", key);
        }
    }


    public static void crateFile(String modelName, String tableName) {


        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
//        dsc.setUrl("jdbc:mysql://47.105.130.1:3306/dreamland?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&allowMultiQueries=true");
//        // dsc.setSchemaName("public");
//        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
//        dsc.setUsername("wzx");
//        dsc.setPassword("As123456");


        dsc.setUrl("jdbc:mysql://127.0.0.1:3306/dreamland?serverTimezone=UTC&useUnicode=true&characterEncoding=utf8&autoReconnect=true&useSSL=true&zeroDateTimeBehavior=convertToNull");
        // dsc.setSchemaName("public");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("123123");


        // 代码生成器
        com.baomidou.mybatisplus.generator.AutoGenerator mpg = new com.baomidou.mybatisplus.generator.AutoGenerator();
        mpg.setDataSource(dsc);

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = "d:/code/mpgenerator";
        //String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath + "/src/main/java");
        gc.setAuthor("Wang.zx");
        gc.setOpen(false);
        gc.setSwagger2(true); //实体属性 Swagger2 注解
        mpg.setGlobalConfig(gc);


        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setModuleName(modelName);
        pc.setParent("com.zero.dreamland");
        mpg.setPackageInfo(pc);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };

        // 如果模板引擎是 freemarker
        String templatePath = "/templates/mapper.xml.ftl";


        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return projectPath + "/src/main/resources/mapper/" + pc.getModuleName()
                        + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        /*
        cfg.setFileCreate(new IFileCreate() {
            @Override
            public boolean isCreate(ConfigBuilder configBuilder, FileType fileType, String filePath) {
                // 判断自定义文件夹是否需要创建
                checkDir("调用默认方法创建的目录");
                return false;
            }
        });
        */
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();

        // 配置自定义输出模板
        //指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
        // templateConfig.setEntity("templates/entity2.java");
        // templateConfig.setService();
        // templateConfig.setController();

        templateConfig.setXml(null);
        mpg.setTemplate(templateConfig);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setSuperEntityClass("com.zero.dreamland.biz.common.base.BaseModel");
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        strategy.entityTableFieldAnnotationEnable(true);
        // 公共父类
        strategy.setSuperControllerClass("com.zero.dreamland.api.controller.BaseController");
        // 写于父类中的公共字段
        String[] arr = {"id", "create_by", "update_by", "update_date", "create_date", "del_flag"};
        strategy.setSuperEntityColumns(arr);
        strategy.setInclude(tableName);
        strategy.setControllerMappingHyphenStyle(true);
        // strategy.setTablePrefix(pc.getModuleName() + "_");//去掉表前缀
        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }

}
