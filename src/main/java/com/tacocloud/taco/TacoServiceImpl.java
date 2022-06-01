package com.tacocloud.taco;

import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class TacoServiceImpl implements TacoService {

    private final TacoRepository tacoRepository;

    public TacoServiceImpl(TacoRepository tacoRepository) {
        this.tacoRepository = tacoRepository;
    }

    @Override
    public Taco save(Taco taco) {
        // TODO use pre-persist
        taco.setCreatedAt(Instant.now());
        return tacoRepository.save(taco);
    }

}
