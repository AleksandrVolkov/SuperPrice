package CRUD3.CRUD3.services.impl;

import CRUD3.CRUD3.model.Role;
import CRUD3.CRUD3.repository.repos.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public Role findByName(String name) {
        return roleRepository.findByName(name);
    }
}
