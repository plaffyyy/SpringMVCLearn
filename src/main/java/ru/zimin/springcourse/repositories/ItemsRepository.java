package ru.zimin.springcourse.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.zimin.springcourse.models.Item;
import ru.zimin.springcourse.models.Person;

import java.util.List;

public interface ItemsRepository extends JpaRepository<Item, Integer> {
    List<Item> findByOwner(Person owner);

    List<Item> findByItemName(String itemName);
}
