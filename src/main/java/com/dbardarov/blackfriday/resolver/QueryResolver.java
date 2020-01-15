package com.dbardarov.blackfriday.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.dbardarov.blackfriday.dao.entity.Campaign;
import com.dbardarov.blackfriday.dao.entity.Product;
import com.dbardarov.blackfriday.dao.entity.Receipt;
import com.dbardarov.blackfriday.dao.entity.User;
import com.dbardarov.blackfriday.service.CampaignService;
import com.dbardarov.blackfriday.service.ProductService;
import com.dbardarov.blackfriday.service.ReceiptService;
import com.dbardarov.blackfriday.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class QueryResolver implements GraphQLQueryResolver {

  @Autowired
  public ProductService productService;
  @Autowired
  public UserService userService;
  @Autowired
  public ReceiptService receiptService;
  @Autowired
  public CampaignService campaignService;

  public List<Product> products() {
    return this.productService.getAll();
  }

  public List<User> users() { return this.userService.getAll(); }

  public List<Receipt> receipts() { return this.receiptService.getAll(); }

  public List<Campaign> campaigns() { return this.campaignService.getAll(); }

  public Optional<Campaign> activeCampaign() { return this.campaignService.getActiveCampaign(); }
}
