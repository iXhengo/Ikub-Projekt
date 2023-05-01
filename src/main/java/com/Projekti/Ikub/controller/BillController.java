package com.Projekti.Ikub.controller;


import com.Projekti.Ikub.dto.bill.BillDTO;
import com.Projekti.Ikub.dto.car.CarDTO;
import com.Projekti.Ikub.entity.Bill;
import com.Projekti.Ikub.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/bills")
public class BillController {

    @Autowired
    private BillService billService;


    @PostMapping("/admin/{userId}")
    public ResponseEntity<BillDTO> addBill(@PathVariable Integer userId) {
        return ResponseEntity.ok(billService.addBill(userId));
    }

    @PostMapping("/admin/car/{userId}")
    public ResponseEntity<BillDTO> addBillForCar(@PathVariable Integer userId,@RequestBody CarDTO carDTO) {
        return ResponseEntity.ok(billService.addBillForCar(userId,carDTO));
    }


    @GetMapping("/admin/bill/{Id}")
    public ResponseEntity<BillDTO> findBill(@PathVariable Integer Id) {
        return ResponseEntity.ok(billService.findBill(Id));
    }

    @GetMapping("/admin/bill/user/{userId}")
    public ResponseEntity<List<BillDTO>> getUserBills(@PathVariable Integer userId) {
        return ResponseEntity.ok(billService.getUserBills(userId));
    }

    @PutMapping("/admin/bill/{id}")
    public ResponseEntity<Void> payBill(@PathVariable Integer id) {
        return ResponseEntity.ok(billService.payBill(id));
    }

    @DeleteMapping("/admin/bill/{id}")
    public ResponseEntity<Void> deleteBill(@PathVariable Integer id) {
        return ResponseEntity.ok(billService.deleteBill(id));
    }
}