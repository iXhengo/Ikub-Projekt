package com.Projekti.Ikub.service;

import com.Projekti.Ikub.dto.bill.BillDTO;
import com.Projekti.Ikub.dto.car.CarDTO;
import com.Projekti.Ikub.entity.Bill;

import java.util.List;

public interface BillService {

    BillDTO addBill(Integer userId);
    BillDTO addBillForCar(Integer userId,CarDTO carDTO);
    BillDTO findBill(Integer id);
    List<BillDTO> getUserBills(Integer userId);
    Void  payBill(Integer id);
    Void deleteBill(Integer id);


}
