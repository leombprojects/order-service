package org.leombprojects.order.services.mappers;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class Mapper {
    private final ModelMapper modelMapper;

    public <T> T convertToDto(Object entityClass, Class<T> dtoClass){
        Class<T> clazz = (Class<T>) modelMapper.map(entityClass, dtoClass);
        return (T) clazz;
    }
    public <T> List<T> convertToDto(List<?> listEntityClass, Class<T> dtoClass){
        return (List<T>)  listEntityClass.stream().map(t -> {
            return modelMapper.map(t, dtoClass);
        }).collect(Collectors.toList());
    }

    public <T> T convertToDto(Page listEntityClass, Class<T> dtoClass){
        Object map = modelMapper.map(listEntityClass, dtoClass);
        return (T) map;
    }
 }
