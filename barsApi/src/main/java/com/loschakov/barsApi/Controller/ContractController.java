package com.loschakov.barsApi.Controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.loschakov.barsApi.Model.Contract;
import com.loschakov.barsApi.Model.Views;
import com.loschakov.barsApi.Repository.ContractRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/contract")
public class ContractController {

    private final ContractRepository contractRepository;

    @Autowired
    public ContractController(ContractRepository contractRepository) {
        this.contractRepository = contractRepository;
    }


    @GetMapping
    @JsonView({Views.NameCrtUpd.class})
    public List<Contract> getContract(){
        return contractRepository.findAll(Sort.by(Sort.Direction.DESC,"updateDate"));
    }

    @PostMapping
    public Contract addContract(@RequestBody Contract contract) {
        contract.setCreateDate(LocalDate.now());
        contract.setUpdateDate(LocalDate.now());
        return contractRepository.save(contract);
    }

    @PutMapping("{id}")
    public Contract updateContract(
            @PathVariable("id") Contract contractDb,
            @RequestBody Contract contract){
        BeanUtils.copyProperties(contract, contractDb, "id");
        contractDb.setUpdateDate(LocalDate.now());
        return contractRepository.save(contractDb);
    }

    @DeleteMapping("{id}")
    public void deleteContract(@PathVariable("id") Contract contract) {
        contractRepository.delete(contract);
    }
}
