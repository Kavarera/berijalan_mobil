package com.kavarera.berijalantest.mobil;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MobilRepository extends JpaRepository<Mobil,Integer> {
    List<Mobil> findByNamaContaining(String name);
    List<Mobil> findByTipe(MobilEnum tipe);


}
