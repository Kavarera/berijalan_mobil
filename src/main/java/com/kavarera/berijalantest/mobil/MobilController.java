package com.kavarera.berijalantest.mobil;

import com.kavarera.berijalantest.auth.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mobil")
@RequiredArgsConstructor
public class MobilController {
    private final MobilService mobilService;
    private final AuthService authService;

    @GetMapping
    public ResponseEntity<List<Mobil>> getAllMobil(){
        List<Mobil> datas = mobilService.findAll();
        return ResponseEntity.ok(datas);
    }

    @GetMapping("/type/{tipemobil}")
    public ResponseEntity<List<Mobil>> getMobilByTipe(
            @PathVariable MobilEnum tipemobil){
        List<Mobil> datas = mobilService.findByTipe(tipemobil);
        return ResponseEntity.ok(datas);
    }

    @GetMapping("/type")
    public ResponseEntity<List<String>> getAllMobilType(){
        List<String> datas = mobilService.findAllTipeMobil();
        return ResponseEntity.ok(datas);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Mobil>> getMobilByContainingName(
            @RequestParam String name
    ){
        List<Mobil> datas = mobilService.findByNamaContaining(name);
        return ResponseEntity.ok(datas);
    }

    @PostMapping
    public ResponseEntity<Mobil> createMobil(@RequestBody Mobil mobil){
        mobil = mobilService.save(mobil);
        return new ResponseEntity<>(mobil, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Mobil> updateMobil(
            @PathVariable Integer id,
            @RequestBody Mobil mobil){
         var data =mobilService.findById(id)
                .orElseThrow(()->new IllegalArgumentException("Id("+id+") not found"));
         data.setNama(mobil.getNama());
         data.setHarga(mobil.getHarga());
         data.setTipe(mobil.getTipe());
         return ResponseEntity.ok(mobilService.save(data));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMobil(@PathVariable Integer id){
        mobilService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
