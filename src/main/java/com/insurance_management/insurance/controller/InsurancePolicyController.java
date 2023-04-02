package com.insurance_management.insurance.controller;

import com.insurance_management.insurance.exceptions.InsurancePolicyNotFoundException;
import com.insurance_management.insurance.model.Client;
import com.insurance_management.insurance.model.InsurancePolicy;
import com.insurance_management.insurance.service.ClientService;
import com.insurance_management.insurance.service.InsurancePolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/policies")
public class InsurancePolicyController {

    @Autowired
    private InsurancePolicyService insurancePolicyService;

    @Autowired
    private ClientService clientService;



    @GetMapping
    public List<InsurancePolicy> getAllPolicies() {
        return insurancePolicyService.getAllInsurancePolicies();
    }

    @GetMapping("/{id}")
    public InsurancePolicy getPolicyById(@PathVariable Long id) {
        return insurancePolicyService.getInsurancePolicyById(id);
    }


    @PostMapping("/{clientId}")
    public InsurancePolicy addPolicy(@PathVariable Long clientId, @Valid @RequestBody InsurancePolicy policy) {
        Client client = clientService.getClientById(clientId);
        policy.setClient(client);
        return insurancePolicyService.createInsurancePolicy(clientId,policy);
    }

    @PutMapping("/{id}")
    public ResponseEntity<InsurancePolicy> updateInsurancePolicy(@PathVariable Long id, @RequestBody InsurancePolicy updatedPolicy) {
        InsurancePolicy policy = insurancePolicyService.updateInsurancePolicy(id, updatedPolicy);
        return ResponseEntity.ok(policy);
    }

    @DeleteMapping("/{id}")
    public void deletePolicy(@PathVariable Long id) {
        insurancePolicyService.deleteInsurancePolicy(id);
    }

    @ExceptionHandler
    public String handlePolicyNotFound(InsurancePolicyNotFoundException exception) {
        return exception.getMessage();
    }
}
