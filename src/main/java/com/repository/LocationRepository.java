package com.repository;

import com.entity.Location;
import com.entity.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface LocationRepository extends JpaRepository<Location, Long> {

    @Query(value = "Select * from location inner JOIN evaluation on evaluation.id_location = location.id " +
            "where evaluation.id_user = ?1", nativeQuery = true)
    public List<Location> getAllLocationByUser(Long id);

    public Page<Location> findAllByIdPlaceCategory(Long id, Pageable pageable);

    public List<Location> findByIdPlaceCategory(Long id);

    @Query(value = "Select * from location where id_place_category = ?1 LIMIT(10);", nativeQuery = true)
    public List<Location> findTop10ByIdPlaceCategory(Long id);

    @Query(value = "SELECT location.* FROM location INNER JOIN evaluation ON location.id = evaluation.id_location WHERE evaluation.id_user = ?2 AND evaluation.id_location NOT IN (SELECT evl.id_location FROM evaluation AS evl Where evl.id_user = ?1 ) ORDER BY evaluation.score DESC LIMIT 10;" , nativeQuery = true)
    public List<Location> getLocationRecommend(Long idUserRecommend, Long idUser2);

    @Query(value = "SELECT location.* FROM location INNER JOIN evaluation ON location.id = evaluation.id_location ORDER BY evaluation.score DESC LIMIT 10;" , nativeQuery = true)
    public List<Location> getLocationRecommendWithHeightScore();

    @Query(value = "SELECT location.* from location  order BY location.id DESC  limit(1);", nativeQuery = true)
    public Location findLastestLocation();

    @Query(value = "SELECT location.* from location INNER JOIN place_category ON location.id_place_category = place_category.id INNER JOIN place_type ON place_category.id_place_type = place_type.id WHERE place_type.id = ?1 order BY location.id DESC limit(1);", nativeQuery = true)
    public Location findLastestLocationByIdType(Long idType);

}
