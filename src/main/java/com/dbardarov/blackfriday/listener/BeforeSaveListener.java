package com.dbardarov.blackfriday.listener;

import com.dbardarov.blackfriday.exception.ValidationException;
import org.springframework.context.ApplicationListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeSaveEvent;
import org.springframework.stereotype.Component;

import javax.validation.Validation;
import javax.validation.Validator;

@Component
public class BeforeSaveListener implements ApplicationListener<BeforeSaveEvent> {
  @Override
  public void onApplicationEvent(BeforeSaveEvent beforeSaveEvent) {
    Validator validator = Validation.buildDefaultValidatorFactory()
        .getValidator();
    validator.validate(beforeSaveEvent.getSource())
        .forEach(validation -> {
          throw new ValidationException(validation.getMessage());
        });
  }
}
