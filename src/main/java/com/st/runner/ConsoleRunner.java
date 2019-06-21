package com.st.runner;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import com.st.entity.Product;
import com.st.repo.ProductRepository;

@Component
public class ConsoleRunner implements ApplicationRunner{

	@Autowired
	private ProductRepository repo;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		Page<Product> page=repo.findByProdCost(5.5,PageRequest.of(0, 3));
		System.err.println(page.isEmpty());
		System.err.println(page.isFirst());
		System.err.println(page.isLast());
		System.err.println(page.hasPrevious());
		System.err.println(page.hasNext());
		System.err.println(page.getSize());
		System.err.println(page.getNumber());
		System.err.println(page.getTotalPages());
		
		page.stream().sorted((p1,p2)->p1.getProdId()-p2.getProdId()).
		map(p->p.getProdId()+"\t"+p.getProdName()+"\t"+p.getProdModel()).
		forEach(System.out::println);
	}

}
