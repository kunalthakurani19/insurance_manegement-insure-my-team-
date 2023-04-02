package com.insurance_management.insurance.service;

import com.insurance_management.insurance.exceptions.ClaimNotFoundException;
import com.insurance_management.insurance.model.Claim;
import com.insurance_management.insurance.model.InsurancePolicy;
import com.insurance_management.insurance.repo.ClaimRepository;
import com.insurance_management.insurance.repo.InsurancePolicyRepository;
import com.sun.jdi.request.InvalidRequestStateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClaimService {

    @Autowired
    private ClaimRepository claimRepository;

    @Autowired
    private InsurancePolicyRepository policyRepository;

    public List<Claim> getAllClaims() {
        return claimRepository.findAll();
    }

    public Claim getClaimById(Long id) {
        Optional<Claim> optionalClaim = claimRepository.findById(id);
        if (optionalClaim.isPresent()) {
            return optionalClaim.get();
        } else {
            throw new ClaimNotFoundException("Claim not found with id: " + id);
        }
    }

    public Claim createClaim(Claim claim) {
        Long policyId = claim.getPolicy().getId();
        InsurancePolicy policy = policyRepository.findById(policyId)
                .orElseThrow(() -> new ClaimNotFoundException("Policy with ID " + policyId + " not found"));
        claim.setPolicy(policy);
        return claimRepository.save(claim);
    }


    public Claim updateClaim(Long id, Claim claim) {
        Optional<Claim> optionalClaim = claimRepository.findById(id);
        if (optionalClaim.isPresent()) {
            Claim existingClaim = optionalClaim.get();

            // Check if the policy property is not null
            if (claim.getPolicy() != null) {
                existingClaim.setPolicy(claim.getPolicy());
            }

            existingClaim.setClaimNumber(claim.getClaimNumber());
            existingClaim.setDescription(claim.getDescription());
            existingClaim.setClaimStatus(claim.getClaimStatus());
            // set other properties as needed

            return claimRepository.save(existingClaim);
        } else {
            throw new ClaimNotFoundException("Claim not found with id: " + id);
        }
    }

    public void deleteClaim(Long id) {
        Optional<Claim> optionalClaim = claimRepository.findById(id);
        if (optionalClaim.isPresent()) {
            claimRepository.delete(optionalClaim.get());
        } else {
            throw new ClaimNotFoundException("Claim not found with id: " + id);
        }
    }
}