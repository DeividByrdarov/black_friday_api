package com.dbardarov.blackfriday.dao.repository;

import com.dbardarov.blackfriday.dao.entity.Campaign;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CampaignRepository extends MongoRepository<Campaign, String> {
  Optional<Campaign> findByActive(boolean active);
}
