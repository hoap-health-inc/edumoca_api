package com.edumoca.soma.entities.repositories;

import com.edumoca.soma.entities.AppUser;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface UserRepository extends PagingAndSortingRepository<AppUser,Integer> {
    public Optional<AppUser> findByLoginId(String loginId);
}
