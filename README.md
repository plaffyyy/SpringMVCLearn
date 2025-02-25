# Проект изучение SpringMVC
## Для того чтобы запустить проект(jdbc):
1. Создайте 2 таблицы через удобную базу данных (например PostgreSQL)
2. Первая - Таблица person(id(primery key auto increment), name(varchar), age(intger), email(varchar), address(varchar), is_admin(boolean))
3. Вторая - Таблица book(book_id(primery key auto increment), person_id(foreign key), name(varchar), author(varchar), year_release(integer))
4. Проект лежит в ветке master. Склонируйте проект и откройте данную ветку. 

## Проект с использованием Hibernate вместо jdbc лежит в ветке hibernate

## Проект с использованием Data JPA и подключение к нему Hibernate лежит в ветке JPAWithDAO

Все проекты аналогичны, просто используют разные инструменты
