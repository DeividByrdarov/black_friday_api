package com.dbardarov.blackfriday.dao.repository;

import com.dbardarov.blackfriday.dao.entity.Receipt;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReceiptRepository extends MongoRepository<Receipt, String> {
}
