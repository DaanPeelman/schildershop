package be.vdab.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import be.vdab.entities.Product;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/spring/dao.xml")
public class JPATest {
	private EntityManager entityManager;
	
	@PersistenceContext
	public void setEntityManger(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@Test
	public void Test() {
		entityManager.find(Product.class, 1L);
	}
}
