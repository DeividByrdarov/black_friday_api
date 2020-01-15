package com.dbardarov.blackfriday.resolver;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.dbardarov.blackfriday.dao.entity.Campaign;
import com.dbardarov.blackfriday.dao.entity.DiscountItem;
import com.dbardarov.blackfriday.dao.entity.Product;
import com.dbardarov.blackfriday.service.CampaignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ProductResolver implements GraphQLResolver<Product> {

  @Autowired
  private CampaignService campaignService;

  public Double getDiscount(Product product) {
    Optional<Campaign> optionalCampaign = this.campaignService.getActiveCampaign();
    if (optionalCampaign.isPresent()) {
      Campaign campaign = optionalCampaign.get();
      for (DiscountItem item : campaign.getItems()) {
        if (item.getProduct().getId().equals(product.getId())) {
          return item.getDiscount();
        }
      }
    }
    return null;
  }
}
