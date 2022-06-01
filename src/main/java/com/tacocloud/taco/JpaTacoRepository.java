package com.tacocloud.taco;

import org.springframework.context.annotation.Profile;
import org.springframework.data.repository.CrudRepository;

@Profile("jpa")
public interface JpaTacoRepository
        extends TacoRepository, CrudRepository<Taco, Long> {
}
