package com.example.transactions.trade.repository;

import com.example.transactions.trade.model.Trn;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface TrnRepository extends CrudRepository<Trn, Long> {

    List<Trn> findAllByClientNumber(Long clientNumber);

    List<Trn> findAllByType(String type);

    List<Trn> findAllByClientNumberAndType(Long clientNumber, String type);

    List<Trn> findAllByClientNumberAndCreationDateBetween(Long clientNumber, Date startDate, Date endDate);

    Long countByClientNumberAndCreationDateBetween(Long clientNumber, Date startDate, Date endDate);

    List<Trn> findAllByClientNumberIn(List<Long> clientNumber);

    List<Trn> findAllByClientNumberInAndTypeIn(List<Long> clientNumber, List<String> type);

    List<Trn> findAllByClientNumberInAndTypeNotIn(List<Long> clientNumber, List<String> type);

    List<Trn> findAllByTypeAndCreationDateBetween(String type, Date startDate, Date endDate);

    List<Trn> findAllByCreationDateBetween(Date startDate, Date endDate);

    Long countByType(String type);

    Long countByTypeAndCreationDateBetween(String type, Date startDate, Date endDate);

    Long countByCreationDateBetween(Date startDate, Date endDate);

}
