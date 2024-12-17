package com.goodinfluenceshop.controller;


import com.goodinfluenceshop.dto.StoreDto;

import com.goodinfluenceshop.enums.Category;
import com.goodinfluenceshop.enums.MembershipLevel;
import com.goodinfluenceshop.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

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

    @PostMapping("/getEmail")
    public ResponseEntity<String> getEmailByNameAndPhone(@RequestBody Map<String, String> request) {
        String ceoName = request.get("ceoName");
        String phoneNumber = request.get("phoneNumber");

        try {
            String email = storeService.findEmail(ceoName, phoneNumber);
            return ResponseEntity.ok(email);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Store not found");
        }
    }

    @PostMapping("/getPassword")
    public ResponseEntity<String> getPassword (@RequestBody Map<String, String> request) {
        String ceoName = request.get("ceoName");
        String phoneNumber = request.get("phoneNumber");
        String storeEmail = request.get("storeEmail");

        try {
            String password = storeService.findpassword(ceoName, phoneNumber, storeEmail);
            return ResponseEntity.ok(password);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Store not found");
        }
    }

    @GetMapping("/by-enroll-date")
    public List<StoreDto> getStoresByEnrollDate(@RequestParam String startDate, @RequestParam String endDate) {
        LocalDate start = LocalDate.parse(startDate); // 문자열 -> LocalDate 변환
        LocalDate end = LocalDate.parse(endDate);
        return storeService.getStoresByEnrollDate(start, end);
    }

    @GetMapping("/filter")
    public List<StoreDto> getStoresWithSingleFilter(@RequestParam String startDate,
                                                    @RequestParam String endDate,
                                                    @RequestParam(required = false) MembershipLevel level,
                                                    @RequestParam(required = false) Boolean depositCheck,
                                                    @RequestParam(required = false) Boolean stickerSend,
                                                    @RequestParam(required = false) Boolean kitSend,
                                                    @RequestParam(required = false) Category businessTypeBig) {
        LocalDate start = LocalDate.parse(startDate);
        LocalDate end = LocalDate.parse(endDate);

        return storeService.getStoresWithSingleFilter(start, end, level, depositCheck, stickerSend, kitSend, businessTypeBig);
    }

}
