package com.goodinfluenceshop.controller;


import com.goodinfluenceshop.dto.StoreDto;

import com.goodinfluenceshop.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/admin/stores")
public class StoreRestController {
    private final StoreService storeService;

    /*
    * private final FileUpload fileUpload;
    public DefaultRestController(FileUpload fileUpload) {
        this.fileUpload = fileUpload;
    }

    @Operation(summary = "파일업로드",
            description = "파일을 서버에 업로드(일반) \n"
                    + "@param MultipartFile multipartFile \n"
                    + "@return HttpStatus.CREATED(201) ResponseEntity\\<String\\> \n"
                    + "@exception \n"
    )
    @PostMapping("/uploadFile")
    public ResponseEntity<DefaultDto.FileResDto> uploadFile(@Valid @RequestParam("file") MultipartFile file, HttpServletRequest request) {
        DefaultDto.FileResDto urlResDto = null;
        try {
            //urlResDto = DefaultDto.FileResDto.builder().url(fileUpload.s3(file)).build();
            urlResDto = DefaultDto.FileResDto.builder().url(fileUpload.local(file, request)).build();
        } catch (IOException e) {
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(urlResDto);
    }

    @CrossOrigin("*")
    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.status(HttpStatus.OK).body("112233");
    }
    * */

   @Autowired
    public StoreRestController(StoreService storeService) {
        this.storeService = storeService;
    }

    @PostMapping("/create")
    public ResponseEntity<StoreDto> createStore(@RequestBody StoreDto storeDto) {
        StoreDto createdStore = storeService.createStore(storeDto);
        return new ResponseEntity<>(createdStore, HttpStatus.CREATED);
    }

    @GetMapping("/get/all")
    public ResponseEntity<List<StoreDto>> getAllStores() {
        List<StoreDto> stores = storeService.getAllStore();
        return new ResponseEntity<>(stores, HttpStatus.OK);
    }

    @GetMapping("/get/{no}")
    public ResponseEntity<StoreDto> getStoreByno(@PathVariable int no) {
        return storeService.getStoreById(no)
                .map(storeDto -> new ResponseEntity<>(storeDto, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/update/{no}")
    public ResponseEntity<StoreDto> updateStore(@PathVariable int no, @RequestBody StoreDto storeDto) {
        StoreDto updatedStore = storeService.updateStore(no, storeDto);
        return new ResponseEntity<>(updatedStore, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{no}")
    public ResponseEntity<Void> deleteStore(@PathVariable int no) {
        storeService.deleteStore(no);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
