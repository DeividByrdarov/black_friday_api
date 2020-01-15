package com.dbardarov.blackfriday.resolver;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.dbardarov.blackfriday.dao.entity.*;
import com.dbardarov.blackfriday.enums.Role;
import com.dbardarov.blackfriday.input.DiscountItemInput;
import com.dbardarov.blackfriday.input.ReceiptItemInput;
import com.dbardarov.blackfriday.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class MutationResolver implements GraphQLMutationResolver {

  @Autowired
  private ProductService productService;
  @Autowired
  private UserService userService;
  @Autowired
  private UserSessionService userSessionService;
  @Autowired
  private ReceiptService receiptService;
  @Autowired
  private CampaignService campaignService;

  // Product
  public Product createProduct(String name, String description, double price, double minPrice, int quantity) {
    return this.productService.create(name, description, price, minPrice, quantity);
  }

  public Product updateProduct(String id, String name, String description, double price, double minPrice, int quantity) {
    return this.productService.update(id, name, description, price, minPrice, quantity);
  }

  public boolean deleteProduct(String productId) {
    return this.productService.delete(productId);
  }

  // User
  public User createUser(String name, String username, String password, String repeatPassword, Role role) {
    return this.userService.create(name, username, password, repeatPassword, role);
  }

  public User updateUser(String id,String name, String username, Role role) {
    return this.userService.update(id, name, username, role);
  }

  public boolean deleteUser(String userId) { return this.userService.delete(userId); }

  // User Session
  public UserSession createUserSession(String username, String password) {
    return this.userSessionService.create(username, password);
  }

  public boolean deleteUserSession(String userSessionId) {
    return this.userSessionService.delete(userSessionId);
  }

  // Receipt
  public Receipt createReceipt(String userSessionId, ArrayList<ReceiptItemInput> items) {
    return this.receiptService.create(userSessionId, items);
  }

  // Campaign
  public Campaign createCampaign(String name, String description, boolean active, ArrayList<DiscountItemInput> items) {
    return this.campaignService.create(name, description, active, items);
  }

  public Campaign updateCampaign(String id, String name, String description, boolean active, ArrayList<DiscountItemInput> items) {
    return this.campaignService.update(id, name, description, active, items);
  }

  public boolean deleteCampaign(String campaignId) {
    return this.campaignService.delete(campaignId);
  }

}
