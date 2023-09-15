package com.rance.generator;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.fill.Column;

import java.sql.SQLException;
import java.util.Collections;


public class CodeGenerator {
    private static final String url =  "jdbc:mysql://124.222.41.123:3306/seckill?useSSL=false&useUnicode=true&characterEncoding=UTF-8";
    private static final String projectPath = "D:\\CodeSpace\\IdeaProjects\\seckill";
    private static final String username = "root";
    private static final String password = "Long.0331";
    private static final String parentPackageName = "com.rance";
    private static final String moduleName = "seckill";
    private static final String writer = "Rance";
    private static final String outPath = projectPath + "\\src\\main\\java";
    private static final String mapperPath = projectPath + "\\src\\main\\java\\com\\rance\\seckill\\mapper\\xml\\";


    public static void main(String[] args) throws SQLException {
        String[] tableNames = {"t_user"};
        CodeGenerator.execute(tableNames);

    }

    public static void execute(String[] tableNames){
        FastAutoGenerator.create(url, username, password)
                .globalConfig(builder -> {
                    builder.author(writer)
                            .enableSwagger()
                            .fileOverride()
                            .outputDir(outPath)
                            .disableOpenDir();
                })
                .packageConfig(builder -> {
                    builder.parent(parentPackageName)
                            .moduleName(moduleName)
                            .pathInfo(Collections.singletonMap(OutputFile.xml, mapperPath));
                })
                .strategyConfig(builder -> {
                    builder.addInclude(tableNames)
                            .addTablePrefix("t_")
                            .serviceBuilder()
                            .formatServiceFileName("%sService")
                            .formatServiceImplFileName("%sServiceImpl")
                            .entityBuilder()
                            .enableChainModel()
                            .enableLombok()
                            .enableTableFieldAnnotation()
                           /* .logicDeleteColumnName("deleted")   // 默认删除属性名称(database)
                            .logicDeletePropertyName("deleted") // 默认删除属性名称(entity)
                            .versionColumnName("version")       // 乐观锁属性名(数据库)
                            .versionPropertyName("version")     // 乐观锁属性名(实体)
                            .addTableFills(new Column("create_time", FieldFill.INSERT))
                            .addTableFills(new Column("update_time", FieldFill.INSERT_UPDATE))*/
                            .controllerBuilder()
                            .enableRestStyle();
                })
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();
    }

}