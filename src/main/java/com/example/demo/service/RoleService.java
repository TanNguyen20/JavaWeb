package com.example.demo.service;

import com.example.demo.model.Role;
import com.example.demo.repository.RoleRepository;
import com.example.demo.utils.Log;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Builder
@Service
public class RoleService {
    @Autowired
    RoleRepository roleRepository;

    public List<Role> getRoles() {
        return roleRepository.findAll();
    }

    public Role getRoleById(Long id) {
        return roleRepository.findById(id).orElse(null);
    }

    public Role getRoleByName(String name) {
        return roleRepository.findByName(name);
    }

    public Role saveRole(Role role) {
        Log.info("Save role to database with name: " + role.getName());
        return roleRepository.save(role);
    }

    public List<Role> saveRoles(List<Role> roles) {
        return roleRepository.saveAll(roles);
    }

    public String deleteRole(Long id) {
        roleRepository.deleteById(id);
        return "role removed !! " + id;
    }

    public Role updateRole(Role role) {
        Role existingRole = roleRepository.findById(role.getId()).orElse(null);
        assert existingRole != null;
        existingRole.setName(role.getName());
        return roleRepository.save(existingRole);
    }

}
