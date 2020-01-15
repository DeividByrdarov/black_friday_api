package com.dbardarov.blackfriday.service;

import com.dbardarov.blackfriday.dao.entity.User;
import com.dbardarov.blackfriday.dao.entity.UserSession;
import com.dbardarov.blackfriday.dao.repository.UserRepository;
import com.dbardarov.blackfriday.dao.repository.UserSessionRepository;
import com.dbardarov.blackfriday.exception.InvalidCredentialsException;
import com.dbardarov.blackfriday.exception.InvalidSessionException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

@Service
public class UserSessionService {
  private final UserSessionRepository userSessionRepository;
  private final UserRepository userRepository;

  public UserSessionService(UserSessionRepository userSessionRepository, UserRepository userRepository) {
    this.userSessionRepository = userSessionRepository;
    this.userRepository = userRepository;
  }

  @Transactional
  public boolean delete(String userSessionId) {
    Optional<UserSession> userSession = this.userSessionRepository.findById(userSessionId);

    if(!userSession.isPresent()) {
      throw new InvalidSessionException();
    }

    this.userSessionRepository.deleteById(userSessionId);

    return true;
  }

  @Transactional
  public UserSession create(String username, String password) {
    Optional<User> user = this.userRepository.findByUsername(username);

    if (!user.isPresent()) {
      throw new InvalidCredentialsException();
    }

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);
    if (!encoder.matches(password, user.get().getPassword())) {
      throw new InvalidCredentialsException();
    }

    Calendar calendar = Calendar.getInstance();
    calendar.setTime(new Date());
    calendar.add(Calendar.HOUR, 1);

    UserSession userSession = new UserSession();
    userSession.setUser(user.get());
    userSession.setCreatedAt(new Date());
    userSession.setExpireAt(calendar.getTime());

    return this.userSessionRepository.save(userSession);
  }
}
