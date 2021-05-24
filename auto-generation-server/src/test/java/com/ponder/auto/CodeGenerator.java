package com.ponder.auto;

import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * CodeGenerator.java
 * 代码生成器
 * @author Ponder Yao
 * @version 1.0.0  2021/5/5 0:24
 */
public class CodeGenerator {

    /**
     * 获取项目根路径
     */
    private static String projectPath = System.getProperty("user.dir");

    /**
     * 执行代码生成
     * @param args
     */
    public static void main(String[] args) {
        //代码生成器
        AutoGenerator generator = new AutoGenerator();
        //添加全局配置
        generator.setGlobalConfig(newGlobalConfig());
        //添加数据源配置
        generator.setDataSource(newDataSourceConfig());
        //添加包配置
        generator.setPackageInfo(newPackageConfig());
        //添加自定义配置
        generator.setCfg(newCfg());
        //添加模板配置
        generator.setTemplate(newTemplateConfig());
        //添加策略配置
        generator.setStrategy(newStrategyConfig());
        //设置模板引擎
        generator.setTemplateEngine(new FreemarkerTemplateEngine());
        //开始生成
        generator.execute();
    }

    /**
     * 全局配置
     * @return 全局配置属性
     */
    private static GlobalConfig newGlobalConfig() {
        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setOutputDir(projectPath + "/src/main/java");
        globalConfig.setAuthor("Ponder Yao");
        globalConfig.setOpen(false);
        globalConfig.setServiceName("%sService");
        return globalConfig;
    }

    /**
     * 数据源配置
     * @return 数据源配置属性
     */
    private static DataSourceConfig newDataSourceConfig() {
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setUrl("jdbc:mysql://localhost:3308/webserver?useUnicode=true&useSSL=false&characterEncoding=utf8&serverTimezone=UTC");
        dataSourceConfig.setDriverName("com.mysql.cj.jdbc.Driver");
        dataSourceConfig.setUsername("root");
        dataSourceConfig.setPassword("103123Yzp");
        return dataSourceConfig;
    }

    /**
     * 包配置
     * @return 包配置属性
     */
    private static PackageConfig newPackageConfig() {
        PackageConfig packageConfig = new PackageConfig();
        packageConfig.setModuleName(null);
        packageConfig.setParent("com.ponder");
        return packageConfig;
    }

    /**
     * 自定义配置
     * @return 自定义配置属性
     */
    private static InjectionConfig newCfg() {
        InjectionConfig injectionConfig = new InjectionConfig() {
            @Override
            public void initMap() { }
        };
        //freemarker模板引擎
        String templatePath = "templates/mapper.xml.ftl";
        //velocity模板引擎
        //String templatePath = "templates/mapper.xml.vm";
        //自定义输出配置（优先输出）
        List<FileOutConfig> fileOutConfigList = new ArrayList<>();
        fileOutConfigList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名，如果Entity设置了前后缀，xml的名称会跟着发生变化
                return projectPath + "/src/main/resources/com/ponder/mapper" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        injectionConfig.setFileOutConfigList(fileOutConfigList);
        return injectionConfig;
    }

    /**
     * 模板配置
     * @return 模板配置属性
     */
    private static TemplateConfig newTemplateConfig() {
        TemplateConfig templateConfig = new TemplateConfig();
        templateConfig.setXml(null);
        return templateConfig;
    }

    /**
     * 策略配置
     * @return 策略配置属性
     */
    private static StrategyConfig newStrategyConfig() {
        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig.setNaming(NamingStrategy.underline_to_camel);
        strategyConfig.setColumnNaming(NamingStrategy.underline_to_camel);
        strategyConfig.setEntityLombokModel(true);
        strategyConfig.setRestControllerStyle(true);
        strategyConfig.setInclude(scanner("表名（多个英文逗号分割）").split(","));
        strategyConfig.setControllerMappingHyphenStyle(true);
        strategyConfig.setTablePrefix("m_");
        return strategyConfig;
    }

    private static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入" + tip + "：");
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotBlank(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }

}
