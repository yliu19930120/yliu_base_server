package com.yliu.service;

import com.yliu.bean.PageResult;
import com.yliu.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BaseService <T,ID,V, M extends MongoRepository<T,ID>>{

    @Autowired
    protected M repository;

    protected Class<T> daoClass;

    protected Class<V> voClass;

    public BaseService() {
        this.daoClass = getDaoClass();
        this.voClass = getVoClass();
    }

    protected Class<T> getDaoClass(){
        Type superClass = getClass().getGenericSuperclass();
        Type[] actualTypeArguments = ((ParameterizedType) superClass).getActualTypeArguments();
        return (Class<T>) actualTypeArguments[0];
    }

    protected Class<V> getVoClass(){
        Type superClass = getClass().getGenericSuperclass();
        Type[] actualTypeArguments = ((ParameterizedType) superClass).getActualTypeArguments();
        return (Class<V>) actualTypeArguments[2];
    }

    public void save(V vo){
        T t = Utils.copy(vo,daoClass);
        repository.save(t);
    }

    public void save(List<V> voList){
        List<T> daoList = voList.stream().map(t-> Utils.copy(t,this.daoClass)).collect(Collectors.toList());
        repository.saveAll(daoList);
    }

    public PageResult<V> findAll(V vo, Pageable pageable){
        Example<T> example = Example.of(Utils.copy(vo,daoClass));
        Page<T> all = repository.findAll(example, pageable);
        Page<V> map = all.map(t -> Utils.copy(t, this.voClass));
        return new PageResult<>(map);
    }

    public PageResult<V> findAll(Pageable pageable){
        Page<T> all = repository.findAll(pageable);
        Page<V> map = all.map(t -> Utils.copy(t, this.voClass));
        return new PageResult<>(map);
    }

    public List<V> findAll(){
        List<T> all = repository.findAll();
        Stream<V> vStream = all.stream().map(t -> Utils.copy(t, voClass));
        return vStream.collect(Collectors.toList());
    }

    public List<V> findAll(V vo){
        Example<T> example = Example.of(Utils.copy(vo,daoClass));
        List<T> all = repository.findAll(example);
        Stream<V> vStream = all.stream().map(t -> Utils.copy(t, voClass));
        return vStream.collect(Collectors.toList());
    }

    public V findOne(V vo){
        Example<T> example = Example.of(Utils.copy(vo,daoClass));
        Optional<T> one = repository.findOne(example);
        return one.isPresent()?Utils.copy(one.get(),voClass):null;
    }
}
