package nocast.storeservice.user.service;

import nocast.storeservice.user.dto.ReadUserDto;
import nocast.storeservice.user.dto.UserFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface UserService {

    Page<ReadUserDto> findAll();

    Page<ReadUserDto> findAll(Pageable pageable, UserFilter filter);

    Optional<ReadUserDto> findById(Long id);
}
