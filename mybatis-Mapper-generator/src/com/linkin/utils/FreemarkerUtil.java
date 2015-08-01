package com.linkin.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import com.linkin.models.Querys;
import com.linkin.models.Table;

import templates.ResourceHolder;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class FreemarkerUtil {
	
	public static void output(Table... tables) {
		for (Table table : tables) {
			output(table);
		}
	}

	public static void output(Table table) {
		try {
			Map<String, Object> root = new HashMap<String, Object>();
			root.put("table", table);

			Querys querys = table.getQuerys();
			if (querys != null) {
				root.put("associations", querys.getAssociations());
				root.put("querys", querys.getQuerys());
			}

			FreemarkerUtil.output("JavaMapperTemplate.ftl", root,
					table.getEntityName() + "Mapper.java");
			FreemarkerUtil.output("MysqlMapperTemplate.ftl", root,
					table.getEntityName() + "Mapper.xml");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private static void output(String ftlName, Map<String, Object> root,
			String outFile) throws Exception {
		try {
			File file = new File(outputPath(outFile));
			if (file.exists())
				file.delete();

			if (!file.getParentFile().exists()) {
				file.getParentFile().mkdirs();
			}

			FileWriter out = new FileWriter(file);
			Template template = getTemplate(ftlName);
			template.process(root, out); // 模版输出
			out.flush();
			out.close();
		} catch (TemplateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static Template getTemplate(String ftlName) throws Exception {
		try {
			Configuration cfg = new Configuration();
			cfg.setEncoding(Locale.CHINA, "utf-8");
			cfg.setDirectoryForTemplateLoading(new File(StringUtil
					.substringAfter(ResourceHolder.path2(), "file:/")));
			Template temp = cfg.getTemplate(ftlName);
			return temp;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	private static String outputPath(String fileName) {
		return StringUtil.substringBetween(ResourceHolder.path2(), "file:/",
				"/Freemarker") + "/Freemarker/output/MysqlMapper/" + fileName;
	}
}
