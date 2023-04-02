package com.insurance_management.insurance.controller;

import com.insurance_management.insurance.exceptions.ClaimNotFoundException;
import com.insurance_management.insurance.model.Claim;
import com.insurance_management.insurance.model.InsurancePolicy;
import com.insurance_management.insurance.repo.InsurancePolicyRepository;
import com.insurance_management.insurance.service.ClaimService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/claims")
public class ClaimController {

    @Autowired
    private ClaimService claimService;

    @Autowired
    private InsurancePolicyRepository insurancePolicyRepository;

    @GetMapping
    public List<Claim> getAllClaims() {
        return claimService.getAllClaims();
    }

    @GetMapping("/{id}")
    public Claim getClaimById(@PathVariable("id") Long id) {
        return claimService.getClaimById(id);
    }

    @PostMapping
    public ResponseEntity<Claim> createClaim(@RequestBody Claim claim) {
        // Retrieve the policy object from the database using its ID
        InsurancePolicy policy = insurancePolicyRepository.findById(claim.getPolicy().getId())
                .orElseThrow(() -> new ClaimNotFoundException("Policy not found"));

        // Set the policy field of the claim object to the retrieved policy object
        claim.setPolicy(policy);

        Claim createdClaim = claimService.createClaim(claim);
        return new ResponseEntity<>(createdClaim, HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    public Claim updateClaim(@PathVariable("id") Long id, @Valid @RequestBody Claim claim) {
        return claimService.updateClaim(id, claim);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClaim(@PathVariable("id") Long id) {
        claimService.deleteClaim(id);
        return ResponseEntity.noContent().build();
    }

}

