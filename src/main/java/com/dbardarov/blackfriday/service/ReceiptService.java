package com.dbardarov.blackfriday.service;

import com.dbardarov.blackfriday.dao.entity.*;
import com.dbardarov.blackfriday.dao.repository.CampaignRepository;
import com.dbardarov.blackfriday.dao.repository.ProductRepository;
import com.dbardarov.blackfriday.dao.repository.ReceiptRepository;
import com.dbardarov.blackfriday.dao.repository.UserSessionRepository;
import com.dbardarov.blackfriday.exception.InvalidAmountException;
import com.dbardarov.blackfriday.exception.InvalidSessionException;
import com.dbardarov.blackfriday.exception.ProductNotFoundException;
import com.dbardarov.blackfriday.input.ReceiptItemInput;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReceiptService {
  private final ReceiptRepository receiptRepository;
  private final ProductRepository productRepository;
  private final UserSessionRepository userSessionRepository;
  private final CampaignRepository campaignRepository;

  public ReceiptService(ReceiptRepository receiptRepository, ProductRepository productRepository, UserSessionRepository userSessionRepository, CampaignRepository campaignRepository) {
    this.receiptRepository = receiptRepository;
    this.productRepository = productRepository;
    this.userSessionRepository = userSessionRepository;
    this.campaignRepository = campaignRepository;
  }

  @Transactional
  public List<Receipt> getAll() { return this.receiptRepository.findAll(); }

  @Transactional
  public Receipt create(String userSessionId, ArrayList<ReceiptItemInput> items) {
    ArrayList<ReceiptItem> receiptItems = new ArrayList<>();
    Optional<Campaign> optionalCampaign = this.campaignRepository.findByActive(true);

    double total = 0;

    for (ReceiptItemInput item : items) {
      Optional<Product> product = this.productRepository.findById(item.getProductId());

      if (!product.isPresent()) {
        throw new ProductNotFoundException();
      }
      if (item.getAmount() > product.get().getQuantity()) {
        throw new InvalidAmountException(product.get().getName());
      }

      double itemPrice = item.getAmount() * product.get().getPrice();

      if (optionalCampaign.isPresent()) {
        DiscountItem discountItem = optionalCampaign.get().getItems().stream()
            .filter(discItem -> discItem.getProduct().getId().equals(product.get().getId()))
            .findAny()
            .orElse(null);
        if (discountItem != null) {
          itemPrice -= itemPrice * discountItem.getDiscount() / 100;
        }
      }

      total += itemPrice;
      receiptItems.add(new ReceiptItem(item.getAmount(), product.get()));
    }

    for (ReceiptItem receiptItem : receiptItems) {
      Product product = receiptItem.getProduct();
      product.setQuantity(product.getQuantity() - receiptItem.getAmount());
      this.productRepository.save(product);
    }

    Optional<UserSession> userSession = this.userSessionRepository.findById(userSessionId);

    if (!userSession.isPresent()) {
      throw new InvalidSessionException();
    }

    Receipt receipt = new Receipt();

    receipt.setTotal(total);
    receipt.setUser(userSession.get().getUser());
    receipt.setItems(receiptItems);

    return this.receiptRepository.save(receipt);
  }
}
