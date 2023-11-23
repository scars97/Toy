package org.example.mealcrud.service;

import org.example.mealcrud.model.dto.*;

import java.util.Map;

public interface InsertAll {

    Map<String, Object> insertAll(MultipleDtoRequest multipleDto);
}
