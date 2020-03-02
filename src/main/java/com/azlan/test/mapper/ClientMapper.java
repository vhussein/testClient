package com.azlan.test.mapper;

import com.azlan.test.model.Client;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ClientMapper {

    Client selectOne(long id);

    List<Client> findAll();

    void insertClient(Client client);

    List<Client> findByFirstName(String firstName);
}
