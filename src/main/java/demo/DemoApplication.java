package demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.gemfire.CacheFactoryBean;
import org.springframework.data.gemfire.LocalRegionFactoryBean;
import org.springframework.data.gemfire.repository.config.EnableGemfireRepositories;

import com.gemstone.gemfire.cache.GemFireCache;

//@EnableGemfireRepositories
@ImportResource("classpath:cache-config.xml")
@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

//    @Bean
//    CacheFactoryBean cacheFactoryBean() {
//        return new CacheFactoryBean();
//    }

//    @Bean
//    LocalRegionFactoryBean<String, Order> localRegionFactory(final GemFireCache cache) {
//        return new LocalRegionFactoryBean<String, Order>() {
//
//            {
//                setCache(cache);
//                setName("hello");
//                setClose(false);
//            }
//        };
//    }

    @Autowired
    OrderRepository OrderRepository;

    @Override
    public void run(String... strings) throws Exception {
    	
    	
    	Order order1 = new Order("EE5A134C-50D3-3500-BB8B-4BCE8B7E29CB", "MC",
    			"373682612507139", "Wal-Mart", 89.45, "883-5634 Quam Ave", 
    			"Omaha", 78180, "NE", "United States", -72.06302, -164.8027,
    			1450177420);
    	Order order2 = new Order("3156B90F-0381-F693-B8F0-E1CBADD71C35", "VISA", 
    			"5154281641268179", "Kroger", 4.15, "Ap #678-1399 Sollicitudin Rd.",
    			"Wichita", 59699, "KS", "United States", 4.79957, 123.26522,
    			1409751816);
    	Order order3 = new Order("F6C344E8-582F-48C0-F59B-F467223F9F9D", "AmEx", 
    			"5374945988841358", "Costco", 11.17, "3398 Nam St.", 
    			"Burlington", 16861, "VT", "United States", 47.28902, -123.88272,
    			1452918744);

        System.out.println("Before linking up with Gemfire...");
        for (Order Order : new Order[] { order1, order2, order3 }) {
            System.out.println("\t" + Order);
        }

        OrderRepository.save(order1);
        OrderRepository.save(order2);
        OrderRepository.save(order3);

        System.out.println("Lookup each Order by transactionId...");
        for (String name : new String[] { order1.getTransactionId(), 
        		order2.getTransactionId(),
        		order3.getTransactionId() }) {
            System.out.println("\t" + OrderRepository.findByTransactionId(name));
        }

        System.out.println("Order (Amount over 50):");
        for (Order Order : OrderRepository.findByAmountGreaterThan(50)) {
            System.out.println("\t" + Order);
        }

        System.out.println("Order (Amount less than 50):");
        for (Order Order : OrderRepository.findByAmountLessThan(50)) {
            System.out.println("\t" + Order);
        }

        System.out.println("Order (Amount between 12 and 20):");
        for (Order Order : OrderRepository.findByAmountGreaterThanAndAmountLessThan(12, 20)) {
            System.out.println("\t" + Order);
        }
    }

    public static void main(String[] args) throws IOException {
        SpringApplication.run(DemoApplication.class, args);
    }
}