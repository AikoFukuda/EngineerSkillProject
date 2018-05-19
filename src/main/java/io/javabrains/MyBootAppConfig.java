package io.javabrains;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

@Configuration
public class MyBootAppConfig {
	@Bean
//	テンプレートの具体的な処理を担当する部分
	public ClassLoaderTemplateResolver templateResolver() {
		ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
//		インスタンスを作成
		templateResolver.setPrefix("templates/");
//		templatesフォルダ内から検索できるようになる
		templateResolver.setCacheable(false);
//		キャッシュさせないようにする
		templateResolver.setSuffix(".html");
		templateResolver.setTemplateMode("HTML5");
		templateResolver.setCharacterEncoding("UTF-8");
		return templateResolver;
	}
	
	@Bean
	public SpringTemplateEngine templateEngine() {
		SpringTemplateEngine templateEngine = new SpringTemplateEngine();
//		インスタンス化している
		templateEngine.setTemplateResolver(templateResolver());
//		作成したインスタンスにテンプレートリゾルバを設定
		templateEngine.addDialect(new MyDialect());
//		MyDilaectを入れ込む
		return templateEngine;
		}
}
