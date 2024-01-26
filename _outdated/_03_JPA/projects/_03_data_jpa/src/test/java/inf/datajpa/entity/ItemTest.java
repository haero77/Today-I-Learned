package inf.datajpa.entity;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import inf.datajpa.repository.ItemRepository;

@SpringBootTest
class ItemTest {

	@Autowired
	private ItemRepository itemRepository;

	@Test
	void save() {
		Item item = new Item("A");
		itemRepository.save(item);
	}

}