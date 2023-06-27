package com.logistic.driverlogistic.service;

import org.springframework.data.domain.Page;

public interface DomainService<T, K> {

  K add(T t);

  void delete(long id);

  K update(T t, long id);

  K get(long id);

  Page<K> getAll(int page, int size);

}
