package com.kavarera.berijalantest.mobil;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class MobilService {
    private final MobilRepository mobilRepository;

    public List<Mobil> findAll(){
        return mobilRepository.findAll();
    }

    public List<Mobil> findByNamaContaining(String name) {
        return mobilRepository.findByNamaContaining(name);
    }

    public List<Mobil> findByTipe(MobilEnum tipe){
        return mobilRepository.findByTipe(tipe);
    }

    public List<String> findAllTipeMobil() {
        return Stream.of(MobilEnum.values())
                .map(Enum::name)
                .collect(Collectors.toList());
    }

    public Mobil save(Mobil mobil) {
        return mobilRepository.save(mobil);
    }

    public void delete(Integer id) {
        mobilRepository.deleteById(id);
    }

    public Optional<Mobil> findById(Integer id) {
        return mobilRepository.findById(id);
    }
}
