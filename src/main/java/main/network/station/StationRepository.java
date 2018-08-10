package main.network.station;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StationRepository extends JpaRepository<StationDomain, Long> {
    @Query("SELECT s FROM StationDomain s WHERE s.name = :name")
    public StationDomain findStationByName(@Param("name") String name);

}
