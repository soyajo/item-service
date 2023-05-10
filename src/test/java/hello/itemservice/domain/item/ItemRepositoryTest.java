package hello.itemservice.domain.item;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ItemRepositoryTest {
    ItemRepository itemRepository = new ItemRepository();

    @AfterEach
    void afterEach() {
        itemRepository.clearStore();
    }

    @Test
    public void save() {
        // given - 설정
        Item item = new Item("itemA", 10000, 10);
        // when - 로직실행
        Item saveItem = itemRepository.save(item);

        // then - 결과
        Item findItem = itemRepository.findById(item.getId());
        assertThat(findItem).isEqualTo(saveItem);
    }

    @Test
    public void findAll() {
        // given - 설정
        Item item1 = new Item("item1", 10000, 10);
        Item item2 = new Item("item2", 20000, 20);

        itemRepository.save(item1);
        itemRepository.save(item2);


        // when - 로직실행
        List<Item> items = itemRepository.findAll();


        // then - 결과
        assertThat(items.size()).isEqualTo(2);

    }

    @Test
    public void updateItem() {
        // given - 설정
        Item item1 = new Item("item1", 10000, 10);
        Item savedItem = itemRepository.save(item1);
        Long itemId = savedItem.getId();

        // when - 로직실행
        Item newItem = new Item("newItem", 20000, 20);
        itemRepository.update(itemId, newItem);


        // then - 결과
        Item findItem = itemRepository.findById(itemId);

        assertThat(findItem.getPrice()).isEqualTo(newItem.getPrice());
        assertThat(findItem.getItemName()).isEqualTo(newItem.getItemName());
        assertThat(findItem.getQuantity()).isEqualTo(newItem.getQuantity());


    }
}
