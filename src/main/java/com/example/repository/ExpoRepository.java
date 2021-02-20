package com.example.repository;


import com.example.entity.Expo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.Optional;


public interface ExpoRepository extends CrudRepository<Expo, Integer> {
    Optional<Page<Expo>> findAll(Pageable pageable);
    Optional<Page<Expo>> findByExhibitedTrue(Pageable pageable);
    Optional<Page<Expo>> findByExhibitedTrueOrderByTicketPriceDesc(Pageable pageable);
    Optional<Page<Expo>> findByExhibitedTrueOrderByTicketPriceAsc(Pageable pageable);
    Optional<Page<Expo>> findByExhibitedTrueAndDescriptionOrDescriptionUaContaining(String theme, String themeUa, Pageable pageable);
    Optional<Page<Expo>> findByExhibitedTrueAndStartDateBetween(LocalDate startDate, LocalDate endDate, Pageable pageable);

    Optional<Expo> findByIdAndExhibitedTrueAndAmountGreaterThan(Integer id, Integer amount);

}
