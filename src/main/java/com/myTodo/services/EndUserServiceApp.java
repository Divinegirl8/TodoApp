package com.myTodo.services;

import com.myTodo.data.model.EndUser;
import com.myTodo.data.repository.EndUserRepository;
import com.myTodo.exception.MyTodoException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EndUserServiceApp implements EndUserService{
    private EndUserRepository endUserRepository;

    @Override
    public EndUser findUserBy(Long id) throws MyTodoException {
        return endUserRepository.findById(id).orElseThrow(()-> new MyTodoException("id not found"));
    }
}
