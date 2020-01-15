package com.dbardarov.blackfriday.service;

import com.dbardarov.blackfriday.dao.entity.Campaign;
import com.dbardarov.blackfriday.dao.entity.DiscountItem;
import com.dbardarov.blackfriday.dao.entity.Product;
import com.dbardarov.blackfriday.dao.repository.CampaignRepository;
import com.dbardarov.blackfriday.dao.repository.ProductRepository;
import com.dbardarov.blackfriday.exception.CampaignNotFoundException;
import com.dbardarov.blackfriday.exception.DiscountTooBigException;
import com.dbardarov.blackfriday.input.DiscountItemInput;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CampaignService {
  private final CampaignRepository campaignRepository;
  private final ProductRepository productRepository;

  public CampaignService(CampaignRepository campaignRepository, ProductRepository productRepository) {
    this.campaignRepository = campaignRepository;
    this.productRepository = productRepository;
  }

  @Transactional
  public Optional<Campaign> getActiveCampaign() {
    return this.campaignRepository.findByActive(true);
  }

  @Transactional
  public List<Campaign> getAll() { return this.campaignRepository.findAll(); }

  @Transactional
  public boolean delete(String campaignId) {
    this.campaignRepository.deleteById(campaignId);
    return true;
  }

  @Transactional
  public Campaign update(String id, String name, String description, boolean active, ArrayList<DiscountItemInput> items) {
    ArrayList<DiscountItem> discountItems = this.inputsToItems(items);

    if (active) {
      this.deactivateActiveCampaign();
    }

    Optional<Campaign> optionalCampaign = this.campaignRepository.findById(id);
    if (!optionalCampaign.isPresent()) {
      throw new CampaignNotFoundException();
    }

    Campaign campaign = optionalCampaign.get();

    campaign.setName(name);
    campaign.setDescription(description);
    campaign.setActive(active);
    campaign.setItems(discountItems);

    return this.campaignRepository.save(campaign);
  }

  @Transactional
  public Campaign create(String name, String description, boolean active, ArrayList<DiscountItemInput> items) {
    ArrayList<DiscountItem> discountItems = this.inputsToItems(items);

    if (active) {
      this.deactivateActiveCampaign();
    }

    Campaign campaign = new Campaign();

    campaign.setName(name);
    campaign.setDescription(description);
    campaign.setActive(active);
    campaign.setItems(discountItems);

    return this.campaignRepository.save(campaign);
  }

  private void deactivateActiveCampaign() {
    Optional<Campaign> optionalCampaign = this.campaignRepository.findByActive(true);
    if (optionalCampaign.isPresent()) {
      Campaign activeCampaign = optionalCampaign.get();
      activeCampaign.setActive(false);
      this.campaignRepository.save(activeCampaign);
    }
  }

  private ArrayList<DiscountItem> inputsToItems(ArrayList<DiscountItemInput> items) {
    ArrayList<DiscountItem> discountItems = new ArrayList<>();

    for (DiscountItemInput item : items) {
      Optional<Product> optionalProduct = this.productRepository.findById(item.getProductId());
      if (optionalProduct.isPresent()) {
        Product product = optionalProduct.get();
        if (product.getMinPrice() > product.getPrice() - (product.getPrice() * (item.getDiscount() / 100))) {
          throw new DiscountTooBigException(product);
        }
        discountItems.add(new DiscountItem(item.getDiscount(), product));
      }
    }

    return discountItems;
  }
}
