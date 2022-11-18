package com.edumoca.soma.entities.repositories;

import com.edumoca.soma.entities.Parent;
import com.edumoca.soma.entities.models.ParentResponse;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface ParentRepository extends PagingAndSortingRepository<Parent,Integer> {

    @Query("select new com.edumoca.soma.entities.models.ParentResponse(p.userId,p.loginId,p.profilePic,p.gender,p.firstName,p.middleName,p.lastName,p.fullName,p.emailAddress,p.motherTongue,p.occupation,'PARENT') from Parent p where p.loginId = :parentLoginId")
    public ParentResponse getParentByLoginId(@Param("parentLoginId") String parentLoginId);
}
