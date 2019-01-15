package vng.repository;


import org.springframework.data.repository.PagingAndSortingRepository;

import vng.entity.Message;

public interface MessageRepository extends PagingAndSortingRepository<Message, Integer> {

}