package top.kjwang.auth.utils;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Collections;

/**
 * @author kjwang
 * @date 2023/7/25 17:50
 * @description MyBatis Plus 生成工具类
 */
public class AutoGeneratorUtils {

	public static void main(String[] args) {
		FastAutoGenerator.create("jdbc:mysql://localhost:3306/spring_security?zeroDateTimeBehavior=convertToNull&useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai&autoReconnect=true",
						"root",
						"12345678")
				.globalConfig(builder -> {
					builder.author("kjwang") // 设置作者
							.fileOverride() // 覆盖已生成文件
							.outputDir("/Users/wangkaijie/Desktop/Spring Security 学习/2-auth-database/src/main/java"); // 指定 Java 代码输出目录
				})
				.packageConfig(builder -> {
					builder.parent("top.kjwang.auth") // 设置父包名
							.pathInfo(Collections.singletonMap(OutputFile.xml, "/Users/wangkaijie/Desktop/Spring Security 学习/2-auth-database/src/main/resources/mapper")); // 设置 mapperXml 生成路径
				})
				.strategyConfig(builder -> {
					builder.addInclude("user") // 设置需要生成的表名
							.addTablePrefix("t_", "c_"); // 设置过滤表前缀
				})
				.templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
				.execute();
	}
}