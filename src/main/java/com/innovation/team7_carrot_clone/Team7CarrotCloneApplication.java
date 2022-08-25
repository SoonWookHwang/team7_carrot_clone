package com.innovation.team7_carrot_clone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.MultipartProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

@EnableJpaAuditing
@SpringBootApplication
public class Team7CarrotCloneApplication {

	public static void main(String[] args) {
		SpringApplication.run(Team7CarrotCloneApplication.class, args);
	}

	@Bean
	public CommonsMultipartResolver multipartResolver() {
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
		multipartResolver.setDefaultEncoding("UTF-8"); // 파일 인코딩 설정
		multipartResolver.setMaxUploadSizePerFile(5 * 1024 * 1024); // 파일당 업로드 크기 제한 (5MB)
		return multipartResolver;
	}

	@Bean(name = DispatcherServlet.MULTIPART_RESOLVER_BEAN_NAME)
	public StandardServletMultipartResolver multipartResolver(MultipartProperties multipartProperties) {
		StandardServletMultipartResolver multipartResolver = new StandardServletMultipartResolver();
		multipartResolver.setStrictServletCompliance(true);
		multipartResolver.setResolveLazily(multipartProperties.isResolveLazily());
		return multipartResolver;
	}

//	@Bean
//		public CommandLineRunner demo(PostRepository postRepository) {
//			return (args) -> {
//				Post post = new Post("전자렌지 팔아요", "사용한지 6개월 된 거의 새 제품 입니다.", "전자/가전", "25000원", "/images/제목없음.png");
//				postRepository.save(post);
//				List<Post> postList = postRepository.findAll(); // 조회
//				for(Post p : postList){
//					System.out.println(p.getTitle());
//					System.out.println(p.getContents());
//					System.out.println(p.getCategory());
//					System.out.println(p.getPrice());
//					System.out.println(p.getImageUrl());
//				}
//			};
//		}
}
