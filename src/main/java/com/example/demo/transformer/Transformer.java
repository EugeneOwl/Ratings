package com.example.demo.transformer;

import com.example.demo.dto.Dto;
import com.example.demo.model.BaseEntity;

public interface Transformer<E extends BaseEntity, D extends Dto> {
    D transform(E entity);
    E transform(D entity);
}
