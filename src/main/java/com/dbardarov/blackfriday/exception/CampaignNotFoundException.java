package com.dbardarov.blackfriday.exception;

public class CampaignNotFoundException extends ValidationException {

  public CampaignNotFoundException() {
    super("Campaign with this ID can't be found!");
  }
}
