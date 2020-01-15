package com.dbardarov.blackfriday.service;

import com.dbardarov.blackfriday.dao.entity.User;
import com.dbardarov.blackfriday.dao.repository.UserRepository;
import com.dbardarov.blackfriday.enums.Role;
import com.dbardarov.blackfriday.exception.PasswordsDoNotMatchException;
import com.dbardarov.blackfriday.exception.UserNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
;import java.util.List;
import java.util.Optional;

@Service
public class UserService {
  private final UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Transactional
  public List<User> getAll() {
    return this.userRepository.findAll();
  }

  @Transactional
  public User create(String name, String username, String password, String repeatPassword, Role role) {
    if (!password.equals(repeatPassword)) {
      throw new PasswordsDoNotMatchException();
    }

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);
    String hashedPassword = encoder.encode(password);

    User user = new User();
    user.setName(name);
    user.setUsername(username);
    user.setPassword(hashedPassword);
    user.setRole(role);

    return this.userRepository.save(user);
  }

  @Transactional
  public User update(String id, String name, String username, Role role) {
    Optional<User> optionalUser = this.userRepository.findById(id);

    if (!optionalUser.isPresent()) {
      throw new UserNotFoundException();
    }

    User user = optionalUser.get();

    user.setName(name);
    user.setUsername(username);
    user.setRole(role);

    return this.userRepository.save(user);
  }

  @Transactional
  public boolean delete(String userId) {
    this.userRepository.deleteById(userId);
    return true;
  }
}
