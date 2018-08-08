package main.network.station;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StationRepository extends JpaRepository<StationImpl, Long> {
    @Query("SELECT s FROM StationImpl s WHERE s.name = :name")
    public StationImpl findStationByName(@Param("name") String name);

}
