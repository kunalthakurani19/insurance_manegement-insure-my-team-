package com.insurance_management.insurance.service;

import com.insurance_management.insurance.exceptions.ClientNotFoundException;
import com.insurance_management.insurance.exceptions.InsurancePolicyNotFoundException;
import com.insurance_management.insurance.model.Client;
import com.insurance_management.insurance.model.InsurancePolicy;
import com.insurance_management.insurance.repo.ClientRepository;
import com.insurance_management.insurance.repo.InsurancePolicyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InsurancePolicyService {

    @Autowired
    private InsurancePolicyRepository insurancePolicyRepository;

    @Autowired
    private ClientService clientService;

    @Autowired
    private ClientRepository clientRepository;


    public List<InsurancePolicy> getAllInsurancePolicies() {
        return insurancePolicyRepository.findAll();
    }

    public InsurancePolicy getInsurancePolicyById(Long id) {
        Optional<InsurancePolicy> insurancePolicy = insurancePolicyRepository.findById(id);
        if(insurancePolicy.isPresent()) {
            return insurancePolicy.get();
        } else {
            throw new InsurancePolicyNotFoundException("Insurance policy with ID " + id + " not found.");
        }
    }

    public InsurancePolicy createInsurancePolicy(Long clientId, InsurancePolicy insurancePolicy) {
        Optional<Client> optionalClient = clientRepository.findById(clientId);
        if (optionalClient.isPresent()) {
            Client client = optionalClient.get();
            insurancePolicy.setClient(client);
            return insurancePolicyRepository.save(insurancePolicy);
        } else {
            throw new ClientNotFoundException("Client with ID " + clientId + " not found.");
        }
    }



    public InsurancePolicy updateInsurancePolicy(Long id, InsurancePolicy updatedPolicy) {
        Optional<InsurancePolicy> existingPolicy = insurancePolicyRepository.findById(id);
        if(existingPolicy.isPresent()) {
            InsurancePolicy policy = existingPolicy.get();
            policy.setPolicyNumber(updatedPolicy.getPolicyNumber());
            policy.setStartDate(updatedPolicy.getStartDate());
            policy.setEndDate(updatedPolicy.getEndDate());
            policy.setPremium(updatedPolicy.getPremium());
            policy.setClient(updatedPolicy.getClient());
            policy.setCoverageAmount(updatedPolicy.getCoverageAmount());
            policy.setType(updatedPolicy.getType());

            return insurancePolicyRepository.save(policy);
        } else {
            throw new InsurancePolicyNotFoundException("Insurance Policy with ID " + id + " not found.");
        }
    }

    public void deleteInsurancePolicy(Long id) {
        Optional<InsurancePolicy> insurancePolicy = insurancePolicyRepository.findById(id);
        if(insurancePolicy.isPresent()) {
            insurancePolicyRepository.deleteById(id);
        } else {
            throw new InsurancePolicyNotFoundException("Insurance policy with ID " + id + " not found.");
        }
    }
}
