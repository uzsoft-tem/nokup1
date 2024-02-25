package com.alibou.security.controller;

import com.alibou.security.dto.ApiResponse;
import com.alibou.security.dto.FadeBackDto;
import com.alibou.security.dto.SalonImagesDto;
import com.alibou.security.dto.SalonProfDto;
import com.alibou.security.sevices.FadeBackService;
import com.alibou.security.sevices.SalonGalleryService;
import com.alibou.security.sevices.SalonProfService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/salonProf/")
@RequiredArgsConstructor
public class SalonProfController {
    @Autowired
    private SalonProfService salonProfService;

    @Autowired
    private SalonGalleryService salonGalleryService;

    @Autowired
    private FadeBackService fadeBackServiceImp;


    @PostMapping("/addSalon")
    public HttpEntity<?> addSalon(@Validated @ModelAttribute SalonProfDto dto) throws IOException {
        ApiResponse apiResponse=salonProfService.createSalonProf(dto);
        return ResponseEntity.ok(apiResponse);
    }

    @PutMapping("/updateSalon/{id}")
    public HttpEntity<?> updateSalon(@Validated @PathVariable Long id,@Validated @ModelAttribute SalonProfDto salonProfDto) throws IOException {
        ApiResponse apiResponse=salonProfService.updateSalonProf(id,salonProfDto);
        return ResponseEntity.ok(apiResponse);
    }

    @DeleteMapping("/deleteSalon/{id}")
    public HttpEntity<?> deletedSalon(@PathVariable Long id){
        ApiResponse apiResponse=salonProfService.deleteSalonProf(id);
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/getSalon/{uId}/{id}")
    public  HttpEntity<?> getUserSalon(@PathVariable Long uId,@PathVariable Long id){
        ApiResponse apiResponse=salonProfService.getSalonProf(uId,id);
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/getAll")
    public  HttpEntity<?> getAllSalon(){
        ApiResponse apiResponse=salonProfService.getAllSalonProf();
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/getUserAll/{id}")
    public  HttpEntity<?> getAllUserSalon(@PathVariable Long id){
        ApiResponse apiResponse=salonProfService.getAllUserSalonProf(id);
        return ResponseEntity.ok(apiResponse);
    }

    //Gallery
    @PostMapping("/uploadGallery/")
    public void AddSalonGallery(@Validated @ModelAttribute SalonImagesDto dto) throws IOException {
        salonGalleryService.AddImages(dto);
    }

    @GetMapping("/getGallery/{salonId}")
    public HttpEntity<?> getGallery(@PathVariable Long salonId){
        ApiResponse apiResponse=salonGalleryService.viewImages(salonId);
        return ResponseEntity.ok(apiResponse);
    }

    //FadeBack

    @PostMapping("/createFadeBack")
    public HttpEntity<?> createFadeBack(@Validated @ModelAttribute FadeBackDto dto){
        ApiResponse apiResponse=fadeBackServiceImp.createFabeBack(dto);
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/allSalonFadeBack/{salonId}")
    public HttpEntity<?> allSalonFadeBack(@PathVariable Long salonId){
        ApiResponse apiResponse=fadeBackServiceImp.getAllFadeBack(salonId);
        return ResponseEntity.ok(apiResponse);
    }
}








