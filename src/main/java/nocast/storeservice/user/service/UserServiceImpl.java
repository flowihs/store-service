package nocast.storeservice.user.service;

import lombok.RequiredArgsConstructor;
import nocast.storeservice.user.dto.ReadUserDto;
import nocast.storeservice.user.dto.UserFilter;
import nocast.storeservice.user.mapper.ReadDtoMapper;
import nocast.storeservice.user.mapper.UserPredicateMapper;
import nocast.storeservice.user.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
private final UserRepository userRepository;
private final ReadDtoMapper readDtoMapper;
private final Pageable defaultPageable = PageRequest.of(0, 20);
private final UserPredicateMapper predicateMapper;

    @Override
    public Page<ReadUserDto> findAll() {
        return userRepository.findAll(defaultPageable)
                .map(readDtoMapper::map);
    }

    @Override
    public Page<ReadUserDto> findAll(Pageable pageable, UserFilter filter) {
        return userRepository.findAll(predicateMapper.map(filter), pageable)
                .map(readDtoMapper::map);
    }

    @Override
    public Optional<ReadUserDto> findById(Long id) {
        return userRepository.findById(id)
                .map(readDtoMapper::map);
    }
}
