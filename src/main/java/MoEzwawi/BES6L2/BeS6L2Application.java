package MoEzwawi.BES6L2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BeS6L2Application {

	public static void main(String[] args) {

		SpringApplication.run(BeS6L2Application.class, args);
		/*BlogPost b1 = new BlogPost(BlogPostCategory.SPRING_DATA,"CIAO");
		String spCat = "SPRING4_DATA";
		if(b1.getCategory().equals(BlogPostCategory.valueOf(spCat))){
			System.err.println("CAMPIONIIIIIII");
		}*/
	}

}
