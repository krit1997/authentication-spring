package com.example.authentication.spring.boots.services.auth;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.authentication.spring.boots.dtos.auth.PaginateRequest;
import com.example.authentication.spring.boots.entities.User;
import com.example.authentication.spring.boots.repositories.UserRepository;
import com.example.authentication.spring.boots.services.mapResponse.ToPaginatedResponse;
import com.example.authentication.spring.boots.services.mapResponse.response.PaginatedResponse;

@Service
public class PaginateUsersService {

    private final UserRepository userRepository;
    private final ToPaginatedResponse pagination;

    public PaginateUsersService(UserRepository userRepository, ToPaginatedResponse pagination) {
        this.userRepository = userRepository;
        this.pagination = pagination;
    }

    public PaginatedResponse<User> execute(PaginateRequest dto) {
        Pageable pageable = PageRequest.of(
                dto.getPage() - 1,
                dto.getLimit(),
                Sort.by(Sort.Direction.fromString(dto.getOrder()), dto.getSortByColumn()));

        String keyword = dto.getSearch() != null ? dto.getSearch() : "";

        Page<User> data = userRepository.findByColumnPage(
                dto.getSearchByColumn(), "%" + keyword + "%", pageable);
        return pagination.execute(data);
    }
}
